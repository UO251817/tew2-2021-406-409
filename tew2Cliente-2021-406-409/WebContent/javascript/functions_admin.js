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


function View(){
	
	
	var usu = localStorage.getItem('usuario');
	
	
	
	this.todosUsuarios = function (lista){
		$("#tablaTodosUsuarios").html(
				"<thead>" + "<tr>" + "<th>Email</th>" + "<th>Borrar</th>"
				+"</tr>" + "</thead>" + "<tbody>" + "</tbody>");

		for (var i in lista) {
			var usu = lista[i];
			if(usu.rol == "usuario"){
				$("#tablaTodosUsuarios tbody").append("<tr> <td>"
						+ usu.email  
						+ "<td> <button type='submit' class='btn btn-default borrarUsuario' id='borrarUsuario'>Borrar</button></td>" 
						+"</td></tr>");
			}		
		}
	}
	
}

function Controller(varmodel) {
	var that = this;
	this.model = varmodel;
	this.init = function() {
		
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