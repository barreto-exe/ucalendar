<?php

include 'conexion.php';

$query = "SELECT * FROM c_eventos ORDER BY id_evento DESC";

$resultado = $conexion->query($query);

while($fila = $resultado->fetch_assoc())
{
    echo json_encode($fila, JSON_UNESCAPED_UNICODE);
}


$conexion->close();