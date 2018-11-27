function ExportToTable() {
	if( $("input[data-colrow]").val() == "" ){
		getErrorMessage("Debes de agregar los nombres de las columnas");
		$("input[data-importxls]").val("");
		return false;
	}
	var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.xlsx|.xls)$/;
	if (regex.test($("input[data-importxls]").val().toLowerCase())) {
		var xlsxflag = false;  
		if ($("input[data-importxls]").val().toLowerCase().indexOf(".xlsx") > 0) xlsxflag = true;
		if (typeof (FileReader) != "undefined") {
			getLoadMessage();
			var reader = new FileReader();
			reader.onload = function (e) {
				var data = e.target.result;
				var workbook;
				xlsxflag == true ? workbook = XLSX.read(data, { type: 'binary' }) : workbook = XLS.read(data, { type: 'binary' });
				var sheet_name_list = workbook.SheetNames;
				var cnt = 0;
				sheet_name_list.forEach(function (y) {
					var exceljson;
					xlsxflag == true ? exceljson = XLSX.utils.sheet_to_json(workbook.Sheets[y]) : exceljson = XLS.utils.sheet_to_row_object_array(workbook.Sheets[y]);
					if (exceljson.length > 0 && cnt == 0) {
						
						var camposRow = $("input[data-colrow]").val().split(",");
						var headers = getHeaders( exceljson );
						if( isListInList( headers, camposRow ) ){
							BindTable(exceljson, camposRow);
							cnt++;
						}else{
							dropLoadMessage();
							getErrorMessage("Las cabeceras que agregaste no corresponden al documento");
							$("input[data-importxls]").val("");
						}
					}
				});
			}
			dropLoadMessage();
			xlsxflag == true ? reader.readAsArrayBuffer($("input[data-importxls]")[0].files[0]) : reader.readAsBinaryString($("input[data-importxls]")[0].files[0]);
		}else
			getErrorMessage("No soportado");
	} else
		getErrorMessage("Archivo inválido");
}
  		
function BindTable(jsondata, camposRow) {
	var all = [];
	var posiciones = [];
	var preguntas = [];
	var pregunta = "";            
	var contpre = 0;
	var type = "";
	var j = 0;
	var pos = 0;
	for (var i = 0; i < jsondata.length; i++) {
		if( jsondata[i][ camposRow[0] ] != null ){                    
			if(contpre != 0){
				if( contpre == 1)
					type = "radio";
				else if(contpre > 1)
					type = "checkbox";
                        
				all.push( new QuestionXLSX(pregunta, preguntas, posiciones, "", 1, type) );
				posiciones = [];
				preguntas = [];
			}
			pregunta = isEmptyCell( jsondata[i][camposRow[0]] );
			pos = 0;
			contpre = 0;
		}
		if( jsondata[i][camposRow[2]] != null ){
			preguntas.push( jsondata[i][camposRow[2]] );
			pos++;
		}
		if( jsondata[i][camposRow[1]]  != undefined ){
			contpre ++;
			posiciones.push( (pos -1) );
		}
	}
	createForm(all);
}
  		
function createForm( componets ){
	var countListXLSX = 0;
	var counquest = 0;
	var formulario = new Element('form',{id:"formloadXLSX"},[] );
	var conenedor = new Element('div',{},[] );
	$.each(componets, function(index, value){
		var respuestas = new Element('div',{"data-listquest":""},[] );
		let answers = 0;
		$.each(value.options, function(indexResp, valueResp){
			var isCheck = isCorrectAnswer( value.answers, answers );  				
			var radiocheck;
			if(isCheck)
				radiocheck = new Element('input',{type: value.type, checked: isCheck , id:'xlsxcorr' + countListXLSX, onclick:'radioXLSX(this, "questdiv' + counquest+'")', value:'rad' + countListXLSX},[]);
			else
				radiocheck = new Element('input',{type: value.type, id:'xlsxcorr' + countListXLSX, onclick:'radioXLSX(this, "questdiv' + counquest+'")', value:'rad' + countListXLSX},[]);
  					
			answers++;
			respuestas.appendChild( getElementCheckRad( radiocheck, countListXLSX, valueResp ) );
			countListXLSX++;
		});
		var preguntacompleta = new Element('div',{ id : "questdiv" + counquest, "data-question":"" },[
			new Element('textarea',{ id:'questionxlsx' + counquest, class:'form-control',placeholder:"Pregunta" },[ value.question ] ),
			respuestas,
			new Element('br',{},[] ),
			new Element('div',{ class:'grid-delq'},[
				new Element('div',{ class:'item-grid-q'},[
					new Element('input',{type:'button', value:'Eliminar pregunta', class:'btn btn-danger',onclick:"removeQuestion(questdiv"+counquest+");" },[])
				]),
				new Element('div',{ class:'item-grid-q'},[
					new Element('input',{type:'number', value:'1', "data-score":"", class:'form-control', min:"1" },[])
				])
			]),
			new Element('hr',{},[] )
		]);
		counquest++;
		conenedor.appendChild(preguntacompleta);
	});
	
	setInForm( formulario, conenedor );
}

