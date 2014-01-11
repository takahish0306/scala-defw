package com.github.takahish0306.framework.websocket

import scala.collection.mutable.Set

object WebSocketClient {

  var clients = Set.empty[WebSocket]

  def addClient(client: WebSocket): Unit = {
    clients += client
  }

  def deleteClient(client: WebSocket): Unit = { 
    clients -= client
  }

  def getAllClient: Set[WebSocket] = { 
    clients
  }

}
