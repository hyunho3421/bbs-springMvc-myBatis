/**
 * Created by hyunhokim on 2017. 4. 18..
 */
$(document).ready(function () {
    var formObj = $("form[role='form']");

    console.log(formObj);

    //기능 안됨.
    $('.btn-warning').on("click", function () {
        var no = $('#no').val();
        document.location = "/bbs/view?no=" + no;
    });

    $(".btn-primary").on("click", function () {
        formObj.attr("action", "/bbs/modify");
        formObj.attr("method", "POST");
        formObj.submit();
    });
});
