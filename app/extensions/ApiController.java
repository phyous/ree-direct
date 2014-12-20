package extensions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import play.mvc.Controller;
import play.mvc.Http;

public class ApiController extends Controller {
    /**
     * Render an application/json response with a specific status code
     * Also exclude any fields that don't have @Expose annotation
     * @param o The Java object to serialize
     * @param status The HTTP status code to return on the response
     */
    protected static void renderJSON(Object o, int status) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(o);
        throw new RenderJsonExtension(json, status);
    }

    protected static void renderJSON(Object o) {
        ApiController.renderJSON(o, Http.StatusCode.OK);
    }

}
