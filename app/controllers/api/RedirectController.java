package controllers.api;

import extensions.ApiController;
import models.Redirect;
import play.mvc.Http;
import views.api.ApiError;

public class RedirectController extends ApiController {
    public static void create() {
        final String URL_PARAM = "url";
        final String ALIAS_PARAM = "alias";
        final String PASSWORD_PARAM = "password";

        final String url = request.params.get(URL_PARAM);
        final String alias = request.params.get(ALIAS_PARAM);
        String password = request.params.get(PASSWORD_PARAM);

        // Input Validation
        if (url == null || url.isEmpty()) {
            renderJSON(ApiError.parameterValidationError(URL_PARAM), Http.StatusCode.BAD_REQUEST);
        }
        if (alias == null || alias.isEmpty()) {
            renderJSON(ApiError.parameterValidationError(ALIAS_PARAM), Http.StatusCode.BAD_REQUEST);
        }

        // Check duplicate alias
        if (Redirect.findByAlias(alias) != null) {
            renderJSON(new ApiError(
                    ApiError.INVALID_PARAMETER,
                    String.format("Redirect for \"%s\" already exists. Try another alias.", alias),
                    Http.StatusCode.BAD_REQUEST
            ), Http.StatusCode.BAD_REQUEST);
        }

        // Empty password counts as no password
        if (password != null && password.isEmpty()) {
            password = null;
        }

        Redirect redirect = Redirect.create(url, alias, password);

        renderJSON(redirect);
    }

    public static void get() {
        final String ALIAS_PARAM = "alias";
        final String PASSWORD_PARAM = "password";

        final String alias = request.params.get(ALIAS_PARAM);
        final String password = request.params.get(PASSWORD_PARAM);

        // Input Validation
        if (alias == null || alias.isEmpty()) {
            renderJSON(ApiError.parameterValidationError(ALIAS_PARAM), Http.StatusCode.BAD_REQUEST);
        }

        Redirect redirect = Redirect.findByAlias(alias);

        // Validate alias exists
        if (redirect == null) {
            renderJSON(new ApiError(
                            "Redirect not found",
                            "The redirect you're looking for doesn't exist",
                            Http.StatusCode.NOT_FOUND),
                    Http.StatusCode.NOT_FOUND);
        }

        // Password validation
        if (redirect.hasPassword() && !redirect.password.equals(password)) {
            renderJSON(new ApiError(
                            "Invalid password",
                            "The password provided for this alias was incorrect",
                            Http.StatusCode.FORBIDDEN
                    ),
                    Http.StatusCode.FORBIDDEN);
        }

        renderJSON(redirect);
    }
}
