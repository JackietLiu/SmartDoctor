function initScript() {
    $(".scrollbar").mCustomScrollbar({
        theme: "minimal-dark"
    });
    $(".radio").click(function () {
        $('.radio').removeClass("checked");
        $(this).addClass("checked");
    });
}

$(document).ready(function () {
    initScript();
});