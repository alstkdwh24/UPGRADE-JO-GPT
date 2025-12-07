	  const user_no = $('.session_id').data('user-no');
	  const game_no = $(".game_no").data('game-no');
	  const total = $('#total_price').text();
	  console.log(total);
	  
	  $("#checkoutButton").click(function() {
		    window.location.href="/command/payment"
	  });
	  
	  $(".delete_btn").click(function(e) {
			$.get("/del/cart", {user_no: user_no, game_no: game_no})
			 .done(function(data){
				 console.log("successfully deleted")
				 e.target.parentElement.style.display="none";
				 window.location.href="/command/cart";
			 })
			 .fail(function(error){
				 console.log("fail to delete");
			 })
	  })
	  
	  var totalContent = $(".game_title").val() ;