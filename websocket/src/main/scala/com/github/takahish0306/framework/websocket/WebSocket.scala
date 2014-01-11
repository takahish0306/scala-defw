package com.github.takahish0306.framework.websocket

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.eclipse.jetty.websocket.WebSocket.{Connection, OnTextMessage}

class WebSocket extends OnTextMessage {

  var connection: Connection = _
  
  override def onMessage(data: String): Unit = {
    WebSocketClient.getAllClient.foreach{ c => c.connection.sendMessage(data) }
  }
  
  override def onOpen(connection:Connection): Unit = {
    this.connection = connection
    WebSocketClient.addClient(this)
  }
  
  override def onClose(closeCode:Int, message:String): Unit = {
    WebSocketClient.deleteClient(this)
  }

}
