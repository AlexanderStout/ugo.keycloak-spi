package com.ugo

import org.keycloak.Config
import org.keycloak.models.KeycloakSession
import org.keycloak.models.KeycloakSessionFactory
import org.keycloak.events.EventListenerProvider
import org.keycloak.events.EventListenerProviderFactory

class CustomEventListenerProviderFactory : EventListenerProviderFactory {
  override fun create(session: KeycloakSession): EventListenerProvider {
    return CustomEventListenerProvider(session)
  }

  override fun init(config: Config.Scope) {
  }

  override fun postInit(factory: KeycloakSessionFactory) {
  }

  override fun close() {
  }

  override fun getId(): String = "customEventListener"
}