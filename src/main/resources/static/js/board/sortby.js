function sortByLatest(){
    // ajax 통신
    let header = $("meta[name='_csrf_header']").attr('content');
    let token = $("meta[name='_csrf']").attr('content');
    $.ajax({
        async: true,
        type : "get",
        contentType: "application/json",
        url : "/latest",
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success:
            function (data) {
               $("#latest").attr("disabled", "disabled");
               $("#view").removeAttr("disabled");
                setGridData(data.posts);
            },
        error :
            function (request, status, error){
                alert("실패"+ "code:"+request.status+"\n"+" message : " + request.responseText +"\n"+"error:"+error);
            }
    });
}
function setGridData(list){
    // GRID 에 데이터를 입력한다.
    let arrData = [];
    for(let i = 0; i < list.length; i++) {
        let postV = {}
        postV.no = list[i].postId;
        postV.title = list[i].postTitle;
        postV.name = list[i].member.memberNickname;
        postV.date = list[i].writtenDate;
        postV.view = list[i].viewCount;
        arrData.push(postV);
    }
    grid.resetData( arrData );
}
function sortByView(){
    // ajax 통신
    let header = $("meta[name='_csrf_header']").attr('content');
    let token = $("meta[name='_csrf']").attr('content');
    $.ajax({
        async: true,
        type : "get",
        contentType: "application/json",
        url : "/popular",
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success:
            function (data) {
                $("#latest").removeAttr("disabled");
                $("#view").attr("disabled", "disabled");
                setGridData(data.posts);
            },
        error :
            function (request, status, error){
                alert("실패"+ "code:"+request.status+"\n"+" message : " + request.responseText +"\n"+"error:"+error);
            }
    });
}

