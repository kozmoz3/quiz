if(typeview){	
	getQuizWizard();
}else{
	$(".clock").removeClass("col-md-6");
	$(".plain").removeClass("col-md-6");
	$(".plain").addClass("col-md-12");
	$(".clock").css({
	    "display": "block",
	    "float": "left",
	    "position": "fixed",
	    "right": "50px",
	    "bottom": "100px"
	});
	$("#itemslist").remove();
	$(".col-md-6").removeClass("col-md-6");
	$("#itemquest").addClass("col-md-12");
	deleteElements( [".clock", $(".container .row")[0] ] );
	undoElements( [".container", ".container"] );
	$(".container hr")[0].remove();
	getQuizList();
}

function getQuizList(){
	$.each(lista,function( k , v ){
		lstresp.push( new Answer(v.idquestion, []) );
		getQuestionInList(v.options, v.type, v.q, k, true);
	});
	$("#nextfinished").append( new Element('input',{type:'button',class:'btn btn-info',id:"btnfinlist", value:'Terminar' },[ ] ) );
}

function getQuizWizard(){
	$.each(lista,function( k , v ){
		var btn = new Element('button',{type:'button',class:'btn btn-info', 'data-in':k },[ (k+1) ] );
		btn.addEventListener("click", function(){ // click en item de
			setReactInSelected( k );
			getQuestion(v.options, v.type, v.q, k, false);
		});
		$("#itemslist").append(btn);
		lstresp.push( new Answer(v.idquestion, []) );
		
		if( k == 0 ){
			getQuestion(v.options, v.type, v.q, k, false);
			$("#nextfinished").append( new Element('input',{type:'button',class:'btn btn-info', id:'btnnextFin', 'data-on': ( k + 1 ), value:'Siguiente' },[ ] ) );
		}
		
	});
}

function radioInList( idlst, element ){
	var list = $("#"+idlst).find("input[type='radio']").filter(function(item){ return $(this).attr("id") == $(element).attr("id") ? false: true; });
	$.each(list,function(i, elem){ $(elem).prop("checked", false); });
}

function radio( element ){
	var list = $("#itemquest").find("input[type='radio']").filter(function(item){ return $(this).attr("id") == $(element).attr("id") ? false: true; });
	$.each(list,function(i, elem){ $(elem).prop("checked", false); });
}

function getTypeRadio( ishere, isList, id, type, key ){
	if( ishere )
		return new Element('input',{ type:type, onclick: isList ? 'radioInList(\'' + id + '\',this)':'radio(this)', id:'ans' + key, checked: ishere },[]);
	else
		return new Element('input',{ type:type, onclick: isList ? 'radioInList(\'' + id + '\',this)':'radio(this)', id:'ans' + key },[]);
}

function getQuestionInList( options, type, q, k , isList){
	let respuestas =  new Element('div',{ class:'options' },[ ]);
	$.each(options, function(key, valu){
		var ishere = false;
		if( lstresp[k].answers.length > 0  ){
			$.each(lstresp[k].answers, function(keyl, valuel){
				if( valuel == key ) ishere = true;
			});
		}
		var valuesCheck = getTypeRadio(ishere, isList, 'idques'+k, type, key);
		
		respuestas.append( new Element('div',{ class:'form-row' },[
			new Element('div',{ class:'col-auto', style:'padding:5px;' },[ valuesCheck ]),
			new Element('div',{ class:'col-auto', style:'padding:5px;' },[
				new Element('p',{ },[ valu ])
			])
		]));
	});
	$("#itemquest").append(new Element('div',{ class:'contextual', id:'idques'+k },[ 
		new Element('div',{ class:'question' },[ q,
			new Element('div',{ class:'options' },[ respuestas ] )
		] )
	]));
}

function getQuestion( options, type, q, k, isList ){
	$(".contextual").remove();
	getQuestionInList( options, type, q, k, isList );	
	if( k > 0){
		if($("#btnbefore").val() == undefined)
			$("#before").append( new Element('input',{type:'button', class:'btn btn-info', id:'btnbefore',onclick:'getAction(this);', 'data-on': ( k - 1 ), value:'Anterior' },[ ] ) );
		else
			$("#btnbefore").attr('data-on', (k-1) );
	}else
		$("#btnbefore").remove();

	if( (lista.length -1) == k )
		$("#btnnextFin").val("Terminar");
	else{
		$("#btnnextFin").val("Siguiente");
		$("#btnnextFin").attr("data-on", (k + 1) );
	}
	activeOption(k);
}

