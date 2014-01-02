package com.github.takahish0306.framework.http

import org.scalatest._

class HttpTest extends FlatSpec {

  "Http.get" should "request to http://rss.dailynews.yahoo.co.jp/fc/rss.xml in HTTP/GET" in {
    val uri = "http://rss.dailynews.yahoo.co.jp/fc/rss.xml"
    val result = Http.get(uri)
    assert(result.get._1 === 200)
  }

  "Http.post" should "request to http://rss.dailynews.yahoo.co.jp/fc/rss.xml in HTTP/POST" in {
    val uri = "http://rss.dailynews.yahoo.co.jp/fc/rss.xml"
    val parameter = Map("key" -> "value")
    val result = Http.post(uri, parameter)
    assert(result.get._1 === 200)
  }

}
