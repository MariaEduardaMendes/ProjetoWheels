import java.util.List;
import java.util.ArrayList;

public class ControladorWheels {
    private List<Cliente> listaClientes = new ArrayList<>();
    private List<Bicicleta> listaBicicletas = new ArrayList<>();
    private List<Aluguel> alugueisAtivos = new ArrayList<>();
    private List<Aluguel> todosOsAlugueis = new ArrayList<>();
    private List<Manutencao> manutencoesAtivas = new ArrayList<>();

    private GerenciadorCSV gerenciadorCSV = new GerenciadorCSV();

    public ControladorWheels() {
        this.listaClientes = gerenciadorCSV.carregarClientes();
        this.listaBicicletas = gerenciadorCSV.carregarBicicletas();

        if (listaBicicletas.isEmpty()) {
            listaBicicletas.add(new Bicicleta());
            listaBicicletas.add(new Bicicleta());
            gerenciadorCSV.salvarBicicletas(listaBicicletas);
        }
        this.todosOsAlugueis = gerenciadorCSV.carregarAlugueis(listaClientes, listaBicicletas, alugueisAtivos);
    }

    public void cadastrarCliente(String nome, String cpf, String telefone) {
        Cliente novoCliente = new Cliente(nome, cpf, telefone);
        listaClientes.add(novoCliente);
        gerenciadorCSV.salvarCliente(novoCliente);
        System.out.println("SUCESSO: Cliente cadastrado e salvo no banco!");
    }


    public void cadastrarBicicleta() {
        Bicicleta b = new Bicicleta();
        listaBicicletas.add(b);
        gerenciadorCSV.salvarBicicletas(listaBicicletas);
        System.out.println("Nova bicicleta adicionada à frota! ID: " + b.getId());
    }

    public void iniciarAluguel(int idCliente) {
        Cliente cliente = null;
        for (Cliente c : listaClientes) {
            if (c.getId() == idCliente) {
                cliente = c;
            }
        }

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        Bicicleta bicicleta = null;
        for (Bicicleta b : listaBicicletas) {
            if (b.getEstado().equals("ESTOQUE")) {
                bicicleta = b;
                break;
            }
        }

        if (bicicleta == null) {
            System.out.println("Não há bicicletas disponíveis no estoque.");
            return;
        }

        Aluguel aluguel = new Aluguel();
        aluguel.setCliente(cliente);
        aluguel.setBicicleta(bicicleta);
        aluguel.realizarAluguel();

        alugueisAtivos.add(aluguel);
        todosOsAlugueis.add(aluguel);

        gerenciadorCSV.salvarBicicletas(listaBicicletas);
        gerenciadorCSV.salvarTodosAlugueis(todosOsAlugueis);

        System.out.println("Aluguel #" + aluguel.getId() + " iniciado com sucesso para " + cliente.getNome());
    }

    public void finalizarAluguel(int idAluguel, double horasSimuladas) {
        Aluguel aluguel = null;
        for (Aluguel a : alugueisAtivos) {
            if (a.getId() == idAluguel) aluguel = a;
        }
        if (aluguel == null) {
            System.out.println("Aluguel não encontrado.");
            return;
        }
        aluguel.registrarDevolucao(horasSimuladas);
        aluguel.verificarPagamento();

        Recibo recibo = new Recibo();
        recibo.adicionarAluguel(aluguel);
        recibo.emitirRecibo();

        alugueisAtivos.remove(aluguel);

        aluguel.getCliente().adicionarAoHistorico(aluguel);
        for(Cliente c : listaClientes) {
            c.adicionarAoHistorico(aluguel);
        }

        Bicicleta b = aluguel.getBicicleta();
        if (b.getEstado().equals("CONSERTO")) {
            Manutencao nova = new Manutencao(manutencoesAtivas.size() + 1, b);
            nova.solicitarConserto();
            manutencoesAtivas.add(nova);
        }

        gerenciadorCSV.salvarBicicletas(listaBicicletas);
        gerenciadorCSV.salvarTodosAlugueis(todosOsAlugueis);
    }

    public void finalizarManutencao(int index) {
        if (index >= 0 && index < manutencoesAtivas.size()) {
            Manutencao m = manutencoesAtivas.get(index);
            m.finalizarConserto();
            manutencoesAtivas.remove(index);
            gerenciadorCSV.salvarBicicletas(listaBicicletas);
        } else {
            System.out.println("Dado Inválido.");
        }
    }
    public List<Cliente> getListaClientes() { return listaClientes; }
    public List<Bicicleta> getListaBicicletas() { return listaBicicletas; }
    public List<Aluguel> getAlugueisAtivos() { return alugueisAtivos; }
    public List<Manutencao> getManutencoesAtivas() { return manutencoesAtivas; }
}