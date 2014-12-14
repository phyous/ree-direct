package extensions;

import play.mvc.Controller;

public class ApiController extends Controller {
    /**
     * Render an application/json response with a specific status code
     * @param o The Java object to serialize
     */
    protected static void renderJSON(Object o, int status) {
        throw new RenderJsonWithStatus(o, status);
    }

}