function activeOption(position){
	$.each( $("#itemslist").find("button"),function (k, val){ $(val).removeClass("active"); });
	$("button[data-in='"+position+"']").addClass("active");
}

$("#btnnextFin").click(function(){
	var position = parseInt($(this).attr("data-on"));
	if( $(this).val() == "Siguiente" ){
		setReact( position );
		getQuestion( lista[position].options, lista[position].type, lista[position].q, position, false );
	}else{
		setReact( position +1 );
		getOptionalMessage( "Quiz", "¿Está seguro de querer terminar el Quiz?", function(){
			sendParse();
			clearInterval(countTimer);
		}, function(){
			getNotification("Continúa","warning");
		});
	}
});

function getAction(elment){
	var position = parseInt($(elment).attr("data-on"));
	setReactBef( position );
	getQuestion( lista[position].options, lista[position].type, lista[position].q, position,false );
};

function setReactInSelected( position ){
	if( $(".contextual").find("input[id*=ans]").filter(function(index,item){ console.log($(item)); return $(item).is(":checked") ? true: false; }).length > 0  ){
		$.each(lstresp[position ].answers,function(key,value){ lstresp[position ].answers.pop(); });
		var listaDivs = $(".contextual").find("input[id*=ans]");
		$.each(listaDivs,function(key,value){
			if( $(value).is(":checked") ) lstresp[position ].answers.push(key);
		});
		// console.log("Había seleccionadas");
	}else{
		// if( position == 0) position++;
		// console.log("No había seleccionadas");
		$.each(lstresp[position].answers,function(key,value){ lstresp[position].answers.pop(); });
	}
	// getQuestion( lista[position].options, lista[position].type,
	// lista[position].q, position, false );
}

function setReactBef( position ){
	if( $(".contextual").find("input[id*=ans]").filter(function(item){ return $(this).is(":checked") ? true: false; }).length > 0  ){
		$.each(lstresp[position +1 ].answers,function(key,value){ lstresp[position +1 ].answers.pop(); });
		var listaDivs = $(".contextual").find("input[id*=ans]");
		$.each(listaDivs,function(key,value){
			if( $(value).is(":checked") ) lstresp[position + 1].answers.push(key);
		});
	}else{
		$.each(lstresp[position +1].answers,function(key,value){ lstresp[position +1].answers.pop(); });
	}
}

function setReact( position ){
	if( $(".contextual").find("input[id*=ans]").filter(function(item){ return $(this).is(":checked") ? true: false; }).length > 0  ){
		$.each(lstresp[position -1 ].answers,function(key,value){ lstresp[position -1 ].answers.pop(); });
		var listaDivs = $(".contextual").find("input[id*=ans]");
		$.each(listaDivs,function(key,value){
			if( $(value).is(":checked") ) lstresp[position - 1].answers.push(key);
		});
	}else{
		if( position == 0) position++;
		$.each(lstresp[position -1].answers,function(key,value){ lstresp[position -1].answers.pop(); });
	}
}

var segundos=200;
function countDown(){
	var minutes = Math.round( (segundos-30) /60);
	var remain = segundos % 60;
	if(remain<1) remain = "0";
	
	document.getElementById("timer").innerHTML = minutes + ":" + remain;
	if(segundos==0){
		clearInterval(countTimer);
		if(!typeview) takeList();
		sendParse();
	}else segundos--;
}
// var countTimer = setInterval(countDown,1000);

function sendParse(){
	var cadena = "";
	$.each(lstresp, function(k,v){
		cadena += v.idquestion + ",";
		$.each(v.answers, function(key,val){
			if(v.answers.length == key) cadena += val; else cadena += val +"-";
		});
		cadena = cadena.substring(0, cadena.length - 1);
		if(lstresp.length != k) cadena += ":";
	});
	cadena = cadena.substring(0, cadena.length - 1);
	console.log(lstresp);
	setDataEnd(new Registroquiz(lista.length,0,(lista.length / 100),cadena, document.getElementById("timer").innerHTML, lstresp));
}

