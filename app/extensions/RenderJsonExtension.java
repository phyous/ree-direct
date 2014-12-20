package extensions;

import play.mvc.Http;
import play.mvc.results.RenderJson;

public class RenderJsonExtension extends RenderJson {

    private int status;

    public RenderJsonExtension(Object o, int status) {
        super(o);
        this.status = status;
    }

    public RenderJsonExtension(String s, int status) {
        super(s);
        this.status = status;
    }

    @Override
    public void apply(Http.Request request, Http.Response response) {
        response.status = status;
        super.apply(request, response);
    }
}
