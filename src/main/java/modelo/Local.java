package modelo;

import java.util.List;

public abstract class Local {
    protected Distrito distrito;
    protected List<Veiculo> veiculos;

    public Local(Distrito distrito) {
        this.distrito = distrito;
    }

    public abstract void transportarVeiculo(Veiculo veiculo, Local localDestino);
}
