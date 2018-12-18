$("div[data-showt] input[type='radio']").click(function(){
	var parent = $(this).parents("div[data-showt]");
	if( $(this).attr("data-block") != undefined ){
		$.each( $(parent).find("input[type!='radio']"), function(index, value){
			$(value).prop("disabled", false);
		});
	}else{
		$.each( $(parent).find("input[type!='radio']"), function(index, value){
			$(value).val("");
			$(value).prop("disabled", true);
		});
	}
});
$("#strvence").click(function(){
	if( $(this).is(":checked") )
		onBlock( ["#strvenceini","#strvencefin"], false );
	else
		onBlock( ["#strvenceini","#strvencefin"], true );
});

$("#strtiempo").click(function(){
	if( $(this).is(":checked") )
		onBlock( ["#strshowtimeonlymin","#strshowtimeonlyhor"], false );
	else
		onBlock( ["#strshowtimeonlymin","#strshowtimeonlyhor"], true );
});
$("input[data-presscant]").keypress(function(){
	if( $(this).val().length > ($(this).attr("data-presscant") - 1) ) $(this).val("");
});