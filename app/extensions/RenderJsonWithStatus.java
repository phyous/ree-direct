package extensions;

import play.mvc.Http;
import play.mvc.results.RenderJson;

public class RenderJsonWithStatus extends RenderJson {

    private int status;

    public RenderJsonWithStatus(Object o, int status) {
        super(o);
        this.status = status;
    }

    @Override
    public void apply(Http.Request request, Http.Response response) {
        response.status = status;
        super.apply(request, response);
    }
}
