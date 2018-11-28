var OperationsElements = /** @class */ (function () {
	
    function OperationsElements(arraylst) {
        this.arraylst = arraylst;
    }
    
    OperationsElements.prototype.clearList = function () {
        for (var _i = 0, arraylst_3 = this.arraylst; _i < arraylst_3.length; _i++) {
            var itemClear = arraylst_3[_i];
            switch (jQuery("#" + itemClear).get(0).nodeName) {
                case "INPUT":
                    switch (jQuery("#" + itemClear).attr("type")) {
                        case "checkbox":
                        	jQuery("#" + itemClear).prop('checked', false);
                            break;
                        case "radio":
                        	jQuery("#" + itemClear).prop('checked', false);
                            break;
                        default:
                            jQuery("#" + itemClear).val("");
                            break;
                    }
                    break;
                case "SELECT":
                    jQuery("#" + itemClear)[0].selectedIndex = 0;
                    break;
                case "TEXTAREA":
                    jQuery("#" + itemClear).val("");
                    break;
            }
        }
    };
    
    OperationsElements.prototype.clearElements = function () {
        jQuery.each(this.arraylst, function (key, value) {
            switch (jQuery(value).get(0).nodeName) {
                case "INPUT":
                    switch (jQuery(value).attr("type")) {
                        case "checkbox":
                        	jQuery(value).prop('checked', false);
                            break;
                        case "radio":
                        	jQuery(value).prop('checked', false);
                            break;
                        default:
                            jQuery(value).val("");
                            break;
                    }
                    break;
                case "SELECT":
                    jQuery(value).selectedIndex = 0;
                    break;
                case "TEXTAREA":
                    jQuery(value).val("");
                    break;
            }
        });
    };
    
    OperationsElements.prototype.convertToObject = function (remain) {
        var objetoCreado = {};
        jQuery.each(this.arraylst, function (key, value) {
            switch (jQuery(value).get(0).nodeName) {
                case "INPUT":
                    switch (jQuery(value).attr("type")) {
                        case "checkbox":
                            {
                                var stringValue = jQuery(value).attr("id");
                                var cadena = stringValue.substring(remain, stringValue.length);
                                objetoCreado[cadena] = jQuery(value).is(":checked") ? true : false;
                            }
                            break;
                        case "radio":
                            {
                                var stringValue = jQuery(value).attr("id");
                                var cadena = stringValue.substring(remain, stringValue.length);
                                objetoCreado[cadena] = jQuery(value).is(":checked") ? true : false;
                            }
                            break;
                        default:
                            {
                                var stringValue = jQuery(value).attr("id");
                                var cadena = stringValue.substring(remain, stringValue.length);
                                objetoCreado[cadena] = jQuery(value).val();
                            }
                            break;
                    }
                    break;
                case "SELECT":
                    {
                        var stringValue = jQuery(value).attr("id");
                        var cadena = stringValue.substring(remain, stringValue.length);
                        objetoCreado[cadena] = jQuery(value).selectedIndex;
                    }
                    break;
                case "TEXTAREA":
                    {
                        var stringValue = jQuery(value).attr("id");
                        var cadena = stringValue.substring(remain, stringValue.length);
                        objetoCreado[cadena] = jQuery(value).val();
                    }
                    break;
            }
        });
        return objetoCreado;
    };
    return OperationsElements;
}());


