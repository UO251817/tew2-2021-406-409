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
	//this.tbcandidatosFiltro = null;
	this.tbUsuarios = null;
	
	//Carga los datos del servicio
	this.load = function() {
		
		this.tbPublicacionesMis = PublicacionServicesRs.getPublicaciones({type: 'misPublis', email : localStorage.getItem('usuario')});
		this.tbPublicacionesAmigos = PublicacionServicesRs.getPublicacionesAmigos({type: 'amigosPublis', email: localStorage.getItem('usuario')});
		this.tbSolcitudes = AmigosServicesRs.getAmigos({type: 'solicitudes', email: localStorage.getItem('usuario')});
		this.tbCandidatos = UsuariosServicesRs.getUsuarios2({filtro: "''" ,email: localStorage.getItem('usuario')});
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
	
	//Filtramos los amigos
	this.filtrar = function(fil){
		var f = UsuariosServicesRs.getUsuarios2({filtro: "'"+fil+"'" ,email: localStorage.getItem('usuario')});
		return f;
	}
	
	//Guardamos la peticion
	this.solAmistad = function(solicitud){
		UsuariosServicesRs.guardarAmigo({
			$entity: solicitud,
			$contentType: "application/json"
		});
		this.load();
	}
	
	//Aceptar peti
	this.aceptarPeti = function(email_amigo){
		AmigosServicesRs.aceptar({email_usuario: email_amigo, email_amigo: localStorage.getItem('usuario')});
		this.load();
	}
};
 

/////////////Clase que contiene la gestión de la capa Vista//////////

function View(){
	var usu = localStorage.getItem('usuario');
	
	//////PUBLICAICONES////////////	
	//Lista de mis publis 
	this.listMisPublis = function (lista){
		if(lista.length==0){
			$("#tablaMisPubli").html("No tienes publicaciones");
		}
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
		if(lista.length==0){
			$("#tablaAmigosPubli").html("No hay publicaciones");
		}
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
		location.href = "publisTodas.html";
		return publi;		
	}
	
	//////////AMIGOS//////////////////////////
	
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
						+ amigo.email_usuario  
						+ "<td> <button type='submit' class='btn btn-default AceptarAmigo' id='AceptarAmigo'>Aceptar</button></td>" +"</td></tr>");		
			}
		}
	}

	this.añadirAmigos = function (lista){
		if(lista.length==0){
			$("#tablaAddAmi").html("No hay amigos disponibles");
		}
		else{
			$("#tablaAddAmi").html("");
			$("#tablaAddAmi").html(
					"<thead>" + "<tr>" + "<th>Email</th>" + "<th>Nombre</th>" +"<th>Añadir</th>"
					+"</tr>" + "</thead>" + "<tbody>" + "</tbody>");

			for (var i in lista) {
				var amigo = lista[i];
				if(amigo.rol == "usuario"){
					$("#tablaAddAmi tbody").append("<tr> <td>"
							+ amigo.email + "</td>" 
							+ "<td>" + amigo.nombre 
							+ "<td> <button type='submit' class='btn btn-default AñadirAmigo' id='AñadirAmigo'>Enviar peticion</button></td>" + "</tr>");		
				}
			}
		}
	}

	this.filtAmi = function(){
		var filtro = $('#filtro').val();
		return filtro;		
	}
	
	/////////////USUARIOS////////
	
	this.solAmis = function(fila){
		var d = fila.closest('tr').find('td').get(0).innerHTML;
		return d;	
		
	}
	
	this.aceptarPetis = function(fila){
		var d = fila.closest('tr').find('td').get(0).innerHTML;
		return d;	
		
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


		$("#publisnuevas").bind("submit",
				// Método que gestiona el evento de clickar el botón submit del
				// formulario
				function(event) {
			var publicacion = that.view.cargaPubli();
			that.model.add(publicacion);
			//Listamos publis
			that.view.listMisPublis(that.model.tbPublicacionesMis);
			that.view.listPublisAmigos(that.model.tbPublicacionesAmigos);
		});


		$("#filtro").on("keyup",
				
				function(event) {
			var filtro = that.view.filtAmi();
			var listFil = that.model.filtrar(filtro);
			if(filtro ===""){
				that.view.añadirAmigos(that.model.tbCandidatos);
			}else{
				that.view.añadirAmigos(listFil);
				
			}
		});
		

		$("#tablaAddAmi").on("click",".AñadirAmigo",
				function(event) {
			var email_ami = that.view.solAmis($(this));
			alert("Peticion enviada a " + email_ami);
			var solicitud = {
					
					email_usuario: localStorage.getItem('usuario'),
					email_amigo: email_ami
			}
			that.model.solAmistad(solicitud);
			that.view.añadirAmigos(that.model.tbCandidatos);
		});
		
		
		$(".AceptarAmigo").on("click",
				function(event) {
			var email_ami = that.view.aceptarPetis($(this));
			that.model.aceptarPeti(email_ami);
			location.reload(true);
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
