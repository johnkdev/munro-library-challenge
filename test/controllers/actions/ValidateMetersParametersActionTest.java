package controllers.actions;

import config.Constants;
import models.MaxHeight;
import models.MinHeight;
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

public class ValidateMetersParametersActionTest {
    @Test
    public void testNoAttributeSetIfMinAndMaxHeightNotSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<MinHeight> minHeight = req.attrs().getOptional(Constants.Attrs.MIN_HEIGHT);
                    Optional<MaxHeight> maxHeight = req.attrs().getOptional(Constants.Attrs.MAX_HEIGHT);
                    assertFalse(minHeight.isPresent());
                    assertFalse(maxHeight.isPresent());
                    return ok();
                });
        assertEquals(Http.Status.OK, r.status());
    }

    @Test
    public void testMinHeightAttributeSetIfMinHeightValueSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?minHeight=5");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<MinHeight> minHeight = req.attrs().getOptional(Constants.Attrs.MIN_HEIGHT);
                    assertEquals(minHeight.get(), new MinHeight(5));
                    return ok().withHeader("Actual-Min-Height", String.valueOf(minHeight.get().getValue()));
                });
        assertEquals(Http.Status.OK, r.status());
        assertEquals("5.0", r.headers().get("Actual-Min-Height"));
    }

    @Test
    public void test400ReturnedIfNegativeMinHeightSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?minHeight=-1");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<MinHeight> minHeight = req.attrs().getOptional(Constants.Attrs.MIN_HEIGHT);
                    assertFalse(minHeight.isPresent());
                    return ok();
                });
        assertEquals(Http.Status.BAD_REQUEST, r.status());
        assertEquals(Constants.ErrorMessages.INVALID_MIN_HEIGHT_QUERY_PARAMETER, contentAsString(r));
    }

    @Test
    public void test400ReturnedIfInvalidMinHeightSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?minHeight=foo");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<MinHeight> minHeight = req.attrs().getOptional(Constants.Attrs.MIN_HEIGHT);
                    assertFalse(minHeight.isPresent());
                    return ok();
                });
        assertEquals(Http.Status.BAD_REQUEST, r.status());
        assertEquals(Constants.ErrorMessages.INVALID_MIN_HEIGHT_QUERY_PARAMETER, contentAsString(r));
    }

    @Test
    public void test400ReturnedIfZeroMinHeightSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?minHeight=0");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<MinHeight> minHeight = req.attrs().getOptional(Constants.Attrs.MIN_HEIGHT);
                    assertFalse(minHeight.isPresent());
                    return ok();
                });
        assertEquals(Http.Status.BAD_REQUEST, r.status());
        assertEquals(Constants.ErrorMessages.INVALID_MIN_HEIGHT_QUERY_PARAMETER, contentAsString(r));
    }

    @Test
    public void testMaxHeightAttributeSetIfMaxHeightValueSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?maxHeight=5");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<MaxHeight> maxHeight = req.attrs().getOptional(Constants.Attrs.MAX_HEIGHT);
                    assertEquals(maxHeight.get(), new MaxHeight(5));
                    return ok().withHeader("Actual-Max-Height", String.valueOf(maxHeight.get().getValue()));
                });
        assertEquals(Http.Status.OK, r.status());
        assertEquals("5.0", r.headers().get("Actual-Max-Height"));
    }

    @Test
    public void test400ReturnedIfNegativeMaxHeightSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?maxHeight=-1");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<MaxHeight> maxHeight = req.attrs().getOptional(Constants.Attrs.MAX_HEIGHT);
                    assertFalse(maxHeight.isPresent());
                    return ok();
                });
        assertEquals(Http.Status.BAD_REQUEST, r.status());
        assertEquals(Constants.ErrorMessages.INVALID_MAX_HEIGHT_QUERY_PARAMETER, contentAsString(r));
    }

    @Test
    public void test400ReturnedIfInvalidMaxHeightSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?maxHeight=foo");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<MaxHeight> maxHeight = req.attrs().getOptional(Constants.Attrs.MAX_HEIGHT);
                    assertFalse(maxHeight.isPresent());
                    return ok();
                });
        assertEquals(Http.Status.BAD_REQUEST, r.status());
        assertEquals(Constants.ErrorMessages.INVALID_MAX_HEIGHT_QUERY_PARAMETER, contentAsString(r));
    }

    @Test
    public void test400ReturnedIfZeroMaxHeightSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?maxHeight=0");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<MaxHeight> maxHeight = req.attrs().getOptional(Constants.Attrs.MAX_HEIGHT);
                    assertFalse(maxHeight.isPresent());
                    return ok();
                });
        assertEquals(Http.Status.BAD_REQUEST, r.status());
        assertEquals(Constants.ErrorMessages.INVALID_MAX_HEIGHT_QUERY_PARAMETER, contentAsString(r));
    }

    @Test
    public void testMinHeightAndMaxHeightAttributeSetIfValuesSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?minHeight=5&maxHeight=10");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<MinHeight> minHeight = req.attrs().getOptional(Constants.Attrs.MIN_HEIGHT);
                    Optional<MaxHeight> maxHeight = req.attrs().getOptional(Constants.Attrs.MAX_HEIGHT);
                    assertEquals(minHeight.get(), new MinHeight(5));
                    assertEquals(maxHeight.get(), new MaxHeight(10));
                    return ok()
                            .withHeader("Actual-Min-Height", String.valueOf(minHeight.get().getValue()))
                            .withHeader("Actual-Max-Height", String.valueOf(maxHeight.get().getValue()));
                });
        assertEquals(Http.Status.OK, r.status());
        assertEquals("5.0", r.headers().get("Actual-Min-Height"));
        assertEquals("10.0", r.headers().get("Actual-Max-Height"));
    }

    @Test
    public void test400ReturnedIfMinHeightGreaterThanMaxHeight() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?minHeight=10&maxHeight=5");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<MinHeight> minHeight = req.attrs().getOptional(Constants.Attrs.MIN_HEIGHT);
                    Optional<MaxHeight> maxHeight = req.attrs().getOptional(Constants.Attrs.MAX_HEIGHT);
                    assertFalse(minHeight.isPresent());
                    assertFalse(maxHeight.isPresent());
                    return ok();
                });
        assertEquals(Http.Status.BAD_REQUEST, r.status());
        assertEquals(Constants.ErrorMessages.INVALID_MIN_HEIGHT_GREATER_THAN_MAX_HEIGHT, contentAsString(r));
    }

    private Result callWithFunction(Http.Request req, Function<Http.Request, Result> f)
            throws Exception {
        ValidateMetersParametersAction action = new ValidateMetersParametersAction();
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
