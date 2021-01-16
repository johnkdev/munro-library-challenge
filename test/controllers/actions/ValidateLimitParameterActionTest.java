package controllers.actions;

import config.Constants;
import models.Limit;
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

public class ValidateLimitParameterActionTest {
    @Test
    public void testNoAttributeSetIfLimitNotSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<Limit> limit = req.attrs().getOptional(Constants.Attrs.LIMIT);
                    assertFalse(limit.isPresent());
                    return ok();
                });
        assertEquals(Http.Status.OK, r.status());
    }

    @Test
    public void testAttributeSetIfLimitValueSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?limit=5");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<Limit> limit = req.attrs().getOptional(Constants.Attrs.LIMIT);
                    assertEquals(limit.get(), new Limit(5));
                    return ok().withHeader("Actual-Limit", String.valueOf(limit.get().getValue()));
                });
        assertEquals(Http.Status.OK, r.status());
        assertEquals("5", r.headers().get("Actual-Limit"));
    }

    @Test
    public void test400ReturnedIfNegativeLimitSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?limit=-1");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<Limit> limit = req.attrs().getOptional(Constants.Attrs.LIMIT);
                    assertFalse(limit.isPresent());
                    return ok();
                });
        assertEquals(Http.Status.BAD_REQUEST, r.status());
        assertEquals(Constants.ErrorMessages.INVALID_LIMIT_QUERY_PARAMETER, contentAsString(r));
    }

    @Test
    public void test400ReturnedIfZeroLimitSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?limit=0");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<Limit> limit = req.attrs().getOptional(Constants.Attrs.LIMIT);
                    assertFalse(limit.isPresent());
                    return ok();
                });
        assertEquals(Http.Status.BAD_REQUEST, r.status());
        assertEquals(Constants.ErrorMessages.INVALID_LIMIT_QUERY_PARAMETER, contentAsString(r));
    }

    @Test
    public void test400ReturnedIfInvalidLimitSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?limit=foo");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<Limit> limit = req.attrs().getOptional(Constants.Attrs.LIMIT);
                    assertFalse(limit.isPresent());
                    return ok();
                });
        assertEquals(Http.Status.BAD_REQUEST, r.status());
        assertEquals(Constants.ErrorMessages.INVALID_LIMIT_QUERY_PARAMETER, contentAsString(r));
    }

    private Result callWithFunction(Http.Request req, Function<Http.Request, Result> f)
            throws Exception {
        ValidateLimitParameterAction action = new ValidateLimitParameterAction();
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
