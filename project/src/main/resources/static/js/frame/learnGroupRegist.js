document.addEventListener("DOMContentLoaded", function() {
  var startDateInput = document.getElementById("lg-startDate");
  var endDateInput = document.getElementById("lg-endDate");
  var resultDiv = document.getElementById("lg__month");

  startDateInput.addEventListener("change", calculateDifference);
  endDateInput.addEventListener("change", calculateDifference);

  function calculateDifference() {
      if (startDateInput.value && endDateInput.value) {
          var startDate = new Date(startDateInput.value);
          var endDate = new Date(endDateInput.value);

          var differenceInTime = endDate.getTime() - startDate.getTime();
          var differenceInDays = Math.floor(differenceInTime / (1000 * 3600 * 24));

          var months = Math.floor(differenceInDays / 30);
          var daysLeft = differenceInDays % 30;

          resultDiv.textContent = months + "개월 " + daysLeft + "일";
          document.getElementById("pre__head__date").innerHTML = months + "개월 " + daysLeft + "일";
      } else {
          resultDiv.textContent = "";
      }
  }
});


document.getElementById("learn__group__title").oninput = function() {
  var learn__group__title = document.getElementById("learn__group__title").value;
  document.getElementById("pre__title").innerHTML = learn__group__title;
};

document.getElementById("learn__group__limit").oninput = function() {
  var learn__group__limit = document.getElementById("learn__group__limit").value;
  document.getElementById("pre__head__limit").innerHTML = learn__group__limit + " 명";
};

document.getElementById("learn__group__level").oninput = function() {
  var learn__group__level = document.getElementById("learn__group__level").value;
  document.getElementById("pre__head__level").innerHTML = learn__group__level + " 레벨";
};


// 모달창(팝업으로 내 구매이력 가져오기)
var button = document.querySelector('.lg__tbody__one button');

  $(document).ready(function() {
    // 버튼 클릭 이벤트 핸들러 등록
    $('.lg__tbody__one button').click(function() {
      // 모달 창 열기
      $('#myModal').css('display', 'block');
    });

    // 모달 닫기
    $('.close').click(function() {
      // 모달 창 닫기
      $('#myModal').css('display', 'none');
    });

    // 모달 외부 클릭 시 닫기
    $(window).click(function(event) {
      if (event.target == $('#myModal')[0]) {
        $('#myModal').css('display', 'none');
      }
    });
  });
  
  
    $('input[type="radio"]').change(function() {
        // 선택된 라디오 버튼의 값을 가져옴
        var selectedValue = $('input[type="radio"]:checked').val();
        //console.log(selectedValue); // 가져온 값 확인 (콘솔에 출력)
        
        
		var purchaseVOs = [];
		$(".listImg").val().match(/PurchaseVO\((.*?)\)/g).forEach(function(item) {
		    var attributes = item.match(/\(([^)]+)\)/)[1].split(", ");
		    var purchaseVO = {};
		    attributes.forEach(function(attribute) {
		        var keyValue = attribute.split("=");
		        purchaseVO[keyValue[0].trim()] = keyValue[1].trim();
		    });
		    purchaseVOs.push(purchaseVO);
		});
		
		
		// 해당하는 game_no를 가진 데이터를 찾아서 HTML에 넣기
	    var selectedList = null;
	    purchaseVOs.forEach(function(item) {
	        if (item.game_no == selectedValue) {
	            selectedList = item;
	            return false; // forEach 루프를 종료함
	        }
	    });
	    
	    // 선택된 list가 있을 경우
	    if (selectedList !== null) {
	        // 선택된 list의 값을 가져와서 HTML에 넣기
	        $('.lg__tbody__two img').attr('src', '/display/' + selectedList.filepath + '/' + selectedList.uuid + '/' + selectedList.filename);
	        $('.lg__tbody__three').text(selectedList.game_title);
	        
            // purchase_date 값을 변경하여 출력
		    var purchaseDate = selectedList.purchase_date;
		    var dateObject = new Date(purchaseDate);
		    dateObject.setMonth(dateObject.getMonth()); // 한 달 뒤로 설정
		    var oldDate = dateObject.toISOString().slice(0, 10);
		    dateObject.setMonth(dateObject.getMonth() + 12); // 1년 뒤로 설정
		    var newDate = dateObject.toISOString().slice(0, 10);
	        
	        $('.lg__tbody__four').text(oldDate + ' ~ ' + newDate);
	        
            // 숨은 입력란 추가
		    $('<input>').attr({
		        type: 'hidden',
		        id: 'selected_game_no',
		        name: 'game_no',
		        value: selectedList.game_no
		    }).appendTo('body');
		    
        	$("#selected_gameNo").val(selectedList.game_no);
	    }
	    
        // ----------------------------가져오기 2번째부터 안 돼서 이거 넣어줘야함
		 // 모달창(팝업으로 내 구매이력 가져오기)
		  $(document).ready(function() {
		    // 버튼 클릭 이벤트 핸들러 등록
		    $('.lg__tbody__one button').click(function() {
		      // 모달 창 열기
		      $('#myModal').css('display', 'block');
		    });
		
		    // 모달 닫기
		    $('.close').click(function() {
		      // 모달 창 닫기
		      $('#myModal').css('display', 'none');
		    });
		
		    // 모달 외부 클릭 시 닫기
		    $(window).click(function(event) {
		      if (event.target == $('#myModal')[0]) {
		        $('#myModal').css('display', 'none');
		      }
		    });
		  });
            
            
    });

$('#learn__group__limit').on('input', function() {
    // 입력된 값 가져오기
    var inputValue = $(this).val();
    
    // .lg__tbody__five 요소에 입력된 값 채우기
    $('.lg__tbody__five').text(inputValue + '명');
});

