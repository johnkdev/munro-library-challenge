package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import java.util.*;

import static org.junit.Assert.*;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class MunrosControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testSearch() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search");

        Result result = route(app, request);

        assertEquals(OK, result.status());
        assertEquals(Http.MimeTypes.JSON, result.contentType().get());
        assertNotEquals("", contentAsString(result));

        JsonNode json = Json.parse(contentAsString(result));

        int itemCount = json.size();

        Set<String> actualKeys = getJsonKeys(json);
        Set<String> expectedKeys = new HashSet<>(Arrays.asList("name", "heightInMeters", "hillCategory", "gridReference"));

        JsonNode firstEntry = json.get(0);
        JsonNode secondEntry = json.get(1);

        assertFalse(json.isEmpty());
        assertTrue(json.isArray());
        assertEquals(509, itemCount);
        assertEquals(expectedKeys, actualKeys);

        assertEquals("A' Bhuidheanach Bheag", firstEntry.get("name").asText());
        assertEquals("MUN", firstEntry.get("hillCategory").asText());
        assertEquals("NN660775", firstEntry.get("gridReference").asText());
        assertEquals("936", firstEntry.get("heightInMeters").asText());

        assertEquals("A' Bhuidheanach Bheag - Glas Mheall Mor", secondEntry.get("name").asText());
        assertEquals("TOP", secondEntry.get("hillCategory").asText());
        assertEquals("NN680769", secondEntry.get("gridReference").asText());
        assertEquals("927.9", secondEntry.get("heightInMeters").asText());
    }

    @Test
    public void testReturn400IfCategoryQueryParameterInvalid() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?category=foo");

        Result result = route(app, request);
        assertEquals(BAD_REQUEST, result.status());
    }

    @Test
    public void testReturn400IfLimitQueryParameterInvalid() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?limit=foo");

        Result result = route(app, request);
        assertEquals(BAD_REQUEST, result.status());
    }

    @Test
    public void testReturn400IfMinHeightQueryParameterInvalid() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?minHeight=foo");

        Result result = route(app, request);
        assertEquals(BAD_REQUEST, result.status());
    }

    @Test
    public void testReturn400IfMaxHeightQueryParameterInvalid() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?maxHeight=foo");

        Result result = route(app, request);
        assertEquals(BAD_REQUEST, result.status());
    }

    @Test
    public void testReturn400IfMinHeightAndMaxHeightQueryParametersInvalid() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?minHeight=foo&maxHeight=bar");

        Result result = route(app, request);
        assertEquals(BAD_REQUEST, result.status());
    }

    @Test
    public void testReturn400IfSortQueryParameterInvalid() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?sort=foo");

        Result result = route(app, request);
        assertEquals(BAD_REQUEST, result.status());
    }

    private Set<String> getJsonKeys(JsonNode json) {
        Set<String> keys = new HashSet<>();

        Iterator<JsonNode> node = json.elements();

        while (node.hasNext()) {
            JsonNode next = node.next();
            next.fieldNames().forEachRemaining(keys::add);
        }

        return keys;
    }
}
