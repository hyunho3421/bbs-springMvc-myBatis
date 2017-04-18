/**
 * Created by hyunhokim on 2017. 4. 18..
 */
$(document).ready(function () {
    var formObj = $("form[role='form']");

    $('#btnCancel').on("click", function () {
        var no = $('#no').val();
        document.location = "/bbs/view?no=" + no;
    });

    $("#btnModify").on("click", function () {
        formObj.attr("action", "/bbs/modify");
        formObj.attr("method", "POST");
        formObj.submit();
    });
});
