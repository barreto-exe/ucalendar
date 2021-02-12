<?php 

include 'conexion.php';

$idUsuario = $_POST['idUsuario'];
$idEvento  = $_POST['idEvento'];

//Insertar like en base de datos
$query = 
    "INSERT INTO c_likes(id_usuario, id_evento, fecha) 
    VALUES ($idUsuario, $idEvento, current_timestamp());";

if($conexion->query($query) == TRUE)
{
    echo "to bien";
}
else
{
    echo $conexion->error;
}

//Subir cantidad de likes
$query = 
    "UPDATE c_eventos SET cantidad_likes = cantidad_likes+1
    WHERE id_evento = $idEvento;";

if($conexion->query($query) == TRUE)
{
    echo "to bien";
}
else
{
    echo $conexion->error;
}


$conexion->close();