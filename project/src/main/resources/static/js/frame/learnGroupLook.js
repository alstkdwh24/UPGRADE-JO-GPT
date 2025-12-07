document.addEventListener('DOMContentLoaded', function() {
    var buttons = document.querySelectorAll('.looks__wrap__footer button');
    
    buttons.forEach(function(button) {
        button.addEventListener('click', function() {
    
            var activeButton = document.querySelector('.looks__wrap__footer button.active');
            if (activeButton) {
                activeButton.classList.remove('active');
            }
            

            this.classList.add('active');
        });
    });
});