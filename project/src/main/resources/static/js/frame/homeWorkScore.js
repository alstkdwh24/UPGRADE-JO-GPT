// 숙제 클릭했을 때 homework_no 받아오기
$('.homework-checkbox').change(function () {
    if ($(this).is(':checked')) {
        var homeworkNo = $(this).closest('.score__tbody__content').find('.homeworkNo').val();
        console.log('Homework No:', homeworkNo);
        
        $.ajax({
			url: "/homeworkReceive/" + homeworkNo,
			method: "GET",
			success: function(data) {
				console.log(data)
				
				// 일단 한 번 비워주고
    			$(".score__tbody2").empty();
    			
    			data.forEach(function(user) {
				// 일단 행 생성
		        var userInfo = $("<div class='score__tbody2__content'>");
		        
		        // 숙제 정보를 표시
				userInfo.append('<div class="score__tbody2__one"><input type="checkbox" class="user-checkbox" data-user-no="' + user.user_no + '" data-homework-no="' + user.homework_no + '"></div>');
		        userInfo.append('<div class="score__tbody2__two">' + user.user_name + '</div>');  // 학습자명
		        userInfo.append('<div class="score__tbody2__three">' + user.teach_assigndate + '</div>'); 
		        userInfo.append('<div class="score__tbody2__four">' + user.stu_content + '</div>'); 
		        userInfo.append('<div class="score__tbody2__five">' + user.stu_q + '</div>'); 
		        userInfo.append('<div class="score__tbody2__six">' + 
		                        '<select name="grade" class="grade-select">' +
		                        '<option value="none">평가</option>' +
		                        '<option value="3">우수</option>' +
		                        '<option value="2">보통</option>' +
		                        '<option value="1">미흡</option>' +
		                        '</select>' +
		                        '</div>');
		        
		        // 생성된 숙제를 테이블에 추가합니다.
		        $(".score__tbody2").append(userInfo);
				}) 


			},
			error: function(error) {
				console.log("에러: ", error)
			}
		})
        
    }
});



$("#save").click(function() {
    var completedRequests = 0; // 완료된 요청 수를 추적하는 변수

    // 생긴 checkbox 누르면, 그녀석 user_no랑 homework_no가 나옴
    $('.user-checkbox').each(function() {
        if ($(this).is(':checked')) {
			var selectedGrade = $(this).closest('.score__tbody2__content').find('.grade-select').val();
            var userNo = $(this).data('user-no');
            var homeworkNo = $(this).data('homework-no');
            console.log('User No:', userNo);
            console.log('Homework No:', homeworkNo);
            console.log("selectedGrade", selectedGrade);

            if(selectedGrade === 'none') {
                alert("체크한 학생의 평가를 해주세요");
                return;
            }
            
            $.ajax({
				url: "/homeworkGrade/" + homeworkNo + "/" + userNo + "/" + selectedGrade + "/",
				method: "GET",
				success: function(data) {
	                completedRequests++; // 완료된 요청 수를 증가시킵니다.
	                if (completedRequests === 1) {
	                    alert("성공적으로 평가가 완료되었습니다");
	                }
	                
/*	                if(selectedGrade <= 2) {
						$.ajax({
							url: "/homeworkProgressInsert/" + homeworkNo + "/" + userNo + "/" + selectedGrade + "/",
							method: "GET",
							success: function(data) {
								alert("hw_prog도 성공")
								
								
							}
						})
					}*/
	                
				}
				
			})
            
            
        }
    });
});

