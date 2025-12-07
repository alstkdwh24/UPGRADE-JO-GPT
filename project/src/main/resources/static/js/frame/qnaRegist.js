document.getElementById("qna__title").oninput = function() {
  // 입력된 값을 가져와서 output 요소에 출력
  var qna__title = document.getElementById("qna__title").value;
  document.getElementById("board__title").innerHTML = qna__title;
};


document.getElementById("item__select").onchange = function() {
  // 선택된 값을 가져와서 output 요소에 출력
  var selectedValue = document.getElementById("item__select").value;

  if(selectedValue == 1){
    document.getElementById("board__category").innerHTML = "회원정보 - ";
  }else if( selectedValue == 2){
    document.getElementById("board__category").innerHTML = "상품구매 - ";
  }else if( selectedValue == 3){
    document.getElementById("board__category").innerHTML = "사이트이용 - ";
  }else if(selectedValue == 4){
    document.getElementById("board__category").innerHTML = "기타 - ";
  }else{
    document.getElementById("board__category").innerHTML = " ";
  }
  
};



document.addEventListener("DOMContentLoaded", function() {
  // 체크박스 요소들과 출력을 표시할 div 요소 가져오기
  var comment__permit = document.getElementById("comment__permit");
  var comment__reject = document.getElementById("comment__reject");
  var outputDiv = document.getElementById("board__date");


  comment__permit.addEventListener('change', function() {
    if (comment__permit.checked) {
      comment__reject.checked = false;

      if(comment__permit.value == 1){
        document.getElementById("board__date").innerHTML = "[알림 허용]";
      }else{
        document.getElementById("board__date").innerHTML = "[알림 거절]";
      }

    }
  });

  comment__reject.addEventListener('change', function() {
    if (comment__reject.checked) {
      comment__permit.checked = false;

      if(comment__reject.value == 2){
        document.getElementById("board__date").innerHTML = "[알림 거절]";
      }else{
        document.getElementById("board__date").innerHTML = "[알림 허용]";
      }

    }
  });

});


document.getElementById("notice__textArea").oninput = function() {
  // 입력된 값을 가져와서 output 요소에 출력
  var notice__textArea = document.getElementById("notice__textArea").value;
  document.getElementById("board__textArea").innerHTML = notice__textArea;
};




var fileInput = document.getElementById("fileInput");

// 파일 입력(input file)의 값이 변경될 때마다 실행되는 함수
fileInput.addEventListener("change", function() {
    // 선택된 파일 가져오기
    var file = fileInput.files[0];

    // 파일이 선택되었는지 확인
    if (file) {
        // 파일의 이름을 화면에 표시
        document.getElementById("board__file").innerHTML =  file.name;
    } else {
        // 파일이 선택되지 않았을 경우 메시지 표시
        document.getElementById("board__file").innerHTML = "파일을 선택해주세요.";
    }
});