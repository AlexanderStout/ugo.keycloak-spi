package com.ugo

import com.fasterxml.jackson.databind.ObjectMapper
import org.keycloak.events.Event
import org.keycloak.events.EventListenerProvider
import org.keycloak.events.EventType
import org.keycloak.events.admin.AdminEvent
import org.keycloak.models.KeycloakSession
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers
import java.net.http.HttpResponse
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

class CustomEventListenerProvider(private val session: KeycloakSession) : EventListenerProvider {

  private var httpClient: HttpClient = HttpClient.newHttpClient()
  private val objectMapper: ObjectMapper = ObjectMapper()
  private val logger: Logger = LogManager.getLogManager().getLogger("CustomEventListenerProvider")

  override fun onEvent(event: Event) {
    if (event.type == EventType.REGISTER  ) {
      logger.log(Level.INFO, "new user register event received eventId: ${event.id}" )

      val eventData = objectMapper.writeValueAsString(event.details)

      val httpRequest: HttpRequest = HttpRequest.newBuilder()
        .uri(URI("https://127.0.0.1:8087"))
        .POST(BodyPublishers.ofString(eventData))
        .build()

      val response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())

      logger.log(Level.INFO, "user register event sent to user service, got response: $response" )

    }
  }

  override fun onEvent(p0: AdminEvent?, p1: Boolean) {

  }

  override fun close() {
    // Optional: Cleanup resources if needed
  }
}