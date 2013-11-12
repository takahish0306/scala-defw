package com.github.takahish0306.framework.http

import org.scalatest._
import java.io._
import org.apache.http.client.methods.HttpGet

class ResponseTest extends FlatSpec with Response {
  "Response.withHttpGetResponse" should "request to http://hc.apache.org/ in HTTP/GET" in {
    withHttpGetResponse[Unit]("http://hc.apache.org/") {
      response => {
        val entity = response.getEntity()
        if (entity != null) {
          val instream = entity.getContent()
          try {
            val bufferedReader = new BufferedReader(new InputStreamReader(instream))
	    //Iterator.continually(bufferedReader.readLine).takeWhile(_ != null).foreach(println)
          } catch {
            case e: IOException => {
              throw e
	    }
          } finally {
            instream.close
          }
	}
      }
    }
  }

  "Response.withHttpPostResponse" should "request to http://hc.apache.org/ in HTTP/POST" in {
    withHttpPostResponse[Unit]("http://hc.apache.org/", Map[String, String]()) {
      response => {
        val entity = response.getEntity()
        if (entity != null) {
          val instream = entity.getContent()
          try {
            val bufferedReader = new BufferedReader(new InputStreamReader(instream))
	    //Iterator.continually(bufferedReader.readLine).takeWhile(_ != null).foreach(println)
          } catch {
            case e: IOException => {
              throw e
	    }
          } finally {
            instream.close
          }
	}
      }
    }
  }

}
