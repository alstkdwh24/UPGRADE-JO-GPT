// 체크 박스 클릭시 value 값 호출
var game_checkBox = document.querySelectorAll(".game_checkBox");
	for(var i=0; i<game_checkBox.length; i++){
		game_checkBox[i].addEventListener("click", function(){
			console.log(event.target.value);
		})
}

 document.addEventListener('DOMContentLoaded', function() {
        var checkboxes = document.querySelectorAll('input[name="selectedGame"]'); // name이 "selectedGame"인 체크박스들을 가져옴

        // 체크박스들의 변경 이벤트를 감지하여 다중 선택을 막음
        checkboxes.forEach(function(checkbox) {
            checkbox.addEventListener('change', function(event) {
                checkboxes.forEach(function(cb) {
                    if (cb !== event.target) {
                        cb.checked = false; // 다른 체크박스들의 체크를 해제함
                    }
                });
            });
        });
    });
    
    
$(document).ready(function() {
    $('#sendSelectedValuesButton').click(function() {
        var selectedValues = [];
        $('.game_checkBox:checked').each(function() {
            var value = $(this).val();
            if (!selectedValues.includes(value)) {
                selectedValues.push(value);
            }
        });
        // 선택된 값들을 문자열로 변환하여 쉼표로 구분하여 설정
        var selectedValuesString = selectedValues.join(',');
        // hidden input에 선택된 값들을 설정
        $('#game_no').val(selectedValuesString);
        // 폼 제출
        $('.regist__wrap').attr("action","/admin/learnRegist") ;
    });
});


// 게임컨텐츠 조회 삭제버튼

$(document).ready(function() {
    $(".gl__delete").click(function() {
		alert("해당 컨텐츠를 삭제 하시겠습니까?");
        var selectedGames = [];

        $(this).closest('.gl__tbody__content').find(".game_checkBox:checked").each(function() {
            var gameNo = $(this).val();
            console.log("aaa : " + gameNo);
            selectedGames.push(gameNo);
        });
        $.ajax({
            url: "/deleteSelectedGames",
            method: "POST",
            data: JSON.stringify({ selectedGames: selectedGames }),
            contentType: "application/json",
            success: function(response) {
                // 성공적으로 삭제된 경우 실행할 코드
                console.log("선택된 게임 삭제 완료");
                // 페이지 새로고침 또는 삭제된 항목 갱신
            },
            error: function(xhr, status, error) {
                // 오류 발생 시 실행할 코드
                console.error("삭제 중 오류 발생:", error);
            }
        });
    });
});

var search__level__Btn = document.getElementById("search__level__Btn");
	search__level__Btn.addEventListener('click', function() {
			
	event.preventDefault(); //고유 이벤트 중지
	document.gameLook.submit(); // form submit
			
})