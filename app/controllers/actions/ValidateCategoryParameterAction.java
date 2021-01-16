package controllers.actions;

import config.Constants;
import models.Category;
import play.mvc.Http;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ValidateCategoryParameterAction extends play.mvc.Action.Simple {
    @Override
    public CompletionStage<play.mvc.Result> call(Http.Request req) {
        Optional<String> categoryParameter = req.queryString("category");

        if (!categoryParameter.isPresent())
            return delegate.call(req);

        Category category = Category.fromQueryParameterValue(categoryParameter.get());

        if (category == null) {
            return CompletableFuture.completedFuture(badRequest(Constants.ErrorMessages.INVALID_CATEGORY_QUERY_PARAMETER));
        }

        return delegate.call(req.addAttr(Constants.Attrs.CATEGORY, category));
    }
}
