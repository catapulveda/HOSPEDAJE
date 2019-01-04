package DAO;

import clases.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Habitacion;
import model.Hotel;

public class HabitacionDAO {

    public HabitacionDAO() {
    }
    
    public int save(Habitacion hab, Conexion con) throws SQLException{
        String sql = " INSERT INTO habitacion (idhotel, nombrehabitacion) VALUES("+hab.getHotel().getIdhotel()+" , '"+hab.getNombre()+"')";
        System.out.println(sql);
        PreparedStatement pst = con.getCon().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }
    
    public ArrayList<Habitacion> getHabitaciones(Conexion con) throws SQLException{
        ResultSet rs = con.CONSULTAR("SELECT * FROM habitacion INNER JOIN hotel USING(idhotel)");
        ArrayList<Habitacion> lista = new ArrayList<>();
        while(rs.next()){
            Hotel h = new Hotel();
            h.setIdhotel(rs.getInt("idhotel"));
            h.setNombre(rs.getString("nombrehotel"));
            
            Habitacion hbt = new Habitacion();
            hbt.setHotel(h);
            hbt.setIdhabitacion(rs.getInt("idhabitacion"));
            hbt.setNombre(rs.getString("nombrehabitacion"));
            
            lista.add(hbt);
        }
        return lista;
    }
    
}
