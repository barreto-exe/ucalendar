<?php

include 'conexion.php';

$u_user = $_POST['usuario'];
$u_pass = $_POST['password'];

// $u_user = "test";
// $u_pass = "test";

$query = "SELECT * FROM usuarios WHERE correo = ? AND password = ?";

$sentencia = $conexion->prepare($query);
$sentencia->bind_param('ss',$u_user,$u_pass);
$sentencia->execute();

$resultado = $sentencia->get_result();
if($fila = $resultado->fetch_assoc())
{
    echo json_encode($fila, JSON_UNESCAPED_UNICODE);
}
else
{
    echo "No existe. ";
}

$sentencia->close();
$conexion->close();