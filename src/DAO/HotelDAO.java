package DAO;

import clases.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Hotel;

public class HotelDAO {

    public HotelDAO() {
    }
    
    public int save(Hotel h, Conexion con) throws SQLException{
        String sql = "INSERT INTO hotel (nombrehotel) VALUES ('"+h.getNombre()+"');";
        PreparedStatement pst = con.getCon().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
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
