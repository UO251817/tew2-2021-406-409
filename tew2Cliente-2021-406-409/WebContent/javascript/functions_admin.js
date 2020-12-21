/**
 * 
 */
function Model() {
	
	var user = localStorage.getItem('usuario');
	
	//Lista de usus.
	this.ListaUsuarios= null;


	//Carga los datos del servicio sobreescribiendo el dato this.lUsuarios.
	this.load = function() {
		this.ListaUsuarios = UsuariosServicesRs.getUsuarios();
		for(var a in this.ListaUsuarios){
			var usu = this.ListaUsuarios[a];
			if(usu.rol == 'administrador'){
				this.ListaUsuarios.splice(usu,1);
			}
		}
	}
	
	

	// Eliminación un usuario existente
	this.remove = function(i) {
		var email = this.ListaUsuarios[i].email;
		// Llamamos al servicio de borrado de usuario
		UsuariosServicesRs.delete({email : email});
	}
	
	

	this.find = function(email) {
		function search(u) {
			return u.email == email;
		}
		var usu = this.ListaUsuarios.findByEmail(search);
		return usu;
	}
};


function View(){
	
	
	var usu = localStorage.getItem('usuario');
	
	
	this.todosUsuarios = function (lista){
		$("#tablaTodosUsuarios").html(
				"<thead>" 
				+ "<tr>" 
				+ "<th>Email</th>" 
				+ "<th>Borrar</th>"
				+"</tr>" + "</thead>" + "<tbody>" + "</tbody>");

		
		for (var i in lista) {
			var usu = lista[i];
			if(usu.rol == "usuario"){
				$("#tablaTodosUsuarios tbody").append("<tr> <td>"
						+ usu.email 
						+ "<td><input class='form-check-input position-static selec1' type='checkbox' id='"
                            + i
                            + "'></td><td>"
						+"</td></tr>");
			}		
		}
	}
	
}

function Controller(varmodel, varview) {
	var that = this;
	this.model = varmodel;
	this.view = varview;
	
	this.init = function() {
		
		this.model.load();
		this.view.todosUsuarios(that.model.ListaUsuarios);

		$("#btnBorrar").click(

				function(event){
					var seleccionado = document
					.querySelectorAll('#tablaTodosUsuarios [type=checkbox]:checked');

					for ( var i in seleccionado) {
						var usu = seleccionado[i];
						if (typeof usu.id === 'undefined') {
						} else {
							that.model.remove(usu.id);
						}
					}
					that.model.load();
					that.view.todosUsuarios(that.model.ListaUsuarios);
					location.reload(true);

				});	
	}


	// Manejador del checkbox de selección de usuarios
	$(".selec").change(
			// Método que gestiona el evento de clickar en el evento
			function(event) {
				if (document.getElementById("selec").checked === true) {
					$(".selec1").prop("checked", true);
				} else if (document.getElementById("selec").checked === false) {
            $(".selec1").prop("checked", false);
        }
    });
};	

$(function() {
	// Creamos el modelo con los datos y la conexión al servicio web.
	var model = new Model();
	var view = new View();
	// Creamos el controlador
	var control = new Controller(model,view);
	// Iniciamos la aplicación
	control.init();
});