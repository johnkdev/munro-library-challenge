package controllers.actions;

import config.Constants;
import models.MaxHeight;
import models.MinHeight;
import play.mvc.Http;
import play.mvc.Result;
import utils.StringUtils;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ValidateMetersParametersAction extends play.mvc.Action.Simple {
    @Override
    public CompletionStage<Result> call(Http.Request req) {
        Optional<String> minHeightParameter = req.queryString("minHeight");
        Optional<String> maxHeightParameter = req.queryString("maxHeight");

        if (minHeightParameter.isPresent() && maxHeightParameter.isPresent()) {
            Optional<Double> minHeightNumber = StringUtils.toPositiveNonZeroDouble(minHeightParameter.get());
            Optional<Double> maxHeightNumber = StringUtils.toPositiveNonZeroDouble(maxHeightParameter.get());

            if (!minHeightNumber.isPresent()) {
                return CompletableFuture.completedFuture(badRequest(Constants.ErrorMessages.INVALID_MIN_HEIGHT_QUERY_PARAMETER));
            }

            if (!maxHeightNumber.isPresent()) {
                return CompletableFuture.completedFuture(badRequest(Constants.ErrorMessages.INVALID_MAX_HEIGHT_QUERY_PARAMETER));
            }

            double minHeightValue = minHeightNumber.get();
            double maxHeightValue = maxHeightNumber.get();

            if (minHeightValue > maxHeightValue) {
                return CompletableFuture.completedFuture(badRequest(Constants.ErrorMessages.INVALID_MIN_HEIGHT_GREATER_THAN_MAX_HEIGHT));
            }

            Http.Request newReq = req
                    .addAttr(Constants.Attrs.MIN_HEIGHT, new MinHeight(minHeightValue))
                    .addAttr(Constants.Attrs.MAX_HEIGHT, new MaxHeight(maxHeightValue));

            return delegate.call(newReq);
        }

        if (minHeightParameter.isPresent()) {
            String minHeight = minHeightParameter.get();

            Optional<Double> minHeightNumber = StringUtils.toPositiveNonZeroDouble(minHeight);

            if (!minHeightNumber.isPresent()) {
                return CompletableFuture.completedFuture(badRequest(Constants.ErrorMessages.INVALID_MIN_HEIGHT_QUERY_PARAMETER));
            }

            Http.Request newReq = req.addAttr(Constants.Attrs.MIN_HEIGHT, new MinHeight(minHeightNumber.get()));

            return delegate.call(newReq);
        }

        if (maxHeightParameter.isPresent()) {
            String maxHeight = maxHeightParameter.get();

            Optional<Double> maxHeightNumber = StringUtils.toPositiveNonZeroDouble(maxHeight);

            if (!maxHeightNumber.isPresent()) {
                return CompletableFuture.completedFuture(badRequest(Constants.ErrorMessages.INVALID_MAX_HEIGHT_QUERY_PARAMETER));
            }

            Http.Request newReq = req.addAttr(Constants.Attrs.MAX_HEIGHT, new MaxHeight(maxHeightNumber.get()));

            return delegate.call(newReq);
        }

        return delegate.call(req);
    }
}
