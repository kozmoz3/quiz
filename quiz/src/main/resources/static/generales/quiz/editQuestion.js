var lista = document.getElementById("lst");
var btn = document.getElementById("more");
var countList = 0;
var countListXLSX = 0;
var counquest = 0;

$("#typeOption").change(function(){
	var typeValue = $(this).val();
	$.each($(".grid-quest").find("input[id*='corr']"),function(index, value){
		$(value).prop("checked",false);
		$(value).attr("type",typeValue);
	});
});

btn.addEventListener("click",function(){
	var typeValue = $("#typeOption").val();
	let divrow = new Element('div',{ class:'grid-quest',id:'itemansw' + countListXLSX, 'data-rem':'' },[
		new Element('div',{ class:'item-grid-c', style:'padding:5px;' },[
			new Element('input',{type:typeValue, id:'xlsxcorr' + countListXLSX, onclick:'radioEditQ(this, "#questdiv' + counquest+'")', value:'rad' + countListXLSX},[])
		]),
		new Element('div',{ class:'item-grid-c', style:'padding:5px;' },[
			new Element('input',{ type:'text', class:'form-control' },[] )
		]),
		new Element('div',{ class:'item-grid-c', style:'padding:5px;' },[
			new Element('input',{type:'button', value:'Eliminar', class:'btn btn-danger', onclick:'removeElemet("#itemansw'+countListXLSX+'")' },[])
		])
	]);
	var newsq = document.getElementById("questdiv0");
	countListXLSX++;
	newsq.appendChild(divrow);
});

$("#add").click(function(){
	var valueList = new Resource();
	if($("#question").val() == ""){
		getErrorMessage("Tienes que agregar una pregunta");
		return false;
	}
	if ( $("#questdiv0").find("input[id*=xlsxcorr]").filter(function(item){ 
			return $(this).is(":checked") ? true: false;
		}).length  <= 0 ) {
		getErrorMessage("Hay preguntas no marcadas (Debe de haber al menos una opci&oacute;n correcta)");
		return false;
	}
	
	if ( $("#score-quest").val() < 0 ){
		getErrorMessage("Se encontr&oacute; un score menor a 0, el valor de una pregunta es '0' como m&iacute;nimo");
		return false;
	}
		
	var listaDivs = $("#questdiv0").find("div[data-rem]");
	if ( !valueList.isEmptyList( listaDivs.find("input[type='text']") ) ){
		getErrorMessage("Hay campos vac&iacute;os");
		return false;
	}
	addQuestionEdit(valueList);
});

function addQuestionEdit(objResource){
	let idsend = $("#idv").val();
	let iddom = $("#idv").attr("data-iddom");
	var listaDivs = $("#questdiv0").find("div[data-rem]");
	var opciones = objResource.getCamposXLSX( listaDivs );
	var newquest = new Question($("#question").val(), opciones[0], opciones[1], $("#messages").val(), idsend , 0, $("#typeOption").val(),$("#score-quest").val() );
	setDataWithProgress({
			type: "post",
			url: "/admin/simuladores/preguntas/edit",
			data: newquest,
			othercnf: "/admin/simuladores/preguntas/" + iddom
		}, "", [{ key: "Content-type", value: "application/json; charset=utf-8" }]);
}

$(document).ready(function (){
	$("#question").val(objQuest.question);
	$("#messages").val(objQuest.message);
	$("#score-quest").val(objQuest.score);
	$.each($("#typeOption").find("option"),function(index, value){
		if( $(value).attr("value") == objQuest.type ) $(value).prop("selected",true);
	});
	var respuestas = new Element('div',{"data-listquest":"",id:"questdiv0"},[] );
	let arrOptions = objQuest.options.split("##&amp;&amp;");
	let answers = 0;
	$.each(arrOptions, function(indexResp, valueResp){
		var isCheck = isCorrectAnswer( objQuest.answers, answers );  				
		var radiocheck;
		if(isCheck)
			radiocheck = new Element('input',{type: objQuest.type, checked: isCheck , id:'xlsxcorr' + countListXLSX, onclick:'radioEditQ(this, "#questdiv' + counquest+'")', value:'rad' + countListXLSX},[]);
		else
			radiocheck = new Element('input',{type: objQuest.type, id:'xlsxcorr' + countListXLSX, onclick:'radioEditQ(this, "#questdiv' + counquest+'")', value:'rad' + countListXLSX},[]);					
		answers++;
		respuestas.appendChild( getElementCheckRad( radiocheck, countListXLSX, valueResp ) );
		countListXLSX++;
	});
	lista.appendChild(respuestas);
});

function isCorrectAnswer( lstAnswer, answers ){
	for(var i = 0; i < lstAnswer.length ; i++)
		if(lstAnswer[i] == answers)
			return true;
	return false;
}

function getElementCheckRad( radiocheck, countListXLSX, valueResp ){
	var buttonDell = isTrueFalse(valueResp.toLowerCase()) ? new Element('input',{type:'button', value:'Eliminar', class:'btn btn-danger', onclick:'removeElemet("#itemansw'+countListXLSX+'")' },[]) : "";
	return new Element('div',{ class:'grid-quest',id:'itemansw'+countListXLSX ,'data-rem':'' },[
		new Element('div',{ class:'item-grid-c', style:'padding:5px;' },[
			radiocheck
			]),
			new Element('div',{ class:'item-grid-c', style:'padding:5px;' },[
				new Element('input',{ type:'text', class:'form-control', value:valueResp },[ ] )
				]),
				new Element('div',{ class:'item-grid-c', style:'padding:5px;' },[
					buttonDell
					])
		]);
}

function isTrueFalse(value){
	return (value == "true" || value == "false" || value == "verdadero" || value == "falso") ? false: true;
}

function radioEditQ(element, div){
	var list = $(div).find("input[type='radio']").filter(function(item){ return $(this).attr("id") == $(element).attr("id") ? false: true; });
	$.each(list,function(i, elem){ $(elem).prop("checked", false); });
}

function removeElemet(itm){
	$(itm).remove();
}