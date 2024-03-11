package dao;

import clase.Alumno;
import clase.Modulo;
import utils.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class AlumnoDAO {


    private static final String INSERTAR="insert into alumno (nombre,apellido1,apellido2,correo,dni,fecha,localidad,telefono,modulo_id) values (?,?,?,?,?,?,?,?,?)";

    private static final String getAll = "select * from alumnos";


    public static Alumno insertarAlumno(Alumno alumno){
            Alumno alumnito=alumno;

            try {
                PreparedStatement ps= MySQLConnector.getConexion().prepareStatement(INSERTAR, RETURN_GENERATED_KEYS);
                ps.setString(1,alumno.getNombre());
                ps.setString(2,alumno.getApellido1());
                ps.setString(3,alumno.getApellido2());
                ps.setString(4,alumno.getCorreo());
                ps.setString(5,alumno.getDni());
                ps.setDate(6,alumno.getFecha());
                ps.setString(7,alumno.getLocalidad());
                ps.setString(8,alumno.getTelefono());
                ps.setInt(9,Integer.valueOf((int) alumno.getModuloId()));
                Integer numeroFilaInsertada=ps.executeUpdate();
                ResultSet rs=ps.getGeneratedKeys();
                if (rs.next()){
                    Long id=(long)rs.getInt(1);
                    alumnito.setId(id);
                }
                return alumnito;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }



        /*public static ArrayList<Alumno> getAll(){

            try {
                PreparedStatement ps = MySQLConnector.getConexion().prepareStatement(getAll);

                ResultSet rs = ps.executeQuery();

                while(rs.next()){

                    Alumno alumno = new Alumno();
                    alumno.setId(rs.getLong("id"));
                    alumno.setNombre();

                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }*/


}

        /*Alumno alumnito = alumno;

        try {
            PreparedStatement ps = MySQLConnector.getConexion().prepareStatement(insertar, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,alumno.getNombre());
            ps.setString(2,alumno.getApellido1());
            ps.setString(3,alumno.getApellido2());
            ps.setString(4,alumno.getCorreo());
            ps.setString(5,alumno.getDni());
            ps.setDate(6,alumno.getFecha());
            ps.setString(7,alumno.getLocalidad());
            ps.setString(8,alumno.getTelefono());
            ps.setInt(9,Integer.valueOf((int) alumno.getModuloId()));

            Integer numeroFila = ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()){

                Long id = (long) rs.getInt(1);
                alumnito.setId(id);

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return alumnito;
    }*/






    //private static final String INSERTAR_ALUMNO="insert into alumno (nombre,apellido,ad,sge,pmdm,psp,eie,di,hlc) VALUES (?,?,?,?,?,?,?,?,?)";


   /* public static Alumno insertarAlumno(Alumno alumno){
        Alumno alumnoConId=alumno;
        try {
            PreparedStatement pst= MySQLConnector.getConexion().prepareStatement(INSERTAR_ALUMNO, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,alumno.getNombre());
            pst.setString(2,alumno.getApellido());
            pst.setString(3,alumno.getCorreo());
            pst.setString(4,alumno.getDni());
            pst.setString(5,alumno.getTelefono());
            pst.setString(6,alumno.getFechaNacimiento());
            pst.setString(7,alumno.getLocalidad());
            pst.setInt(8, alumno.getAd());
            pst.setInt(9, alumno.getSge());
            pst.setInt(10, alumno.getPmdm());
            pst.setInt(11, alumno.getPsp());
            pst.setInt(12, alumno.getEie());
            pst.setInt(13, alumno.getDi());
            pst.setInt(14, alumno.getHlc());
            Integer numeroFilasAfectadas=pst.executeUpdate();
            if(numeroFilasAfectadas==1){
                ResultSet rs=pst.getGeneratedKeys();
                if(rs.next()){
                    int idGenerado=rs.getInt(1);
                    alumnoConId.setId(idGenerado);
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alumnoConId;
    }
    public static ArrayList<Alumno> getAllAlumnos(){
        ArrayList<Alumno>alumnos=new ArrayList<>();
        try {
            PreparedStatement pst= MySQLConnector.getConexion().prepareStatement("select * from alumno");
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
                Alumno alumnoRecogido=new Alumno(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellido"),
                        rs.getString("correo"),rs.getString("dni"),rs.getString("telefono"),rs.getString("fechaNacimiento"),
                        rs.getString("localidad"),rs.getInt("ad"),rs.getInt("sge"),
                        rs.getInt("pmdm"),rs.getInt("psp"),rs.getInt("eie"),rs.getInt("di"),rs.getInt("hlc"));
                alumnos.add(alumnoRecogido);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alumnos;
    }
*/



