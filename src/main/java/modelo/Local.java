package modelo;

public abstract class Local {
    protected Distrito distrito;

    public Local(Distrito distrito) {
        this.distrito = distrito;
    }

    public abstract void transportarVeiculo(Veiculo veiculo, Local localDestino);
}
