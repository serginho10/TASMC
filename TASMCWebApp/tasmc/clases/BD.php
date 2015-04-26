<?php
	
	class BD{
		private $conexion;

		public function BD($host,$usuario,$contrasena,$bd){
			$this->conexion = new mysqli($host, $usuario, $contrasena, $bd);
			if($this->conexion->connect_errno){
				die('Could not connect: ' . mysql_error());
			}
		}

		public function consulta($sql){
			$result = $this->conexion->query($sql);
			return $result;
		}

		public function cerrarConexion(){
			$this->conexion->close();
		}

	}

?>