package modelo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class DadosAplicacao {
    public static DadosAplicacao INSTANCE = new DadosAplicacao();

    private List<Filial> filiais;
    private Sede sede;
    private List<Evento> eventos;

    private List<Cliente> clientes;
    private Hashtable<Local, List<Veiculo>> listaVeiculosPorLocal;
    private Hashtable<String, GestorVeiculosPorModelo> listaPecasUsadasEmReparacaoPorMarca;

    private List<Veiculo> veiculosVendidos;
    private List<Veiculo> veiculosPorReparar;
    private List<Veiculo> veiculosProntosParaVenda;

    private List<Categoria> catalogo;

    public DadosAplicacao() {
        sede = new Sede(Distrito.LISBOA, 4500);
        filiais = new ArrayList<>(18);
        eventos = new ArrayList<>();
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
        listaVeiculosPorLocal = new Hashtable<>();
        listaPecasUsadasEmReparacaoPorMarca = new Hashtable<>();
    }

    public ArrayList<Filial> getFiliais() {
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

    public List<String> getPecasUsadasDaMarca(String marca){
        GestorVeiculosPorModelo gestor = listaPecasUsadasEmReparacaoPorMarca.get(marca);
        //TODO
        return null;
    }

    public List<String> getPecasUsadasTodasAsMarcas(){
        //TODO
        //iterar hashtable para ir buscar cada modelo das marcas
        return null;
    }

    public List<Evento> getEventos(Distrito distrito, Data dataInicio, Data dataFim) {
        List<Evento> eventosFiltrados = new ArrayList<>();
        Evento e;

        for (Evento evento : eventos) {
            e = null;
            if(distrito != null){
                if(evento.getDistrito() == distrito){
                    e = evento;
                }else{
                    continue;
                }
            }

            if(dataInicio != null){
               if(evento.getDataInicio() == dataInicio){
                   e = evento;
               }else{
                   continue;
               }
            }

            if(dataFim != null){
                if(evento.getDataFim() == dataFim){
                    e = evento;
                }else{
                    continue;
                }
            }

            eventosFiltrados.add(e);
        }

        return eventosFiltrados;
    }

    public void adicionarEvento (Evento evento){
        eventos.add(evento);
    }

    public boolean isEventoDuplicado(String nome, Data inicio, Data fim) {
        for (Evento e : eventos) {
            if(e.getNome() == nome && e.getDataInicio() == inicio && e.getDataFim() == fim){
                return true;
            }
        }
        return false;
    }
}
