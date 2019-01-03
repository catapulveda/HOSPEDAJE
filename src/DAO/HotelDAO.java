package DAO;

import clases.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Hotel;

public class HotelDAO {

    public HotelDAO() {
    }
    
    public ArrayList<Hotel> getHotels(Conexion con) throws SQLException{        
        ArrayList<Hotel> hoteles = new ArrayList<>();
        ResultSet rs = con.CONSULTAR("SELECT * FROM hotel");
        while(rs.next()){
            Hotel h = new Hotel();
            h.setIdhotel(rs.getInt("idhotel"));
            h.setNombre(rs.getString("nombrehotel"));
            
            hoteles.add(h);
        }        
        return hoteles;
    }
    
}
