function setupInitialFocus() {
    var FOCUS_FIELD_ID = "#redirectPassword"

    $(FOCUS_FIELD_ID).focus();
}

function setupPostAction() {
    var SUBMIT_BUTTON_ID = "#passwordSubmit";
    var SUBMIT_FORM_ID = "#passwordForm"
    var PASSWORD_FIELD_ID = "#redirectPassword"
    var ERROR_ALERT_ID = "#apiErrorAlert"
    var SUBMIT_URL = "/redirect";

    $(SUBMIT_BUTTON_ID).click(function() {
        $.ajax({
            type: "GET",
            url: SUBMIT_URL + "?" + $(SUBMIT_FORM_ID).serialize(),
            success: function(data)
            {
                var password = $(PASSWORD_FIELD_ID).val()
                window.location.replace("/" + data.alias + "?password=" + encodeURIComponent(password));
            },
            error: function(jqXHR, textStatus, errorThrown)
            {
                $(ERROR_ALERT_ID).empty().append("<strong>ERROR:</strong> " + jqXHR.responseJSON.description)
                $(ERROR_ALERT_ID).hide().removeClass("hide").slideDown(400);
                setupInitialFocus();
            },
            });

        return false;
    });
}

setupPostAction();
setupInitialFocus();