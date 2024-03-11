package dao;

import clase.Modulo;
import utils.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModuloDAO {

    private static final String insertar = "insert into modulo(ad,sge,di,pmdm,psp,eie,hlc) values (?,?,?,?,?,?,?)";

    public static Modulo insertarModulo(Modulo modulo) {

        Modulo modulito = modulo;

        try {
            PreparedStatement ps = MySQLConnector.getConexion().prepareStatement(insertar, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, modulo.getAd());
            ps.setLong(2, modulo.getSge());
            ps.setLong(3, modulo.getDi());
            ps.setLong(4, modulo.getPmdm());
            ps.setLong(5, modulo.getPsp());
            ps.setLong(6, modulo.getEie());
            ps.setLong(7, modulo.getHlc());

            Integer numeroFila = ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                Long id = (long) rs.getInt(1);
                modulito.setId(id);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return modulito;
    }




}
