<?php
	session_start();

	if(isset($_GET['exit'])){
		session_start();
	    session_destroy();
	    header('location:index.php');
	}

	if(isset($_GET['error'])){
		$error = $_GET['error'];
		if($error == 1)
			echo '<script>alert("Tus credenciales son incorrectas. Ingresa las correctas.");</script>';
	}

	if(isset($_SESSION['usuario'])){
		echo '
		<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
		<html>
		    <head>
		        <title>Administración TASMC</title>
		        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		        <link rel="stylesheet" href="css/style.css" type="text/css" charset="utf-8"/>
		        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" charset="utf-8"/>
		        <script type="text/javascript" src="js/jquery-1.3.2.js"></script>
		    </head>
		    <style>
		        body{
		            background:url("img/fondoIndex.jpg")no-repeat center fixed;
					-webkit-background-size: cover;
					-moz-background-size: cover;
					-o-background-size: cover;
				 	background-size: cover;
		        }
		        .header{
		            width:600px;
		            height:56px;
		            position:absolute;
		            top:0px;
		            left:25%;
		        }
		        a.back{
		            width:256px;
		            height:73px;
		            position:fixed;
		            bottom:15px;
		            right:15px;
		        }
		        .scroll{
		            width:133px;
		            height:61px;
		            position:fixed;
		            bottom:15px;
		            left:150px;
		        }
		        .info{
		            text-align:right;

		        }
		    </style>
		    <body>
		        <div class="header"></div>
		        <div class="scroll"></div>
		        <ul id="navigation">
		            <li class="inicio"><a href="index.php" title="Inicio"></a></li>
		            <li class="usuarios"><a href="index.php?action=usuario" title="Usuario"></a></li>
		            <li class="equipajes"><a href="index.php?action=equipaje" title="Equipaje"></a></li>
		            <li class="servicios"><a href="index.php?action=servicio" title="Servicio"></a></li>
		            <li class="logout"><a href="index.php?exit=true" title="Cerrar Sesión"></a></li>
		        </ul>';

		        if(isset($_GET['action'])){
		        	$action = $_GET['action'];
		        	if($action == "usuario"){
		        		if(isset($_GET['eliminar']))
		        			$_SESSION['eliminar'] = $_GET['eliminar'];
		        		include_once("secciones/usuario.php");
		        	}
		        	if($action == "equipaje"){
		        		if(isset($_GET['nuevo']))
		        			$_SESSION['nuevo'] = $_GET['nuevo'];
		        		if(isset($_GET['eliminar']))
		        			$_SESSION['eliminar'] = $_GET['eliminar'];
		        		if(isset($_GET['editar']))
		        			$_SESSION['editar'] = $_GET['editar'];
		        		if(isset($_GET['objeto']))
		        			$_SESSION['objeto'] = $_GET['objeto'];
		        		if(isset($_GET['categoria']))
		        			$_SESSION['categoria'] = $_GET['categoria'];
		        		include_once("secciones/equipaje.php");
		        	}
		        	if($action == "servicio"){
		        		if(isset($_GET['eliminar']))
		        			$_SESSION['eliminar'] = $_GET['eliminar'];
		        		if(isset($_GET['nombre'])){
		        			$_SESSION['nombre'] = $_GET['nombre'];
		        			$_SESSION['tipo'] = $_GET['tipo'];
		        			$_SESSION['precios'] = $_GET['precios'];
		        			$_SESSION['telefono'] = $_GET['telefono'];
		        			$_SESSION['local'] = $_GET['local'];
		        			$_SESSION['horario'] = $_GET['horario'];
		        			$_SESSION['categoria'] = $_GET['categoria'];
		        		}
		        		if(isset($_GET['function'])){
		        			$_SESSION['update'] = true;	
		        			$_SESSION['id'] = $_GET['id'];
		        		}
		        		include_once("secciones/servicio.php");
		        	}
		        }
		        if(isset($_POST['checkboxes'])){
		        	$_SESSION['editar'] = $_POST['agregar'];
		        	$_SESSION['boxes'] = $_POST['checkboxes'];
		        	include_once("secciones/equipaje.php");
		        }

		        echo '<script type="text/javascript">
		            $(function() {
		                $("#navigation a").stop().animate({"marginLeft":"-85px"},1000);
		                $("#navigation > li").hover(
		                    function () {
		                        $("a",$(this)).stop().animate({"marginLeft":"-2px"},200);
		                    },
		                    function () {
		                        $("a",$(this)).stop().animate({"marginLeft":"-85px"},200);
		                    }
		                );
		            });
		        </script>
		    </body>
		</html>
	';	
	}else{
		include_once("secciones/login.php");
	}
		
?>