var OperationsValidation = /** @class */ (function () {
	
    function OperationsValidation( isVisible, styleerror, mailmesage ) {
        this.styleerror = styleerror;
        this.isVisible = isVisible;
        this.mailmesage = mailmesage;
    }
    
    OperationsValidation.prototype.getEmail = function ( item, typeDOM ) {
        var valoritem = typeDOM == true ? jQuery(item).val() : jQuery("#" + item).val();
        if (valoritem === "") {
            typeDOM == true ? jQuery(item).css(this.styleerror) : jQuery("#" + item).css(this.styleerror);
            if (this.isVisible)
                typeDOM == true ? jQuery("#err" + jQuery(item).attr("id")).show("slow") : jQuery("#err" + item).show("slow");
            return false;
        }
        if (this.getExpresion(valoritem, "c") === false) {
            typeDOM == true ? jQuery(item).css(this.styleerror) : jQuery("#" + item).css(this.styleerror);
            if (this.mailmesage.confemail) 
                typeDOM == true ? jQuery("#err-email-" + jQuery(item).attr("id")).show("slow") : jQuery("#err-email-" + item).show("slow");
            if (this.isVisible)
                typeDOM == true ? jQuery("#err" + jQuery(item).attr("id")).show("slow") : jQuery("#err" + item).show("slow");
            return false;
        }
        return true;
    };
    
    OperationsValidation.prototype.isPassword = function (obj) {
        if (/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&#/()=?¡¿+-.,;:{}|])(^[A-Za-z\d$@$!%*?&#/()=?¡¿+-.,;:{}|]+$)/
            .test(obj.value)
            && obj.len >= 8) {
            $(obj.onTrue).show("fast");
            $(obj.onFalse).hide("fast");
        }
        else {
            $(obj.onTrue).hide("fast");
            $(obj.onFalse).show("fast");
        }
    };
    
    OperationsValidation.prototype.getValueText = function (item, typeDOM) {
        var valoritem = typeDOM == true ? jQuery(item).val() : jQuery("#" + item).val();
        if (valoritem === "") {
            typeDOM == true ? jQuery(item).css(this.styleerror) : jQuery("#" + item).css(this.styleerror);
            if (this.isVisible)
                typeDOM == true ? jQuery("#err" + jQuery(item).attr("id")).show("slow") : jQuery("#" + item).show("slow");
            return false;
        }
        return true;
    };
    
    OperationsValidation.prototype.getValueSelect = function (item, typeDOM) {
        var valoritem = typeDOM == true ? jQuery(item).val() : jQuery("#" + item).val();
        if (valoritem === "" || valoritem === "0" || valoritem === undefined) {
            typeDOM == true ? jQuery(item).css(this.styleerror) : jQuery("#" + item).css(this.styleerror);
            if (this.isVisible)
                typeDOM == true ? jQuery("#err" + jQuery(item).attr("id")).show("slow") : jQuery("#" + item).show("slow");
            return false;
        }
        return true;
    };
    
    OperationsValidation.prototype.getExpresion = function (value, type) {
        if (jQuery.trim(type) === "n")
            return !/^([1-9])*$/.exec(value) ? true: false;
        else if (jQuery.trim(type) === "d")
            return !/^[1-9]+([.])?([0-9]+)?$/.exec(value) ? true : false;
        else if (jQuery.trim(type) === "c")
            return !/^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,3})$/.exec(value) ? false : true;
        else if (jQuery.trim(type) === "f")
            return !/^(19|20)[0-9]{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/.exec(value) ? false : true;
    };
    
    return OperationsValidation;
}());


var Validacion = /** @class */ (function () {
    
	function Validacion(objConfig, isElement) {
        this.isElement = isElement;
        this.lstelements = objConfig.lstE;
        this.arraylst = objConfig.lstS;
        this.stylenormal = objConfig.stlN;
        this.type = objConfig.show;
        this.styleerror = objConfig.stlE;
        this.mailmesage = objConfig.other;
    }
    
    Validacion.prototype.getType = function () {
        var i = 0;
        var operations = new OperationsValidation( this.type, this.styleerror, this.mailmesage);
        var values = new Array();
        if (this.isElement) {
            jQuery.each(this.lstelements, function (key, value) {
                var atributoId = jQuery(value).attr("id");
                switch (jQuery(value).get(0).nodeName) {
                    case "INPUT":
                        {
                            switch (jQuery(value).attr("type")) {
                                case "email":
                                	values[i] = operations.getEmail(value, true);
                                    break;
                                case "text":
                                	values[i] = operations.getValueText(value, true);
                                    break;
                                case "tel":
                                	values[i] = operations.getValueText(value, true);
                                    break;
                                case "password":
                                	values[i] = operations.getValueText(value, true);
                                    break;
                                case "file":
                                	values[i] = operations.getValueText(value, true);
                                    break;
                                case "checkbox":
                                	values[i] = jQuery(value).is(":checked") ? true : false;
                                    break;
                                case "radio":
                                	values[i] = jQuery(value).is(":checked") ? true : false;
                                    break;
                                case "date":
                                	values[i] = operations.getExpresion(jQuery(value).val(), "f");
                                    break;
                            }
                        }
                        break;
                    case "TEXTAREA":
                    	values[i] = operations.getValueText(value, true);
                        break;
                    case "SELECT":
                    	values[i] = operations.getValueSelect(value, true);
                        break;
                    default: break;
                }
                
                i++;
            });
        }
        
        for (var _i = i, arraylst_1 = this.arraylst; _i < arraylst_1.length; _i++) {
            var datoItem = arraylst_1[_i];
            switch (jQuery("#" + datoItem).get(0).nodeName) {
                case "INPUT":
                    {
                        switch (jQuery("#" + datoItem).attr("type")) {
                            case "email":
                            	values[_i] = operations.getIEmail(datoItem, false);
                                break;
                            case "text":
                            	values[_i] = operations.getValueText(datoItem, false);
                                break;
                            case "tel":
                            	values[_i] = operations.getValueText(datoItem, false);
                                break;
                            case "password":
                            	values[_i] = operations.getValueText(datoItem, false);
                                break;
                            case "file":
                            	values[_i] = operations.getValueText(datoItem, false);
                                break;
                            case "checkbox":
                            	values[_i] = jQuery("#" + datoItem).is(":checked") ? true : false;
                                break;
                            case "radio":
                            	values[_i] = jQuery("#" + datoItem).is(":checked") ? true : false;
                                break;
                            case "date":
                            	values[_i] = operations.getExpresion(jQuery("#" + datoItem).val(), "f");
                                break;
                        }
                    }
                    break;
                case "TEXTAREA":
                	values[_i] = operations.getValueText(datoItem, false);
                    break;
                case "SELECT":
                	values[_i] = operations.getValueSelect(datoItem, false);
                    break;
                default: break;
            }
        }
        var desition = values.filter(function (item) {
            return item == false;
        });
        return desition.length > 0 ? false : true;
    };
    return Validacion;
}());


