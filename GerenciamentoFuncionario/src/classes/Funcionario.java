package classes;

/**
 *
 * @author gabriel
 */
public class Funcionario extends Pessoa{
    private int id;
    private String nmCargo;

    public String getNmCargo() {
        return nmCargo;
    }

    public void setNmCargo(String nmCargo) {
        this.nmCargo = nmCargo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
