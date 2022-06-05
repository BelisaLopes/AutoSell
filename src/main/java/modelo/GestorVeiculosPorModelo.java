package modelo;

import java.util.Hashtable;
import java.util.List;

public class GestorVeiculosPorModelo {
    private Hashtable<String, List<Veiculo>> veiculosPorModelo;

    public GestorVeiculosPorModelo() {
        this.veiculosPorModelo = new Hashtable<>();
    }

    //cada veiculo tem peças usadas -> para fazer as estatisticas
}
