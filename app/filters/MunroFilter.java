package filters;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import javax.inject.Inject;
import akka.stream.Materializer;
import config.Constants;
import play.Logger;
import play.mvc.*;
import services.MunroCSVFile;

import static play.mvc.Results.internalServerError;

public class MunroFilter extends Filter {

    private final play.Logger.ALogger log = Logger.of(this.getClass());

    private MunroCSVFile munroCSVFile;

    @Inject
    public MunroFilter(Materializer mat, MunroCSVFile munroCSVFile) {
        super(mat);
        this.munroCSVFile = munroCSVFile;
    }

    @Override
    public CompletionStage<Result> apply(
            Function<Http.RequestHeader, CompletionStage<Result>> nextFilter,
            Http.RequestHeader requestHeader) {
        if (munroCSVFile.getMunros() == null) {
            log.error(Constants.ErrorMessages.MUNRO_DATA_NOT_LOADED);
            return CompletableFuture.completedFuture(internalServerError());
        }
        return nextFilter.apply(requestHeader);
    }
}
