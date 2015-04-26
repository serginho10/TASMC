<?php

	session_start();

	include_once "clases/BD.php";
	include_once "clases/Usuario.php";

	echo '
		<div class="table-header">
			<table class="table">
				<thead>
					<tr>	
						<th width="2%">id</th>
						<th width="25%">Email</th>
						<th width="20%">Contraseña</th>
						<th width="10%">Tipo</th>
						<th width="10%">Clase Preferida</th>
						<th width="10%">Categoría Preferida</th>
						<th width="10%">Equipaje</th>
						<th width="8%">Ultima Visita</th>
						<th width="5%"></th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="tabla">
			<table class="table table-hover table-strigger">
				<tbody>';

				$bd = new BD("127.0.0.1","root","root","tasmc");
				if(isset($_SESSION['eliminar'])){
					$bd->consulta("delete from Usuario where idUsuario = ".$_SESSION['eliminar']);	
					echo '<script>alert("Se elimino el registro '.$_SESSION['eliminar'].'");</script>';
				}
				unset($_SESSION['eliminar']);
				$query = $bd->consulta("select * from Usuario");
				$bd->cerrarConexion();
				$usuarios = array();
				while($row = $query->fetch_assoc()){
					$usuarios[$row['idUsuario']] = new Usuario($row['email'],$row['contrasena'],$row['tipo'],$row['email'],
					$row['clasePref'],$row['catPref'],$row['Equipaje_idEquipaje'],$row['ultimaVisita']);
					echo '
					<tr>
						<td width="2%">'.$row["idUsuario"].'</td>
						<td width="25%">'.$row["email"].'</td>
						<td width="20%">'.$row["contrasena"].'</td>
						<td width="10%">'.$row["tipo"].'</td>
						<td width="10%">'.$row["clasePref"].'</td>
						<td width="10%">'.$row["catPref"].'</td>
						<td width="10%">'.$row["Equipaje_idEquipaje"].'</td>
						<td width="8%">'.$row["ultimaVisita"].'</td>
						<td width="5%"><input type="image" src="img/eliminar.png" onclick="eliminar(this)" name="'.$row["idUsuario"].'"></td>
					</tr>
					';
				}
		echo '	</tbody>
			</table>
		</div>

		<script>
			function eliminar(id){
				confirmar = confirm("¿Estás seguro de eliminar al usuario?");
				if(confirmar){
					window.location="index.php?action=usuario&eliminar="+id.name;
				}
			}
		</script>
	';

?>