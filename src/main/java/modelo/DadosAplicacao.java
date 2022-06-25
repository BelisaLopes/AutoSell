package modelo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

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
        veiculosPorReparar = new ArrayList<>();
        veiculosProntosParaVenda = new ArrayList<>();
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

        veiculosProntosParaVenda.add(new Veiculo("Opel", "Corsa", 2001, "AA-00-AA", "Branco", 2, TipoCombustivel.GASOLINA, 100000,1, "Bom", 10000));
        clientes.add(new Cliente("Joana", "Rua da Escola2", new Data(1,1,2002), "199999999", "915295625"));
        clientes.add(new Cliente("Joaquim", "Rua da Escola", new Data(18,6,2000), "123456789", "911234567"));
        eventos.add(new Evento(Distrito.LEIRIA, "Feira de Maio", new Data(1,5,2022), new Data(31,5,2022)));
        List<Veiculo> v = new ArrayList<>();
        v.add(new Veiculo("Opel", "Corsa", 2001, "AA-00-AA", "Branco", 2, TipoCombustivel.GASOLINA, 100000,1, "Bom", 10000));
        v.add(new Veiculo("Mitsubishi", "Colt", 2005, "AA-00-AA", "Branco", 2, TipoCombustivel.GASOLEO, 200000,1, "Bom", 10000));
        listaVeiculosPorLocal.put(eventos.get(0), v);
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
            e = evento;
            if(distrito != null && evento.getDistrito() != distrito){
                continue;
            }

            if(dataInicio != null && !evento.getDataInicio().equals(dataInicio)){
               continue;
            }

            if(dataFim != null && !evento.getDataFim().equals(dataFim)){
                continue;
            }

            eventosFiltrados.add(e);
        }

        return eventosFiltrados.isEmpty() ? null : eventosFiltrados;
    }

    public void adicionarEvento (Evento evento){
        eventos.add(evento);
        List<Veiculo> veiculos = new ArrayList<>();
        listaVeiculosPorLocal.put(evento,veiculos);
    }

    public boolean isEventoDuplicado(String nome, Data inicio, Data fim) {
        for (Evento e : eventos) {
            if(Objects.equals(e.getNome(), nome) && e.getDataInicio().equals(inicio) && e.getDataFim().equals(fim)){
                return true;
            }
        }
        return false;
    }

    public List<Veiculo> getVeiculosLocal(Local local){
        return listaVeiculosPorLocal.get(local);
    }

    public List<Veiculo> getVeiculosProntosParaVenda(){
        return veiculosProntosParaVenda;
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void adicionarVeiculoPorReparar(Veiculo veiculo) {
        veiculosPorReparar.add(veiculo);
    }

    public List<Cliente> getClientes(String nome, String nif) {

        List<Cliente> clientesFiltrados = new ArrayList<>();
        Cliente c;

        for (Cliente cliente : clientes) {
            c = cliente;
            if(nome != null && !cliente.getNome().equals(nome)){
                continue;
            }

            if(nif != null && !cliente.getNIF().equals(nif)){
                continue;
            }

            clientesFiltrados.add(c);
        }

        return clientesFiltrados.isEmpty() ? null : clientesFiltrados;

    }

    public boolean isNIFDuplicado(String nif) {
        for (Cliente c : clientes) {
            if(c.getNIF().equals(nif)){
                return true;
            }
        }
        return false;
    }

    public boolean existeCategoria(String nomeCategoria){
        for (Categoria categoria: catalogo) {
            if (categoria.getNome().equals(nomeCategoria)){
                return true;
            }
        }
        return false;
    }
}
