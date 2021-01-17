package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import config.Constants;
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
    public void testSearchWithLimitParameter() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?limit=10");

        Result result = route(app, request);

        assertEquals(OK, result.status());
        assertEquals(Http.MimeTypes.JSON, result.contentType().get());
        assertNotEquals("", contentAsString(result));

        JsonNode json = Json.parse(contentAsString(result));

        int itemCount = json.size();

        Set<String> actualKeys = getJsonKeys(json);
        Set<String> expectedKeys = new HashSet<>(Arrays.asList("name", "heightInMeters", "hillCategory", "gridReference"));

        assertFalse(json.isEmpty());
        assertTrue(json.isArray());
        assertEquals(10, itemCount);
        assertEquals(expectedKeys, actualKeys);
    }

    @Test
    public void testSearchWithMinHeightParameter() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?minHeight=1309");

        Result result = route(app, request);

        assertEquals(OK, result.status());
        assertEquals(Http.MimeTypes.JSON, result.contentType().get());
        assertNotEquals("", contentAsString(result));

        JsonNode json = Json.parse(contentAsString(result));

        int itemCount = json.size();

        Set<String> actualKeys = getJsonKeys(json);
        Set<String> expectedKeys = new HashSet<>(Arrays.asList("name", "heightInMeters", "hillCategory", "gridReference"));

        assertFalse(json.isEmpty());
        assertTrue(json.isArray());
        assertEquals(2, itemCount);
        assertEquals(expectedKeys, actualKeys);
    }

    @Test
    public void testSearchWithMaxHeightParameter() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?maxHeight=1309");

        Result result = route(app, request);

        assertEquals(OK, result.status());
        assertEquals(Http.MimeTypes.JSON, result.contentType().get());
        assertNotEquals("", contentAsString(result));

        JsonNode json = Json.parse(contentAsString(result));

        int itemCount = json.size();

        Set<String> actualKeys = getJsonKeys(json);
        Set<String> expectedKeys = new HashSet<>(Arrays.asList("name", "heightInMeters", "hillCategory", "gridReference"));

        assertFalse(json.isEmpty());
        assertTrue(json.isArray());
        assertEquals(508, itemCount);
        assertEquals(expectedKeys, actualKeys);
    }

    @Test
    public void testSearchWithMinHeightAndMaxHeightParameters() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?minHeight=1291&maxHeight=1309");

        Result result = route(app, request);

        assertEquals(OK, result.status());
        assertEquals(Http.MimeTypes.JSON, result.contentType().get());
        assertNotEquals("", contentAsString(result));

        JsonNode json = Json.parse(contentAsString(result));

        int itemCount = json.size();

        Set<String> actualKeys = getJsonKeys(json);
        Set<String> expectedKeys = new HashSet<>(Arrays.asList("name", "heightInMeters", "hillCategory", "gridReference"));

        assertFalse(json.isEmpty());
        assertTrue(json.isArray());
        assertEquals(3, itemCount);
        assertEquals(expectedKeys, actualKeys);
    }

    @Test
    public void testSearchWithSortByNameAscendingParameter() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?sort=name+asc&limit=1");

        Result result = route(app, request);

        assertEquals(OK, result.status());
        assertEquals(Http.MimeTypes.JSON, result.contentType().get());
        assertNotEquals("", contentAsString(result));

        JsonNode json = Json.parse(contentAsString(result));

        int itemCount = json.size();

        Set<String> actualKeys = getJsonKeys(json);
        Set<String> expectedKeys = new HashSet<>(Arrays.asList("name", "heightInMeters", "hillCategory", "gridReference"));

        assertFalse(json.isEmpty());
        assertTrue(json.isArray());
        assertEquals(1, itemCount);
        assertEquals(expectedKeys, actualKeys);

        assertEquals("A' Bhuidheanach Bheag", json.get(0).get("name").asText());
        assertEquals("MUN", json.get(0).get("hillCategory").asText());
        assertEquals("NN660775", json.get(0).get("gridReference").asText());
        assertEquals("936", json.get(0).get("heightInMeters").asText());
    }

    @Test
    public void testSearchWithSortByNameDescendingParameter() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?sort=name+desc&limit=1");

        Result result = route(app, request);

        assertEquals(OK, result.status());
        assertEquals(Http.MimeTypes.JSON, result.contentType().get());
        assertNotEquals("", contentAsString(result));

        JsonNode json = Json.parse(contentAsString(result));

        int itemCount = json.size();

        Set<String> actualKeys = getJsonKeys(json);
        Set<String> expectedKeys = new HashSet<>(Arrays.asList("name", "heightInMeters", "hillCategory", "gridReference"));

        assertFalse(json.isEmpty());
        assertTrue(json.isArray());
        assertEquals(1, itemCount);
        assertEquals(expectedKeys, actualKeys);

        assertEquals("Tom a' Choinich - Tom a' Choinich Beag", json.get(0).get("name").asText());
        assertEquals("TOP", json.get(0).get("hillCategory").asText());
        assertEquals("NH157272", json.get(0).get("gridReference").asText());
        assertEquals("1032", json.get(0).get("heightInMeters").asText());
    }

    @Test
    public void testSearchWithSortByHeightAscendingParameter() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?sort=heightInMeters+asc&limit=1");

        Result result = route(app, request);

        assertEquals(OK, result.status());
        assertEquals(Http.MimeTypes.JSON, result.contentType().get());
        assertNotEquals("", contentAsString(result));

        JsonNode json = Json.parse(contentAsString(result));

        int itemCount = json.size();

        Set<String> actualKeys = getJsonKeys(json);
        Set<String> expectedKeys = new HashSet<>(Arrays.asList("name", "heightInMeters", "hillCategory", "gridReference"));

        assertFalse(json.isEmpty());
        assertTrue(json.isArray());
        assertEquals(1, itemCount);
        assertEquals(expectedKeys, actualKeys);

        assertEquals("Mullach Coire nan Cisteachan [Carn na Caim South Top]", json.get(0).get("name").asText());
        assertEquals("TOP", json.get(0).get("hillCategory").asText());
        assertEquals("NN663806", json.get(0).get("gridReference").asText());
        assertEquals("914.6", json.get(0).get("heightInMeters").asText());
    }

    @Test
    public void testSearchWithSortByHeightDescendingParameter() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?sort=heightInMeters+desc&limit=1");

        Result result = route(app, request);

        assertEquals(OK, result.status());
        assertEquals(Http.MimeTypes.JSON, result.contentType().get());
        assertNotEquals("", contentAsString(result));

        JsonNode json = Json.parse(contentAsString(result));

        int itemCount = json.size();

        Set<String> actualKeys = getJsonKeys(json);
        Set<String> expectedKeys = new HashSet<>(Arrays.asList("name", "heightInMeters", "hillCategory", "gridReference"));

        assertFalse(json.isEmpty());
        assertTrue(json.isArray());
        assertEquals(1, itemCount);
        assertEquals(expectedKeys, actualKeys);

        assertEquals("Ben Nevis", json.get(0).get("name").asText());
        assertEquals("MUN", json.get(0).get("hillCategory").asText());
        assertEquals("NN166712", json.get(0).get("gridReference").asText());
        assertEquals("1344.53", json.get(0).get("heightInMeters").asText());
    }

    @Test
    public void testSearchWithMunCategoryParameter() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?category=munro");

        Result result = route(app, request);

        assertEquals(OK, result.status());
        assertEquals(Http.MimeTypes.JSON, result.contentType().get());
        assertNotEquals("", contentAsString(result));

        JsonNode json = Json.parse(contentAsString(result));

        int itemCount = json.size();

        Set<String> actualKeys = getJsonKeys(json);
        Set<String> expectedKeys = new HashSet<>(Arrays.asList("name", "heightInMeters", "hillCategory", "gridReference"));

        assertFalse(json.isEmpty());
        assertTrue(json.isArray());
        assertEquals(282, itemCount);
        assertEquals(expectedKeys, actualKeys);

        assertEquals("A' Bhuidheanach Bheag", json.get(0).get("name").asText());
        assertEquals("MUN", json.get(0).get("hillCategory").asText());
        assertEquals("NN660775", json.get(0).get("gridReference").asText());
        assertEquals("936", json.get(0).get("heightInMeters").asText());
    }

    @Test
    public void testSearchWithMunTopCategoryParameter() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?category=munro+top");

        Result result = route(app, request);

        assertEquals(OK, result.status());
        assertEquals(Http.MimeTypes.JSON, result.contentType().get());
        assertNotEquals("", contentAsString(result));

        JsonNode json = Json.parse(contentAsString(result));

        int itemCount = json.size();

        Set<String> actualKeys = getJsonKeys(json);
        Set<String> expectedKeys = new HashSet<>(Arrays.asList("name", "heightInMeters", "hillCategory", "gridReference"));

        assertFalse(json.isEmpty());
        assertTrue(json.isArray());
        assertEquals(227, itemCount);
        assertEquals(expectedKeys, actualKeys);

        assertEquals("A' Bhuidheanach Bheag - Glas Mheall Mor", json.get(0).get("name").asText());
        assertEquals("TOP", json.get(0).get("hillCategory").asText());
        assertEquals("NN680769", json.get(0).get("gridReference").asText());
        assertEquals("927.9", json.get(0).get("heightInMeters").asText());
    }

    @Test
    public void testSearchWithMunOrMunTopCategoryParameter() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?category=munro,munro+top");

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
    public void testSearchWithMunOrMunTopCategoryParameterAlt() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?category=munro+top,munro");

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
    public void testSearchWithAllQueryParameters() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?category=munro+top&minHeight=1200&maxHeight=1265&limit=2&sort=name+desc");

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
        assertEquals(2, itemCount);
        assertEquals(expectedKeys, actualKeys);

        assertEquals("Cairn Toul - Stob Coire an t-Saighdeir", firstEntry.get("name").asText());
        assertEquals("TOP", firstEntry.get("hillCategory").asText());
        assertEquals("NN962963", firstEntry.get("gridReference").asText());
        assertEquals("1213", firstEntry.get("heightInMeters").asText());

        assertEquals("Cairn Gorm - Cairn Lochan", secondEntry.get("name").asText());
        assertEquals("TOP", secondEntry.get("hillCategory").asText());
        assertEquals("NH985025", secondEntry.get("gridReference").asText());
        assertEquals("1216", secondEntry.get("heightInMeters").asText());
    }

    @Test
    public void testReturn400IfCategoryQueryParameterInvalid() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?category=foo");

        Result result = route(app, request);
        assertEquals(BAD_REQUEST, result.status());
        assertEquals(Constants.ErrorMessages.INVALID_CATEGORY_QUERY_PARAMETER, contentAsString(result));
    }

    @Test
    public void testReturn400IfLimitQueryParameterInvalid() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?limit=foo");

        Result result = route(app, request);
        assertEquals(BAD_REQUEST, result.status());
        assertEquals(Constants.ErrorMessages.INVALID_LIMIT_QUERY_PARAMETER, contentAsString(result));
    }

    @Test
    public void testReturn400IfMinHeightQueryParameterInvalid() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?minHeight=foo");

        Result result = route(app, request);
        assertEquals(BAD_REQUEST, result.status());
        assertEquals(Constants.ErrorMessages.INVALID_MIN_HEIGHT_QUERY_PARAMETER, contentAsString(result));
    }

    @Test
    public void testReturn400IfMaxHeightQueryParameterInvalid() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?maxHeight=foo");

        Result result = route(app, request);
        assertEquals(BAD_REQUEST, result.status());
        assertEquals(Constants.ErrorMessages.INVALID_MAX_HEIGHT_QUERY_PARAMETER, contentAsString(result));
    }

    @Test
    public void testReturn400IfMinHeightAndMaxHeightQueryParametersInvalid() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?minHeight=foo&maxHeight=bar");

        Result result = route(app, request);
        assertEquals(BAD_REQUEST, result.status());
        assertEquals(Constants.ErrorMessages.INVALID_MIN_HEIGHT_QUERY_PARAMETER, contentAsString(result));
    }

    @Test
    public void testReturn400IfMinHeightValidAndMaxHeightInvalid() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?minHeight=5&maxHeight=bar");

        Result result = route(app, request);
        assertEquals(BAD_REQUEST, result.status());
        assertEquals(Constants.ErrorMessages.INVALID_MAX_HEIGHT_QUERY_PARAMETER, contentAsString(result));
    }

    @Test
    public void testReturn400IfSortQueryParameterInvalid() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/search?sort=foo");

        Result result = route(app, request);
        assertEquals(BAD_REQUEST, result.status());
        assertEquals(Constants.ErrorMessages.INVALID_SORT_QUERY_PARAMETER, contentAsString(result));
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
