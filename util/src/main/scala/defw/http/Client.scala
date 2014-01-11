/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package defw.http

import org.apache.http.impl.client.{HttpClients, CloseableHttpClient}
import defw.log.Logger

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

}
