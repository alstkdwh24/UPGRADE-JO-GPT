

function changeActionAndSubmit(searchType){
	 var form = document.getElementById("question");
	 
	 form.action = "/command/qnaRegist";
	 form.submit();
}