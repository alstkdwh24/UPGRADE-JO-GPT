// 회원가입 상세 주소
function sample4_execDaumPostcode() {
  new daum.Postcode({
      oncomplete: function(data) {
          // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

          // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
          // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
          var roadAddr = data.roadAddress; // 도로명 주소 변수
          var extraRoadAddr = ''; // 참고 항목 변수

          // 법정동명이 있을 경우 추가한다. (법정리는 제외)
          // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
          if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
              extraRoadAddr += data.bname;
          }
          // 건물명이 있고, 공동주택일 경우 추가한다.
          if(data.buildingName !== '' && data.apartment === 'Y'){
            extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
          }
          // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
          if(extraRoadAddr !== ''){
              extraRoadAddr = ' (' + extraRoadAddr + ')';
          }

          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          document.getElementById('sample4_postcode').value = data.zonecode;
          document.getElementById("sample4_roadAddress").value = roadAddr;
          document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
          
          // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
          if(roadAddr !== ''){
              document.getElementById("sample4_extraAddress").value = extraRoadAddr;
          } else {
              document.getElementById("sample4_extraAddress").value = '';
          }

          var guideTextBox = document.getElementById("guide");
          // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
          if(data.autoRoadAddress) {
              var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
              guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
              guideTextBox.style.display = 'block';

          } else if(data.autoJibunAddress) {
              var expJibunAddr = data.autoJibunAddress;
              guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
              guideTextBox.style.display = 'block';
          } else {
              guideTextBox.innerHTML = '';
              guideTextBox.style.display = 'none';
          }
      }
  }).open();
}

	
	// id 중복 체크하기
  	var checkCount = 0;
      $(document).ready(function() {
    	  
      	  // 폼을 넘길 때, 일단 막아버림
    	  $(".join__content").submit(function(event) {
    		  // id 체크를 하지 않았을 경우, id체크 진행하라고 focus시킴
        	  if(checkCount <= 0) {
        		  event.preventDefault();
                  $(".join__item__button").click(function() {
                      $('#user_id').focus();
                      $("#id_check_result").html("id 체크를 진행해주세요");
                  });
        	  } else {
        		  $(".join__content").submit();
        	  }
        	   
    	  }); 
    	  
    	  
      	// id check하기
        $("#id_check").click(function() {

        	var userId = $("#user_id").val();
        
          $.ajax({
              url: "/check/" + userId,
              type: "get",
              success: function(data) {
            	  
            	// 중복데이터가 있을 경우, 혹은 아이디 검증을 하지 않은 경우
                if(data >= 1) {
                  // 버튼 비활성화(눌러도 동작 X)
                  $('#user_id').focus();
                  $("#id_check_result").html("중복된 id가 존재합니다. 다른 id를 입력해주세요");
                  $('.join__item__button button').prop('disabled', true);
                } else {
                  $("#id_check_result").html("현재 id로 가입 가능합니다.")
                  checkCount++;
                  // 버튼 활성화
                  $('.join__item__button button').prop('disabled', false);
                }
              },
              error: function(status, error) {
                $("#id_check_result").html("아이디를 입력해주세요.")
                console.log(status, error)
              }
          });
        	
        });
      	
      	
      	// 이메일, sns체크 중 하나만 선택하게
        $('input[name="user_sns_check"]').click(function() {
            $('input[name="user_email_check"]').prop('checked', false);
        });

        $('input[name="user_email_check"]').click(function() {
            $('input[name="user_sns_check"]').prop('checked', false);
        });
      	
      	
    });
      