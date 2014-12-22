function setupPostAction() {
    var SUBMIT_BUTTON_ID = "#redirectSubmit";
    var SUBMIT_FORM_ID = "#redirectForm"
    var ERROR_ALERT_ID = "#apiErrorAlert"
    var SUBMIT_URL = "/redirect";

    $(SUBMIT_BUTTON_ID).click(function() {
        $.ajax({
            type: "POST",
            url: SUBMIT_URL + "?" + $(SUBMIT_FORM_ID).serialize(),
            success: function(data)
            {
                window.location.replace("/create/" + data.alias + "/confirm?url=" + encodeURIComponent(data.url));
            },
            error: function(jqXHR, textStatus, errorThrown)
            {
                var errorMsg = "Unexpected error";
                if(jqXHR.responseJSON != null && jqXHR.responseJSON.description != null ) {
                    errorMsg = jqXHR.responseJSON.description;
                }
                $(ERROR_ALERT_ID).empty().append("<strong>ERROR:</strong> " + errorMsg)
                $(ERROR_ALERT_ID).hide().removeClass("hide").slideDown(400);
            },
            });

        return false;
    });
}

function setupPasswordField() {
    var CHECKBOX_ID = "#passwordCheckbox"
    var PASSWORD_FIELD_ID = "#passwordField"

    $(CHECKBOX_ID).change(
        function(){
            if ($(this).is(':checked')) {
                $(PASSWORD_FIELD_ID).hide().removeClass("hide").slideDown(400);
            } else {
                $(PASSWORD_FIELD_ID).addClass("hide").hide();
            }
        });
}

function setupInitialFocus() {
    var FOCUS_FIELD_ID = "#alias"
    $(FOCUS_FIELD_ID).focus();
}

setupPostAction();
setupPasswordField();
setupInitialFocus();