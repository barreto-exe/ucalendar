<?php

$host = 'remotemysql.com';
$db = 'HOSiKYC1GP';
$user = 'HOSiKYC1GP';
$pass = '-';

$conexion = new mysqli($host, $user, $pass, $db);

if($conexion->connect_errno)
{
    echo "Error al conectarse. ";
}
else
{
    echo "Conectado a la bbdd. ";
}

?>