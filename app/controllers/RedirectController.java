package controllers;

import models.Redirect;
import play.data.validation.Required;
import play.mvc.Controller;

public class RedirectController extends Controller {

    public static void createRedirect() {
        final String alias = request.routeArgs.get("alias");
        render("Redirect/create.html", alias);
    }

    public static void saveRedirect(@Required String alias, @Required String url) {
        if (validation.hasErrors()) {
            render("Redirect/create.html");
        }

        Redirect.create(url, alias, null);
        render("Redirect/created.html", alias, url);
    }

    public static void get() {
        String alias = request.routeArgs.get("alias");
        if (alias == null) {
            error("Null alias detected.");
        }

        models.Redirect redirect = models.Redirect.findByAlias(alias);

        if (redirect == null) {
            notFound(String.format("Redirect \"%s\" doesn't exist", alias));
        } else {
            redirect(redirect.url, false);
        }
    }

}
