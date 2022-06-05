package modelo;

import java.util.ArrayList;
import java.util.List;

public class DadosAplicacao {
    public static DadosAplicacao INSTANCE = new DadosAplicacao();

    private List<Filial> filiais;
    private Sede sede;

    private List<Cliente> clientes;

    private List<Veiculo> veiculosVendidos;
    private List<Veiculo> veiculosPorReparar;
    private List<Veiculo> veiculosProntosParaVenda;

    private List<Categoria> catalogo;

    public DadosAplicacao() {
        sede = new Sede(Distrito.LISBOA, 4500);
        filiais = new ArrayList<>(18);
        filiais.add(new Filial(Distrito.VIANA_DO_CASTELO, 100));
        filiais.add(new Filial(Distrito.VILA_REAL, 100));
        filiais.add(new Filial(Distrito.BRAGANCA, 100));
        filiais.add(new Filial(Distrito.BRAGA, 100));
        filiais.add(new Filial(Distrito.VISEU, 100));
        filiais.add(new Filial(Distrito.GUARDA, 100));
        filiais.add(new Filial(Distrito.PORTO, 100));
        filiais.add(new Filial(Distrito.AVEIRO, 100));
        filiais.add(new Filial(Distrito.COIMBRA, 100));
        filiais.add(new Filial(Distrito.CASTELO_BRANCO, 100));
        filiais.add(new Filial(Distrito.LEIRIA, 100));
        filiais.add(new Filial(Distrito.SANTAREM, 100));
        filiais.add(new Filial(Distrito.LISBOA, 100));
        filiais.add(new Filial(Distrito.PORTALEGRE, 100));
        filiais.add(new Filial(Distrito.SETUBAL, 100));
        filiais.add(new Filial(Distrito.BEJA, 100));
        filiais.add(new Filial(Distrito.EVORA, 100));
        filiais.add(new Filial(Distrito.FARO, 100));
        clientes = new ArrayList<>();
        catalogo = new ArrayList<>();
    }

    public List<Filial> getFiliais() {
        return new ArrayList<>(filiais);
    }

    public Filial getFilial(Distrito distrito) {
        for (Filial f : filiais) {
            if (f.getDistrito().equals(distrito)) {
                return f;
            }
        }
        return null;
    }

    public Sede getSede() {
        return sede;
    }

    public void adicionarCategoria(String nome) {
        Categoria categoria = new Categoria(nome);
        catalogo.add(categoria);
    }

    public List<Categoria> getCatalogo() {
        return new ArrayList<>(catalogo);
    }
}
