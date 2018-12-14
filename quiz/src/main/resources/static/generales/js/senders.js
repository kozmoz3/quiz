function sendForm(idform, url, type) {
	var data = new FormData(document.getElementById(idform));
	var xhr = new XMLHttpRequest();
	xhr.open(type, url, false);
	xhr.send(data);
	if (xhr.status == 200)
		getSuccessfulMessage();
	else
		getErrorMessage(getCodeStatus(xhr, ""));
}

function getFormInResponse( objRequest ){	
	var req = new XMLHttpRequest();	
	req.open( objRequest.type, objRequest.url, false );	
	req.send();
	
	if (req.status == 200)
		if (objRequest.response != "")
			$(objRequest.response).html( req.response );
	else
		getErrorMessage(getCodeStatus(req, ""));
}

function setDataWithProgress( objRequest, inresponse ){	
	var req = new XMLHttpRequest();
	
	req.onprogress = function (e) {
		if (e.lengthComputable){
			if( $("body").find(".modal-window").length <= 0 ) getLoadMessageProgress();
			$("#progressmsg").progressbar("option", "value", (e.loaded / e.total ) * 100 );
		}
	};
	
	req.addEventListener("error", transferFailed, false);
	req.addEventListener("abort", transferCanceled, false);
	
	req.open( objRequest.type, objRequest.url, false );	
	req.send( objRequest.data );
	
	if (req.status == 200){
		if (inresponse != "")
			$(inresponse).html( req.response );
		getSuccessfulMessageWithText("Operación completada");
	}else{
		getErrorMessage(getCodeStatus(req, ""));
	}
}

function transferFailed(evt) {
	getErrorMessage("Ocurrió un error al cargar información");
}

function transferCanceled(evt) {
	getErrorMessage("La transferencia fue cancelada por el usuario.");
}