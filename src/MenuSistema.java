import java.util.Scanner;

public class MenuSistema {
    private Scanner scanner = new Scanner(System.in);
    private ControladorWheels controlador = new ControladorWheels();

    public void iniciar() {
        System.out.println("Iniciando Sistema Wheels...");
        boolean sistemaRodando = true;

        while (sistemaRodando) {
            System.out.println("\n====== MENU SISTEMA WHEELS ======");
            System.out.println("--- GESTÃO DE CLIENTES ---");
            System.out.println("1. Cadastrar Novo Cliente");
            System.out.println("2. Listar Clientes e Histórico");
            System.out.println("--- GESTÃO DE BICICLETAS ---");
            System.out.println("3. Cadastrar Nova Bicicleta");
            System.out.println("4. Listar Estado das Bicicletas");
            System.out.println("--- OPERAÇÕES (RECEPCIONISTA) ---");
            System.out.println("5. Realizar Aluguel");
            System.out.println("6. Registrar Devolução");
            System.out.println("--- OFICINA (MECÂNICO) ---");
            System.out.println("7. Painel do Mecânico");
            System.out.println("==================================");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: telaCadastrarCliente(); break;
                case 2: telaListarClientes(); break;
                case 3: controlador.cadastrarBicicleta(); break;
                case 4: telaListarBicicletas(); break;
                case 5: telaIniciarAluguel(); break;
                case 6: telaFinalizarAluguel(); break;
                case 7: telaPainelMecanico(); break;
                case 0:
                    sistemaRodando = false;
                    System.out.println("Encerrando o sistema... Até logo!");
                    break;
                default: System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }

    private void telaCadastrarCliente() {
        System.out.println("\n--- NOVO CADASTRO DE CLIENTE ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        controlador.cadastrarCliente(nome, cpf, telefone);
    }

    private void telaListarClientes() {
        System.out.println("\n--- CLIENTES CADASTRADOS ---");
        if (controlador.getListaClientes().isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (Cliente c : controlador.getListaClientes()) {
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome());
        }
        System.out.print("\nDeseja ver o histórico de algum cliente? (S/N): ");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            System.out.print("Digite o ID do cliente: ");
            int id = scanner.nextInt(); scanner.nextLine();
            for (Cliente c : controlador.getListaClientes()) {
                if (c.getId() == id) {
                    c.consultarHistorico();
                    return;
                }
            }
            System.out.println("Cliente não encontrado.");
        }
    }

    private void telaListarBicicletas() {
        System.out.println("\n--- ESTADO DAS BICICLETAS ---");
        for (Bicicleta b : controlador.getListaBicicletas()) {
            System.out.println("Bicicleta ID: " + b.getId() + " | Estado: " + b.getEstado());
        }
    }

    private void telaIniciarAluguel() {
        System.out.println("\n--- REALIZAR ALUGUEL ---");
        System.out.print("Digite o ID do Cliente: ");
        int idCliente = scanner.nextInt(); scanner.nextLine();
        controlador.iniciarAluguel(idCliente);
    }

    private void telaFinalizarAluguel() {
        System.out.println("\n--- REGISTRAR DEVOLUÇÃO ---");
        if (controlador.getAlugueisAtivos().isEmpty()) {
            System.out.println("Não há aluguéis ativos.");
            return;
        }
        for (Aluguel a : controlador.getAlugueisAtivos()) {
            System.out.println("Aluguel #" + a.getId());
        }
        System.out.print("Digite o ID do Aluguel a devolver: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Simular horas de uso: ");
        double horas = scanner.nextDouble();
        scanner.nextLine();

        controlador.finalizarAluguel(id, horas);
    }

    private void telaPainelMecanico() {
        System.out.println("\n--- PAINEL DO MECÂNICO ---");
        if (controlador.getManutencoesAtivas().isEmpty()) {
            System.out.println("Nenhuma bicicleta em manutenção.");
            return;
        }
        for (int i = 0; i < controlador.getManutencoesAtivas().size(); i++) {
            System.out.println("[" + i + "] Ordem de Serviço Pendente");
        }
        System.out.print("Qual Serviço deseja finalizar?");
        int index = scanner.nextInt();
        scanner.nextLine();
        controlador.finalizarManutencao(index);
    }
}