<?php 

include 'conexion.php';

$idUsuario = $_POST['idUsuario'];
$idEvento  = $_POST['idEvento'];

//Boorar like en base de datos
$query = 
    "DELETE FROM c_guardados WHERE id_usuario = $idUsuario AND id_evento = $idEvento";
if($conexion->query($query) == TRUE)
{
    echo "to bien";
}
else
{
    echo $conexion->error;
}

//Bajar cantidad de likes
$query = 
    "UPDATE c_eventos SET cantidad_guardados = cantidad_guardados-1
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