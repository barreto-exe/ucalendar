<?php

include 'conexion.php';

$query = 
"SELECT\n".
"e.nombre AS nombre,\n".
"e.descripcion AS descripcion,\n".
"e.cantidad_likes AS cantidadLikes,\n".
"e.cantidad_guardados AS cantidadGuardados,\n".
"e.fecha_inicio AS fechaInicio,\n".
"e.fecha_final AS fechaFinal,\n".
"e.lugar AS lugar,\n".
"e.color AS color,\n".
"e.foto AS foto,\n".
"u.foto AS fotoCreador,\n".
"c.nombre AS nombreCreador\n".
"FROM c_eventos e \n".
"INNER JOIN c_creadores c ON (e.id_creador = c.id_creador) \n".
"INNER JOIN c_usuarios u ON (c.id_usuario = u.id_usuario)\n".
"ORDER BY id_evento DESC";

$resultado = $conexion->query($query);

$arrayJSON = [];
while($fila = $resultado->fetch_assoc())
{
    $arrayJSON[] = $fila;
}

if(!empty($arrayJSON))
{
    echo json_encode($arrayJSON, JSON_UNESCAPED_UNICODE);
}

$conexion->close();