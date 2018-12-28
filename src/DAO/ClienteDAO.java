package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Cliente;
import clases.Conexion;
import java.sql.Timestamp;
import java.time.LocalDate;

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
        ResultSet rs = con.CONSULTAR("SELECT idcliente,\n" +
"       documento,\n" +
"       nombre,\n" +
"       apellido,\n" +
"       fechanacimiento,\n" +
"       telefono,\n" +
"       fecharegistro\n" +
"  FROM cliente;");
        ArrayList<Cliente> clientes = new ArrayList<>();
        while(rs.next()){
            
            Cliente c = new Cliente();
            c.setIdcliente(rs.getInt(1));
            c.setDocumento(rs.getString(2));
            c.setNombre(rs.getString(3));
            c.setApellido(rs.getString(4));
            c.setFechanacimiento(LocalDate.parse(rs.getString(5)));
            c.setTelefono(rs.getString(6));
            c.setFechaderegistro(Timestamp.valueOf(rs.getString(7)).toLocalDateTime());
            
            clientes.add(c);
        }
        return clientes;
    }
        
    
}
