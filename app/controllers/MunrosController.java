package controllers;

import controllers.actions.ValidateCategoryParameterAction;
import controllers.actions.ValidateLimitParameterAction;
import controllers.actions.ValidateMetersParametersAction;
import controllers.actions.ValidateSortParameterAction;
import play.mvc.*;
import services.MunroCSVFile;

import javax.inject.Inject;

public class MunrosController extends Controller {

    private MunroCSVFile munroCSVFile;

    @Inject
    public MunrosController(MunroCSVFile munroCSVFile) {
        this.munroCSVFile = munroCSVFile;
    }

    @With({ValidateCategoryParameterAction.class,
            ValidateLimitParameterAction.class,
            ValidateMetersParametersAction.class,
            ValidateSortParameterAction.class})
    public Result search(Http.Request request) {
        return ok();
    }
}
