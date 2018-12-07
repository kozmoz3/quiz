function getCodeStatus(jqXHR, textStatus) {
	if (jqXHR.status === 0)
		return 'Error de conexióm.';
	else if (jqXHR.status == 404)
		return 'Página no encontrada.';
	else if (jqXHR.status == 500)
		return 'Error interno en servidor.';
	else if (textStatus === 'parsererror')
		return 'El análisis JSON solicitado fracasó.';
	else if (textStatus === 'timeout')
		return 'Tiempo de espera terminado.';
	else if (textStatus === 'abort')
		return 'Solicitud ajax cancelada.';
	else
		return 'Error desconocido: ' + jqXHR.responseText;
}
function getNotificationPersonal(type, reuselm ){
	let e = new Elements('div',{ class: getTypeNotification(type), style: getStyleOfNotif() },[ reuselm ]);
	document.body.appendChild(e);
	setTimeout(function(){
		$(e).hide("slow");
		$(e).remove();
	},5000);
}

function getNotificationImg( text, type, img ){
	let e = new Elements('div',{ class: getTypeNotification(type), style: getStyleOfNotif() },[
		new Elements("div", {class:'notif-container'},[
			new Elements("div", {class:'notif-img'},[
				new Elements("img", { src: img },[])
			]),
			new Elements("div", {class:'notif-text'},[
				new Elements("p", {},[ getLongMessage( text ) ])
			])
		])
	]);
	document.body.appendChild(e);
	setTimeout(function(){
		$(e).hide("slow");
		$(e).remove();
	},5000);
}

function getNotification( text, type ){
	let e = new Elements('div',{ class: getTypeNotification(type), style: getStyleOfNotif() },[ getLongMessage( text ) ]);
	document.body.appendChild(e);
	setTimeout(function(){
		$(e).hide("slow");
		$(e).remove();
	},5000);
}

function getStyleOfNotif(){
	let style = "bottom:10px;";
	let tamanioc = $(".notif").length;
	if(  tamanioc > 0 ){
		let domNotif = $(".notif")[tamanioc - 1];			
		let bottom = $(domNotif).css("bottom");
		let height = $(domNotif).css("height");
		let valinstr = String(bottom).substring(0, (String(bottom).length - 2 ) );
		style = "bottom:" + (parseInt( valinstr ) + parseInt( height ) + 10) +"px;";
	}
	return style;
}

function getLongMessage( text ){
	if( text.length <= 140)
		return text;
	else
		return text.substring(0, 140) + "...";
}

function getTypeNotification( type ){
	switch(type){
		case "success": return "notif notif-success";
		case "danger": return "notif notif-danger";
		case "info": return "notif notif-info";
		case "warning": return "notif notif-warning";
		default: return "notif notif-default";
	}
}

function getOptionalMessage( title, message, fnSuccess, fnFailure ){
	let btnsuccess = new Elements('input',{type:'button', class:'btn-modal-option', value:'Aceptar'},[ ]);
	let btnfailure = new Elements('input',{type:'button', class:'btn-modal-option', value:'Cancelar'},[ ]);
	btnsuccess.addEventListener("click", function(){
		fnSuccess();
		$(".modal-window").remove();
	});
	btnfailure.addEventListener("click", function(){
		fnFailure();
		$(".modal-window").remove();
	});
	let e = new Modal('div',{class:'modal-window-content'},[
				new Elements('h1',{class:'modal-window-title'},[ title ]),
				new Elements('p',{class:'modal-window-text'},[message]),
				new Elements('div',{class:'modal-window-options'},[  btnfailure, btnsuccess ])
			]);
	document.body.appendChild(e);
}

function dropLoadMessage(){
	$( ".modal-window-load" ).hide( "highlight",{}, 1000, callback );
}

function callback() {
	setTimeout(function() {
		$( ".modal-window-load" ).remove();
	}, 1000 );
}

function getLoadMessage(){
	$("body").append( "<div class='modal-window-load'><div class='modal-window-content'><img src='https://i.redd.it/ounq1mw5kdxy.gif'></div><div>");
}

function getLoadMessageProgress(){
	let e = new Modal('div',{class:'modal-window-content'},[
		new Elements('progress',{id:'progressmsg',value:"1",max:"100"},[''])
		]);
	document.body.appendChild(e);
}

