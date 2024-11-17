package sistema_bancario;

import java.util.ArrayList;
import java.util.List;

public class Banco {
  private List<Cliente> clientes;

  public Banco() {
    this.clientes = new ArrayList<>();
  }

  public void adicionarCliente(Cliente cliente) {
    for (Cliente c : clientes) {
      if (c.getCpf().equals(cliente.getCpf())) {
        System.out.println("Cliente já cadastrado!");
        return;
      }
    }
    clientes.add(cliente);
    System.out.println("Cliente adicionado com sucesso!");
  }

  public Cliente buscarClientePorCpf(String cpf) {
    for (Cliente cliente : clientes) {
      if (cliente.getCpf().equals(cpf)) {
        return cliente;
      }
    }
    return null;
  }

  public void exibirClientes() {
    if (clientes.isEmpty()) {
      System.out.println("Nenhum cliente cadastrado no banco.");
    } else {
      for (Cliente cliente : clientes) {
        System.out.println("Cliente: " + cliente.getNome() + ", CPF: " + cliente.getCpf());
        cliente.exibirContas();
      }
    }
  }
  public void transferir(Conta origem, Conta destino, double valor) {
    if (origem != null && destino != null) {
      if (valor <= 0.0) {
        System.out.println("Valor de transferência inválido.");
      } else {
        if (valor <= origem.getSaldo()) {
          origem.sacar(valor);
          destino.depositar(valor);
          System.out.println("Transferência realizada com sucesso.");
          System.out.println("Saldo atual da conta de origem: " + origem.getSaldo());
          System.out.println("Saldo atual da conta de destino: " + destino.getSaldo());
        } else {
          System.out.println("Transferência não permitida: valor excede o saldo da conta de origem.");
        }

      }
    } else {
      System.out.println("Transferência não realizada: uma das contas é inválida.");
    }
  }
}