function setInForm( formulario, conenedor ){
	formulario.appendChild(conenedor);
	let buttonAgregar = new Element('input',{type:'button', value:'Argegar', class:'btn btn-success' },[] );
	var valueList = new Resource();
	buttonAgregar.addEventListener("click",function(){
		var verificaCampos = true;
		var verificaChecks = true;
		$.each( $("#formloadXLSX").find("div[data-question]") , function(index, value){
			if ( $(value).find("input[id*=xlsxcorr]").filter(function(item){ return $(this).is(":checked") ? true: false; }).length  <= 0 ) verificaChecks = false;
  					
			var listaDivs = $(value).find("div[data-remxlsx]");
			if ( !valueList.isEmptyList( listaDivs.find("input[type='text']") ) ) verificaCampos = false;
		});
		if(!verificaCampos) {getErrorMessage("Hay campos vac&iacute;os");return false;}
		if(!verificaChecks) {getErrorMessage("Hay preguntas no marcadas (Debe de haber al menos una opci&oacute;n correcta)");return false;}
		if( verificaCampos && verificaChecks ){
			addToListQuestions();
		}
	});
	formulario.appendChild(
			new Element('div',{ class:'form-group'},[
				buttonAgregar
				])
	);
	$("#form-questionxlsx").html(formulario);
}

function addToListQuestions(){
	var objResource = new Resource();
	$.each( $("#formloadXLSX").find("div[data-question]") , function(index, value){
		var listaDivs = $(value).find("div[data-remxlsx]");
		var opciones = objResource.getCamposXLSX( listaDivs );
		var type = objResource.getTypeXLSX( listaDivs );
		listQuestions.push( new Question($("#questionxlsx" + index).val(), opciones[0], opciones[1], "", 0, 0, type ) );
	});
	console.log(listQuestions);
}
  		
function isCorrectAnswer( lstAnswer, answers ){
	for(var i = 0; i < lstAnswer.length ; i++)
		if(lstAnswer[i] == answers)
			return true;
	return false;
}
  		
function removeQuestion(id){
	$(id).remove();
}
  		
function getElementCheckRad( radiocheck, countListXLSX, valueResp ){
	var buttonDell = isTrueFalse(valueResp.toLowerCase()) ? new Element('input',{type:'button', value:'Eliminar', class:'btn btn-danger', onclick:'removeElemet("itemansw'+countListXLSX+'")' },[]) : "";
	return new Element('div',{ class:'grid-quest',id:'itemansw'+countListXLSX ,'data-remxlsx':'' },[
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
  		
function isEmptyCell( cellValue ){
	return cellValue == null ? true : cellValue;
}

function radioXLSX(element, div){
	var list = $("#" + div).find("input[type='radio']").filter(function(item){ return $(this).attr("id") == $(element).attr("id") ? false: true; });
	$.each(list,function(i, elem){ $(elem).prop("checked", false); });
}

function getHeaders( jsondata ) {
	var columnSet = [];
	for (var i = 0; i < jsondata.length; i++){
		var rowHash = jsondata[i];
		for (var key in rowHash)
			if (rowHash.hasOwnProperty(key))
				if ($.inArray(key, columnSet) == -1)  
                    columnSet.push(key);
    }
    return columnSet;  
}

function isInList(lstLista, value){
	return lstLista.filter(function(item){
		return item == value;
	}).length > 0 ? true : false;
}

function isListInList(lstToVal, lstToCompare){
	return lstToVal.filter( function( item ) {
		return lstToCompare.filter( function( itemcom ) {
			return item == itemcom;
		}).length > 0 ? true : false;
	}).length == lstToCompare.length ? true : false;
}
