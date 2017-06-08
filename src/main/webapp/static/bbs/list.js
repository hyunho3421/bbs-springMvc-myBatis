/**
 * Created by hyunhokim on 2017. 4. 19..
 */
$(document).ready(function () {
    $("#btnRegister").on("click", function(){
        self.location = "/bbs/register"
            + '?page=' + $('#page').val()
            + '&perPageNum' + $('#perPageNum').val()
            + '&searchType=' + $("select option:selected").val()
            + '&keyword=' + $("#keyword").val();

    });

    $("#btnSearch").on("click", function () {
        self.location = "/bbs/list"
        + '?page=1&perPageNum=10'
        + '&searchType=' + $("select option:selected").val()
        + '&keyword=' + $("#keyword").val();
    });

    //엔터키 눌렀을 떄 검색 되도록
    $("#keyword").keypress(function (e) {
        if (e.keyCode == 13) {
            self.location = "/bbs/list"
                + '?page=1&perPageNum=10'
                + '&searchType=' + $("select option:selected").val()
                + '&keyword=' + $("#keyword").val();
        }
    })

});