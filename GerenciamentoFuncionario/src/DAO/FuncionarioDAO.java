package DAO;

import classes.Funcionario;
import classes.Cargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author gabriel
 */
public class FuncionarioDAO {
    private Connection conecta;
    
    public FuncionarioDAO(){
        this.conecta = new Dao().conecta();
    }
    
    public void cadastroFunc(Funcionario fc, Cargo cg){
        String sql = "INSERT INTO Funcionarios(nome, rg, cpf, s_cargo, status) VALUES (?,?,?,?)";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, fc.getNome());
            stmt.setString(2, fc.getRg());
            stmt.setString(3, fc.getRg());
            stmt.setString(4, cg.getSigla());
            stmt.setString(5, "Ativo");
            stmt.execute();
            stmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public void alterarFunc(Funcionario fc, Cargo cg){
        String sql = "UPDATE Funcionarios SET nome=?, rg=?, cpf=?, s_cargo=? WHERE id=?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, fc.getNome());
            stmt.setString(2, fc.getRg());
            stmt.setString(3, fc.getCpf());
            stmt.setString(4, cg.getSigla());
            stmt.setInt(5, fc.getId());
            stmt.execute();
            stmt.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public void excluirFunc(Funcionario fc){
        String sql = "UPDATE Funcionarios SET status=? WHERE id=?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, "Desabilitado");
            stmt.setInt(2, fc.getId());
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
