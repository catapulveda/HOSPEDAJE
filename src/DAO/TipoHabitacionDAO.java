package DAO;

import clases.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Habitacion;
import model.Hotel;
import model.TipoHabitacion;

public class TipoHabitacionDAO {

    public TipoHabitacionDAO() {
    }

    public int save(TipoHabitacion th, Conexion con) throws SQLException{
        PreparedStatement pst = con.getCon().prepareStatement("INSERT INTO tipodehabitacion (tipo,descripcion) VALUES('"+th.getNombre()+"' , '"+th.getDescripcion()+"')", Statement.RETURN_GENERATED_KEYS);
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }
    
    public ArrayList<Habitacion> getTipoHabitaciones(Conexion con) throws SQLException{
        ResultSet rs = con.CONSULTAR("SELECT * FROM tipodehabitacion INNER JOIN hotel USING(idhotel)");
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
