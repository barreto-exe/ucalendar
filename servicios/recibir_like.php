<?php 

include 'conexion.php';

$idUsuario = $_POST['idUsuario'];
$idEvento  = $_POST['idEvento'];
$cantidadLikes = $_POST['cantidadLikes'];

$query = 
    "INSERT INTO c_likes(id_usuario, id_evento, fecha) 
    VALUES ($idUsuario, $idEvento, current_timestamp());

    UPDATE c_eventos SET cantidad_likes = (SELECT COUNT(*) FROM c_likes WHERE id_evento = $idEvento) 
    WHERE id_evento = $cantidadLikes;";

if($conexion->query($query) == TRUE)
{

}