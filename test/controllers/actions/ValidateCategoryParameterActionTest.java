package controllers.actions;

import config.Constants;
import models.Category;
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

public class ValidateCategoryParameterActionTest {
    @Test
    public void testNoAttributeSetIfCategoryNotSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<Category> category = req.attrs().getOptional(Constants.Attrs.CATEGORY);
                    assertFalse(category.isPresent());
                    return ok();
                });
        assertEquals(Http.Status.OK, r.status());
    }

    @Test
    public void testAttributeSetIfMunroValueSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?category=munro");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<Category> category = req.attrs().getOptional(Constants.Attrs.CATEGORY);
                    assertEquals(category.get(), Category.MUN);
                    return ok().withHeader("Actual-Category", category.get().name());
                });
        assertEquals(Http.Status.OK, r.status());
        assertEquals("MUN", r.headers().get("Actual-Category"));
    }

    @Test
    public void testAttributeSetIfMunroTopValueSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?category=munro+top");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<Category> category = req.attrs().getOptional(Constants.Attrs.CATEGORY);
                    assertEquals(category.get(), Category.MUN_TOP);
                    return ok().withHeader("Actual-Category", category.get().name());
                });
        assertEquals(Http.Status.OK, r.status());
        assertEquals("MUN_TOP", r.headers().get("Actual-Category"));
    }

    @Test
    public void testAttributeSetIfMunroOrMunroTopValueSpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?category=munro,munro+top");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<Category> category = req.attrs().getOptional(Constants.Attrs.CATEGORY);
                    assertEquals(category.get(), Category.MUN_OR_MUN_TOP);
                    return ok().withHeader("Actual-Category", category.get().name());
                });
        assertEquals(Http.Status.OK, r.status());
        assertEquals("MUN_OR_MUN_TOP", r.headers().get("Actual-Category"));
    }

    @Test
    public void testAttributeSetIfMunroOrMunroTopValueSpecifiedAlt() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?category=munro+top,munro");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<Category> category = req.attrs().getOptional(Constants.Attrs.CATEGORY);
                    assertEquals(category.get(), Category.MUN_OR_MUN_TOP);
                    return ok().withHeader("Actual-Category", category.get().name());
                });
        assertEquals(Http.Status.OK, r.status());
        assertEquals("MUN_OR_MUN_TOP", r.headers().get("Actual-Category"));
    }

    @Test
    public void test400ReturnedIfInvalidCategorySpecified() throws Exception {
        Http.RequestBuilder builder = new Http.RequestBuilder();
        builder.uri("/?category=munro2");
        Result r = callWithFunction(
                builder.build(),
                req -> {
                    Optional<Category> category = req.attrs().getOptional(Constants.Attrs.CATEGORY);
                    assertFalse(category.isPresent());
                    return ok();
                });
        assertEquals(Http.Status.BAD_REQUEST, r.status());
        assertEquals(Constants.ErrorMessages.INVALID_CATEGORY_QUERY_PARAMETER, contentAsString(r));
    }

    private Result callWithFunction(Http.Request req, Function<Http.Request, Result> f)
            throws Exception {
        ValidateCategoryParameterAction action = new ValidateCategoryParameterAction();
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
