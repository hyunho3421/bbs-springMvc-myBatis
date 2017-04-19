/**
 * Created by hyunhokim on 2017. 4. 18..
 */
$(document).ready(function () {
    var formObj = $("form[role='form']");

    $('#btnCancel').on("click", function () {
        var no = $('#no').val();
        document.location = "/bbs/view?no=" + no;

        formObj.attr("action", "/bbs/view");
        formObj.attr("method", "GET");
        formObj.submit();
    });

    $("#btnModify").on("click", function () {
        formObj.attr("action", "/bbs/modify");
        formObj.attr("method", "POST");
        formObj.submit();
    });
});
