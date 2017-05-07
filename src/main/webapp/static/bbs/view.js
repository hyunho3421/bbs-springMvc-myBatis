/**
 * Created by hyunhokim on 2017. 4. 18..
 */
$(document).ready(function () {
    getRepliesList();

    var formObj = $("form[role='form']");

    $('#btnModify').on("click", function () {
        formObj.attr("action", "/bbs/modify");
        formObj.attr("method", "GET");
        formObj.submit();
    });

    $("#btnDelete").on("click", function () {
        formObj.attr("action", "/bbs/remove");
        formObj.attr("method", "POST");
        formObj.submit();
    });

    $("#btnList").on("click", function () {
        // history.go(-1);
        formObj.attr("action", "/bbs/list");
        formObj.attr("method", "GET");
        formObj.submit();
    });

    $("#btnReply").on("click", function () {
        var bno = $("input[name=no]").val();
        var replyer = $("#replyer").val();
        var replyText = $("#replyText").val();

        $.ajax({
            type : 'post',
            url : '/replies',
            headers : {
                "Content-Type" : "application/json",
                "X-HTTP-Method-Override" : "POST"
            },
            dataType : 'text',
            data : JSON.stringify({
                bno : bno,
                replyer : replyer,
                replyText : replyText
            }),
            success : function (result) {
                if (result == 'SUCCESS') {
                    alert("등록 되었습니다.");
                    getRepliesList();
                }
            }
        });
    });

});

function getRepliesList() {
    var bno = $("input[name=no]").val();
    var page = $("input[name=page]").val();

    $.getJSON("/replies/"+bno+"/"+page, function (data) {
        console.log(data.list.length);

        var str = "";

        $(data.list).each(function () {
            var date = new Date(commonDate_YYYYMMDD(this.reg_date));

            str += "<div class='panel panel-default'>"
                    + "<div class='panel-heading' data-rno='" + this.rno + "'>"
                    + this.replyer + " (" + commonDate_YYYYMMDD(this.reg_date) + ")"
                    + "</div>"
                    + "<div class='panel-body'>"
                    + this.replyText
                    + "</div>"
                + "</div>";
        });

        $("#replies").html(str);
    });
}