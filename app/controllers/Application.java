package controllers;

import play.mvc.*;

public class Application extends Controller {

    public static void about() {
        render();
    }

    public static void createRedirect() {
        final String alias = request.routeArgs.get("alias");
        render("Redirect/create.html", alias);
    }

    public static void confirmRedirect() {
        final String alias = request.routeArgs.get("alias");
        final String url = request.params.get("url");
        render("Redirect/confirm.html", alias, url);
    }

    public static void lookupRedirect() {
        final String alias = request.routeArgs.get("alias");
        final String password = request.params.get("password");

        if (alias == null) {
            error("Null alias detected.");
        }

        models.Redirect redirect = models.Redirect.findByAlias(alias);

        if (redirect == null) {
            notFound(String.format("Redirect \"%s\" doesn't exist", alias));
        }

        if(redirect.hasPassword() && !redirect.password.equals(password)) {
            render("Redirect/password.html", alias);
        } else {
            redirect(redirect.url, false);
        }
    }
}