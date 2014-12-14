package controllers.api;

import extensions.ApiController;
import models.Redirect;
import play.mvc.Http;
import views.api.ApiError;

public class RedirectController extends ApiController{
    public static void create() {
        final String URL_PARAM = "url";
        final String ALIAS_PARAM = "alias";
        final String PASSWORD_PARAM = "pasword";

        final String url = request.params.get(URL_PARAM);
        final String alias = request.params.get(ALIAS_PARAM);
        final String password = request.params.get(PASSWORD_PARAM);

        // Input Validation
        if (url == null || url.isEmpty()) {
            renderJSON(ApiError.parameterValidationError("url"), Http.StatusCode.BAD_REQUEST);
        }
        if (alias == null || alias.isEmpty()) {
            renderJSON(ApiError.parameterValidationError("alias"), Http.StatusCode.BAD_REQUEST);
        }
        if (password != null && password.isEmpty()) {
            renderJSON(ApiError.parameterValidationError("password"), Http.StatusCode.BAD_REQUEST);
        }
        if (Redirect.findByAlias(alias) != null) {
            renderJSON(new ApiError(
                    ApiError.INVALID_PARAMETER,
                    String.format("Redirect for \"%s\" already exists", alias),
                    Http.StatusCode.BAD_REQUEST
            ), Http.StatusCode.BAD_REQUEST);
        }

        Redirect redirect = Redirect.create(url, alias, null);

        renderJSON(redirect);
    }
}
