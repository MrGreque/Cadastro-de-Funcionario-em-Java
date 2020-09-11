package classes;

import java.math.BigDecimal;

/**
 *
 * @author gabriel
 */
public class Cargo {
    private String nmCargo;
    private String sigla;
    private String descricao;
    private BigDecimal salario;

    public String getNmCargo() {
        return nmCargo;
    }

    public void setNmCargo(String nmCargo) {
        this.nmCargo = nmCargo;
    }
    
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
    
}
