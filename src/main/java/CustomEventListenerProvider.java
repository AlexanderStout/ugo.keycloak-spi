
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class CustomEventListenerProvider implements EventListenerProvider {


    private final HttpClient httpClient = HttpClient.newHttpClient();

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger logger = Logger.getLogger(String.valueOf(CustomEventListenerProvider.class));


    @Override
    public void onEvent(Event event) {
//        logger.info("event received ${event.type}")

        logger.severe("GOT EVENT: " + event.getType().name());

//        if (event.getType() == EventType.REGISTER) {
//            logger.log(Level.INFO, "new user register event received eventId: ${event.id}" )

            String eventData = null;
            try {
                eventData = objectMapper.writeValueAsString(event.getDetails());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            HttpRequest httpRequest = null;
            try {
                httpRequest = HttpRequest.newBuilder()
                        .uri(new URI("https://sashauntitled.eu.ngrok.io"))
                        .POST(HttpRequest.BodyPublishers.ofString(eventData))
                        .build();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

            try {
                httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

//            logger.log(Level.INFO, "user register event sent to user service, got response: $response" )

//        }
    }

    @Override
    public void onEvent(AdminEvent event, boolean includeRepresentation) {

    }

    @Override
    public void close() {

    }
}
