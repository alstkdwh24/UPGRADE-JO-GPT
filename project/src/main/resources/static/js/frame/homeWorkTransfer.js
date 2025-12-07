// 숙제 클릭했을 때 homework_no 받아오기
$('.homework-checkbox').change(function () {
    if ($(this).is(':checked')) {
        var homeworkNo = $(this).closest('.transfer__tbody__content').find('.homeworkNo').val();
        console.log('Homework No:', homeworkNo);
    }
});

/// 동민이 만짐
function getCheckedGroupNumbers() {
    var checkedGroupNumbers = [];
    $(".transfer__tbody2.left input[type='checkbox']:checked").each(function() {
        var sg_no = $(this).data("sg_no");
        checkedGroupNumbers.push(sg_no);
    });
    return checkedGroupNumbers;
}

// 전송하기 눌렀을 때
$("#send").click(function() {
	// 숙제번호 가져오기
	var homeworkNo = $(".homework-checkbox:checked").closest('.transfer__tbody__content').find('.homeworkNo').val();
	console.log("숙제번호: " + homeworkNo);
	
	//여기 동민이 만짐
	var checkedGroupNumbers = getCheckedGroupNumbers();
	
	// 숙제 선택 안 했을 때 return
	if(homeworkNo === undefined) {
		alert("숙제를 선택해주세요");
		return;
	}
	
	// 체크된 학생들 가져오기
	var checkedStudents = [];
	
	$(".transfer__tbody__content2.right input[type='checkbox']:checked").each(function() {
		var checkedStuNum = $(this).data("user_no");
		checkedStudents.push(checkedStuNum);

	})
	console.log(checkedStudents);
	
	    checkedGroupNumbers.forEach(function(sg_no) {
        checkedStudents.forEach(function(studentNo) {
            $.ajax({
                url: "/homeworkSend/" + homeworkNo + "/" + studentNo + "/" + sg_no + "/",
                method: "GET",
                success: function(data) {
                    console.log(data);
					var count = 0;
					count++;
					if(count == 1) {
						alert("숙제 전송 성공")
					}
				}
            });
        });
    });
});

// 오른쪽 체크된 애들 user_no
$(document).on("change", ".transfer__tbody__content2.right input[type='checkbox']", function() {
    if ($(this).is(":checked")) {
        var user_no = $(this).data("user_no");
        console.log("체크된 오른쪽 user_no:", user_no);

		        
    }
});


// 내 학습그룹 불러오기
$("#myGroup").click(function() {
    var userNo = $("#user_no").val();
    $.ajax({
        url: "/mygroup/" + userNo,
        method: "GET",
        success: function(data) {
            var tbodyElement = $(".transfer__tbody2.left"); // 학습자 정보를 넣을 tbody 요소

            // 기존에 있는 학습자 정보 삭제
            tbodyElement.empty();

            // 받아온 데이터를 반복 tbody에 추가
            data.forEach(function(user) {
                var newContent = $('<div class="transfer__tbody__content2"></div>'); // 새로운 컨텐츠 요소 생성

                // 체크박스 컬럼
                var checkboxColumn = $('<div class="transfer__tbody2__one"></div>');
                var checkboxInput = $('<input type="checkbox">');
                checkboxInput.data("sg_no", user.sg_no); // 그룹 번호 설정
                checkboxInput.on('change', function() {
                    if ($(this).is(':checked')) {
                        var sg_no = $(this).data("sg_no");
                        console.log("체크된 그룹번호:", sg_no);
                        // 여기서 필요한 작업 수행
                        $.ajax({
							url: "/mygroupguys/" + sg_no,
							method: "GET",
							success: function(data) {
								
								$(".transfer__tbody2.right").empty();
								
							    data.forEach(function(user) {
						        // 사용자 정보를 보여주는 HTML 요소 생성
						        var userInfo = $('<div class="transfer__tbody__content2 right"></div>');
						        var checkboxColumn = $('<div class="transfer__tbody2__one"></div>');
						        var checkboxInput = $('<input type="checkbox">').data("user_no", user.user_no);
						        checkboxColumn.append(checkboxInput);
						        userInfo.append(checkboxColumn);
						        userInfo.append('<div class="transfer__tbody2__two">' + user.user_name + '</div>');
						        userInfo.append('<div class="transfer__tbody2__three">' + user.user_phone + '</div>');
						        userInfo.append('<div class="transfer__tbody2__four">' + user.sg_level + '레벨</div>');
						
						        // 생성한 요소를 HTML에 추가
						        $('.transfer__tbody2.right').append(userInfo);
						        
						    	});
								
							},
							error: function(status, err) {
								console.log(status, err, "에러남");
            					alert("에러");
							}
						})
                        
                    }
                });
                checkboxColumn.append(checkboxInput);

                // 그룹명
                var nameColumn = $('<div class="transfer__tbody2__two">' +  user.sg_name + '</div>').text(user.name);

                // 현재 그룹 인원
                var contactColumn = $('<div class="transfer__tbody2__three">' + user.sg_capa + '명' + '</div>').text(user.contact);

                // 현재 레벨 컬럼
                var levelColumn = $('<div class="transfer__tbody2__four">' + user.sg_level + '레벨' +'</div>').text(user.level);

                // 컨텐츠에 컬럼들 추가
                newContent.append(checkboxColumn, nameColumn, contactColumn, levelColumn);

                // tbody에 컨텐츠 추가
                tbodyElement.append(newContent);

            });
        },
        error: function(status, err) {
            console.log(status, err, "에러남");
            alert("학습그룹을 불러오는 도중에 에러가 발생했습니다");
        }
    });
});





