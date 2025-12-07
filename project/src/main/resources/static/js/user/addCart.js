const toCart = document.getElementById("toCart"); /*장바구니로 이동*/

const content__purchase = document.getElementById('content__purchase');

/*장바구니 추가시 나타나는 메세지*/
const cartMessages = document.querySelectorAll('#cartMessage');
const paidMessages = document.querySelectorAll('#paidMessage');
const inCartMessages = document.querySelectorAll('#inCartMessage');

const content__baskets = document.querySelectorAll("#myBtn .game__item__wrap myBtnOpenModal");

for (let i = 0; i < content__baskets.length; i++) {
    content__baskets[i].onclick = function() {
        const user_no = $('#user_no').val();
        const game_no = this.closest('.tbody__item').querySelector('[name="game_no"]').value;

        console.log(user_no);
        console.log(game_no);
		
		$.get('/check/purchase', {user_no: user_no, game_no: game_no})
		 .done(function(paid) {
			 console.log("이미 결재한 내역 있음")
			 if(paid == false){
			  	paidMessages.forEach(function(paidMessage) {
                    paidMessage.style.display = "block";
                    clearTimeout(paidMessage.timeoutId);
                    paidMessage.classList.add('visible');
                    paidMessage.style.opacity = '1';
                    paidMessage.timeoutId = setTimeout(function() {
                        paidMessage.style.opacity = '0';
                    }, 2000);
                });
			 } else {
		        $.get('/check/cart', {user_no: user_no, game_no: game_no})
		            .done(function(checkData) {
		                console.log("장바구니 들어있는지 확인 여부 Y");
		                if (checkData == false) {
		                    inCartMessages.forEach(function(inCartMessage) {
                                inCartMessage.style.display = "block";
                                clearTimeout(inCartMessage.timeoutId);
                                inCartMessage.classList.add('visible');
                                inCartMessage.style.opacity = '1';
                                inCartMessage.timeoutId = setTimeout(function() {
                                    inCartMessage.style.opacity = '0';
                                }, 2000);
                            });
		                } else {
		                    $.get('/add/cart', {user_no: user_no, game_no: game_no})
		                        .done(function(data) {
		                            console.log("장바구니에 들어감");
		                            cartMessages.forEach(function(cartMessage) {
                                        cartMessage.style.display = "block";
                                        clearTimeout(cartMessage.timeoutId);
                                        cartMessage.classList.add('visible');
                                        cartMessage.style.opacity = '1';
                                        cartMessage.timeoutId = setTimeout(function() {
                                            cartMessage.style.opacity = '0';
                                        }, 2000);
                                    });
		                        })
		                        .fail(function(error) {
		                            console.log("추가 실패");
		                        });
		                }
		            })
		            .fail(function(error) {
		                console.log("Check 실패");
		            });
                }
            })
             .fail(function(error) {
		                console.log("Check 실패");
		   });
    }
}

