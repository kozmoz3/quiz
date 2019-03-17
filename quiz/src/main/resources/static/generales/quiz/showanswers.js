
	function getAnswers(answers, index){
		for(var i = 0; i<answers.length; i++){
			if(answers[i] == index)
				return true;
		}
		return false;
	}
	
	gerResponses();
	
	function gerResponses(){
		let correcta = (100 * misrespuetas[0].correctas) / (misrespuetas.length);
		let incorrecta = 100 - correcta;
		get((misrespuetas[0].calificacion * 10), correcta, incorrecta );
		$.each(misrespuetas, function(indexham, value){
			let answers = value.answers.split(",");
			let meanswers = value.prespuestas.split(",");
			let respuestas =  new Element('div',{ class:'options' },[ ]);
			$.each(value.options.split("##&&"), function(index, options){
				let clase = "", mrespuesta = "";
				if( getAnswers( answers, index ) )
					clase = "active-res";
				if( getAnswers( meanswers, index ) )
					mrespuesta = "> ";
				respuestas.append(new Element("div",{ class:"form-row " + clase},[
					new Element("div",{ class:"col-auto", style:"padding:5px;"},[
						new Element("p",{},[mrespuesta + options])
					])
				]));
			});
			let element = new Element("div",{ class:"contextual"},[
				new Element("div",{ class:"question"},[
					value.question,
					new Element("div",{ class:"options"},[
						new Element("div",{ class:"options"},[ respuestas ])
					])
				]),
				new Element("hr",{},[])
			]);
			$("#response").append(element);
		});
	}
	
	
	function get(calificacion, correcta, incorrecta){
		let e = new Elements('div',{},[
			new Elements("div",{id:"__panelresponse",class:"modal-window-panel-content"},[
				new Elements("div",{class:"rows"},[
					new Elements("div",{class:"cols"},[
						new Elements("canvas",{ id:"resultados-chart", class:"chartjs", style:"width: 80%;height: 80%;"},[])
					]),
					new Elements("div",{class:"cols"},[
						new Elements("div",{class:"contenidos"},[
							new Elements("h2",{},[calificacion + "%"]),
							new Elements("div",{ style:"display: grid;"},[
								new Elements("label",{"data-res-pregp":""},[misrespuetas.length + " Preguntas"]),
								new Elements("label",{"data-res-pregc":""},[correcta + "% preguntas correctas"]),
								new Elements("label",{"data-res-pregi":""},[incorrecta + "% preguntas incorrectas"]),
								new Elements("label",{},["Calificacion: ",
									new Elements("span",{"data-res-calif":""},[calificacion/10])
								])
							])
						])
					])
				]),
				new Elements("div",{class:"content-afoot"},[
					new Elements("span",{class:"aprovado", "data-res-status":""},["Aprobado"]),
				])
			])
		]);
		$("#response").append(e);
		getGraphic( correcta, incorrecta );
	}
	
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