



document.querySelectorAll(".memberLookUp").forEach(button => {
  button.addEventListener("click", function() {
      var userNo = this.value; // 클릭된 버튼의 값 가져오기
      fetchUserData(userNo); // 서버로 값 전송
  });
});

function fetchUserData(userNo) {
  fetch("/admin/userSelect?user_no=" + userNo)
      .then(response => response.json())
      .then(data => {
          
          document.getElementById("userSleepNo").value = data.user_no;
          document.getElementById("item__first__name").value = data.user_name;
          document.getElementById("item__first__role").value = convertUserRole(data.user_role);
          document.getElementById("item__first__gender").value = convertUserGender(data.user_gender);
          document.getElementById("item__second__phone").value = data.user_phone;
          document.getElementById("item__second__email").value = data.user_email;
          document.getElementById("item__third__address").value = data.user_address;
          document.getElementById("item__third__birth").value = data.user_birth;
          document.getElementById("item__second__regDate").value = data.user_regDate;
          document.getElementById("item__second__regDate2").value = data.user_regDate;
      })
      .catch(error => console.error("Error:", error));
}

function convertUserRole(userRole) {
  
  if(userRole === '1' || userRole === 'ROLE_TEA'){
    return "교육자";
  }else if(userRole === '2' || userRole === 'ROLE_STU'){
    return "학생";
  }else if(userRole === '3' || userRole === 'ROLE_GEN'){
    return "일반";
  }
  return "";
}

function convertUserGender(userGender){

  if(userGender === 'w'){
    return "여성";
  }else{
    return "남성"
  }

}

function redirectToUserSleep() {
    // userSleepNo input 요소에서 값을 가져옴
    var userSleepNoValue = document.getElementById("userSleepNo").value;
    // 가져온 값이 비어있지 않은 경우에만 링크로 이동
    if (userSleepNoValue.trim() !== "") {
        // 링크의 href 속성을 설정하여 값 전달
        document.getElementById("userSleep").href = "/admin/userSleep?userSleepNo=" + userSleepNoValue;
    } else {
        // userSleepNo의 값이 비어있는 경우 사용자에게 알림 등을 표시할 수 있음
        alert("사용자 번호를 입력해주세요.");
  }
}
