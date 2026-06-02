import java.util.List;
import java.util.ArrayList;

public class Bicicleta {
    private static int contadorId = 1;
    private int id;
    private String modelo;
    private String estado;
    private double horasDeUso;
    private double taxaPorHora;

    private static final double LIMITE_HORAS_MANUTENCAO = 100.0;

    public Bicicleta() {
        this.id = contadorId++;
        this.horasDeUso = 0.0;
        this.estado = "ESTOQUE";
        this.taxaPorHora = 15.0;
    }

    // construtor para csv
    public Bicicleta(int id, String modelo, String estado, double horasDeUso, double taxaPorHora) {
        this.id = id;
        this.modelo = modelo;
        this.estado = estado;
        this.horasDeUso = horasDeUso;
        this.taxaPorHora = taxaPorHora;
        if (id >= contadorId) {
            contadorId = id + 1;
        }
    }

    public void atualizarEstado(String novoEstado) {
        this.estado = novoEstado;
        System.out.println("Bicicleta " + id + " mudou para o estado: " + estado);
    }

    public void registrarHorasDeUso(double horas) {
        this.horasDeUso += horas;
        System.out.println("Horas de uso atualizadas. Total atual: " + this.horasDeUso + "h");
    }

    public boolean verificarNecessidadeManutencao() {
        return this.horasDeUso >= LIMITE_HORAS_MANUTENCAO;
    }

    public void zerarHorasDeUso() {
        this.horasDeUso = 0.0;
        System.out.println("As horas de uso da bicicleta " + this.id + " foram zeradas após a revisão.");
    }

    public int getId() { return id; }
    public String getEstado() { return estado; }
    public double getTaxaPorHora() {
        return this.taxaPorHora;
    }
    public String getModelo() { return modelo; }
    public double getHorasDeUso() { return horasDeUso; }

}