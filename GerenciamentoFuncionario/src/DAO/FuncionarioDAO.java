package DAO;

import classes.Funcionario;
import classes.Cargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "INSERT INTO Funcionarios(nome, rg, cpf, s_cargo, status) VALUES (?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, fc.getNome());
            stmt.setString(2, fc.getRg());
            stmt.setString(3, fc.getCpf());
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
    
    public List<Funcionario> listarFuncionarios(){
        String sql = "SELECT id, nome, rg, cpf, cg.nm_cargo FROM Funcionarios fc, Cargos cg WHERE fc.status='Ativo' "
                + "AND s_cargo = cg.sigla ORDER BY id";
        List<Funcionario> func = new ArrayList<Funcionario>();
        List<Cargo> nmCargo = new ArrayList<Cargo>();
        ResultSet rs;
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Funcionario fc = new Funcionario();
                fc.setId(rs.getInt("id"));
                fc.setNome(rs.getString("nome"));
                fc.setRg(rs.getString("rg"));
                fc.setCpf(rs.getString("cpf"));
                fc.setNmCargo(rs.getString("nm_cargo"));
                func.add(fc);
            }
            rs.close();
            stmt.close();
            return func;
        } catch(SQLException e){
            throw new RuntimeException();
        }
    }
}
