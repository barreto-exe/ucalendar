package com.teamihc.ucalendar.backend.basedatos;

/**
 * Clase que almacena en clases estáticas la
 * configuración del usuario.
 */
public class Configuraciones
{
    public static String getCorreoSesion()
    {
        return (String) consultarConfiguracion("correo");
    }
    public static void setCorreoSesion(String correoSesion)
    {
        actualizarConfiguracion("correo", correoSesion);
    }
    public static int getIdUsuarioSesion()
    {
        return (int) consultarConfiguracion("id_usuario_sesion");
    }
    public static void setIdUsuarioSesion(int idUsuarioSesion)
    {
        actualizarConfiguracion("id_usuario_sesion", idUsuarioSesion);
    }
    
    
    
    private static Object consultarConfiguracion(String nombreConfig)
    {
        String query = "SELECT " + nombreConfig + " FROM configuracion";
        SqliteOp op = new SqliteOp(query);
        DBMatriz db = op.consultar();
        if(db.leer())
        {
            return db.getValor(nombreConfig);
        }
        else
        {
            return null;
        }
    }
    private static void actualizarConfiguracion(String nombreConfig, Object valor)
    {
        String query = "UPDATE configuracion SET " + nombreConfig + " = ?";
        SqliteOp op  = new SqliteOp(query);
        op.pasarParametro(valor);
        op.ejecutar();
    }
}
