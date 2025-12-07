//내 학습그룹 불러오기
$("#myGroup").click(function() {
	var userNo = $("#user_no").val();
	
	// 성공했습니다 간지나게 하기
	const cartMessage = document.getElementById('cartMessage');
	const inCartMessage = document.getElementById('inCartMessage'); 	
	
	inCartMessage.style.display = "block";
	
	              clearTimeout(inCartMessage.timeoutId);
					cartMessage.classList.add('visible');
					cartMessage.style.opacity = '1';
					cartMessage.timeoutId = setTimeout(function() {
						cartMessage.style.opacity = '0'; 
					}, 2000)
	
	$.ajax({
		url: "/mygroup/" + userNo,
		method: "GET",
		success: function(data) {
			var selectElement = $("#selectGroup")
			selectElement.empty();
			
			data.forEach(function(group) {
				selectElement.append($('<option>', {
		            value: group.sg_no, // 그룹 번호
		            text: group.sg_name // 그룹 이름
				}));
			})
		},
		error: function(status, err) {
			console.log(status, err, "에러남")
			alert("에러")
		}
	})
})


/* 조회하기 눌렀을 때, 값들 */
$("#searchGroup").click(function() {
    var SG_NO = $("#selectGroup").val();
    console.log("조회하기 눌렀을 때 그룹번호: ", SG_NO);
    
    $.ajax({
        url: "/groupDetail/" + SG_NO,
        method: "GET",
        success: function(data) {
            console.log(data);
            // 그룹 정보 채우기
	        var imgSrc = "/display/" + data.filepath + "/" + data.uuid + "/" + data.filename;
	        $(".grl__bbt__zero img").attr("src", imgSrc);
            $(".grl__bbt__one").text(data.groupdetail.game_title);
            $(".grl__bbt__two").text(data.groupdetail.game_target_level);
            $(".grl__bbt__three").text("Y");
            $(".grl__bbt__four").text(data.groupdetail.sg_capa);
            $(".grl__bbt__five").text(data.groupdetail.sg_grouplimit);

            // 승인 요청 내역 채우기
            $(".grl__bbb__tbody").empty(); // 이전 데이터 삭제

            data.boys.forEach(function(boy, index) {
                var $row = $('<div class="grl__bbtb__wrap">');
                $row.append('<div class="grl_bttbb__one">' + (index + 1) + '</div>');
                $row.append('<div class="grl_bttbb__two">' + boy.user_name + '</div>');
                $row.append('<div class="grl_bttbb__three">' + boy.user_phone + '</div>');
                $row.append('<div class="grl_bttbb__four">' + boy.user_regdate + '</div>');
                $row.append('<div class="grl_bttbb__five"><input type="checkbox" class="checkbox1"></div>');
				$row.append('<div class="grl_bttbb__six"><input type="hidden" class="hiddenNo" value="' + boy.user_no + '"></div>');

                
                $row.find('.checkbox1').change(function() {
			        if($(this).is(':checked')) {
			            // 체크되었을 때의 동작
			            const checkedValue = $(this).closest('.grl__bbtb__wrap').find('.hiddenNo').val();
			            console.log("선택된 사용자: " + checkedValue);
			        } else {
			            console.log("체크 해제됨")
			        }
			        
    			});

                $(".grl__bbb__tbody").append($row);
            });
        },
        
		error: function(error) {
			alert("에러 발생", error);
		}

    });
});

