package modelo;

import java.text.SimpleDateFormat;
import java.util.*;

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

    private Hashtable<Local, List<Veiculo>> listaVeiculosVendidosPorLocal;
    private List<Categoria> catalogo;

    public DadosAplicacao() {
        sede = new Sede(Distrito.LISBOA, 4500);
        filiais = new ArrayList<>(18);
        eventos = new ArrayList<>();
        veiculosPorReparar = new ArrayList<>();
        veiculosProntosParaVenda = new ArrayList<>();
        veiculosVendidos = new ArrayList<>();
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

//        veiculosProntosParaVenda.add(new Veiculo("Opel", "Corsa", 2001, "AA-00-AA", "Branco", 2, TipoCombustivel.GASOLINA, 100000,1, "Bom", 10000)); // TINHAS ISTO BELISA
        veiculosProntosParaVenda.add(new Veiculo("Opel", "Corsa", 2001, "AA-00-AA", "Branco", 2, TipoCombustivel.GASOLINA, 100000,1, "Bom", 10000,sede));
        veiculosProntosParaVenda.add(new Veiculo("Opel", "Corsa", 2001, "BB-55-BB", "Branco", 2, TipoCombustivel.GASOLINA, 100000,1, "Bom", 10000,sede));
        clientes.add(new Cliente("Joana", "Rua da Escola2", new Data(1,1,2002), "199999999", "915295625"));
        clientes.add(new Cliente("Joaquim", "Rua da Escola", new Data(18,6,2000), "123456789", "911234567"));
        eventos.add(new Evento(Distrito.LEIRIA, "Feira de Maio", new Data(1,5,2022), new Data(31,5,2022)));
        List<Veiculo> v = new ArrayList<>();
        v.add(new Veiculo("Opel", "Corsa", 2001, "AA-00-CC", "Branco", 2, TipoCombustivel.GASOLINA, 100000,1, "Bom", 10000,eventos.get(0)));
        v.add(new Veiculo("Mitsubishi", "Colt", 2005, "BB-00-AA", "Branco", 2, TipoCombustivel.GASOLEO, 200000,1, "Bom", 10000,eventos.get(0)));
        listaVeiculosPorLocal.put(eventos.get(0), v);

        initListaVeiculosEstabelecimento();
    }

    private void initListaVeiculosEstabelecimento() {
        List<Veiculo> veiculos = new ArrayList<>();
        listaVeiculosPorLocal.putIfAbsent(sede, veiculos);
        for (Filial f : filiais) {
            veiculos = new ArrayList<>();
            listaVeiculosPorLocal.putIfAbsent(f,veiculos);
        }
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

    public List<Veiculo> getVeiculosPorReparar(){
        return veiculosPorReparar;
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void adicionarVeiculoPorReparar(Veiculo veiculo) {
        veiculosPorReparar.add(veiculo);
    }

    public void adicionarVeiculoVendido(Veiculo veiculo) {
        veiculosVendidos.add(veiculo);
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

    public List<Veiculo> getVeiculos(Local origem, String marca, String modelo, String matricula) {
        List<Veiculo> veiculos = new ArrayList<>();
        List<Veiculo> veiculosLocal = getVeiculosLocal(origem);

        Veiculo v;
        for (Veiculo veiculo : veiculosProntosParaVenda) {
            v = veiculo;
            if(veiculosLocal.contains(v)){
                continue;
            }

            if(!marca.isEmpty() && !v.getMarca().equals(marca)){
                continue;
            }

            if(!modelo.isEmpty() && !v.getModelo().equals(modelo)){
                continue;
            }

            if(!matricula.isEmpty() && !v.getMatricula().equals(matricula)){
                continue;
            }

            veiculos.add(v);
        }

        return veiculos.size() == 0 ? null : veiculos;
    }

    public List<Veiculo> getVeiculosParaTransportar(Local origem, Local destino, String marca, String modelo, String matricula) {
        List<Veiculo> veiculos = new ArrayList<>();
        List<Veiculo> veiculosLocalDestino = getVeiculosLocal(destino);
        List<Veiculo> veiculosLocalOrigem = getVeiculosLocal(origem);

        Veiculo v;
        for (Veiculo veiculo : veiculosLocalOrigem) {
            v = veiculo;
            if(veiculosLocalDestino.contains(v)){
                continue;
            }

            if(!marca.isEmpty() && !v.getMarca().equals(marca)){
                continue;
            }

            if(!modelo.isEmpty() && !v.getModelo().equals(modelo)){
                continue;
            }

            if(!matricula.isEmpty() && !v.getMatricula().equals(matricula)){
                continue;
            }

            veiculos.add(v);
        }

        return veiculos.size() == 0 ? null : veiculos;
    }

    public void adicionarVeiculoAoLocal(Local local, Veiculo veiculo){
        List<Veiculo> veiculos = listaVeiculosPorLocal.get(local);
        veiculos.add(veiculo);
        veiculo.setLocal(local);
    }

    public List<Evento> getEventosTerminados(){
        List<Evento> eventosTerminados = new ArrayList<>();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        Data dataAtual = Data.parseData(date);

        for (Evento evento : eventos) {
            if(Data.isFirstDateAfterSecondDate(dataAtual, evento.getDataFim())){
               eventosTerminados.add(evento);
            }
        }
        return eventosTerminados;
    }

    public List<Evento> getEventosNaoTerminados(){
        List<Evento> eventosNaoTerminados = new ArrayList<>();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        Data dataAtual = Data.parseData(date);

        for (Evento evento : eventos) {
            if(Data.isFirstDateAfterSecondDate(evento.getDataFim(), dataAtual)){
                eventosNaoTerminados.add(evento);
            }
        }
        return eventosNaoTerminados;
    }

    public int getLugaresLivres(Estabelecimento estabelecimento){
        int lotacao = estabelecimento.getCapacidadeMaximaVeiculos();
        List<Veiculo> veiculos = listaVeiculosPorLocal.get(estabelecimento);

        return lotacao - veiculos.size();
    }

    public int getNumeroVeiculosNoLocal(Local local){
        return listaVeiculosPorLocal.get(local).size();
    }

    public void transportarVeiculo (Veiculo veiculo, Local localDestino){
        List<Veiculo> veiculos = listaVeiculosPorLocal.get(veiculo.getLocal());
        veiculos.remove(veiculo);
        adicionarVeiculoAoLocal(localDestino, veiculo);
//        veiculo.setLocal(localDestino);
    }

        public boolean existeCategoria(String nomeCategoria){
            for (Categoria categoria: catalogo) {
                if (categoria.getNome().equals(nomeCategoria)){
                    return true;
                }
            }
            return false;
        }

        public boolean existemCategoriasSemPecas(){
            for (Categoria categoria: catalogo) {
                if (categoria.getPecas().isEmpty()){
                    return true;
                }
            }
            return false;
        }

        public void removerCategoria(Categoria categoria) {
            catalogo.remove(categoria);
        }

        public boolean existemCategorias() {
            return !catalogo.isEmpty();
        }

        public boolean existePeca(String nome) {
            for (Categoria categoria: catalogo) {
                for (Peca peca: categoria.getPecas()) {
                    if (peca.getNome().equals(nome)){
                        return true;
                    }
                }
            }
            return false;
        }

        public void adicionarPeca(Categoria categoria, String nome, String marca, String modelo, String dimensao, double preco, int qtdSede, int qtdFiliais) {
            Peca novaPeca = new Peca(nome, marca, modelo, dimensao, preco, categoria);
            categoria.adicionarPeca(novaPeca);

            sede.getOficina().registarPeca(novaPeca, qtdSede);

            for (Filial filial: filiais) {
                filial.getOficina().registarPeca(novaPeca, qtdFiliais);
            }
        }
}
