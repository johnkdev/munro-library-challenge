package controllers;

import config.Constants;
import controllers.actions.ValidateCategoryParameterAction;
import controllers.actions.ValidateLimitParameterAction;
import controllers.actions.ValidateMetersParametersAction;
import controllers.actions.ValidateSortParameterAction;
import models.Munro;
import models.SearchParams;
import play.libs.Json;
import play.mvc.*;
import services.MunroSearchService;

import javax.inject.Inject;
import java.util.List;

public class MunrosController extends Controller {

    private MunroSearchService munroSearchService;

    @Inject
    public MunrosController(MunroSearchService munroSearchService) {
        this.munroSearchService = munroSearchService;
    }

    @With({ValidateCategoryParameterAction.class,
            ValidateLimitParameterAction.class,
            ValidateMetersParametersAction.class,
            ValidateSortParameterAction.class})
    public Result search(Http.Request request) {

        SearchParams searchParams = new SearchParams(
                request.attrs().getOptional(Constants.Attrs.CATEGORY).orElse(null),
                request.attrs().getOptional(Constants.Attrs.SORT).orElse(null),
                request.attrs().getOptional(Constants.Attrs.LIMIT).orElse(null),
                request.attrs().getOptional(Constants.Attrs.MIN_HEIGHT).orElse(null),
                request.attrs().getOptional(Constants.Attrs.MAX_HEIGHT).orElse(null)
        );

        List<Munro> munros = munroSearchService.search(searchParams);

        return ok(Json.prettyPrint(Json.toJson(munros))).as(Http.MimeTypes.JSON);
    }
}
