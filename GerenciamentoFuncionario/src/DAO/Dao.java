package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gabriel
 */
public class Dao {

    public Connection conecta(){
        try{
            
            String url = "jdbc:postgresql://localhost:5432/biblioteca";
            String usuario = "postgres";
            String senha = "IZAEL1602";
            
            return DriverManager.getConnection(url, usuario, senha);
            
        } catch(SQLException e){
           
            System.out.println("Falha na conexão");
            throw new RuntimeException(e);
            
        }
    }
    

}
