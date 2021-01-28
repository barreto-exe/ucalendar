<?php 

include 'conexion.php';

$idUsuario = $_POST['idUsuario'];
$idEvento  = $_POST['idEvento'];

//Insertar like en base de datos
$query = 
    "INSERT INTO c_guardados(id_usuario, id_evento, fecha) 
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
    "UPDATE c_eventos SET cantidad_guardados = cantidad_guardados+1
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