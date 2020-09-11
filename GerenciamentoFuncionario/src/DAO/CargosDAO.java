package DAO;

import java.sql.Connection;
import classes.Cargo;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class CargosDAO {
    private Connection conecta;
    
    public CargosDAO(){
        this.conecta = new Dao().conecta();
    }
    
    public void cadastrarCargo(Cargo cg) throws SQLException{
        String sql = "INSERT INTO Cargos(sigla, nm_cargo, salario, descricao, status) VALUES (?,?,?,?,'Ativo')";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, cg.getSigla());
            stmt.setString(2, cg.getNmCargo());
            stmt.setBigDecimal(3, cg.getSalario());
            stmt.setString(4, cg.getDescricao());
            stmt.execute();
            stmt.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public void alterarCargo(Cargo cg){
        String sql = "UPDATE Cargos SET salario=?, descricao=? WHERE sigla=?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBigDecimal(1, cg.getSalario());
            stmt.setString(2, cg.getDescricao());
            stmt.setString(3, cg.getSigla());
            stmt.execute();
            stmt.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public void excluirCargo(Cargo cg){
        String sql = "UPDATE Cargos SET status=? WHERE sigla=?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, "Desabilitado");
            stmt.setString(2, cg.getSigla());
            stmt.execute();
            stmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public List<Cargo> listarCargos(){
        String sql = "SELECT sigla, nm_cargo, salario, descricao FROM Cargos WHERE status='Ativo' ORDER BY sigla";
        List<Cargo> cargos = new ArrayList<Cargo>();
        ResultSet rs;
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Cargo cg = new Cargo();
                cg.setSigla(rs.getString("sigla"));
                cg.setNmCargo(rs.getString("nm_cargo"));
                cg.setSalario(rs.getBigDecimal("salario"));
                cg.setDescricao(rs.getString("descricao"));
                cargos.add(cg);
            }
            rs.close();
            stmt.close();
            return cargos;
        } catch(SQLException e){
            throw new RuntimeException();
        }
    }
    
   public List<Cargo> listarNmCargos(){
        String sql = "SELECT nm_cargo FROM Cargos WHERE status='Ativo' ORDER BY nm_cargo";
        List<Cargo> cargos = new ArrayList<Cargo>();
        ResultSet rs;
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Cargo cg = new Cargo();
                cg.setNmCargo(rs.getString("nm_cargo"));
                cargos.add(cg);
            }
            rs.close();
            stmt.close();
            return cargos;
        } catch(SQLException e){
            throw new RuntimeException();
        }
    }
    
   public String descCgForNm(String nome){
       String sql = "SELECT sigla FROM Cargos WHERE nm_cargo = ? AND status='Ativo'";
       ResultSet rs;
       String sigla = null;
       try{
           PreparedStatement stmt = conecta.prepareStatement(sql);
           stmt.setString(1, nome);
           rs = stmt.executeQuery();
           if(rs.next()){
               Cargo cg = new Cargo();
               cg.setSigla(rs.getString("sigla"));
               sigla = cg.getSigla();
           }
           rs.close();
           stmt.close();
           return sigla;
       } catch(SQLException e){
           throw new RuntimeException(e);
       }
   }
   
}
