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
            
            String url = "jdbc:postgresql://localhost:5432/Gerenciamento_Funcionarios";
            String usuario = "postgres";
            String senha = "160215";
            
            return DriverManager.getConnection(url, usuario, senha);
            
        } catch(SQLException e){
           
            System.out.println("Falha na conexão");
            throw new RuntimeException(e);
            
        }
    }
    

}
