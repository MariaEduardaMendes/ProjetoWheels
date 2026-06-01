public class Main {
    static void main(String[] args) {
        System.out.println("=== SISTEMA WHEELS ===\n");

        Cliente cliente = new Cliente();
        Bicicleta bicicleta = new Bicicleta();

        System.out.println("--- Cenário 1: Aluguel Normal ---");
        Aluguel aluguel1 = new Aluguel();
        aluguel1.setBicicleta(bicicleta);
        aluguel1.setCliente(cliente);

        aluguel1.realizarAluguel();


        System.out.println("[Simulação] Adicionando 99 horas de uso prévio na bicicleta...");
        bicicleta.registrarHorasDeUso(99.0);

        aluguel1.registrarDevolucao();
        System.out.println("Estado da bicicleta após devolução 1: " + bicicleta.getEstado() + "\n");


        System.out.println("--- Cenário 2: Estourando o Limite de Horas ---");
        Aluguel aluguel2 = new Aluguel();
        aluguel2.setBicicleta(bicicleta);

        aluguel2.realizarAluguel();

        System.out.println("[Simulação] Adicionando mais 2 horas de uso na bicicleta...");
        bicicleta.registrarHorasDeUso(2.0);

        aluguel2.registrarDevolucao();
        System.out.println("Estado da bicicleta após devolução 2: " + bicicleta.getEstado() + "\n");


        System.out.println("--- Cenário 3: Ação do Mecânico ---");
        Manutencao manutencao = new Manutencao(1, bicicleta);

        manutencao.solicitarConserto();
        System.out.println("Trabalho do mecânico em andamento...");

        manutencao.finalizarConserto();
    }
}