// 승인버튼 눌렀을 때
$("#approve").click(function() {
	// 그룹번호 가져오기
	var sg_no = $("#selectGroup").val();
	
    // checkbox 상태 확인
    var checkBox = $(".grl__bbb__tbody input[type='checkbox']:checked");
    
    checkBox.each(function() {
		var checked = $(this).closest(".grl__bbtb__wrap").find(".hiddenNo").val();
		
		var SG_NO = $("#selectGroup").val();
		$.ajax({
			url: "/approve/" + checked + "/" + sg_no,
			method: "GET",
			success: function(data) {
				// 성공했을 때, 해당 그룹의 승인인원을 + 1씩 시켜주기
				$.ajax({
					url: "/groupcapa/" + SG_NO,
					method: "GET",
					success: function(data) {
						
						// 승인 후에 재조회
					    $.ajax({
					        url: "/groupDetail/" + SG_NO,
					        method: "GET",
					        success: function(data) {
					            console.log(data);
					
					            // 그룹 정보 채우기
					            $(".grl__bbt__one").text(data.groupdetail.game_title);
					            $(".grl__bbt__two").text(data.groupdetail.game_target_level);
					            $(".grl__bbt__three").text("Y");
					            $(".grl__bbt__four").text(data.groupdetail.sg_capa);
					            $(".grl__bbt__five").text(data.groupdetail.sg_grouplimit);
					
					            // 승인 요청 내역 채우기
					            $(".grl__bbb__tbody").empty(); // 이전 데이터 삭제
					
					            data.boys.forEach(function(boy, index) {
					                var $row = $('<div class="grl__bbtb__wrap">');
					                $row.append('<div class="grl_bttbb__one">' + (index + 1) + '</div>');
					                $row.append('<div class="grl_bttbb__two">' + boy.user_name + '</div>');
					                $row.append('<div class="grl_bttbb__three">' + boy.user_phone + '</div>');
					                $row.append('<div class="grl_bttbb__four">' + boy.user_regdate + '</div>');
					                $row.append('<div class="grl_bttbb__five"><input type="checkbox" class="checkbox1"></div>');
									$row.append('<div class="grl_bttbb__six"><input type="hidden" class="hiddenNo" value="' + boy.user_no + '"></div>');
					
					                
					                $row.find('.checkbox1').change(function() {
								        if($(this).is(':checked')) {
								            // 체크되었을 때의 동작
								            const checkedValue = $(this).closest('.grl__bbtb__wrap').find('.hiddenNo').val();
								            console.log("선택된 사용자: " + checkedValue);
								        } else {
								            console.log("체크 해제됨")
								        }
								        
					    			});
					
					                $(".grl__bbb__tbody").append($row);
					            });
					        },
					        
							error: function(error) {
								alert("에러 발생", error);
							}
					
					    });
						
					},
					error: function(error) {
						alert("승인인원 + 1 오류", error)
					}
				})
				
			},
			error: function(error) {
				alert("에러 발생", error);
			}
		})
		
		
	})
    
    console.log(checkBox.length);
});

// 거절버튼 눌렀을 때
$("#reject").click(function() {
	// 그룹번호 가져오기
	var sg_no = $("#selectGroup").val();
    
    // checkbox 상태 확인
    var checkBox = $(".grl__bbb__tbody input[type='checkbox']:checked");
    
    checkBox.each(function() {
		var checked = $(this).closest(".grl__bbtb__wrap").find(".hiddenNo").val();
		
		var SG_NO = $("#selectGroup").val();
		$.ajax({
			url: "/reject/" + checked + "/" + sg_no,
			method: "GET",
			success: function(data) {
				console.log(data + "여기까진 성공했음")
				
						
				// 거절 후에 재조회
			    $.ajax({
			        url: "/groupDetail/" + SG_NO,
			        method: "GET",
			        success: function(data) {
			            console.log(data);
			
			            // 그룹 정보 채우기
			            $(".grl__bbt__one").text(data.groupdetail.game_title);
			            $(".grl__bbt__two").text(data.groupdetail.game_target_level);
			            $(".grl__bbt__three").text("일단 대기");
			            $(".grl__bbt__four").text(data.groupdetail.sg_capa);
			            $(".grl__bbt__five").text(data.groupdetail.sg_grouplimit);
			
			            // 승인 요청 내역 채우기
			            $(".grl__bbb__tbody").empty(); // 이전 데이터 삭제
						
			            data.boys.forEach(function(boy, index) {
			                var $row = $('<div class="grl__bbtb__wrap">');
			                $row.append('<div class="grl_bttbb__one">' + (index + 1) + '</div>');
			                $row.append('<div class="grl_bttbb__two">' + boy.user_name + '</div>');
			                $row.append('<div class="grl_bttbb__three">' + boy.user_phone + '</div>');
			                $row.append('<div class="grl_bttbb__four">' + boy.user_regdate + '</div>');
			                $row.append('<div class="grl_bttbb__five"><input type="checkbox" class="checkbox1"></div>');
							$row.append('<div class="grl_bttbb__six"><input type="hidden" class="hiddenNo" value="' + boy.user_no + '"></div>');
			
			                
			                $row.find('.checkbox1').change(function() {
						        if($(this).is(':checked')) {
						            // 체크되었을 때의 동작
						            const checkedValue = $(this).closest('.grl__bbtb__wrap').find('.hiddenNo').val();
						            console.log("선택된 사용자: " + checkedValue);
						        } else {
						            console.log("체크 해제됨")
						        }
						        
			    			});
			
			                $(".grl__bbb__tbody").append($row);
			            });
			        },
			        
					error: function(error) {
						alert("에여기냐?러 발생", error);
					}
			
			    });
						
				
			},
			error: function(error) {
				alert("에러 끝임?발생", error);
			}
		})
		
		
	})
    
    console.log(checkBox.length);
});
	
	

    




