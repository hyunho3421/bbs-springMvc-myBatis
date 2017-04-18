/**
 * Created by hyunhokim on 2017. 4. 19..
 */
$(document).ready(function () {
    var result = '${msg}';

    if(result == 'register_success') {
        alert("등록되었습니다.");
    }

    if(result == 'remove_success') {
        alert("삭제 되었습니다.")
    }

    if(result == 'success_modify') {
        alert("수정정되었습니다.")
    }

    $("#btnRegister").on("click", function(){
        self.location = "/bbs/register";
    });
});