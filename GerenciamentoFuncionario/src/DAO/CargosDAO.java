package DAO;

import java.sql.Connection;
import classes.Cargo;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        String sql = "INSERT INTO Cargos(sigla, nm_cargo, salario, descricao) VALUES (?,?,?,?)";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, cg.getSigla());
            stmt.setString(2, cg.getNmCargo());
            //Caso o usuário digite com ',' ele converte para .
            String valor = String.valueOf(cg.getSalario());
            BigDecimal v = new BigDecimal(valor.replaceAll("\\.", "").replace(",","."));
            stmt.setBigDecimal(3, v);
            stmt.setString(4, cg.getDescricao());
            stmt.execute();
            stmt.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
