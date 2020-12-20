/**
 * 
 */
function Model() {
	//Lista de usus.
	this.ListaUsuarios= null;


	//Carga los datos del servicio sobreescribiendo el dato this.lUsuarios.
	this.load = function() {
		this.ListaUsuarios = UsuariosServicesRs.getUsuarios();
	}
	this.comprueba = function (user, pass) {
		for(var a in this.ListaUsuarios){
			var usu = this.ListaUsuarios[a];
			if((usu.email == user) && (usu.passwd == pass)){
				window.localStorage.setItem('usuario', usu.email);
				return usu.rol;
			}
		}
	}
};


function Controller(varmodel) {
	var that = this;
	this.model = varmodel;
	this.init = function() {
		// Cargamos la lista de usus del servicio

		this.model.load();
		$("#btnEnviar").click(function(event){
			
					var user=$("#username").val();
					var pass=$("#passwd").val();
					var esRol = that.model.comprueba(user,pass);
					if (esRol == "administrador"){

						parent.document.location.href = "indexAdmin.html";
						alert("H");
						
					} 
					else if(esRol == "usuario"){

						parent.document.location.href = "indexUsu.html";
						alert("HOLA");
						
					}
					else{
						alert("Login incorrecto");
					}
				});

		$("#fuera").click(function(event){
			alert("EStamos saliendo");
					window.localStorage.removeItem('usuario');
			alert("Devolovemos");
					window.location.href="index.html";
				})
	}
};	
$(function() {
	// Creamos el modelo con los datos y la conexión al servicio web.
	var model = new Model();
	// Creamos el controlador
	var control = new Controller(model);
	// Iniciamos la aplicación
	control.init();
});