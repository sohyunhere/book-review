function checkSearch(){
    let word = $("#search").val()
    if (!checkExistData(word, "검색어를")) {
        return false;
    }
    return true;
}
function checkExistData(value, dataName) {
    value = value.trim();
    if (value == "") {
        alert(dataName + " 입력해주세요!");
        return false;
    }
    return true;
}