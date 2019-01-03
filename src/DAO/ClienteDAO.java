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
        
    public int save(Cliente c, Conexion con) throws SQLException{
        String sql = null;
        if(c.getIdcliente()==0){
            sql = "INSERT INTO cliente (documento,nombre,apellido,fechanacimiento,telefono) VALUES(";
            sql += " '"+c.getDocumento()+"' , '"+c.getNombre()+"' , '"+c.getApellido()+"' , '"+c.getFechanacimiento()+"' , '"+c.getTelefono()+"' ";
            sql += " )";
        }else if(c.getIdcliente()>0){
            sql = "UPDATE cliente SET documento='"+c.getDocumento()+"' , nombre='"+c.getNombre()+"' , ";
            sql += " apellido='"+c.getApellido()+"' , fechanacimiento='"+c.getFechanacimiento()+"' , ";
            sql += " telefono='"+c.getTelefono()+"' WHERE idcliente='"+c.getIdcliente()+"' ";
        }
        System.out.println(sql);
        PreparedStatement pst = con.getCon().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }
    
    public int delete(Cliente c, Conexion con) throws SQLException{
        return con.GUARDAR("DELETE FROM cliente WHERE idcliente="+c.getIdcliente());
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
