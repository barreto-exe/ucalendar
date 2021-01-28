<?php

include 'conexion.php';

$idUsuarioSesion = $_POST['id_usuario_sesion'];

$query = 
"SELECT\n".
"e.id_evento AS idEvento,\n".
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
"c.nombre AS nombreCreador,\n".
"IF((SELECT 1 FROM c_likes l WHERE l.id_evento = e.id_evento AND id_usuario = $idUsuarioSesion) = 1, 'true', 'false') AS tieneLike,\n".
"IF((SELECT 1 FROM c_guardados g WHERE g.id_evento = e.id_evento AND id_usuario = $idUsuarioSesion) = 1, 'true', 'false') AS tieneGuardado\n".
"FROM c_eventos e \n".
"INNER JOIN c_creadores c ON (e.id_creador = c.id_creador) \n".
"INNER JOIN c_usuarios u ON (c.id_usuario = u.id_usuario)\n".
"ORDER BY e.id_evento DESC";

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