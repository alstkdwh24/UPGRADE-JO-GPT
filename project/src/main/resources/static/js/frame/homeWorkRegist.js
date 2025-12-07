


document.getElementById("homework__learn__title").oninput = function() {
  // 입력된 값을 가져와서 output 요소에 출력
  var homework__learn__title = document.getElementById("homework__learn__title").value;
  document.getElementById("homework__post__title").innerHTML = homework__learn__title;
};

document.getElementById("homework__learn__content").oninput = function() {
  // 입력된 값을 가져와서 output 요소에 출력
  var homework__learn__content = document.getElementById("homework__learn__content").value;
  document.getElementById("homework__post__info").innerHTML = homework__learn__content;
};


var selectElement = document.getElementById("homework__type__select");

// select 요소의 change 이벤트에 대한 리스너를 추가합니다.
selectElement.addEventListener("change", function() {
    // 선택된 옵션의 값(value)을 가져옵니다.
    var selectedOption = selectElement.options[selectElement.selectedIndex].value;
    
    // 선택된 값이 "none"이 아닌 경우에만 실행합니다.
    if (selectedOption !== "none") {
        // 결과를 보여줄 div 요소를 참조합니다.
        var resultDiv = document.getElementById("homework__post__type");
        
        // 선택된 레벨을 표시합니다.
        resultDiv.textContent = selectedOption + "레벨";
    } else {
        // 선택된 값이 "none"일 경우에는 결과를 지웁니다.
        resultDiv.textContent = "";
    }
});


var selectElement2 = document.getElementById("homework__group__select");

// select 요소의 change 이벤트에 대한 리스너를 추가합니다.
selectElement2.addEventListener("change", function() {
    // 선택된 옵션의 값(value)을 가져옵니다.
    var selectedOption2 = selectElement2.options[selectElement2.selectedIndex].value;
    
    // 선택된 값이 "none"이 아닌 경우에만 실행합니다.
    if (selectedOption2 !== "none") {
        // 결과를 보여줄 div 요소를 참조합니다.
        var resultDiv2 = document.getElementById("homework__post__level");
        
        // 선택된 레벨을 표시합니다.
        resultDiv2.textContent = selectedOption2;
    } else {
        // 선택된 값이 "none"일 경우에는 결과를 지웁니다.
        resultDiv2.textContent = "";
    }
});




document.getElementById("homework__startDate").addEventListener("change", calculateDays);
document.getElementById("homework__endDate").addEventListener("change", calculateDays);

function calculateDays() {
    var startDate = new Date(document.getElementById("homework__startDate").value);
    var endDate = new Date(document.getElementById("homework__endDate").value);
    var resultDiv = document.getElementById("homework__Day");
    var homework__post__count = document.getElementById("homework__post__count");

    // 시작 날짜가 없거나 마지막 날짜가 없을 경우 일 수 계산하지 않음
    if (!isNaN(startDate.getTime()) && !isNaN(endDate.getTime())) {
        // 일 단위로 변환
        var differenceInTime = endDate.getTime() - startDate.getTime();
        // 일 수로 변환
        var differenceInDays = differenceInTime / (1000 * 3600 * 24);

        resultDiv.innerHTML = differenceInDays + "일";
        homework__post__count.innerHTML = "숙제 마감 " + differenceInDays + " 일 전"; 
    } else {
        resultDiv.innerHTML = "";
        homework__post__count.innerHTML = "";
    }
}

// 내 학습그룹 불러오기
$("#myGroup").click(function() {
	var userNo = $("#user_no").val();

	$.ajax({
		url: "/mygroup/" + userNo,
		method: "GET",
		success: function(data) {
			var selectElement = $("#homework__group__select")
			selectElement.empty();

			data.forEach(function(group) {
				console.log(group)
				console.log("==============")
				selectElement.append($('<option>', {
		            value: group.sg_no, // 그룹 번호
		            text: group.sg_name // 그룹 이름
		        }));
			})
			alert("내 학습그룹 불러오기 성공");
		},
		error: function(status, err) {
			console.log(status, err, "에러남")
		}

	})
	console.log(userNo);

})

$("#homework__group__select").change(function() {
    var selectedValue = $("#homework__group__select").val();

    console.log(selectedValue); // 선택된 학습 그룹의 값 출력


    // 나머지 코드...
});

