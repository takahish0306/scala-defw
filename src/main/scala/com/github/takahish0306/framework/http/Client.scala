/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package com.github.takahish0306.framework.http

import org.apache.http.impl.client.{HttpClients, CloseableHttpClient}
import org.apache.http.impl.client.cache.CachingHttpClients
import com.github.takahish0306.framework.log.Logger

trait Client extends Logger {

  /**
   * To use http client
   *
   * @param operation CloseableHttpClient => T
   * @return Option[T]
   */
  def withHttpClient[T](operation: CloseableHttpClient => T): Option[T] = {
    var client: CloseableHttpClient = null
    val exceptionMessage = "! Client.withHttpClient failed."

    try {
      client = HttpClients.createDefault
      Some(operation(client))
    } catch {
      case e: Exception => {
        logger.error(exceptionMessage + " A problem occurred.")
        None
      }
    } finally {
      client.close
    }
  }

  /**
   * To use http client on cache
   *
   * @param cache Cache
   * @param oeperation CloseableHttpClient => T
   * @return Option[T]
   */
  def withCachingHttpClient[T](cacheConfig: CacheConfig)(operation: CloseableHttpClient => T): Option[T] = {
    var cachingClient: CloseableHttpClient = null
    val exceptionMessage = "! Client.withHttpClientOnCache failed."

    try {
      cachingClient = CachingHttpClients.custom()
        .setCacheConfig(cacheConfig.cache)
        .setDefaultRequestConfig(cacheConfig.timeout)
        .build()
      Some(operation(cachingClient))
    } catch {
      case e: Exception => {
        logger.error(exceptionMessage + " A problem occurred.")
        None
      }
    } finally {
      cachingClient.close
    }
  }

}
