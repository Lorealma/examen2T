package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class MySQLConnector {

    private static Logger log=Logger.getLogger(MySQLConnector.class.getName());
    private static Connection conexion;
    static{
        InputStream is=MySQLConnector.class.getClassLoader().getResourceAsStream("mysql.properties");
        Properties p=new Properties();
        try {
            p.load(is);
            conexion= DriverManager.getConnection("jdbc:mysql://"+p.getProperty("url")+":"+p.getProperty("port")+"/"+p.getProperty("nombreBD"),p.getProperty("user"),p.getProperty("password"));
            System.out.println(""+conexion);
            log.info("Conexión realizada con éxito.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            log.severe("Conexión fallida.");
            throw new RuntimeException(e);

        }
    }
    public static Connection getConexion(){
        return conexion;
    }

}
