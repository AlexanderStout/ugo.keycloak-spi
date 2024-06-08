
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ServerInfoAwareProviderFactory;

import java.util.Map;


@SuppressWarnings("unused")
public class CustomEventListenerProviderFactory implements EventListenerProviderFactory, ServerInfoAwareProviderFactory {
    @Override
    public EventListenerProvider create(KeycloakSession keycloakSession) {
        return new CustomEventListenerProvider();
    }

    @Override
    public void init(org.keycloak.Config.Scope scope) {

    }


    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return "customEventListener";
    }

    @Override
    public Map<String, String> getOperationalInfo() {
        return Map.of("event listener", "Custom event listener");
    }
}
