package modelo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class GestorVeiculosPorModelo {
    private Hashtable<String, List<Veiculo>> veiculosPorModelo;
//    private List<Veiculo> veiculosPorMarca;

    public GestorVeiculosPorModelo() {
//        this.veiculosPorMarca = new ArrayList<>();
        this.veiculosPorModelo = new Hashtable<>();
    }

    public void inserirModelo(Veiculo veiculo){
        String modelo = veiculo.getModelo();
        List<Veiculo> veiculosModelo = veiculosPorModelo.get(modelo);
        if(veiculosModelo == null){
            veiculosModelo = new ArrayList<>();
            veiculosPorModelo.put(modelo, veiculosModelo);
        }
        veiculosModelo.add(veiculo);
    }

    public List<String> getPecasUsadasDaMarca(){
        List<String> pecas = new ArrayList<>();
        String marca = null;
        Enumeration<String> modelos = veiculosPorModelo.keys();
        while(modelos.hasMoreElements()){
            int sum = 0;
            String modelo = modelos.nextElement();
            List<Veiculo> veiculos = veiculosPorModelo.get(modelo);
            for (Veiculo veiculo : veiculos) {
                sum += veiculo.getPecasUsadasEmReparacoes();
                if(marca == null){
                    marca = veiculo.getMarca();
                }
            }
            pecas.add(marca + " " + modelo + " - " + sum + " peças");
        }

        return pecas;
    }

    //cada veiculo tem peças usadas -> para fazer as estatisticas
}
