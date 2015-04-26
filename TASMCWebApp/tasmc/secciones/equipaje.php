<?php

	session_start();

	include_once("clases/Equipaje.php");
	include_once("clases/BD.php");

		if(isset($_SESSION['editar'])){
			$id = $_SESSION['editar'];
			unset($_SESSION['editar']);
			$bd = new BD("127.0.0.1","root","root","tasmc");
			if(isset($_SESSION['boxes'])){
				$cajas = $_SESSION['boxes'];
				foreach ($cajas as $value){
					$bd->consulta("insert into Equipaje_has_Objeto values(".$id.",".$value.")");	
				}
				unset($_SESSION['boxes']);
			}
			if(isset($_SESSION['objeto'])){
				$bd->consulta("insert into Objeto values(null,'".$_SESSION['objeto']."','".$_SESSION['categoria']."')");	
				unset($_SESSION['objeto']);
				unset($_SESSION['categoria']);
			}
			$query = $bd->consulta("select * from Equipaje where idEquipaje=".$id);
			$equipaje = "";
			while($row = $query->fetch_assoc()){
				$equipaje = $row['nombre'];
			}
			echo '
			<div class="form-group col-md-offset-4">
			  	<div class="col-md-3">
			  		<input id="objeto" name="objeto" type="text" placeholder="Objeto" class="form-control input-md">
			  	</div>
			  	<div class="col-md-3">
			  		<input id="categoria" name="categoria" type="text" placeholder="Categoría" class="form-control input-md">
			  	</div>
			  	<div class="col-md-2">
			    	<button id="singlebutton" name="singlebutton" onclick="agregar();" class="btn btn-primary">Agregar</button>
			  	</div>
			</div>
			';
			echo '
			<div class="table-header-editarEq">
				<table class="table">
					<thead>
						<tr>	
							<th width="5%">'.$equipaje.'</th>
							<th width="20%">Objeto</th>
							<th width="20%">Categoría</th>
						</tr>
					</thead>
				</table>
			</div>';
			echo '
				<form method="post" action="index.php" id="form">
					<div class="tabla-editarEq">
						<table class="table table-hover table-strigger">
							<tbody>';

							$query = $bd->consulta("select * from Objeto");
							$queryEq = $bd->consulta("select o.idObjeto from Objeto as o, Equipaje as e, Equipaje_has_Objeto as h where o.idObjeto = h.Objeto_idObjeto and h.Equipaje_idEquipaje = e.idEquipaje and e.idEquipaje = ".$id);
							$bd->cerrarConexion();
							$objetos = array();
							while($row = $queryEq->fetch_assoc()){
								$objetos[$row['idObjeto']] = true; 
							}
							while($row = $query->fetch_assoc()){
								if($row['idObjeto'] != 1){
										if(array_key_exists($row['idObjeto'], $objetos)){
											echo '<tr class="success"><td width="5%"><input type="checkbox" disabled name="checkboxes[]" id="checkboxes-0" value="'.$row['idObjeto'].'"></td>';
										}else{
											echo '<tr><td width="5%"><input type="checkbox" name="checkboxes[]" id="checkboxes-0" value="'.$row['idObjeto'].'"></td>';
										}
								echo '	
										<td width="20%">'.$row["nombre"].'</td>
										<td width="20%">'.$row["categoria"].'</td>
									</tr>
									';
								}
							}
						echo '	</tbody>
							</table>
						</div> 
					</form>
						<div class="form-group col-md-offset-5">
					  		<div class="col-md-8">
							    <button type="submit" form="form" name="agregar" value="'.$id.'" class="btn btn-success">Guardar</button>
							    <button id="button2id" name="button2id" onclick="regresar();" class="btn btn-danger">Cancelar</button>
					  		</div>
					  	</div>
				<script>
					function agregar(){
						confirmar = confirm("¿Estás seguro de crear este objeto?");
						if(confirmar){
							window.location="index.php?action=equipaje&editar='.$id.'&objeto="+document.getElementById("objeto").value+"&categoria="+document.getElementById("categoria").value;
						}
					}
					function regresar(){
						window.location="index.php?action=equipaje";
					}
				</script>
			';

		}else{

			echo '
			<div class="table-header-equipaje">
				<table class="table">
					<thead>
						<tr>	
							<th width="5%">id</th>
							<th width="20%">Nombre</th>
							<th width="10%"><button type="button" onclick="nuevo()" class="btn btn-primary">Nuevo Equipaje</button></th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="tabla-equipaje">
				<table class="table table-hover table-strigger">
					<tbody>';

					$bd = new BD("127.0.0.1","root","root","tasmc");
					if(isset($_SESSION['eliminar'])){
						$bd->consulta("delete from equipaje where idEquipaje = ".$_SESSION['eliminar']);	
						echo '<script>alert("Se elimino el registro '.$_SESSION['eliminar'].'");</script>';
					}
					unset($_SESSION['eliminar']);
					if(isset($_SESSION['nuevo'])){
						$bd->consulta("insert into Equipaje values(null,'".$_SESSION['nuevo']."')");	
						echo '<script>alert("Se agrego el equipaje '.$_SESSION['nuevo'].'");</script>';
					}
					unset($_SESSION['nuevo']);
					$query = $bd->consulta("select * from Equipaje");
					$bd->cerrarConexion();
					$equipajes = array();
					while($row = $query->fetch_assoc()){
						$equipajes[$row['idEquipaje']] = new Equipaje($row['idEquipaje'],$row['nombre']);
						if($row['idEquipaje'] != 1){
							echo '
							<tr>
								<td width="5%">'.$row["idEquipaje"].'</td>
								<td width="20%">'.$row["nombre"].'</td>
								<td width="5%"><input type="image" src="img/editar.png" onclick="editar(this)" name="'.$row["idEquipaje"].'"></td>
								<td width="5%"><input type="image" src="img/eliminar.png" onclick="eliminar(this)" name="'.$row["idEquipaje"].'"></td>
							</tr>
							';
						}
					}
				echo '	</tbody>
					</table>
				</div> ';

				echo '	
				<script>
					function nuevo(){
						var equipaje = prompt("Nuevo equipaje:", "");
					    if(equipaje != null && equipaje != "") {
					    	window.location="index.php?action=equipaje&nuevo="+equipaje;   
					    }
					}
					function eliminar(id){
						confirmar = confirm("¿Estás seguro de eliminar el equipaje?");
						if(confirmar){
							window.location="index.php?action=equipaje&eliminar="+id.name;
						}
					}
					function editar(id){
						window.location="index.php?action=equipaje&editar="+id.name;
					}
				</script>
			';	
		}

?>