function setupPostAction() {
    var SUBMIT_BUTTON_ID = "#redirectSubmit";
    var SUBMIT_FORM_ID = "#redirectForm"
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
                // TODO: Add error handling here
                alert(data); // show response
                if(data != "false")
                {
                    console.log(data);
                }
            },
            });

        return false;
    });
}