package controllers.actions;

import config.Constants;
import models.Limit;
import play.mvc.Http;
import play.mvc.Result;
import utils.StringUtils;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ValidateLimitParameterAction extends play.mvc.Action.Simple {
    @Override
    public CompletionStage<Result> call(Http.Request req) {
        Optional<String> limitParameter = req.queryString("limit");

        if (!limitParameter.isPresent())
            return delegate.call(req);

        Optional<Integer> limitNumber = StringUtils.toPositiveNonZeroInteger(limitParameter.get());

        if (!limitNumber.isPresent())
            return CompletableFuture.completedFuture(badRequest(Constants.ErrorMessages.INVALID_LIMIT_QUERY_PARAMETER));

        return delegate.call(req.addAttr(Constants.Attrs.LIMIT, new Limit(limitNumber.get())));
    }
}
