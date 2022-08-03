function checkAll() {
    if (!checkExistData(writePostForm.postTitle.value, "제목을")) {
        writePostForm.postTitle.focus();
        return false;
    }
    if (!checkExistData(writePostForm.readDate.value, "읽은 날짜")) {
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
    return true;
}
function checkExistData(value, dataName) {
    if (value == "") {
        alert(dataName + " 입력해주세요!");
        return false;
    }
    return true;
}