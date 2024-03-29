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
    private int totalPecasUsadasNaReparacao;

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
        listaVeiculosVendidosPorLocal = new Hashtable<>();
        listaPecasUsadasEmReparacaoPorMarca = new Hashtable<>();
        totalPecasUsadasNaReparacao = 0;

        initListaVeiculosEstabelecimento();

//        Veiculo v = new Veiculo("Opel", "Corsa", 2001, "AA-11-AA", "Branco", 3, TipoCombustivel.GASOLINA, 100000,1, "Bom", 10000,sede);
//        veiculosPorReparar.add(v);
//        adicionarVeiculoAoLocal(sede,v);
//
//        v = new Veiculo("Opel", "Corsa", 2001, "BB-22-BB", "Branco", 3, TipoCombustivel.GASOLINA, 100000,1, "Bom", 10000,sede);
//        veiculosPorReparar.add(v);
//        adicionarVeiculoAoLocal(sede,v);
//
//        v = new Veiculo("Opel", "Corsa", 2001, "CC-33-AA", "Branco", 3, TipoCombustivel.GASOLINA, 100000,1, "Bom", 10000,sede);
//        v.setEstadoVeiculo(EstadoVeiculo.REPARADO);
//        adicionarVeiculoAoLocal(sede,v);
//        veiculosProntosParaVenda.add(v);
//
//        v = new Veiculo("Opel", "Corsa", 2001, "DD-44-CC", "Branco", 3, TipoCombustivel.GASOLINA, 100000,1, "Bom", 10000,sede);
//        v.setEstadoVeiculo(EstadoVeiculo.REPARADO);
//        adicionarVeiculoAoLocal(sede,v);
//        veiculosProntosParaVenda.add(v);
//
        clientes.add(new Cliente("Joana", "Rua da Escola2", new Data(1,1,2002), "199999999", "915295625"));
        clientes.add(new Cliente("Joaquim", "Rua da Escola", new Data(18,6,2000), "123456789", "911234567"));
