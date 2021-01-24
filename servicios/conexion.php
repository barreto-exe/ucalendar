<?php

$host = 'remotemysql.com';
$db = 'HOSiKYC1GP';
$user = 'HOSiKYC1GP';
$pass = '2tkg08jx'

$conexion = new mysqli($host, $user, $pass, $db);

if($conexion ->connect_errno)
{
    echo "Error al conectarse";
}