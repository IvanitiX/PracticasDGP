<?php
    require_once '../vendor/autoload.php';

    session_start();
    if(isset($_SESSION['usuario'])){
        header("Location: pagina_inicio.php");
        $rol = $_SESSION['rol'];
    }
    else $rol = "";

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('index.html',['rol' => $rol]);
?>