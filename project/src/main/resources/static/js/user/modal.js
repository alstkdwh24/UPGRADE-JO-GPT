
var modal = document.getElementById("myModal");
var gameContentInfo__wrap__mainTitle = modal.querySelector(".gameContentInfo__wrap__mainTitle"); //제목
var default__info = modal.querySelector(".default__info > span"); //난이도
var default__price = modal.querySelector("#default__price > span"); //정가
var default__information = modal.querySelector(".default__information > span");
var default__price12=modal.querySelector("#default__price12 > span");
var default__price2=modal.querySelector("#default__price2 > span");
let game_no=modal.querySelector("#game_no");
// Get the button that opens the modal
var btn = document.querySelectorAll(".myBtnOpenModal");
let user_no=document.getElementById("user_no").value;

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("material-symbols-outlined")[0];

console.log(user_no);
// When the user clicks the button, open the modal 
for(var i=0; i<btn.length; i++){

  btn[i].addEventListener("click", function(){
    modal.style.display = "block";
    
    
   	//console.log(event.currentTarget)

	var tag = event.currentTarget
	
	//console.log(gameContentInfo__wrap__mainTitle.innerHTML)
	//console.log(tag.querySelector(".game_title"))
	
	gameContentInfo__wrap__mainTitle.innerHTML = tag.querySelector(".game_title").innerHTML;
	default__info.innerHTML = "난이도 : " + tag.querySelector(".game_target_level").innerHTML;
	default__price.innerHTML = "구매 가격 : " + tag.querySelector(".game_price").innerHTML;
	default__price12.innerHTML= "  할인가 : "+tag.querySelector(".game_discount_price").value;
	default__price2.innerHTML="학습기간: " + tag.querySelector(".game_sub_regDate").value + "~" + tag.querySelector(".game_sub_endDate").value

      game_no=tag.querySelector(".game_no").value;
  });

}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

let button=document.getElementById("button");
button.onclick=function (){
    $.ajax({
        type:"Post",
        contentType:"application/json",
        url:"/GameResist",
        data:JSON.stringify({
          game_no:parseInt(game_no, 10),
            user_no:parseInt(user_no,10)
        }),

        success:function () {
            console.log("성공하였습니다.")
        }

    })
}