package com.github.takahish0306.framework.websocket

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.eclipse.jetty.websocket.{WebSocketServlet => JettyWebSocketServlet}

class WebSocketServlet extends JettyWebSocketServlet {

  override def doWebSocketConnect(
    request: HttpServletRequest,
    protocol: String
  ): WebSocket = {
    new WebSocket
  }

}

