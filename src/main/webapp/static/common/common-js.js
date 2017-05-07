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