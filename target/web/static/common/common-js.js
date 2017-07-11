/**
 * Created by hyunhokim on 2017. 4. 19..
 */

function commonDate_YYYYMMDD(timeValue) {
    var dateObj = new Date(timeValue);
    var year = dateObj.getFullYear();
    var month = dateObj.getMonth();
    var date = dateObj.getDate();

    return year + "/" + month + "/" + date;
}


function checkImageType(fileName) {
    var pattern = /jpg|gif|png|jpeg/i;

    return fileName.match(pattern);
}

//파일
function getOriginalName(fileName) {
    if(checkImageType(fileName)) {
        return;
    }

    var idx = fileName.indexOf("_") + 1;

    return fileName.substr(idx);
}

function getImageLink(fileName) {
    if (!checkImageType(fileName)) {
        return;
    }

    var front = fileName.substr(0,12);

    var end = fileName.substr(14);

    return front + end;
}

//이미지
function getFullName(fullName) {

    var result = fullName.substr(14);

    return result.substr(result.indexOf("_") + 1);
}

function getThumnailImageLink(fileName) {
    var front = fileName.substr(0,12);
    var end = fileName.substr(12);

    return front + "s_" + end;
}

//로그 아웃
function logout() {
    var formObj = $("#logout");

    $.ajax({
        url: "/logout",
        type: "POST",
        data: formObj.serialize(),
        success: function (result) {
            if (result == 'logout_success') {

                if (location.pathname == '/bbs/list') {
                    $(".navbar-right").html('<li><a href="/user/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>');
                } else {
                    location.reload();
                }
            }
        }
    });
}