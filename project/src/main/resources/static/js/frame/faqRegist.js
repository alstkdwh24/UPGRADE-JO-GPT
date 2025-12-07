const $drop = document.querySelector(".dropBox");
const $title = document.querySelector(".dropBox h5");

// 드래그한 파일 객체가 해당 영역에 놓였을 때
$drop.ondrop = (e) => {
  e.preventDefault();
  $drop.className = "dropBox";

  // 파일 리스트
  const files = [...e.dataTransfer?.files];

  $title.innerHTML = files.map(v => v.name).join("<br>");
}

// ondragover 이벤트가 없으면 onDrop 이벤트가 실핻되지 않습니다.
$drop.ondragover = (e) => {
  e.preventDefault();
}

// 드래그한 파일이 최초로 진입했을 때
$drop.ondragenter = (e) => {
  e.preventDefault();
 
  $drop.classList.add("active");
}

// 드래그한 파일이 영역을 벗어났을 때
$drop.ondragleave = (e) => {
  e.preventDefault();
  
  $drop.classList.remove("active");
}



document.getElementById("item__select").onchange = function() {
  // 선택된 값을 가져와서 output 요소에 출력
  var selectedValue = document.getElementById("item__select").value;

  if(selectedValue == 1){
    document.getElementById("board__category").innerHTML = "상품결제  ";
  }else if( selectedValue == 2){
    document.getElementById("board__category").innerHTML = "상품관리 / ";
  }else if( selectedValue == 3){
    document.getElementById("board__category").innerHTML = "학습그룹 / ";
  }else if(selectedValue == 4){
    document.getElementById("board__category").innerHTML = "기타 / ";
  }else{
    document.getElementById("board__category").innerHTML = " ";
  }
  
};


document.getElementById("faq__question").oninput = function() {
  // 입력된 값을 가져와서 output 요소에 출력
  var faq__question = document.getElementById("faq__question").value;
  document.getElementById("board__QtextArea").innerHTML = faq__question;
};

document.getElementById("faq__answer").oninput = function() {
  // 입력된 값을 가져와서 output 요소에 출력
  var faq__answer = document.getElementById("faq__answer").value;
  document.getElementById("board__AtextArea").innerHTML = faq__answer;
};