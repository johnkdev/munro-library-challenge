package controllers.actions;

import config.Constants;
import models.Sort;
import org.junit.Test;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static play.mvc.Results.ok;
import static play.test.Helpers.contentAsString;

public class ValidateSortParameterActionTest {
    @Test
    public void testNoAttributeSetIfSortNotSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<Sort> sortOrder = req.attrs().getOptional(Constants.Attrs.SORT);
                    assertFalse(sortOrder.isPresent());
                    return ok();
                });
        assertEquals(Http.Status.OK, r.status());
    }

    @Test
    public void testAttributeSetIfHeightInMetersAscValueSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?sort=heightInMeters+asc");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<Sort> sortOrder = req.attrs().getOptional(Constants.Attrs.SORT);
                    assertEquals(sortOrder.get(), Sort.HEIGHT_IN_METERS_ASC);
                    return ok().withHeader("Actual-Sort", sortOrder.get().name());
                });
        assertEquals(Http.Status.OK, r.status());
        assertEquals("HEIGHT_IN_METERS_ASC", r.headers().get("Actual-Sort"));
    }

    @Test
    public void testAttributeSetIfHeightInMetersDescValueSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?sort=heightInMeters+desc");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<Sort> sortOrder = req.attrs().getOptional(Constants.Attrs.SORT);
                    assertEquals(sortOrder.get(), Sort.HEIGHT_IN_METERS_DESC);
                    return ok().withHeader("Actual-Sort", sortOrder.get().name());
                });
        assertEquals(Http.Status.OK, r.status());
        assertEquals("HEIGHT_IN_METERS_DESC", r.headers().get("Actual-Sort"));
    }

    @Test
    public void testAttributeSetIfNameAscValueSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?sort=name+asc");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<Sort> sortOrder = req.attrs().getOptional(Constants.Attrs.SORT);
                    assertEquals(sortOrder.get(), Sort.NAME_ASC);
                    return ok().withHeader("Actual-Sort", sortOrder.get().name());
                });
        assertEquals(Http.Status.OK, r.status());
        assertEquals("NAME_ASC", r.headers().get("Actual-Sort"));
    }

    @Test
    public void testAttributeSetIfNameDescValueSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?sort=name+desc");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<Sort> sortOrder = req.attrs().getOptional(Constants.Attrs.SORT);
                    assertEquals(sortOrder.get(), Sort.NAME_DESC);
                    return ok().withHeader("Actual-Sort", sortOrder.get().name());
                });
        assertEquals(Http.Status.OK, r.status());
        assertEquals("NAME_DESC", r.headers().get("Actual-Sort"));
    }

    @Test
    public void test400ReturnedIfInvalidSortSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?sort=streetmap+desc");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<Sort> sortOrder = req.attrs().getOptional(Constants.Attrs.SORT);
                    assertFalse(sortOrder.isPresent());
                    return ok();
                });
        assertEquals(Http.Status.BAD_REQUEST, r.status());
        assertEquals(Constants.ErrorMessages.INVALID_SORT_QUERY_PARAMETER, contentAsString(r));
    }

    private Result callWithFunction(Http.Request req, Function<Http.Request, Result> f)
            throws Exception {
        ValidateSortParameterAction action = new ValidateSortParameterAction();
        action.delegate =
                new Action<Object>() {
                    @Override
                    public CompletionStage<Result> call(Http.Request req) {
                        Result r = f.apply(req);
                        return CompletableFuture.completedFuture(r);
                    }
                };
        return action.call(req).toCompletableFuture().get();
    }
}