var onEmpty = function( objectConfg ) {
	if (objectConfg != undefined) {
		
		var arraylst = objectConfg.lstString != undefined ? objectConfg.lstString : [];
		var stylenormal = objectConfg.normalStyle != undefined ? objectConfg.normalStyle: { "border" : "1px solid #eee" };
		var type = objectConfg.showErrors !== undefined ? objectConfg.showErrors : false;
		var styleerror = objectConfg.errorStyle != undefined ? objectConfg.errorStyle: {"border" : "1px solid red"};
		var mailmesage = objectConfg.otherConf != undefined ? objectConfg.otherConf: {"confemail" : false,"elements" : false};
		var lstelements = objectConfg.lstElements != undefined ? objectConfg.lstElements: undefined;
		
		var elementsfist;
		
		var config = { 
				"lstS":arraylst,
				"stlN":stylenormal,
				"show":type,
				"stlE":styleerror,
				"other":mailmesage,
				"lstE":lstelements
		};
		
		if ( lstelements != undefined ) {
			jQuery.each( lstelements, function( key, value ) {
				jQuery( value ).css( stylenormal );
				if ( type ) {
					jQuery("#err" + jQuery( value ).attr("id") ).hide("slow");
				}
				if (mailmesage.confemail) {
					jQuery("#err-email-" + jQuery(value).attr("id")).hide("slow");
				}
			});
			
			var validationes = new Validacion( config, true);				
			return validationes.getType();
		}
		
		for (var _i = 0, arraylst_2 = arraylst; _i < arraylst_2.length; _i++) {
			var valoritem = arraylst_2[_i];
			jQuery("#" + valoritem).css( stylenormal );
			if (type) jQuery("#err" + valoritem ).hide("slow");
			if (mailmesage.confemail) jQuery("#err-email-" + valoritem).hide("slow");
		}
		
		var validationes = new Validacion(config, false);
		return validationes.getType();
	}
	
	return false;
};

jQuery(".data-numeric").keypress(function() {
	if (!/^([1-9])*$/.exec(jQuery(this).val())){
		jQuery(this).val("");
	}
});

jQuery(".data-decimal").keypress(function() {
	if (!/^[1-9]+([.])?([0-9]+)?$/.exec(jQuery(this).val())) {
		jQuery(this).val("");
	}
});

	
var clearList = function( lstString ){
	var execution = new OperationsElements(lstString);
	execution.clearList();
}

var clearElements = function(lstElements) {
	var execution = new OperationsElements(lstElements);
	execution.clearElements();
}

var convertToClass = function(classtoConvert, objeto) {
	jQuery.each(objeto, function(key, value) {
		classtoConvert[key] = value;
	});
	return classtoConvert;
}

var convertToObject = function( arraylst, remain ) {
	var execution = new OperationsElements( arraylst );
	return execution.convertToObject( remain );
}
