package com.github.takahish0306.framework.http

import org.scalatest._
import java.io._
import org.apache.http.client.methods.HttpGet

class ClientTest extends FlatSpec with Client {
  "Client.withHttpGetResponse" should "request for http://hc.apache.org/ by HTTP/GET" in {
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

  "Client.withHttpPostResponse" should "request for http://hc.apache.org/ by HTTP/POST" in {
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
