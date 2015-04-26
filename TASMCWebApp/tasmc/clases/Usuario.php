<?php

	class Usuario{
		private $email;
		private $contrasena;
		private $tipo;
		private $clasePref;
		private $catPref;
		private $Equipaje_idEquipaje;
		private $ultimaVisita;

		public function Usuario($email, $contrasena, $tipo, $clasePref, $catPref, $Equipaje_idEquipaje, $ultimaVisita){
			$this->email = $email;
			$this->contrasena = $contrasena;
			$this->tipo = $tipo;
			$this->clasePref = $clasePref;
			$this->catPref = $catPref;
			$this->Equipaje_idEquipaje = $Equipaje_idEquipaje;
			$this->ultimaVisita = $ultimaVisita;
		}

		public function getEmail(){
			return $this->email;
		}
	}

?>