<?php
    include_once("../operaciones_api/autenticacionApi.php");

    if($_SERVER['REQUEST_METHOD'] === "POST"){
        $infoEliminar = array();
        $infoEliminar['username'] = $_POST['username'];

        $jsonEliminacion = json_encode($infoEliminar);

        var_dump($jsonEliminacion);

        /*Descomentar cuando se haya implementado
        
        eliminacionUsuarioApi($jsonEliminacion);

        */
    }
?>