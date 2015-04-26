<?php

	session_start();

	include_once "../clases/BD.php";
	include_once "../clases/Usuario.php";
	
	function existe($email,$contrasena,$query){
		while($row = $query -> fetch_assoc()){
			if($row['email'] == $email && $row['contrasena'] == $contrasena && $row['tipo'] == "Administrador"){
				$_SESSION['usuario'] = new Usuario($row['email'],$row['contrasena'],$row['tipo'],$row['email'],
					$row['clasePref'],$row['catPref'],$row['Equipaje_idEquipaje'],$row['ultimaVisita']);
				return true;
			}
		}
		return false;
	}

	if(isset($_POST['email'])){
		$bd = new BD("127.0.0.1","root","root","tasmc");
		$query = $bd->consulta("select * from Usuario");
		$bd->cerrarConexion();
		if(existe($_POST['email'],$_POST['contrasena'],$query)){
			header("location:../index.php");
		}else{
			header("location:../index.php?error=1");
		}

	}

	echo "
		<!DOCTYPE html>
		<html>	
			<head>
				<title>TASMC</title>
				<meta charset='utf-8'>
				<link href='css/style2.css' rel='stylesheet' type='text/css' />
				<meta name='viewport' content='width=device-width, initial-scale=1'>
				<script type='application/x-javascript'> addEventListener('load', function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
				<link href='http://fonts.googleapis.com/css?family=Open+Sans:600italic,400,300,600,700' rel='stylesheet' type='text/css'>
			</head>
			<body>
				<img class='regLogo' src='img/logotipo.png'>
				<div class='login-form'>
					<div class='head'>
						<img src='img/logo.png' width='170' height='160' alt=''/>
					</div>
					<form method='POST' action='secciones/login.php'>
						<li>
							<input type='email' name='email' required class='text' value='Email' onfocus='onFocus(this)' onblur='onBlurU(this)' ><a href='#' class='icon user'></a>
						</li>
						<li>
							<input type='password' name='contrasena' required value='Password' onfocus='onFocus(this)' onblur='onBlurP(this)'><a href='#' class='icon lock'></a>
						</li>
						<div class='p-container'>
							<input name='login' type='submit' value='ENTRAR' >
							<div class='clear'> </div>
						</div>
					</form>
				</div>
			</body>
		</html>

		<script>
			function onFocus(x){
				x.value = '';
			}
			function onBlurU(x){
				if(x.value == ''){
					x.value = 'Email';
				}
			}
			function onBlurP(x){
				if(x.value == ''){
					x.value = 'Password';
				}
			}
		</script>
		";
?>