package controllers.actions;

import config.Constants;
import models.Sort;
import play.mvc.Http;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ValidateSortParameterAction extends play.mvc.Action.Simple {
    @Override
    public CompletionStage<play.mvc.Result> call(Http.Request req) {
        Optional<String> sortParameter = req.queryString("sort");

        if (!sortParameter.isPresent())
            return delegate.call(req);

        Sort sortOrder = Sort.fromQueryParameterValue(sortParameter.get());

        if (sortOrder == null) {
            return CompletableFuture.completedFuture(badRequest(Constants.ErrorMessages.INVALID_SORT_QUERY_PARAMETER));
        }

        return delegate.call(req.addAttr(Constants.Attrs.SORT, sortOrder));
    }
}
