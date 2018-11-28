$("#strmostraronly").click(function(){
	var parent = $(this).parents(".form-group")[0];
	if( $(parent).find("input[type!='radio']").length <= 0){
		$(parent).append(
				new Element("input",{
					type:"number",
					class:"form-control", 
					value:"1", 
					id:"strshowonly",
					min:"1",
					placeholder:"#"
				},[] )
		);
	}
});
$("#strmostrarall").click(function(){
	$( $(this).parents(".form-group")[0] ).find("input[type='number']")[0].remove();
});

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
	if( $(this).is(":checked") ){
		var parent = $(this).parents(".form-group")[0];
		$(parent).append(
				new Element("input",{
					type:"time",
					class:"form-control", 
					value:"1", 
					id:"strshowonly", 
					placeholder:"#"
				},[] )
		);
	}else{
		var parent = $(this).parents(".form-group")[0];
		$(parent).find("input[type!='checkbox']")[0].remove();
	}
});