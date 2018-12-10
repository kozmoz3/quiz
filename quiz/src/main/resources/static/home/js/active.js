(function ($) {
    'use strict';

    if ($.fn.owlCarousel) {
        $(".app_screenshots_slides").owlCarousel({
            items: 1,
            loop: true,
            autoplay: true,
            smartSpeed: 800,
            margin: 30,
            center: true,
            dots: true,
            responsive: {
                0: {
                    items: 1
                },
                480: {
                    items: 3
                },
                992: {
                    items: 5
                }
            }
        });
    }
    
    if ($.fn.scrollUp) {
        $.scrollUp({
            scrollSpeed: 1500,
            scrollText: '<i class="fa fa-angle-up"></i>'
        });
    }
    
    if ($.fn.onePageNav) {
        $('#nav').onePageNav({
            currentClass: 'active',
            scrollSpeed: 2000,
            easing: 'easeOutQuad'
        });
    }

    $('a[href="#"]').click(function ($) {
        $.preventDefault()
    });

    var $window = $(window);

    if ($window.width() > 767) {
        new WOW().init();
    }
    
    $window.on('scroll', function () {
        if ($window.scrollTop() > 48) {
            $('.header_area').addClass('sticky slideInDown');
        } else {
            $('.header_area').removeClass('sticky slideInDown');
        }
    });
    
    
    /*contact*/
    $("#send").click(function(){
    	var cantin = ["#name","#email","#message"];
    	var isEmptys = true;
    	$.each(cantin, function(index, value){
    		if( $.trim( $(value).val() ) == "" ){
    			$(value).css("border","1px solid red");
    			isEmptys = false;
    		}else
    			$(value).css("border","1px solid #ced4da");
    	});
    	if( isEmptys ){
    		if(sendMailTo({
    			name: $("#name").val(),
	    		email: $("#email").val(),
	    		message: $("#message").val()	
    		})){
    			getSuccessfulMessageWithText("Mensaje enviado correctamente");
    			$.each(cantin, function(index, value){ $(value).val(""); });
    		}
    	}
    });
    
    function sendMailTo( data ){	
    	var req = new XMLHttpRequest();	  
    	req.open( "post", "/sendcontact", false );
    	req.setRequestHeader('Content-type','application/json; charset=utf-8');
    	req.send( JSON.stringify(data) );
    	console.log(req);
    	if (req.status == 200){
    		return true;
    	}else{
    		getErrorMessage(getCodeStatus(req, ""));
    		return false;
    	}
    }

})(jQuery);
