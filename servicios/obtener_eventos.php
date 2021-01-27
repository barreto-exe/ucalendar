<?php

include 'conexion.php';

$query = "SELECT * FROM c_eventos ORDER BY id_evento DESC";

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