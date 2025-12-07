const pagination = document.getElementById("pagination");
const search_btn = document.getElementById("search_btn");

function simulateLoading() {
    var loadingSpinner = document.querySelector(".loading-wrap--js");
    var loadingMessage = document.getElementById("load");

    loadingSpinner.style.display = "flex";
    loadingMessage.textContent = "AI가 추천할 만한 콘텐츠를 찾고 있는 중입니다.";
    loadingMessage.style.fontWeight = "bold";  

    setTimeout(function () {
        loadingSpinner.style.display = "none";
        loadingMessage.textContent = "완료!!";
        loadingMessage.style.fontWeight = "normal"; 
    }, 3300);
}

$(".action-button").click(function() {
    $(".spinner").css("opacity", "1"); 
    simulateLoading();
    $(".body").css("opacity", "0.5"); 
    const user_no = $('#user_no').val();

    $.get("/check/ai", {user_no: user_no})
        .done(function(aiList) {
            setTimeout(function () {
                $(".body").css("opacity", "1");
                displayStudyGroups(aiList);
                pagination.style.display="none";
            }, 3750);
          
        })
        .fail(function(error) {
            console.log("실패");
            $('#studyGroupList').html('<p>불러오는데 실패하였습니다.</p>'); 
        });
});
 
 function displayStudyGroups(aiList) {
    var content = $(".looks__tbody").empty(); 
    if (!aiList || aiList.length === 0) {
        content.html('<div class="looks__tbody__content"><p>해당 유저에 추천할 만한 스터디 그룹이 없습니다.</p></div>');
        return;
    }

    aiList.forEach(function(group, index) {
        var html = `
            <div class="looks__tbody__content">
                <div id="looks__tbody__first">${index + 1}</div>
                <a href="/student/groupApplyList?sg_no=${group.sg_no}" id="looks__tbody__second">${group.sg_name}</a>
                <div id="looks__tbody__third">${group.game_no}</div>
                <div id="looks__tbody__fourth">${group.sg_capa}명 / ${group.sg_grouplimit}명</div>
                <div id="looks__tbody__fifth">${group.sg_regdate} ~ ${group.sg_enddate}</div>
                <div id="looks__tbody__sixth">${group.sg_level}레벨</div>
                <div id="looks__tbody__seventh">${group.sg_capa}명</div>
            </div>`;
        content.append(html);  
    });
}
search_btn.click(function(){
	$.get("/group/search", )
})
                 	
                    	
                