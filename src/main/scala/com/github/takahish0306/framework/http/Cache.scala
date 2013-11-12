/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package com.github.takahish0306.framework.http

import org.apache.http.impl.client.cache.{CacheConfig => ApacheCacheConfig}
import org.apache.http.client.config.{RequestConfig => ApacheRequestConfig}

/**
 * Cache config class
 *
 * @param cache ApacheCacheConfig
 * @param timeout ApacheRequestConfig
 */
case class CacheConfig(cache: ApacheCacheConfig, timeout: ApacheRequestConfig)

trait Cache {

  /**
   * Apply
   *
   * @param maxCacheEntries Int
   * @param maxObjectSize Long
   * @param connectTimeout Int
   * @param socketTimeout Int
   * @return CacheConfig
   */
  def apply(maxCacheEntries: Int, maxObjectSize: Long, connectTimeout: Int, socketTimeout: Int): CacheConfig = {
    CacheConfig(
      ApacheCacheConfig.custom()
        .setMaxCacheEntries(maxCacheEntries)
        .setMaxObjectSize(maxObjectSize)
        .build(),
      ApacheRequestConfig.custom()
        .setConnectTimeout(connectTimeout)
        .setSocketTimeout(socketTimeout)
        .build()
    )
  }

  /**
   * Unapply
   *
   * @param cacheConfig CacheConfig
   * @return Option[(Int, Long, Int, Int)]
   */
  def unapply(cacheConfig: CacheConfig): Option[(Int, Long, Int, Int)] = {
    Some(
      (cacheConfig.cache.getMaxCacheEntries,
       cacheConfig.cache.getMaxObjectSize,
       cacheConfig.timeout.getConnectTimeout,
       cacheConfig.timeout.getSocketTimeout)
    )
  }

}
