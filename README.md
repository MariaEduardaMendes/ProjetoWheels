# 🚲 Projeto Wheels - Sistema de Aluguel de Bicicletas

> ⚠️ **Status do Projeto: Em Desenvolvimento (Work in Progress)** ⚠️
> Este repositório contém a documentação, modelagem estrutural e os esboços iniciais da implementação em Java para um sistema de gestão de locação de bicicletas. O projeto encontra-se atualmente na fase de mapeamento de requisitos e transição para o paradigma de Programação Orientada a Objetos (POO).

---

## 📖 1. Definição de Atores e Funcionalidades

### Atores do Sistema
* **Administrador:** Responsável pela gestão estratégica, controle de cadastros base (clientes e bicicletas) e visualização de fluxos financeiros.
* **Recepcionista:** Ator operacional encarregado do atendimento direto ao público, processando os fluxos de locação, devolução e recebimento de valores.
* **Mecânico:** Ator técnico especializado encarregado da manutenção, reparos e atualização do estado de conservação da frota.
* **Cliente:** Consumidor final que fornece dados para o registo, consome o serviço de locação e recebe os devidos comprovativos/recibos.

### Interações e Comportamentos Esperados
* O sistema deve automatizar por completo o cálculo do valor do aluguer com base no tempo decorrido.
* Deve gerar resumos consolidados de tempo de utilização no ato da devolução.
* Deve manter uma verificação e atualização constante dos estados das bicicletas (`ESTOQUE`, `ALUGADA`, `CONSERTO`).

---

## 🎯 2. Casos de Uso

### Requeridos pela Especificação:
1. **Manter Cadastro de Bicicletas:** Registar detalhes técnicos, modelo, taxa horária e o estado atual da frota.
2. **Manter Cadastro de Clientes:** Registar dados pessoais, de contacto e histórico de transações.
3. **Realizar Aluguel:** Processar a saída operacional de uma bicicleta vinculada a um cliente.
4. **Registrar Devolução:** Calcular o tempo efetivo de uso, atualizar o estado da bicicleta de volta para o stock e calcular o valor final.
5. **Emitir Recibo:** Unificar e consolidar as transações de aluguer de um cliente num único documento de faturação.

### Casos de Uso Adicionais Sugeridos:
* **Gerar Relatório Financeiro:** Permitir ao administrador a visualização do fluxo de caixa e faturação do sistema.
* **Notificar Manutenção:** Alerta automático do sistema quando uma bicicleta atinge um limite crítico de horas de uso acumuladas.

---

## 🗺️ 3. Mapeamento de Atores para Casos de Uso

| Caso de Uso | Ator (Fornecedor / Iniciador) | Ator (Consumidor / Beneficiário) |
| :--- | :--- | :--- |
| **Manter Cadastro de Clientes** | Administrador | Sistema |
| **Manter Cadastro de Bicicletas** | Administrador | Sistema / Recepcionista |
| **Realizar Aluguel** | Recepcionista | Cliente |
| **Calcular Valor** | Sistema (Automático) | Recepcionista / Cliente |
| **Emitir Recibo** | Recepcionista | Cliente |
| **Receber Devolução** | Recepcionista / Cliente | Sistema / Recepcionista |
| **Verificar Pagamento** | Recepcionista | Sistema |
| **Solicitar Conserto** | Administrador / Recepcionista | Mecânico |
| **Consertar Bicicleta** | Mecânico | Sistema |

---

## 📐 4. Diagrama de Classes (UML - Mermaid)

```mermaid
classDiagram
    %% Classes de Domínio
    class Cliente {
        -int id
        -String nome
        -String cpf
        -String telefone
        -List~Aluguel~ historicoDeTransacoes
        +cadastrar() Void
        +atualizarDados() Void
        +consultarHistorico() Void
    }

    class Bicicleta {
        -int id
        -String modelo
        -String estado
        -double horasDeUso
        -double taxaPorHora
        +cadastrar() Void
        +atualizarEstado() Void
        +registrarHorasDeUso() Void
        +verificarNecessidadeManutencao() Void
    }

    class Aluguel {
        -int id
        -LocalDateTime dataHoraRetirada
        -LocalDateTime dataHoraDevolucao
        -double tempoDeUso
        -double valorTotal
        -String statusPagamento
        -Cliente cliente
        -Bicicleta bicicleta
        -Recibo recibo
        +realizarAluguel() Void
        +registrarDevolucao() Void
        +calcularValor() Void
        +verificarPagamento() Void
        +gerarResumo() Void
    }

    class Recibo {
        -int id
        -LocalDateTime dataEmissao
        -double valorTotalConsolidado
        -List~Aluguel~ alugueis
        +emitirRecibo() Void
    }

    class Manutencao {
        -int id
        -LocalDateTime dataSolicitacao
        -LocalDateTime dataConclusao
        -String status
        -Bicicleta bicicleta
        +solicitarConserto() Void
        +finalizarConserto() Void
    }

    %% Relacionamentos
    Cliente "1" -- "1..*" Aluguel : realiza >
    Bicicleta "1" -- "0..*" Aluguel : vinculada a >
    Bicicleta "1" -- "0..*" Manutencao : passa por >
    Recibo "1" -- "1..*" Aluguel : contem >
