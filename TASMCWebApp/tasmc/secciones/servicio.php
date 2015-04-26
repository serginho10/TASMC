<?php
	
	session_start();

	include_once("clases/BD.php");


	echo '
			<div class="form-group col-md-offset-1">
			  	<div class="col-md-2">
			  		<input id="nombre" name="nombre" type="text" placeholder="Nombre" class="form-control input-md">
			  		<input id="tipo" name="tipo" type="text" placeholder="Tipo" class="form-control input-md">
			  	</div>
			  	<div class="col-md-2">
			  		<input id="precios" name="precios" type="text" placeholder="Precios" class="form-control input-md">
			  		<input id="telefono" name="telefono" type="text" placeholder="Telefono" class="form-control input-md">
			  	</div>
			  	<div class="col-md-2">
			  		<input id="local" name="local" type="text" placeholder="Local" class="form-control input-md">
			  		<input id="horario" name="horario" type="text" placeholder="Horario" class="form-control input-md">
			  	</div>
			  	<div class="col-md-2">
			  		<input id="categoria" name="categoria" type="text" placeholder="Categoría" class="form-control input-md">
			  	</div>
			  	<div class="col-md-2">
			    	<button id="singlebutton" name="singlebutton" onclick="agregar();" class="btn btn-primary">Agregar</button>
			  	</div>
			</div>
			';
	echo '
		<div class="table-header">
			<table class="table">
				<thead>
					<tr>	
						<th width="5%">id</th>
						<th width="15%">Nombre</th>
						<th width="12.5%">Tipo</th>
						<th width="10%">Precios</th>
						<th width="10%">Telefono</th>
						<th width="10%">Local</th>
						<th width="10%">Horario</th>
						<th width="12.5%">Categoria</th>
						<th width="2.5%"></th>
						<th width="2.5%"></th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="tabla">
			<table class="table table-hover table-strigger">
				<tbody>';

				$bd = new BD("127.0.0.1","root","root","tasmc");
				if(isset($_SESSION['eliminar'])){
					$bd->consulta("delete from Servicio where idServicio = ".$_SESSION['eliminar']);	
					echo '<script>alert("Se elimino el registro '.$_SESSION['eliminar'].'");</script>';
					unset($_SESSION['eliminar']);
				}
				if(isset($_SESSION['update'])){
					$bd->consulta("update Servicio set nombreLugar='".$_SESSION['nombre']."',tipo='".$_SESSION['tipo']."',rangoPrecios='".$_SESSION['precios']."',telefono='".$_SESSION['telefono']."',local='".$_SESSION['local']."',horario='".$_SESSION['horario']."',categoria='".$_SESSION['categoria']."' where idServicio=".$_SESSION['id']);	
					echo '<script>alert("Se actualizo el registro '.$_SESSION['nombre'].'");</script>';
					unset($_SESSION['id']);
					unset($_SESSION['nombre']);
					unset($_SESSION['tipo']);
					unset($_SESSION['precios']);
					unset($_SESSION['telefono']);
					unset($_SESSION['local']);
					unset($_SESSION['horario']);
					unset($_SESSION['categoria']);
					unset($_SESSION['update']);
				}else if(isset($_SESSION['nombre'])){
					$bd->consulta("insert into Servicio values(null,'".$_SESSION['nombre']."','".$_SESSION['tipo']."','".$_SESSION['precios']."','".$_SESSION['telefono']."','".$_SESSION['local']."','".$_SESSION['horario']."','".$_SESSION['categoria']."')");	
					echo '<script>alert("Se agrego el registro '.$_SESSION['nombre'].'");</script>';
					unset($_SESSION['nombre']);
					unset($_SESSION['tipo']);
					unset($_SESSION['precios']);
					unset($_SESSION['telefono']);
					unset($_SESSION['local']);
					unset($_SESSION['horario']);
					unset($_SESSION['categoria']);
				}
				$query = $bd->consulta("select * from Servicio");
				$bd->cerrarConexion();
				$usuarios = array();
				while($row = $query->fetch_assoc()){
					echo '
					<tr>
						<td width="5%">'.$row["idServicio"].'</td>
						<td width="15%"><input size="20" disabled type="text" id="nombre'.$row["idServicio"].'" value="'.$row["nombreLugar"].'"/></td>
						<td width="12.5%"><input size="20" disabled type="text" id="tipo'.$row["idServicio"].'" value="'.$row["tipo"].'"/></td>
						<td width="10%"><input size="10" disabled type="text" id="precios'.$row["idServicio"].'" value="'.$row["rangoPrecios"].'"/></td>
						<td width="10%"><input size="12" disabled type="text" id="telefono'.$row["idServicio"].'" value="'.$row["telefono"].'"/></td>
						<td width="10%"><input size="10" disabled type="text" id="local'.$row["idServicio"].'" value="'.$row["local"].'"/></td>
						<td width="10%"><input size="11" disabled type="text" id="horario'.$row["idServicio"].'" value="'.$row["horario"].'"/></td>
						<td width="12.5%"><input size="20" disabled type="text" id="categoria'.$row["idServicio"].'" value="'.$row["categoria"].'"/></td>
						<td width="2.5%"><input type="image" src="img/editar.png" onclick="editar(this)" name="'.$row["idServicio"].'"></td>
						';

						echo '	<td width="2.5%"><input type="image" src="img/eliminar.png" onclick="eliminar(this)" id="boton'.$row["idServicio"].'" name="'.$row["idServicio"].'"></td></tr>';
						

				
				}
		echo '	</tbody>
			</table>
		</div>

		<script>
			function agregar(){
				var nombre = document.getElementById("nombre").value;
				var tipo = document.getElementById("tipo").value;
				var precios = document.getElementById("precios").value;
				var telefono = document.getElementById("telefono").value;
				var local = document.getElementById("local").value;
				var horario = document.getElementById("horario").value;
				var categoria = document.getElementById("categoria").value;
				window.location="index.php?action=servicio&nombre="+nombre+"&tipo="+tipo+"&precios="+precios+"&telefono="+telefono+"&local="+local+"&horario="+horario+"&categoria="+categoria;
			}
			function editar(id){
				document.getElementById("nombre"+id.name).disabled = false;
				document.getElementById("tipo"+id.name).disabled = false;
				document.getElementById("precios"+id.name).disabled = false;
				document.getElementById("telefono"+id.name).disabled = false;
				document.getElementById("local"+id.name).disabled = false;
				document.getElementById("horario"+id.name).disabled = false;
				document.getElementById("categoria"+id.name).disabled = false;
				document.getElementById("boton"+id.name).src = "img/aceptar.png";
				document.getElementById("boton"+id.name).onclick = function(){				
				var nombre = document.getElementById("nombre"+id.name).value;
				var tipo = document.getElementById("tipo"+id.name).value;
				var precios = document.getElementById("precios"+id.name).value;
				var telefono = document.getElementById("telefono"+id.name).value;
				var local = document.getElementById("local"+id.name).value;
				var horario = document.getElementById("horario"+id.name).value;
				var categoria = document.getElementById("categoria"+id.name).value;
				window.location="index.php?action=servicio&function=update&id="+id.name+"&nombre="+nombre+"&tipo="+tipo+"&precios="+precios+"&telefono="+telefono+"&local="+local+"&horario="+horario+"&categoria="+categoria;
				};
			}
			function eliminar(id){
				confirmar = confirm("¿Estás seguro de eliminar el servicio?");
				if(confirmar){
					window.location="index.php?action=servicio&eliminar="+id.name;
				}
			}
		</script>
	';

?>