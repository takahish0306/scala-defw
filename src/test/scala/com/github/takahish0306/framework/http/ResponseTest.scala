package com.github.takahish0306.framework.http

import org.scalatest._
import java.io._
import org.apache.http.client.methods.HttpGet

class ResponseTest extends FlatSpec with Response {

  "Response.withHttpGetResponse" should "request to http://rss.dailynews.yahoo.co.jp/fc/rss.xml in HTTP/GET" in {
    val uri = URI("http://rss.dailynews.yahoo.co.jp/fc/rss.xml")
    val status = withHttpGetResponse[Int](uri) {
      response => response.getStatusLine().getStatusCode()
    }
    assert(status.get === 200)
  }

  "Response.withHttpPostResponse" should "request to http://rss.dailynews.yahoo.co.jp/fc/rss.xml in HTTP/POST" in {
    val uri = URI("http://rss.dailynews.yahoo.co.jp/fc/rss.xml")
    val parameter = Parameter(Map("key" -> "value"))
    val status = withHttpPostResponse[Int](uri, parameter) {
      response => response.getStatusLine().getStatusCode()
    }
    assert(status.get === 200)
  }

}
