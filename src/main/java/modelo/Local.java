package modelo;

import java.util.List;
import java.util.Objects;

public abstract class Local {
    protected Distrito distrito;
//    protected List<Veiculo> veiculos;

    public Local(Distrito distrito) {
        this.distrito = distrito;
    }

    public abstract void transportarVeiculo(Veiculo veiculo, Local localDestino);

    public Distrito getDistrito() {
        return distrito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Local)) return false;
        Local local = (Local) o;
        return distrito == local.distrito;
    }

    @Override
    public int hashCode() {
        return Objects.hash(distrito);
    }
}
