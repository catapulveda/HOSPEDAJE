package DAO;

import clases.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Habitacion;
import model.Hotel;

public class HabitacionDAO {

    public HabitacionDAO() {
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