function getErrorMessage( error ){
	let e = new Modal('div',{class:'modal-window-content'},[
		new Elements('div',{class:'modal-window-image-error'},['<svg enable-background="new 0 0 512 512" id="Layer_1" viewBox="0 0 512 512" style="height: 30px;width: 30px;float: right;"><linearGradient gradientUnits="userSpaceOnUse" id="SVGID_1_" x1="256" x2="256" y1="512" y2="-9.094947e-013"><stop offset="0" style="stop-color:#E73827"></stop><stop offset="1" style="stop-color:#F85032"></stop></linearGradient><circle cx="256" cy="256" fill="url(#SVGID_1_)" r="256"></circle><path d="M268.7,256l119.6-119.6c3.2-3.2,3.2-8.3,0-11.4c-3.2-3.2-8.3-3.2-11.4,0L257.2,244.6L135.1,122.5  c-3.2-3.2-8.3-3.2-11.4,0c-3.2,3.2-3.2,8.3,0,11.4L245.8,256L123.7,378.1c-3.2,3.2-3.2,8.3,0,11.4c1.6,1.6,3.7,2.4,5.7,2.4  c2.1,0,4.1-0.8,5.7-2.4l122.1-122.1l119.6,119.6c1.6,1.6,3.7,2.4,5.7,2.4c2.1,0,4.1-0.8,5.7-2.4c3.2-3.2,3.2-8.3,0-11.4L268.7,256z" fill="#FFFFFF"></path></svg>']),
		new Elements('h1',{class:'modal-window-title'},[ "¡UPS!" ]),
		new Elements('p',{class:'modal-window-text'},[error]) ]);
	document.body.appendChild(e);
}

function getSuccessfulMessageWithText( text ){
	let e = new Modal('div',{class:'modal-window-content'},[
		new Elements('div',{class:'modal-window-image'},["<svg viewBox=\"0 0 32 32\" style=\"fill:#48DB71\"><path d=\"M1 14 L5 10 L13 18 L27 4 L31 8 L13 26 z\"></path></svg>"]),
		new Elements('h1',{class:'modal-window-title'},[ "¡LISTO!" ]),
		new Elements('p',{class:'modal-window-text'},[ text ]) ]);
	document.body.appendChild(e);
}

function getSuccessfulMessage(){
	let e = new Modal('div',{class:'modal-window-content'},[
		new Elements('div',{class:'modal-window-image'},["<svg viewBox=\"0 0 32 32\" style=\"fill:#48DB71\"><path d=\"M1 14 L5 10 L13 18 L27 4 L31 8 L13 26 z\"></path></svg>"]),
		new Elements('h1',{class:'modal-window-title'},[ "¡LISTO!" ]),
		new Elements('p',{class:'modal-window-text'},['La operación se hizo correctamente']) ]);
	document.body.appendChild(e);
}

var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
        return extendStatics(d, b);
    }
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var Elements = /** @class */ (function(){
	function Elements( type, attributes, children ){
		return this.createCustomElement( type, attributes, children );
	}
		
	Elements.prototype.createCustomElement = function( type, attributes, children ){
		var element = document.createElement( type );
		if(children !== undefined) this.addChildren( element, children );
		this.addAttributes(element, attributes);
		return element;
	};
		
	Elements.prototype.addAttributes = function(element, attrObj){
		for(let attr in attrObj){
			if(attrObj.hasOwnProperty( attr )) element.setAttribute( attr, attrObj[attr] );
		}
	};
		
	Elements.prototype.addChildren = function(element, children){
		for(var i = 0; i < children.length; i++){
			if(children[i].nodeType== 1 || children[i].nodetype==11) element.appendChild(children[i]);
			else element.innerHTML += children[i]
		}
	};
	return Elements;
}());
	
var Modal = /** @class */ (function (_super) {
    __extends(Modal, _super);
    function Modal(type, attributes, children) {
        var _this = _super.call(this, 'div', { class: 'modal-window' }, []) || this;
        Elements.prototype.addChildren(_this, [Elements.prototype.createCustomElement(type, attributes, children)]);
        _this.addEventListener('click', function (e) {
            if (e.target === this)
                Modal.prototype.closeModal.call(this);
        });
        return _this;
    }
    Modal.prototype.closeModal = function () {
        document.body.removeChild(this);
    };
    return Modal;
}(Elements));


var ModalLoad = /** @class */ (function (_super) {
    __extends(ModalLoad, _super);
    function ModalLoad(type, attributes, children) {
        var _this = _super.call(this, 'div', { class: 'modal-window-load' }, []) || this;
        Elements.prototype.addChildren(_this, [Elements.prototype.createCustomElement(type, attributes, children)]);
        return _this;
    }
    return ModalLoad;
}(Elements));


var FireworkLand = /** @class */ (function (_super) {
    __extends(FireworkLand, _super);
    function FireworkLand(type, attributes, children) {
        var _this = _super.call(this, 'div', { class: 'fireworks-window' }, []) || this;
        Elements.prototype.addChildren(_this, [Elements.prototype.createCustomElement(type, attributes, children)]);
        _this.addEventListener('click', function (e) {
            if (e.target === this)
            	FireworkLand.prototype.closeModal.call(this);
        });
        return _this;
    }
    FireworkLand.prototype.closeModal = function () {
        document.body.removeChild(this);
    };
    return FireworkLand;
}(Elements));