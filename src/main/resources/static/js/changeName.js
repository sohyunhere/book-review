function checkNickname() {
    if (!checkExistData(changeNameForm.nickname.value), "닉네임을") {
        return false;
    }
    return true;
}
function checkExistData(value, dataName) {
    if (value == "") {
        alert(dataName + " 입력해주세요!");
        return false;
    }
    return true;
}