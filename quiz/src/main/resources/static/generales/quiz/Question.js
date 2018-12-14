var Question = /** @class */ (function () {
    function Question(question, options, answers, message, idquestion, idquiz, type, score) {
        this.question = question;
        this.options = options;
        this.answers = answers;
        this.message = message;
        this.idquestion = idquestion;
        this.idquiz = idquiz;
		this.type = type;
		this.score = score;
    }
    
    Question.prototype.setObject = function ( model ) {
    	this.question = model.question;
        this.options = model.options;
        this.answers = model.answers;
        //this.message = model.message;
        this.idquestion = model.idquestion;
        //this.idquiz = model.idquiz;
		this.type = model.type;
		this.score = model.score;
    };
    return Question;
}());

var QuestionXLSX = /** @class */ (function () {
    function QuestionXLSX(question, options, answers, message, idquiz, type, score) {
        this.question = question;
        this.options = options;
        this.answers = answers;
        this.message = message;
        this.idquiz = idquiz;
		this.type = type;
		this.score = score;
    }
    return QuestionXLSX;
}());

var Answer = /** @class */ (function () {
    function Answer(idquestion, answers) {
        this.answers = answers;
        this.idquestion = idquestion;
    }
    return Answer;
}());
var Registroquiz = /** @class */ (function () {
    function Registroquiz(countquest,calificacion,porcent,respuestas,timer, lstres) {
		//this.idalumno = idalumno;
		this.countquest = countquest;
		this.calificacion = calificacion;
		this.porcent = porcent;
		this.respuestas = respuestas;
		this.timer = timer;
		//this.lstans = lstans;
		this.lstres = lstres;
    }
    return Registroquiz;
}());
var Quiz = /** @class */ (function () {
    function Quiz(lstQuestion) {
        this.lstQuestion = lstQuestion;
    }
    Quiz.prototype.getObject = function () {
        var questions = [];
        this.lstQuestion.forEach(function (item) {
			var question = {
				q: item.question,
				options: item.options,
				answers: item.answers,
				message: item.message,
				idquestion: item.idquestion,
				idquiz: item.idquiz,
				type: item.type
			};
            questions.push(question);
        });
        return questions;
    };
    return Quiz;
}());
var Resource = /** @class */ (function () {
    function Resource() {
    }
    Resource.prototype.getRandom = function (lista) {
        return lista.sort(function () { return Math.random() - 0.5; });
    };
	Resource.prototype.getNewArray = function (maximo, arrreglo) {
        var newLst=[];
        var list = this.getRandom(arrreglo);
        list.forEach(function (item) {
            if (maximo >= 1) {
                newLst.push(item);
				maximo--;
            }
        });
		return newLst;
    };
	Resource.prototype.getCampos = function (arrreglo) {
		var options = [];
		var answers=[];
		for(var i = 0; i < arrreglo.length; i++){
			var checRadio = $(arrreglo[i]).find("input[id*=corr]");
			var input = $(arrreglo[i]).find("input[type='text']");
			if( $(checRadio[0]).is(":checked") ) answers.push(i);
			options.push( $(input).val() );
		}
		return [ options, answers ];
	};
	
	Resource.prototype.isEmptyList = function (lista){
		var value = true;
		$.each(lista,function(i, itmelem){ if( $(itmelem).val() == "" ) value = false; });
		return value;
	}
	
	Resource.prototype.getCamposXLSX = function (arrreglo) {
		var options = [];
		var answers=[];
		for(var i = 0; i < arrreglo.length; i++){
			var checRadio = $(arrreglo[i]).find("input[id*=xlsxcorr]");
			var input = $(arrreglo[i]).find("input[type='text']");
			if( $(checRadio[0]).is(":checked") ) answers.push(i);
			options.push( $(input).val() );
		}
		return [ options, answers ];
	};
	
	Resource.prototype.getTypeXLSX = function (arrreglo) {
		for(var i = 0; i < arrreglo.length; i++){
			var checRadio = $(arrreglo[i]).find("input[id*=xlsxcorr]");
			return $(checRadio[0]).attr("type");
		}
	};
	
    return Resource;
}());
var Element = /** @class */ (function(){
	function Element( type, attributes, children ){
		return this.createCustomElement( type, attributes, children );
	}
		
	Element.prototype.createCustomElement = function( type, attributes, children ){
		var element = document.createElement( type );
		if(children !== undefined) this.addChildren( element, children );
		this.addAttributes(element, attributes);
		return element;
	};
		
	Element.prototype.addAttributes = function(element, attrObj){
		for(let attr in attrObj){
			if(attrObj.hasOwnProperty( attr )) element.setAttribute( attr, attrObj[attr] );
		}
	};
		
	Element.prototype.addChildren = function(element, children){
		for(var i = 0; i < children.length; i++){
			if(children[i].nodeType== 1 || children[i].nodetype==11) element.appendChild(children[i]);
			else element.innerHTML += children[i]
		}
	};
	return Element;
}());