//        Evento e = new Evento(Distrito.LEIRIA, "Feira de Maio", new Data(1,5,2022), new Data(31,5,2022));
//        adicionarEvento(e);
    }

    private void initListaVeiculosEstabelecimento() {
        List<Veiculo> veiculos = new ArrayList<>();
        listaVeiculosPorLocal.put(sede, veiculos);
        veiculos = new ArrayList<>();
        listaVeiculosVendidosPorLocal.put(sede, veiculos);
        for (Filial f : filiais) {
            veiculos = new ArrayList<>();
            listaVeiculosPorLocal.put(f,veiculos);
            veiculos = new ArrayList<>();
            listaVeiculosVendidosPorLocal.put(f,veiculos);
        }
    }

    public ArrayList<String> iterarHashTransacoesVendidosPorLocal(){
        Set<Local> keys = listaVeiculosVendidosPorLocal.keySet();
        ArrayList<String> string = new ArrayList<>();

        for (Local key : keys) {
            int quantidadeTransações = 0;
            List<Veiculo> veiculos = listaVeiculosVendidosPorLocal.get(key);
            for (Veiculo v : veiculos) {
                quantidadeTransações++;
            }
            string.add(key.toString() + " " + quantidadeTransações + " transações\n");
        }
        return string;
    }

    public ArrayList<String> iterarHashVeiculosVendidosPorLocal(){
        Set<Local> keys = listaVeiculosVendidosPorLocal.keySet();
        ArrayList<String> string = new ArrayList<>();
        for (Local key : keys) {
            int quantidadeTransações = 0;
            List<Veiculo> veiculos = listaVeiculosVendidosPorLocal.get(key);
            for (Veiculo v : veiculos) {
                quantidadeTransações++;
            }
            string.add(key.toString() + " " + quantidadeTransações + " veiculos\n");
        }
        return string;
    }

    public String iterarHashMarcasModelosVendidosPorLocal(){
        Set<Local> keys = listaVeiculosVendidosPorLocal.keySet();
        ArrayList<String> string = new ArrayList<>();

        for (Local key : keys) {
            int quantidadeTransações = 0;
            List<Veiculo> veiculos = listaVeiculosVendidosPorLocal.get(key);
            for (Veiculo v : veiculos) {
                quantidadeTransações++;
                string.add(v.getMarca() + " " + v.getModelo() + " - " + quantidadeTransações + " unidades\n");
            }
        }
        return string.get(string.size() - 1);
    }

    public String iterarHashMarcasModelosVendidos() {
        List<String> string = new ArrayList<>();
        List<Veiculo> veiculos = veiculosVendidos;
        int quantidadeTransações = 0;

        for (Veiculo v : veiculos) {
            quantidadeTransações++;
            string.add(v.getMarca() + " " + v.getModelo() + " - " + quantidadeTransações + " unidades\n");
        }

        return string.get(string.size() - 1);
    }

    public int numeroVeiculosVendidos(){
        return veiculosVendidos.size();
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

    public void adicionarCategoria(Categoria categoria) {
        catalogo.add(categoria);
    }

    public List<Categoria> getCatalogo() {
        return new ArrayList<>(catalogo);
    }

    public List<String> getPecasUsadasDaMarca(String marca){
        GestorVeiculosPorModelo gestor = listaPecasUsadasEmReparacaoPorMarca.get(marca);
        return gestor == null? new ArrayList<>() : gestor.getPecasUsadasDaMarca();
    }

    public List<String> getPecasUsadasTodasAsMarcas(){
        List<String> pecasParaTodasAsMarcas = new ArrayList<>();
        Enumeration<String> keys = listaPecasUsadasEmReparacaoPorMarca.keys();
        while(keys.hasMoreElements()) {
            String marca = keys.nextElement();
            pecasParaTodasAsMarcas.addAll(getPecasUsadasDaMarca(marca));
        }
        return pecasParaTodasAsMarcas;
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

    public List<Veiculo> getVeiculosVendidosLocal(Local local){
        return listaVeiculosVendidosPorLocal.get(local);
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
        List<Veiculo> veiculos = listaVeiculosVendidosPorLocal.get(veiculo.getLocal());
        veiculos.add(veiculo);

        List<Veiculo> antigos = listaVeiculosPorLocal.get(veiculo.getLocal());
        antigos.remove(veiculo);
    }

    public void removerVeiculoVendido(Veiculo veiculo) {
        veiculosProntosParaVenda.remove(veiculo);
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

    public boolean isNIFDuplicado(String nif, Cliente cliente) {
        if(cliente.getNIF().equals(nif)){
            return false;
        }
        for (Cliente c : clientes) {
            if(c.getNIF().equals(nif)){
                return true;
            }
        }
        return false;
    }

    public boolean isNIFDuplicadoNovoRegisto(String nif) {
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

    public List<Evento> getEventosNaoDecorridos(Distrito distrito, Data inicio, Data fim){
        List<Evento> eventosNaoDecorridos = new ArrayList<>();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        Data dataAtual = Data.parseData(date);

        for (Evento evento : eventos) {
            if(Data.isFirstDateAfterSecondDate(evento.getDataInicio(), dataAtual)){
                eventosNaoDecorridos.add(evento);
            }
        }

        List<Evento> eventosFiltrados = new ArrayList<>();
        Evento e;
        for(Evento evt : eventosNaoDecorridos) {
            e = evt;
            if(distrito != null && evt.getDistrito() != distrito){
                continue;
            }
            if(inicio != null && !evt.getDataInicio().equals(inicio)){
                continue;
            }

            if(fim != null && !evt.getDataFim().equals(fim)){
                continue;
            }

            if(listaVeiculosPorLocal.get(evt).size() > 0){
                continue;
            }

            eventosFiltrados.add(e);
        }

        return eventosFiltrados.size() == 0 ? null : eventosFiltrados;
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

    public boolean existemPecas() {
        for (Categoria categoria: catalogo) {
            if (!categoria.getPecas().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void adicionarPeca(Peca peca, int qtdSede, int qtdFiliais) {
        sede.registarPecaNaOficina(peca, qtdSede);

        for (Filial filial: filiais) {
            filial.registarPecaNaOficina(peca, qtdFiliais);
        }
    }

    public List<Peca> getPecas(Categoria categoria, String marca, String modelo, String dimensao, double preco){
        List<Peca> pecas = new ArrayList<>();
        List<Peca> pecasFiltradas = new ArrayList<>();

        if(categoria!=null){
            pecas = categoria.getPecas();
            if(pecas.isEmpty()){
                return pecas;
            }
        }else {
            for (Categoria c : catalogo) {
                for (Peca peca : c.getPecas()) {
                    pecas.add(peca);
                }
            }
        }

        for (Peca peca: pecas) {
            Peca p = peca;
            if(marca.length()!=0 && !peca.getMarca().matches(marca)){
                continue;
            }

            if(modelo.length()!=0 && !peca.getModelo().matches(modelo)){
                continue;
            }

            if(dimensao.length()!=0 && !peca.getDimensao().matches(dimensao)){
                continue;
            }

            if(preco != -1 && peca.getPreco()!=preco){
                continue;
            }

            pecasFiltradas.add(p);
        }

        return pecasFiltradas.isEmpty() ? new ArrayList<>() : pecasFiltradas;
    }

    public Peca getPeca(String nomePeca) {
        for (Categoria categoria : catalogo) {
            for (Peca peca : categoria.getPecas()) {
                if (peca.getNome() == nomePeca) {
                    return peca;
                }
            }
        }
        return null;
    }

    public void removerEvento(Evento evento) {
        eventos.remove(evento);
        listaVeiculosPorLocal.remove(evento);
    }

    public List<Estabelecimento> getEstabelecimentos(){
        List<Estabelecimento> lista = new ArrayList<>();
        lista.add(sede);
        lista.addAll(filiais);
        return lista;
    }

    public List<Veiculo> getTodosVeiculos(Estabelecimento estabelecimento, String marca, String modelo, String matricula) {
        List<Veiculo> veiculos = new ArrayList<>();
        List<Veiculo> veiculosLocal = new ArrayList<>();
        List<Veiculo> veiculoList;
        if(estabelecimento != null){
            veiculosLocal = getVeiculosLocal(estabelecimento);
        }else{
            List<Local> locais = new ArrayList<>();
            locais.add(sede);
            locais.addAll(filiais);
            locais.addAll(eventos);
            for (Local l : locais) {
                veiculoList = listaVeiculosPorLocal.get(l);
                for (Veiculo veiculo : veiculoList) {
                    veiculosLocal.add(veiculo);
                }
            }
        }

        Veiculo v;
        for (Veiculo veiculo : veiculosLocal) {
            v = veiculo;

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

    public List<Veiculo> getVeiculosReparados(Estabelecimento estabelecimento, String marca, String modelo, String matricula){
        List<Veiculo> veiculos = new ArrayList<>();


        Veiculo v;
        for (Veiculo veiculo : veiculosProntosParaVenda) {
            v = veiculo;

            if(veiculo.getEstadoVeiculo() == EstadoVeiculo.VENDIDO || veiculo.getEstadoVeiculo() == EstadoVeiculo.POR_REPARAR){
                continue;
            }

            if(estabelecimento != null && veiculo.getLocal() != estabelecimento){
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

    public void definirVeiculoPorReparar(Veiculo veiculo) {
        veiculo.setEstadoVeiculo(EstadoVeiculo.POR_REPARAR);
        veiculosProntosParaVenda.remove(veiculo);
        veiculosPorReparar.add(veiculo);
    }

    public void definirVeiculoReparado(Veiculo veiculo, Integer total){
        veiculo.setEstadoVeiculo(EstadoVeiculo.REPARADO);
        veiculosPorReparar.remove(veiculo);
        veiculosProntosParaVenda.add(veiculo);
        veiculo.adicionarPecasUsadas(total);
        GestorVeiculosPorModelo gestor = listaPecasUsadasEmReparacaoPorMarca.get(veiculo.getMarca());
        if(gestor==null){
            gestor = new GestorVeiculosPorModelo();
            listaPecasUsadasEmReparacaoPorMarca.put(veiculo.getMarca(),gestor);
        }
        gestor.inserirModelo(veiculo);
        totalPecasUsadasNaReparacao+=total;
    }

    public List<Veiculo> getVeiculosPorReparar(Estabelecimento estabelecimento, String marca, String modelo, String matricula){
        List<Veiculo> veiculos = new ArrayList<>();
        List<Veiculo> veiculosPorRepararNoLocal = new ArrayList<>();
        List<Veiculo> veiculosLocal = listaVeiculosPorLocal.get(estabelecimento);
        for (Veiculo veiculo : veiculosLocal) {
            if(veiculo.getEstadoVeiculo() == EstadoVeiculo.POR_REPARAR){
                veiculosPorRepararNoLocal.add(veiculo);
            }
        }
        Veiculo v;
        for (Veiculo veiculo : veiculosPorRepararNoLocal) {
            v = veiculo;

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

    public int size() {
        return clientes.size();
    }

    public boolean existemTransacoes() {
        for (Cliente cliente: getClientes(null, null)) {
            if(!cliente.getTransacoes().isEmpty()){
                return true;
            }
        }
        return false;
    }

    public List<Transacao> getTransacoes(Local local, Data data) {
        List<Transacao> transacoes = new ArrayList<>();

        for (Cliente cliente: getClientes(null,null)) {
            for (Transacao transacao: cliente.getTransacoes()) {
                Transacao t = transacao;

                if(local!=null && !transacao.getLocal().equals(local)){
                    continue;
                }

                if(data!=null && !transacao.getDataTransacao().equals(data)){
                    continue;
                }

                transacoes.add(t);
            }
        }

        return transacoes.isEmpty() ? new ArrayList<>() : transacoes;
    }

    public int getTotalPecasUsadasNaReparacao() {
        return totalPecasUsadasNaReparacao;
    }
}
