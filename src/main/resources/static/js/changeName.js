function checkNickname() {
    if (!checkExistData(changeNameForm.nickname.value, "닉네임을")) {
        return false;
    }
    // if(!checkSameNickname()){
    //     return false;
    // }
    return true;
}
function checkExistData(value, dataName) {
    if (value == "") {
        alert(dataName + " 입력해주세요!");
        return false;
    }
    return true;
}
// function checkSameNickname(){
//     let name = [[${loginUser.getMemberNickname()}]]
//     let change = chageNameForm.nickname.value
//     if(name == change){
//         alert("닉네임이 달라지지 않았습니다.")
//         return false;
//     }
//     return true;
// }