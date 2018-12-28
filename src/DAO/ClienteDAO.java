package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Cliente;
import clases.Conexion;

public class ClienteDAO {

    public ClienteDAO() {
    }
        
    public int guardar(Cliente c, Conexion con) throws SQLException{
        
        String sql = "INSERT INTO cliente (documento,nombre,apellido,fechanacimiento,telefono) VALUES(";
        sql += " '"+c.getDocumento()+"' , '"+c.getNombre()+"' , '"+c.getApellido()+"' , '"+c.getFechanacimiento()+"' , '"+c.getTelefono()+"' ";
        sql += " )";
        PreparedStatement pst = con.getCon().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if(rs.next()){           
            return rs.getInt(1);
        }        
        return 0;        
    }
    
    public ArrayList<Cliente> clientes(Conexion con) throws SQLException{
        ResultSet rs = con.CONSULTAR("SELECT * FROM cliente");
        ArrayList<Cliente> clientes = new ArrayList<>();
        while(rs.next()){
            Cliente c = new Cliente();
            c.setIdcliente(rs.getInt("idcliente"));
            c.setDocumento(rs.getString("documento"));
            c.setNombre(rs.getString("nombre"));
            c.setApellido(rs.getString("apellido"));
            c.setFechanacimiento(rs.getDate("fechanacimiento").toLocalDate());
            c.setFechaderegistro(rs.getTimestamp("fecharegistro").toLocalDateTime());
            
            clientes.add(c);
        }
        return clientes;
    }
        
    
}
