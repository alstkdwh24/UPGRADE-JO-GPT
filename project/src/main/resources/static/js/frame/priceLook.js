
var sum = document.querySelectorAll(".pb__tbody__four");
var allSum = 0;

for(var i=0; i<sum.length; i++){
	
	var price = parseFloat(sum[i].getAttribute("data-value"));
	if (!isNaN(price)) {
        allSum += price;
    }
}

document.getElementById("total__all__price").value = allSum + " 원";


var count = document.querySelectorAll(".pb__tbody__three");
var allCount = 0;

for(var i=0; i<count.length; i++){
	
	var value = parseFloat(count[i].getAttribute("data-value"));
	
	if(!isNaN(value)){
		allCount += value;
	}
	
}

document.getElementById("total__all__count").value = allCount + " 건";






var priceModal = document.getElementById("priceModal");

// Get the button that opens the modal
var priceModalBtn = document.querySelectorAll("#priceModalBtn");

// Get the <span> element that closes the modal
var priceClose = document.getElementsByClassName("priceClose")[0];

var purchase_date = document.getElementById("purchase_date");

for(var i=0; i<priceModalBtn.length; i++){
	priceModalBtn[i].onclick = function(){
		event.preventDefault();

		priceModal.style.display = "block";
	}
}


// When the user clicks on <span> (x), close the modal
priceClose.onclick = function() {
  priceModal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == priceModal) {
    priceModal.style.display = "none";
  }
}


document.querySelectorAll("#priceModalBtn").forEach(button => {
  button.addEventListener("click", function() {
      var purchase_date = this.value; // 클릭된 버튼의 값 가져오기
      fetchUserData(purchase_date); // 서버로 값 전송
  });
});


function fetchUserData(purchase_date) {
  fetch("/priceLookDetail?purchase_date=" + purchase_date)
      .then(response => response.json())
      .then(data => {
				document.getElementById("pm__tbody").innerHTML = '';
				data.forEach((item, index) => {

					var container = document.createElement("div");
					container.classList.add("pm__tbody__container");
					
					var one = document.createElement("div");
					one.classList.add("pm__tbody__one");
					one.id = "pm__tbody__one_" + (index + 1);
					one.innerText = index + 1; 

					var two = document.createElement("div");
					two.classList.add("pm__tbody__two");
					two.innerText = item.user_name;

					var three = document.createElement("div");
					three.classList.add("pm__tbody__three");
					three.innerText = item.game_title;

					var four = document.createElement("div");
					four.classList.add("pm__tbody__four");
					four.innerText = item.game_discount_price;

					var five = document.createElement("div");
					five.classList.add("pm__tbody__five");
					five.innerText = item.purchase_date;

					container.appendChild(one);
					container.appendChild(two);
					container.appendChild(three);
					container.appendChild(four);
					container.appendChild(five);

					document.getElementById("pm__tbody").appendChild(container);
					
				});

		})
		
      .catch(error => console.error("Error:", error));
}

