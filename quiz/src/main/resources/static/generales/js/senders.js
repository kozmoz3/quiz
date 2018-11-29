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

function setDataWithProgress(type, url, data){
	getLoadMessageProgress();
	
	var req = new XMLHttpRequest();
	
	req.onprogress = function (e) {
		console.log( e );
		if (e.lengthComputable)
			$("#progressmsg").progressbar("option", "value", (e.loaded / e.total ) * 100 );
	};
	req.addEventListener("load", transferComplete, false);
	req.addEventListener("error", transferFailed, false);
	req.addEventListener("abort", transferCanceled, false);
	
	req.open(type, url, true);
	
	req.send(data);
	console.log(req);
	if (req.status == 200){
		console.log(req.response);
		getSuccessfulMessage();
	}else
		getErrorMessage(getCodeStatus(req, ""));
}

function transferComplete(evt) {
	getSuccessfulMessageWithText("Operación completada");
}

function transferFailed(evt) {
	getErrorMessage("Ocurrió un error al cargar información");
}

function transferCanceled(evt) {
	getErrorMessage("La transferencia fue cancelada por el usuario.");
}