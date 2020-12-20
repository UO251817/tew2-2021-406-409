/**
 * 
 */
$(function() {
	 //Creamos el modelo con los datos y la conexión al servicio web.
	 var model = new Model();
	 model.load();
}) 

/////////////Clase que contiene el Modelo de la aplicación//////////

function Model() {
	
	var usu = localStorage.getItem('usuario');
	
	//Lista de publicaciones.
	this.tbPublicaciones = null;
	this.tbPublicacionesMis = null;
	this.tbPublicacionesAmigos = null;
	this.tbSolicitudes = null;
	this.tbCandidatos = null;
	this.tbUsuarios = null;
	
	//Carga los datos del servicio
	this.load = function() {
		
		this.tbPublicacionesMis = PublicacionServicesRs.getPublicaciones({type: 'misPublis', email : localStorage.getItem('usuario')});
		this.tbPublicacionesAmigos = PublicacionServicesRs.getPublicacionesAmigos({type: 'amigosPublis', email: localStorage.getItem('usuario')});
		this.tbSolcitudes = AmigosServicesRs.getAmigos({type: 'solicitudes', email: localStorage.getItem('usuario')});
		this.tbCandidatos = UsuariosServicesRs.getUsuarios2({filtro: "''",email: localStorage.getItem('usuario')});
		this.tbUsuarios = UsuariosServicesRs.getUsuarios();
		
	}
	
	
	//Añadir nueva Publicacion
	this.add = function(publicacion) {
		// Llamamos al servicio de alta de publi
		PublicacionServicesRs.save({
			$entity : publicacion,
			$contentType : "application/json"
		}); 
		// Recargamos la lista de Publis.
		this.load();
	}
};
 

/////////////Clase que contiene la gestión de la capa Vista//////////

function View(){
	var usu = localStorage.getItem('usuario');
	
	//PUBLICAICONES	
	//Lista de mis publis 
	this.listMisPublis = function (lista){
		$("#tablaMisPubli").html("");
		$("#tablaMisPubli").html(
				"<thead>" + "<tr>" + "<th>Email</th>" + "<th>Titulo</th>"+"<th>Texto</th>" + "<th>Fecha</th>"
				+"</tr>" + "</thead>" + "<tbody>" + "</tbody>");
		
		for (var i in lista) {
			var publica = lista[i];
			$("#tablaMisPubli tbody").append("<tr> <td>"
                    + publica.email + "</td>" 
                    + "<td>" + publica.titulo + "</td>" 
                    + "<td>" + publica.texto + "</td>"
                    + "<td>" + publica.fecha_legible   +"</td></tr>");		
		}
	}
	
	//Lista de publis de amigos
	this.listPublisAmigos = function (lista){
		$("#tablaAmigosPubli").html("");
		$("#tablaAmigosPubli").html(
				"<thead>" + "<tr>" + "<th>Email</th>" + "<th>Titulo</th>"+"<th>Texto</th>" + "<th>Fecha</th>"
				+"</tr>" + "</thead>" + "<tbody>" + "</tbody>");
		
		for (var i in lista) {
			var publica = lista[i];
			$("#tablaAmigosPubli tbody").append("<tr> <td>"
                    + publica.email + "</td>" 
                    + "<td>" + publica.titulo + "</td>" 
                    + "<td>" + publica.texto + "</td>"
                    + "<td>" + publica.fecha_legible  +"</td></tr>");		
		}
	}
	
	//Crear nueva publi
	this.cargaPubli = function(){
		var date = new Date();
		var tim = date.getTime();
		var publi= {
				email: localStorage.getItem('usuario'),
				titulo: $('#titulo').val(),
				texto: $('#texto').val(),
				fecha: tim
		};
		return publi;		
	}
	
	//AMIGOS
	
	this.verSolicitudes = function (lista){

		if(lista.length==0){
			$("#tablaSolAmi").html("No hay peticiones pendientes");
		}
		else{
			$("#tablaSolAmi").html("");
			$("#tablaSolAmi").html(
					"<thead>" + "<tr>" + "<th>Email</th>" + "<th>Aceptar</th>" +"</tr>" + "</thead>" + "<tbody>" + "</tbody>");
			for (var i in lista) {
				var amigo = lista[i];
				$("#tablaSolAmi tbody").append("<tr> <td>"
						+ amigo.email_usuario +"</td></tr>");		
			}
		}
	}

	this.añadirAmigos = function (lista){
		$("#tablaAddAmi").html("");
		$("#tablaAddAmi").html(
				"<thead>" + "<tr>" + "<th>Email</th>" + "<th>Nombre</th>" +"<th>Añadir</th>"
				+"</tr>" + "</thead>" + "<tbody>" + "</tbody>");
		
		for (var i in lista) {
			var amigo = lista[i];
			if(amigo.rol == "usuario"){
			$("#tablaAddAmi tbody").append("<tr> <td>"
                    + amigo.email + "</td>" 
                    + "<td>" + amigo.nombre +"</td></tr>");		
			}
		}
	}
	
	//USUARIOS
	this.todosUsuarios = function (lista){
		//$("#tablaTodosUsuarios").html("");
		$("#tablaTodosUsuarios").html(
				"<thead>" + "<tr>" + "<th>Email</th>" + "<th>Borrar</th>"
				+"</tr>" + "</thead>" + "<tbody>" + "</tbody>");

		for (var i in lista) {
			var usu = lista[i];
			if(usu.rol == "usuario"){
				$("#tablaTodosUsuarios tbody").append("<tr> <td>"
						+ usu.email +"</td></tr>");
			}		
		}
	}


};


/////////////////Controller///////////////////

function Controller(varmodel, varview) {
	// Definimos una copia de this para evitar el conflicto con el this (del
	// objeto que recibe el evento)
	// en los manejadores de eventos
	var that = this;
	// referencia al modelo
	this.model = varmodel;
	// refefencia a la vista
	this.view = varview;
	// Funcion de inicialización para cargar modelo y vista, definición de manejadores
	this.init = function() {
		// Cargamos la lista de publicaciones 
		this.model.load();
		// Repintamos la lista de publis
		this.view.listMisPublis(this.model.tbPublicacionesMis);
		this.view.listPublisAmigos(this.model.tbPublicacionesAmigos);
		this.view.verSolicitudes(this.model.tbSolcitudes);
		this.view.añadirAmigos(this.model.tbCandidatos);
		this.view.todosUsuarios(this.model.tbUsuarios);
		

		$("#publisnuevas").bind("submit",
				// Método que gestiona el evento de clickar el botón submit del
				// formulario
			function(event) {
				var publicacion = that.view.cargaPubli();
				that.model.add(publicacion);
				//Listamos publis
				that.view.listMisPublis(that.model.tbPublicacionesMis);
				window.location.href = "misPublis.html";		
				});
		}
	}; 
	
	
$(function() {
	// Creamos el modelo con los datos y la conexión al servicio web.
	var model = new Model();
	// Creamos la vista que incluye acceso al modelo. 
	var view = new View();
	// Creamos el controlador
	var control = new Controller(model, view);
	// Iniciamos la aplicación
	control.init();
}); 
