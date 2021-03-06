var objResource = new Resource();
var listQuestions = [];

var objQuiz = new Quiz(listQuestions);
var lista = objQuiz.getObject();

var btn = document.getElementById("more");
var lista = document.getElementById("lst");
var clear = document.getElementById("clear");
var add = document.getElementById("add");
var listQuest = document.getElementById("listQuest");
var saveConfig = document.getElementById("saveConfig");
var sendAll = document.getElementById("sendAll");

var countList = 0;
var config = {
	aleatorio: false,
	timer: false,
	ontimer: "",
	maxquest:0,
	maxint:0,
	onmaxint:0,
	itsOk:false
};

$("#typeOption").change(function(){
	var typeValue = $(this).val();
	$.each($(".grid-quest").find("input[id*='corr']"),function(index, value){
		$(value).prop("checked",false);
		$(value).attr("type",typeValue);
	});
});

$("input[data-masiva]").click(function(){
	$("form[data-display-masiva]").show("slow");
	$( $(this).parent("div")[0] ).hide("slow");
	$( $(this).parent("div")[0] ).remove();
});

$("input[data-manual]").click(function(){
	$("div[data-display-manual]").show("slow");
	$( $(this).parent("div")[0] ).hide("slow");
	$( $(this).parent("div")[0] ).remove();
});

btn.addEventListener("click",function(){
	var typeValue = $("#typeOption").val();
	let divrow = new Element('div',{ class:'grid-quest',id:'li' + countList, 'data-rem':'' },[
		new Element('div',{ class:'item-grid-c', style:'padding:5px;' },[
			new Element('input',{type:typeValue, id:'corr' + countList, onclick:'radio(this)', value:'rad' + countList},[])
		]),
		new Element('div',{ class:'item-grid-c', style:'padding:5px;' },[
			new Element('input',{ type:'text', id:'resp' + countList, class:'form-control' },[] )
		]),
		new Element('div',{ class:'item-grid-c', style:'padding:5px;' },[
			new Element('input',{type:'button', id:'dell' + countList, value:'Delete', class:'btn btn-danger', onclick:'removeElemet("li'+countList+'")' },[])
		])
	]);
	countList++;
	lista.appendChild(divrow);
});

clear.addEventListener("click",function(){
	clearAll();
});

add.addEventListener("click",function(){
	if( $("#question").val() == "" ){ getErrorMessage("Agrega una pregunta"); return false; }
	if( $("#score-quest").val() < 0 ){getErrorMessage("Se encontr&oacute; un score menor a 0, el valor de una pregunta es '0' como m&iacute;nimo");return false;}
	
	var isEmptyElem = $("#lst").find("input[id*=corr]").filter(function(item){ return $(this).is(":checked") ? true: false; });
		
	if( isEmptyElem.length > 0 ) {
		var listaDivs = $("#lst").find("div[data-rem]");
		if( objResource.isEmptyList( listaDivs.find("input[type='text']") ) > 0 ){
			var opciones = objResource.getCampos( listaDivs );
			listQuestions.push( new Question($("#question").val(), opciones[0], opciones[1], $("#messages").val(), 0, 0, $("#typeOption").val(),$("#score-quest").val() ) );
			$("#question").val("");
			$("#messages").val("");
			setList();
			clearAll();
		}else
			getErrorMessage("Hay respuestas sin rellenar");
		
	}else{
		getErrorMessage("Tiene que haber una respuesta correcta");
	}
});

/*saveConfig.addEventListener("click",function(){
	if( $(".mainForce").find("input[id='sendAll']").length == 0 ){		
		var sendAll = new Element('input',{ type:'button',id:'sendAll',class:'btn btn-success',value:'Guardar' },[]);
		
		sendAll.addEventListener("click", function(){
			if( listQuestions.length <= 0 ){
				alert("No puedes guardar un cuestionario sin preguntas");
			}else{
				console.log({
					configuration: config,		
					lstQuest: listQuestions
				});
			}
		});
		
		$(".mainForce").append( sendAll );
	}
	
	config.aleatorio = $("#aleatorio").is(":checked");
	config.timer = $("#times").is(":checked");
	config.ontimer = $("#times").is(":checked") ? $("#time").val(): "";
	config.maxquest = $("#cantidadmuestra").val();
	config.maxint = $("#intento").is(":checked");
	config.onmaxint = $("#intento").is(":checked") == true ? $("#number").val(): 0;
	config.itsOk = true;
});*/

function removeElemet(id){
	$("#" + id).remove();
}

function radio( element ){
	var list = $("#lst").find("input[type='radio']").filter(function(item){ return $(this).attr("id") == $(element).attr("id") ? false: true; });
	$.each(list,function(i, elem){ $(elem).prop("checked", false); });
}

function clearAll(){
	$.each( $("#lst").find("div[data-rem]") ,function(i, elem){ $(elem).remove(); });
	$("#typeOption").prop("disabled",false);
	countList = 0;
}

function removeQuest( position ){
	listQuestions.splice(position, 1);
	setList();
}

setList();

function setList( ){
	var countListObj = 0;
	$.each( $("#listQuest").find("li[data-runge]"),function(k,v){ $(v).remove(); });
	$.each(listQuestions,function(k,v){
		var li = new Element('li',{ id:'quest'+countListObj, 'data-runge':'' },[
			new Element('div',{ class:'alert alert-info alert-dismissible', role:'alert' },[
				new Element('button',{ type:"button", class:'close', 'data-dismiss':'alert',onclick:'removeQuest(\''+countListObj+'\');', 'aria-label':'Close' },[
					new Element('span',{ 'aria-hidden':'true' },['&times;'] )
				]),
				v.question
			])
		]);
		countListObj++;
		listQuest.appendChild(li);
	});

}
$("#times").click(function(){
	if( $(this).is(":checked") )
		$(".times").append( new Element('input',{ type:'time',class:'form-control',id:'time',  pattern:'[0-9]{2}:[0-9]{2}:[0-9]{2}', step:'3', min:'00:01:00', max:'02:59:59' },[]) );
	else
		$("#time").remove();
});

$("#intento").click(function(){
	if( $(this).is(":checked") )
		$(".intento").append( new Element('input',{ type:'number', id:'number', min:'1', value:'1', class:'form-control' },[]) );
	else
		$("#number").remove();
});

$("#listo").click(function(){
	let idsend = $(this).attr("data-idsend");
	$.each(listQuestions,function(k,v){
		v.idquiz = idsend;
		v.idquestion = null;
	});
	setDataWithProgress({
				type: "post",
				url: "/admin/simuladores/preguntas/add",
				data: listQuestions,
				othercnf: "/admin/simuladores/preguntas/" + idsend
			},"",[{ key: "Content-type", value: "application/json; charset=utf-8" }]);
});

// COOKIES
function getCookie(cname) {
	var name = cname + "=";
	var decodedCookie = decodeURIComponent(document.cookie);
	var ca = decodedCookie.split(';');
	for(var i = 0; i <ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}

function getExpiresCookie(){
	var d = new Date();
	d.setTime(d.getTime() + (13 * 24 * 60 * 60 * 1000));
	return "expires=" + d.toUTCString();
}
//0445566553079 