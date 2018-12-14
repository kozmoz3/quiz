	var successColor ={
		    "border-bottom": "solid 1px #ddd"
	};
	var errorColor ={
		    "border-bottom": "solid 1px red"
	}
	
	var objectSend = {
		type : "null",
		url : "null",
		data: {},
		cache : false,
		success : function( response ) {
			console.log( response );
			getSuccessfulMessage();
		},
		error : function ( XMLHttpRequest, textStatus, errorthrows ){
			getErrorMessage( getCodeStatus( XMLHttpRequest, textStatus ) );
		}
	};
	
	var datosMetadata = {
            url: "null",
            type: "null",
            dataType: "html",
            data: "null",
            cache: false,
            contentType: false,
            processData: false
    };
	
	function isInList(lstLista, value){
		return lstLista.filter(function(item){
			return $(this).attr("id") == $(value).attr("id");
		}).length > 0 ? true : false;
	}
	
	function getListElements( inthis ){
		var lstInputs = $(inthis).find("input");
		var lstSelect = $(inthis).find("select");
		var textarea = $(inthis).find("textarea");
		for (var i = 0 ; i < lstSelect.length; i++) lstInputs.push(lstSelect[i]);		
		for (var i = 0 ; i < textarea.length; i++) lstInputs.push(textarea[i]);
		let colldat =  $("table[data-collection]").find("input[data-selck]");
		
		return lstInputs.filter(function(item){
			return !isInList(colldat, $(this) );
		}).filter(function(itemt){
			return $(this).attr("data-allselck") == undefined;
		});
	}
	
	function sendDataForm( idElement ) {
		var lstElements = getListElements( $(idElement).parents("form")[0] );
		var lstStringscmd = $(idElement).attr("data-list-str") != undefined ? $(idElement).attr("data-list-str") : "";
		var arraryString = lstStringscmd =="" ? [] : lstStringscmd.split(",");
		var reduce = $(idElement).attr("data-remain") == '' ? 0: $(idElement).attr("data-remain");
		
		if( validate( idElement, lstElements, arraryString )  ){
			
			var objetoAdmin = convertToObject( lstElements.filter(function( item ){ return $(this).attr("data-noset") != undefined ? false : true; }),  reduce );
			if( $("input[data-allselck]").length > 0 )
				objetoAdmin[ $("table[data-collection]").attr("data-collection") ] = getNewPropertySerch();
			
			if( $(idElement).attr("data-ismetadata") != undefined ){
				sendActionMetadata( idElement, objetoAdmin );
				if( $(idElement).attr("data-onclear") == "true"){
					$("#imagePreview img").remove();
					clearFElements( lstElements );
					clearList(arraryString);
				}
				return false;
			}
			
			var ulrString = arraryString.length > 0 ? getNewsStrings(arraryString, reduce) : "";
			if(getPasswordinArray()){
				setActionMethod( idElement, objetoAdmin, ulrString.substring( 0, (ulrString.length -1) ) );
				if( $(idElement).attr("data-onclear") == "true"){
					clearFElements( lstElements );
					clearList(arraryString);
				}
			}else
				getErrorMessage("Los campos contraseñas deben coincidir");
		}else
			getErrorMessage("Se encontraron campos requeridos vacíos, por favor, verifica");
	
	}
	
	function sendData( idElement ) {
		var lstElements = getListElements( $(".valid-errors") );
		var lstStringscmd = $(idElement).attr("data-list-str") != undefined ? $(idElement).attr("data-list-str") : "";
		var arraryString = lstStringscmd =="" ? [] : lstStringscmd.split(",");
		var reduce = $(idElement).attr("data-remain") == '' ? 0: $(idElement).attr("data-remain");
		
		if( validate( idElement, lstElements, arraryString )  ){
			
			var objetoAdmin = convertToObject( lstElements.filter(function( item ){ return $(this).attr("data-noset") != undefined ? false : true; }),  reduce );
			if( $("input[data-allselck]").length > 0 )
				objetoAdmin[ $("table[data-collection]").attr("data-collection") ] = getNewPropertySerch(); 
			
			if( $(idElement).attr("data-ismetadata") != undefined ){
				sendActionMetadata( idElement, objetoAdmin );
				if( $(idElement).attr("data-onclear") == "true"){
					$("#imagePreview img").remove();
					clearFElements( lstElements );
					clearList(arraryString);
				}
				return false;
			}
			
			var ulrString = arraryString.length > 0 ? getNewsStrings(arraryString, reduce) : "";
			if(getPasswordinArray()){
				setActionMethod( idElement, objetoAdmin, ulrString.substring( 0, (ulrString.length -1) ) );
				if( $(idElement).attr("data-onclear") == "true"){
					clearFElements( lstElements );
					clearList(arraryString);
				}
			}else
				getErrorMessage("Los campos contraseñas deben coincidir");
		}else
			getErrorMessage("Se encontraron campos requeridos vacíos, por favor, verifica");
	
	}
	
	function getNewPropertySerch(){
		let arrChecks = $("table[data-collection]").find("input[type='checkbox']").filter(function(item){return $(this).is(":checked");});
		let onlyValues =[];
		$.each(arrChecks, function(index, val){
			onlyValues.push( $(val).attr("data-selck") );
		});
		return onlyValues;
	}
	
	function sendActionMetadata( idElement, objetoAdmin ){
		console.log(objetoAdmin);
		var formData = toFormData(objetoAdmin);
		if($("input[type='file']")[0].files.length != 0)
			formData.append( $("input[type='file']").attr("id"), new Blob( 
					[ $("input[type='file']")[0].files[0] ],
					{ type: $("input[type='file']")[0].files[0].type } )
			);
		var url = $(idElement).attr("data-href-url");
		datosMetadata.type = $(idElement).attr("data-href-method");
		datosMetadata.url = getUrlToType( datosMetadata.type, url );
		datosMetadata.data= formData;
		var inresponse = "";
		if( $(idElement).attr("data-response") != undefined )
			inresponse = getOnResponse( $(idElement).attr("data-response") );
		setDataWithProgress( datosMetadata, inresponse );
	}
	
	function getOnResponse( response ){
		var types = response.split("|");
		if(types[0] == "ptext")
			return "";
		else if(types[0] == "view")
			return $(types[1]);
		else 
			return "";
	}
	
	function getUrlToType( type, url ){
		switch( type ){
			case "post": return url +"/add";
			case "put":  return url +"/edit";
			case "delete":  return url +"/delete";
			default: return ""; break;
		}
	}
	
	function toFormData( objetoAdmin ){
		var formData = new FormData();
		$.each(objetoAdmin, function(index, value){
			if(index != "img");
				formData.append(index, value);
		});
		return formData;
	}
	
	function getNewsStrings(arraryString, reduce){
		var ulrString = "";
		for(var i = 0; i < arraryString.length; i++){
			var cadena = arraryString[i].substring(reduce, arraryString[i].length);
			ulrString += cadena + "="+ $("#"+arraryString[i]).val() + "?";
		}
		return ulrString;
	}
	
	function getPasswordinArray(){
		var listpass = $(".form").find("input[type='password']");
		if(listpass.length == 2)
			if($(listpass[0]).val() != $(listpass[1]).val() ) 
				return false
		return true;
		
	}
	
	function clearFElements( lstElements ){
		clearElements($( lstElements ).filter(function( item ){
			return $(this).attr("data-cleable") != undefined ? false : true;
		}));
	}
	
	function setActionMethod( idElement, objeto, stringCadeurl ){
		var value = $(idElement).attr("data-href-method");
		var url = $(idElement).attr("data-href-url");
		objectSend.data = objeto;
		switch( value ){
			case "post":
				objectSend.type="post";
				objectSend.url = url +"/add?"+ stringCadeurl;
				break;
			case "put": 
				objectSend.type="put";
				objectSend.url = url +"/edit";
				break;
			case "delete": 
				objectSend.type="delete";
				objectSend.url = url +"/delete";
				break;
			default: return; break;
		}
		
		var inresponse = "";
		if( $(idElement).attr("data-response") != undefined )
			inresponse = getOnResponse( $(idElement).attr("data-response") );
		
		setDataWithProgress(objectSend, inresponse);
	}
	
	function getSize( input ){
		var tamanio = $(input).attr("data-image-size").trim().split(",");
	    var fReader = new FileReader();
	    fReader.onload = function( event ){
	    	var img = new Image();
	    	img.src = event.target.result;
	    	img.onload = function() {
	    		$(input).attr( "data-loaded", "false" );
	    		if ( this.height > tamanio[1] && this.width > tamanio[0] ){
	    			$(input).attr( "data-loaded", "true" );
	    		}
	    	}
	    }
	    fReader.readAsDataURL( input[0].files[0] );
	}
	
	function setArchive( fileElement ){
		var progress = $(fileElement).attr("data-progress");
	    if( progress != undefined)
	    	$( "#" + progress ).show("slow");
	    
	    if( $(fileElement).attr("data-image-size") != undefined ){
	    	getSize( $(fileElement) );
		}
	
	    var filePath = $(fileElement).val();
	    var lstExtensions = $(fileElement).attr("accept").trim().split(",").filter(function(item){
	    	return item == (filePath.substring(filePath.lastIndexOf("."))).toLowerCase();
	    });
	
	    if(lstExtensions.length == 0){
	        $(fileElement).val("");
	        return false;
	    }else{
	    	if( progress != undefined)
	            getProgress( progress );
	    	setTimeLeftImg( $(fileElement), progress );
	    }
	};
	
	function getProgress( progress ){
		var width = 1;
	    var id = setInterval(function () {
	        if (width >= 100) {
	            clearInterval(id);
	        } else {
	            width++;
	            $("#" + progress).val(width);
	        }
	    }, 30);
	}
	
	function setTimeLeftImg( reference, idprogress ){
		setTimeout( function(){
			if( reference.attr("data-loaded") == "true" ){
				if (reference[0].files && reference[0].files[0] && reference.attr("data-preview") != undefined) {
					var reader = new FileReader();
					reader.onload = function(e) {
						$("#" + reference.attr("data-preview") ).fadeIn("slow", function() {
							var dato = '<img class="rounded-circle mx-auto d-block" src="'+e.target.result+'">';
							$(this).html(dato).slideDown("slow");
						});
					};
					reader.readAsDataURL(reference[0].files[0]);
				}
				if( idprogress != undefined)
		              $("#" + idprogress).hide("slow");
			}else{
				reference.val("");
				$("#" + reference.attr("data-preview")).fadeOut("slow", function() {
					$(this).html("").slideUp("slow");
				});
				if( idprogress != undefined)
		              $("#" + idprogress).hide("slow");
				return false;
			}
		}, 3000, reference);
	}
	
	$(".sendDataMeta").on("submit", function(e){
	    e.preventDefault();
	    var f = $(this);
	    var formData = new FormData(document.getElementsByClassName("sendDataMeta"));
	    
	    var lstElements = getListElements();
		var lstStringscmd = $(this).attr("data-list-str") != undefined ? $(this).attr("data-list-str") : "";
		var arraryString = lstStringscmd =="" ? [] : lstStringscmd.split(",");
		var reduce = $(this).attr("data-remain") == '' ? 0: $(this).attr("data-remain");
		
		if( validate( $(this), lstElements, arraryString )  ){
			var value = $(this).attr("data-href-method");
			var url = $(this).attr("data-href-url");
			
	    	$.ajax({
	            url: url,
	            type: value,
	            dataType: "html",
	            data: formData,
	            cache: false,
	            contentType: false,
	            processData: false
	        }).done(function(res){
	            
	        });
	    	if( $(this).attr("data-onclear") == "true"){
				clearFElements( lstElements );
				clearList(arraryString);
			}
	    }else
	    	return false;
	    
	});
		
	function validate( idElement, lstElements, lstStrings ){
		var result = onEmpty({
			lstString: lstStrings != "" ? lstStrings : [],
			normalStyle : successColor,
			showErrors : true,
			errorStyle : errorColor,
			otherConf : { "confemail" : false, "elements":true },
			lstElements: lstElements.filter(function( item ){
				return $(this).attr("data-optional") != undefined ? false : true;
				})
		});
		return result;
	}

	
	function isValidType( idElement ){
		$(idElement).attr("pattern");
	}
	
	function getItem(id) {
		var hrefs = $(id).attr("data-href-to-view");
		if ($(id).attr("data-href-to-menu") != undefined) {
			var contenido = $(id).attr("data-href-to-menu");
			$(".change-menu").find(".active").removeClass("active");
			$(".change-menu li span[data-href*='" + contenido + "']").parent("li").addClass("active");
			document.title = contenido;
		}
	
		var idelement = {
			id : 0
		};
		if ($(id).attr("data-href-to-view-data") != undefined) {
			idelement.id = $(id).attr("data-href-to-view-data");
			$.ajax({
				type : "get",
				url : hrefs + "/" + idelement.id,
				cache : false,
				success : function(dato) {
					$(".main-content").fadeOut("slow", function() {
						$(this).html(dato).slideDown("slow");
					});
				},
				error : function(XMLHttpRequest, textStatus, errorthrows) {
					alert('Error: ' + errorthrows);
				}
			});
		}else{
			$.ajax({
				type : "get",
				url : hrefs,
				cache : false,
				success : function(dato) {
					$(".main-content").fadeOut("slow", function() {
						$(this).html(dato).slideDown("slow");
					});
				},
				error : function(XMLHttpRequest, textStatus, errorthrows) {
					alert('Error: ' + errorthrows);
				}
			});
		}
		
	}
	
	function getItemOnLoad(){
		$.ajax({
			type : "get",
			url : "/admin/index",
			cache : false,
			success : function(dato) {
				$(".main-content").fadeOut("slow", function() {
					$(this).html(dato).slideDown("slow");
				});
			},
			error : function(XMLHttpRequest, textStatus, errorthrows) {
				alert('Error: ' + errorthrows);
			}
		});
	}
	
	
		
	function sendAjaxCodeZip( value ){
		$.ajax({
			type: "get",
			url: "https://api-codigos-postales.herokuapp.com/v2/buscar",
			data: { codigo_postal: value },
			success: function(response){
				$.get(
					"https://api-codigos-postales.herokuapp.com/v2/codigo_postal/" + response.codigos_postales[0],
					function( objeto ){
						sendDataCode( objeto );
					}
				);
			},
			error: function ( XMLHttpRequest, textStatus, errorthrows ){
				getErrorMessage( getCodeStatus( XMLHttpRequest, textStatus ) );
			}
		});
	}

	function sendDataCode( objeto ){
		$("#idmaauns").html("Estado: <span>"+objeto.estado+"</span><br>Municipio: <span>"+objeto.municipio+"</span>");
		$("#stridestado").val(objeto.estado);
		$("#stridmunicipio").val(objeto.municipio);
	}
	
	$(".zip").click(function(){
		if ( $("#strcp").val().length == 0 ) return;
		
		var btnZIP = $(this);
		var dondeBtnOk = $(this).attr("data-in");
		
		let mensaje = new Element('p',{class:'modal-window-text'},['¿Están correctos tus datos?']);		
		let estado = new Element('p',{id:'idmaauns'},[] );		
		let btnok = new Element('input',{ class: 'btn btn-default', type: 'button',style:'margin:4px', value:"Si" },[]);
		let btnnot = new Element('input',{ class: 'btn btn-default', type: 'button',style:'margin:4px', value:"No" },[]);
		
		btnok.addEventListener('click',function(e){
			$("#strcp").attr("disabled", true);
			$("#"+dondeBtnOk).append( getButtonMain() );
			$(".zip").remove();
			$(".modal-window").remove();
		});
		
		btnnot.addEventListener('click',function(e){
			$("#strcp").attr("disabled", false);
			$("#register-al").remove();
			$(".modal-window").remove();
		});
		
		let divbotones = new Element('div',{style:'display:block;float:center;position: relative;text-align: center;'},[btnok,btnnot ]);
		
		let e = new Modal('div',{class:'modal-window-content'},[ mensaje, estado, divbotones]);
		document.body.appendChild(e);
		sendAjaxCodeZip( $("#strcp").val() );
	});
	
	function getButtonMain(){
		return new Element('input',{ 
			'type': 'button', 'id': 'register-al',
			'onclick': 'sendData(this);',
			'data-onclear': 'true',
			'data-href-method': 'post',
			'data-remain': '3',
			'data-href-url': '/register', 'data-optional': '', 'data-noset': '',
			'data-cleable': '', class: 'btn btn-primary align-right', 
			'value': "Registrarse" },[]);
	}

$("input[type='radio']").click(function(){
	var element = $(this).attr("id");
	var parent = $(this).parents(".radio-group");
	var list = $(parent).find("input[type='radio']").filter(function(item){
		return $(this).attr("id") == element ? false: true; 
	});
	$.each(list,function(i, elem){ $(elem).prop("checked", false); });
});

function onBlock( lstElementsids, is ) {
	for ( var i = 0; i < lstElementsids.length; i++ )
		$(lstElementsids[i]).prop("disabled", is);
}

$("input[data-allselck]").click(function(){
	let check = $(this);
	$.each($("table").find("input[data-selck]"),function(index, value){
		if( $(check).is(":checked") )
			$(value).prop("checked",true);
		else
			$(value).prop("checked",false);
	});
});

function editQ( element ){
	var url = $(element).attr("data-urls");
	var response = $(element).attr("data-response");
	if($.trim(url) == "") return false;	
	getFormInResponse({ type:"get", url:url, response: response });
}

