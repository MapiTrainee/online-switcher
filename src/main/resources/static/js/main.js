$(document).ready(function () {
    getState().then(function (data) {
        updateView(data.switchOn);
    });
});

$("#switch").on("click", function () {
    /*$("#switch").blur();*/
    var state;
    if ($("#switch").hasClass("btn-on")) {
        state = {switchOn: true};
    } else {
        state = {switchOn: false};
    }
    postState(state).then(function (data) {
        updateView(data.switchOn);
        console.log(data.switchOn);
    });
});

function updateView(switchOn) {
    if (!switchOn) {
        if (!$("#switch").hasClass("btn-on")) {
            $("#switch").addClass("btn-on");
        }

        if ($("#switch").hasClass("btn-off")) {
            $("#switch").removeClass("btn-off");
        }

        if (!$("body").hasClass("bg-dark")) {
            $("body").addClass("bg-dark");
        }

        if ($("body").hasClass("bg-warning")) {
            $("body").removeClass("bg-warning");
        }
    } else {
        if (!$("#switch").hasClass("btn-off")) {
            $("#switch").addClass("btn-off");
        }

        if ($("#switch").hasClass("btn-on")) {
            $("#switch").removeClass("btn-on");
        }

        if (!$("body").hasClass("bg-warning")) {
            $("body").addClass("bg-warning");
        }

        if ($("body").hasClass("bg-dark")) {
            $("body").removeClass("bg-dark");
        }
    }
}

function getState() {
    return $.ajax({
        url: '/state',
        dataType: 'JSON',
        success: function (data) {
            return data;
        },
        error: function () {
            alert("Incorrect state ...");
        }
    });
}

function postState(state) {
    return $.ajax({
        url: '/state',
        type: "POST",
        dataType: 'JSON',
        contentType: "application/json",
        data: JSON.stringify(state),
        success: function (data) {
            return data;
        },
        error: function () {
            alert("Incorrect state ...");
        }
    });
}