
const approvedMessage = document.getElementById('approvedMessage'); 
const reapplyMessage = document.getElementById('reapplyMessage'); 
const processMessage = document.getElementById('processMessage'); 
const rejectMessage = document.getElementById('rejectMessage'); 
const applyMessage = document.getElementById("applyMessage");
const groupApplyBtn = document.getElementById("groupApply");
const hiddenSg_No = document.getElementById("hiddenSg_No");

const reapply = document.querySelector(".re-apply");
const noapply = $(".no-apply");

	const sg_no = $("#hiddenSg_No").val();
    const user_no = $("#hiddenUser_No").val();
    const sg_level = $("#hiddenSg_Level").val();

	console.log(sg_no);
	console.log(user_no);
	console.log(sg_level);
	
groupApplyBtn.onclick = function() {

    $.get('/reject/group', {user_no: user_no, sg_no: sg_no})
     .done(function(rejectData) {
		 console.log("reject/group 실행됨");
		 if(rejectData === true) {
			 displayRejectMessage(rejectMessage);
		 } else {
			 $.get('/check/group', {user_no: user_no, sg_no: sg_no})
			  .done(function(checkData) {
			 	console.log("/check/group 실행됨");
	             if(checkData === true) { 
	                 displayMessage(processMessage); 
	             } else {
	                 $.get('/apply/group', {user_no: user_no, sg_no: sg_no, sg_level: sg_level})
	                  .done(function(data) {
	                      displayMessage(applyMessage); 
	                  })
	                  .fail(function(error) {
	                      console.log("가입 실패");
	                  });
	             } 
	         })
	         .fail(function(error) {
	             console.log("중복체크 실패");
	         });
		 }			 
	 });
}
    
    
function re_apply() {
	$.get("/reapply/group", {user_no: user_no, sg_no: sg_no})
	 .done(function(){
		 displayRejectMessage(reapplyMessage);
	 })
	 .fail(function(error){
		 console.log("reapply 실패")
	 })
}
	 
function no_apply() {
	history.back();
}   

function displayMessage(element) {
    element.style.display = "block";
    clearTimeout(element.timeoutId);
    element.classList.add('visible');
    element.style.opacity = '1';
    element.timeoutId = setTimeout(function() {
        element.style.opacity = '0';
        setTimeout(() => { element.style.display = "none"; }, 300); // Adjust timing as necessary
    }, 2000); 
}
function displayRejectMessage(element) {
    element.style.display = "block";
    clearTimeout(element.timeoutId);
    element.classList.add('visible');
    element.style.opacity = '1';
    element.timeoutId = setTimeout(function() {
        element.style.opacity = '0';
        setTimeout(() => { element.style.display = "none"; }, 300); // Adjust timing as necessary
    }, 6000); 
}
	




