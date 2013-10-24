/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package com.github.takahish0306.framework.dao

import java.sql.{DriverManager, Connection, Statement, SQLException}
import com.github.takahish0306.framework.log.Logger

trait Driver extends Logger {

  /**
   * To use connection
   *
   * @param driver String
   * @param uri String
   * @param user String
   * @param passowrd String
   * @param operation Connection => T
   * @return Option[T]
   */
  def withDaoConnection[T](driver: String, uri: String, user: String, password: String)(operation: Connection => T): Option[T] = {
    Class.forName(driver).newInstance

    var connection: Connection = null
    val exceptionMessage = "! Driver.withDaoConnection failed."

    try {
      connection = DriverManager.getConnection(uri, user, password)
      Some(operation(connection))
    } catch {
      case e: SQLException => {
        logger.error(exceptionMessage + " A database access error occurs.")
        None
      }
      case e: Exception => {
        logger.error(exceptionMessage + " Unknown. " + e.getMessage)
        None
      }
    } finally {
      connection.close
    }
  }

  /**
   * To use connected statement
   *
   * @param driver String
   * @param uri String
   * @param user String
   * @param password String
   * @param operation: Statement => T
   * @return Option[T]
   */
  def withDaoStatement[T](driver: String, uri: String, user: String, password: String)(operation: Statement => T): Option[T] = {
    withDaoConnection[T](driver: String, uri, user, password) {
      connection => {
        var statement: Statement = null
        val exceptionMessage = "! Driver.withDaoStatement failed."

        try {
          statement = connection.createStatement
          operation(statement)
        } catch {
          case e: SQLException => {
            logger.error(exceptionMessage + " A database access error occurs or this method is called on a closed connection.")
            throw e
          }
          case e: Exception => {
            logger.error(exceptionMessage + " Unknown.")
            throw e
          }
        } finally {
          statement.close
        }
      }
    }
  }
}