$("#btnfinlist").click(function(){
	getOptionalMessage( "Quiz", "¿Está seguro de querer terminar el Quiz?", function(){
		takeList();
		sendParse();
		clearInterval(countTimer);
	}, function(){
		
		let buttonOk = new Elements("input",{type:"button",class:"btn btn-success", value:"Ok"},[]);
		buttonOk.addEventListener("click",function(){
			$(".modal-window-panel").remove();
			location.href='/me/';
		});
		
		let graph = new Elements("div",{},[]), showtimer = new Elements("div",{},[]);
		if(configurationcs.grafico)
			graph = new Elements("div",{class:"cols"},[
				new Elements("canvas",{ id:"resultados-chart", class:"chartjs", style:"width: 80%;height: 80%;"},[])
				]);
		if(configurationcs.istiempo)
			showtimer = new Elements("label",{},["Tiempo restante: ",
				new Elements("span",{"data-res-timer":""},["03:20"])
			]);
		
		let e = new Elements('div',{class:'modal-window-panel'},[
			new Elements("div",{id:"__panelresponse",class:"modal-window-panel-content"},[
				new Elements("div",{class:"rows"},[
					graph,
					new Elements("div",{class:"cols"},[
						new Elements("div",{class:"contenidos"},[
							new Elements("h2",{},["100%"]),
							new Elements("div",{ style:"display: grid;"},[
								new Elements("label",{"data-res-pregp":""},[jsonval.length + " Preguntas"]),
								new Elements("label",{"data-res-pregc":""},["8 preguntas correctas"]),
								new Elements("label",{"data-res-pregi":""},["0 preguntas incorrectas"]),
								new Elements("label",{},["Calificacion: ",
									new Elements("span",{"data-res-calif":""},["10"])
								])
							])
						])
					])
				]),
				new Elements("div",{class:"content-afoot"},[
					showtimer,
					new Elements("span",{class:"aprovado", "data-res-status":""},["Aprobado"]),
				]),
				new Elements("div",{class:"content-footer"},[ buttonOk ])
			])
		]);
		document.body.appendChild(e);
		if(configurationcs.grafico)
			getGraphic( 80, 20 );
		if($("resultados-chart").attr("class") === undefined)
			$("#__panelresponse").css("width","30%");
		getFireworks();		
		getNotification("Continúa","warning");
	});
});

function getGraphic( correctas, incorrectas ){
	new Chart(document.getElementById("resultados-chart"),{
		"type":"doughnut",
		"data":{
			"labels":[
				"Incorrectas","Correctas"
			],
			"datasets":[
				{
					"label":"Datos completos",
					"data":[ incorrectas, correctas ],
					"backgroundColor":["rgb(255, 0, 0)","rgb(57,132,57)"]
				}
			]
		}
	});
}
function getFireworks(){
	let canv = new Elements('canvas',{id:'canv'},[]);
	canv.addEventListener("click",function (e) {
		$(".fireworks-window").remove();
	});
	let e = new FireworkLand('div',{class:'fireworks-content', id:"canvas-container"},[
		canv
	]);
	document.body.appendChild(e);
	loop();
}

function takeList(){
	$.each($("#itemquest").find(".contextual"),function(index, value){
		var listaDivs = $(value).find("input[id*=ans]");
		$.each(listaDivs,function(key,valuedivs){
			if( $(valuedivs).is(":checked") ) lstresp[index].answers.push(key);
		});
	});
}

function setDataEnd(senddata){
	$.ajax({
		url:"/quizquest/edit/"+id,
		data:senddata,
		type:"put",
		success:function(response){
			console.log(response.idregistro);
			
		},
		error : function ( XMLHttpRequest, textStatus, errorthrows ){
			getErrorMessage( getCodeStatus( XMLHttpRequest, textStatus ) );
		}
	});
}

function undoElements( here ){
	for ( var i = 0; i < arrobjectElement.length; i++ ){
		var objAttr = {};
		for ( var j = 0; j < arrobjectElement[i].attributes.length; j++ ){
			$.each( Object.entries( arrobjectElement[i].attributes[j] ) ,function( key, value ){
				objAttr[ value[0] ] = value[1];
			});
		}
		$(here[i]).append(new Element(arrobjectElement[i].type, objAttr , arrobjectElement[i].children ));
	}
	arrobjectElement = [];
}

function deleteElements( lstElementsids ){
	for ( var i = 0; i < lstElementsids.length; i++ ){		
		arrobjectElement.push({
			"type": $( lstElementsids[i] ).get(0).nodeName,
			"attributes": getAttributes( $(lstElementsids[i] ) ),
			"children": $( lstElementsids[i] ).children()
		});
		$(lstElementsids[i]).remove();
	}
}

function getAttributes( element ){
	var lstAttributes = [];
	$(element).each(function() {
	  $.each(this.attributes, function() {
	    if(this.specified){
				var objetoAttr={};
				objetoAttr[this.name] = this.value;
				lstAttributes.push(objetoAttr);
			}
	  });
	});
	return lstAttributes;
}
