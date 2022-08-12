function checkAll() {
    if (!checkExistData(writePostForm.postTitle.value, "제목을")) {
        writePostForm.postTitle.focus();
        return false;
    }
    if(!checkReadDate(writePostForm.readDate.value)){
        writePostForm.readDate.focus();
        return false;
    }

    if (!checkExistData(writePostForm.bookTitle.value, "도서 제목을")) {
        writePostForm.bookTitle.focus();
        return false;
    }
    if (!checkExistData(writePostForm.author.value, "지은이를")) {
        writePostForm.author.focus();
        return false;
    }
    if (!checkExistData(writePostForm.publisher.value, "출판사")) {
        writePostForm.publisher.focus();
        return false;
    }
    if (!checkExistData(writePostForm.categoryId.value, "카테고리")) {
        writePostForm.category.focus();
        return false;
    }
    if (!checkExistData(writePostForm.content.value, "내용을")) {
        writePostForm.content.focus();
        return false;
    }
    // if(!checkExistData(writePostForm.editor.value, "내용울")){
    //     writePostForm.editor.focus();
    //     return false;
    // }

    return true;
}
function checkReadDate(value){
    if (!checkExistData(value, "읽은 날짜")) {
        return false;
    }

    if(value){
        let today = new Date();

        let year = today.getFullYear();
        let month = ('0' + (today.getMonth() + 1)).slice(-2);
        let day = ('0' + today.getDate()).slice(-2);
        let dateString = year + '-' + month  + '-' + day;

        if(value > dateString){
            alert("책을 읽은 날짜는 오늘 날짜까지 선택 가능합니다.")
            return false;
        }
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