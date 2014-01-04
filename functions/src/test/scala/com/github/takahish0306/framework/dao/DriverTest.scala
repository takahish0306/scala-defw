package com.github.takahish0306.framework.functions.dao

import org.scalatest._

class DriverTest extends FlatSpec with Driver {
  val driver   = "org.postgresql.Driver"
  val uri      = "jdbc:postgresql://192.168.101.139/book"
  val user     = "takahish0306"
  val password = "6030hsihakat"

  "Driver.withDaoConnection" should "get data from postgresql server" in {
    val query = "SELECT country_code, country_name FROM countries LIMIT 1;"
      
    val (countryCode, countryName) = withDaoConnection[Map[String, String]](driver, uri, user, password) {
      connection => {
        val statement = connection.createStatement
        try {
          val row = statement.executeQuery(query)
          var result = Map[String, String]()
          while (row.next) {
            result = Map("country_code" -> row.getString(1), "country_name" -> row.getString(2))
          }
          result
        } finally {
          statement.close
        }
      }
    } match {
      case Some(v) => (v("country_code"), v("country_name"))
      case None    => (null, null)
    }

    assert(countryCode === "us")
    assert(countryName === "United States") 
  }

  // Error log output
  "Driver.withDaoConnection" should "throw exception with due to bad query" in {
    // 'contries' is spelling mistake
    val query = "SELECT country_code, country_name FROM contries"

    withDaoConnection[Unit](driver, uri, user, password) {
      connection => {
        val statement = connection.createStatement
        try {
          statement.executeQuery(query)
        } finally {
          statement.close
        }
      }
    }
  }

  "Driver.withDaoStatement" should "get data from postgresql server" in {
    val query = "SELECT country_code, country_name FROM countries LIMIT 1;"

    val (countryCode, countryName) = withDaoStatement[Map[String, String]](driver, uri, user, password) {
      statement => {
        val row = statement.executeQuery(query)
        var result = Map[String, String]()
        while (row.next) {
          result = Map("country_code" -> row.getString(1), "country_name" -> row.getString(2))
        }
        result
      }
    } match {
      case Some(v) => (v("country_code"), v("country_name"))
      case None    => (null, null)
    }

    assert(countryCode === "us")
    assert(countryName === "United States")
  }

  // Error log output
  "Driver.withDaoStatement" should "throw exception with due to bad query" in {
    // 'contries' is spelling mistake
    val query = "SELECT country_code, country_name FROM contries"

    withDaoStatement[Unit](driver, uri, user, password) {
      statement => {
        statement.executeQuery(query)
      }
    }
  }  
}
