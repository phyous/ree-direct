package views.api;

import play.mvc.Http;

public class ApiError {
    public static final String INVALID_PARAMETER = "Invalid API parameter";

    private String message;
    private String description;
    private int status;

    public ApiError(String message, String description, int status) {
        this.message = message;
        this.description = description;
        this.status = status;
    }

    public static ApiError parameterValidationError(String paramName) {
        return new ApiError(ApiError.INVALID_PARAMETER,
                String.format("Invalid value for parameter %s", paramName),
                Http.StatusCode.BAD_REQUEST);
    }
}
