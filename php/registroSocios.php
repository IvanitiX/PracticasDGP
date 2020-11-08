<?php
    include_once("../operaciones_api/autenticacionApi.php");

    session_start();
    if(isset($_SESSION['usuario']) && ($_SESSION['rol'] == "admin" || $_SESSION['rol'] == "ambos")){
     if($_SERVER['REQUEST_METHOD'] === "POST"){
        $infoRegistro = array();

        $infoRegistro['nombre'] = $_POST['Nombre'];
        $infoRegistro['username'] = $_POST['Identificador'];
        $infoRegistro['password'] = md5($_POST['Contrasena']);
        $infoRegistro['rol'] = "socio";

        $jsoninfoRegistro = json_encode($infoRegistro);

        registroApi($jsoninfoRegistro);

        header('Location: ../perfil.php?username='.$infoRegistro['username']);
    }
    }
?>