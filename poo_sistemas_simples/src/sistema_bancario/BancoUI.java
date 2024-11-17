package sistema_bancario;

import java.util.Scanner;

public class BancoUI {
  public static void main(String[] args) {
    Scanner sc = new Scanner(dadosDeTeste());
    Banco banco = new Banco();

    int opc;
    do {
      menuPrincipal();
      opc = sc.nextInt();
      sc.nextLine();
      switch (opc) {
        case 1:
          criarCliente(sc, banco);
          break;
        case 2:
          criarConta(sc, banco);
          break;
        case 3:
          exibirInformacoesCliente(sc, banco);
          break;
        case 4:
          realizarDeposito(sc, banco);
          break;
        case 5:
          realizarSaque(sc, banco);
          break;
        case 6:
          realizarTransferencia(sc, banco);
          break;
        case 7:
        case 8:
        default:
          System.out.println("Opção inválida. Tente novamente.");
          break;
        case 9:
          System.out.println("Saindo do sistema...");
      }
    } while(opc != 9);

    sc.close();
  }

  private static void criarCliente(Scanner sc, Banco banco) {
    System.out.print("Digite seu nome completo:\n==> ");
    String nome = sc.nextLine();
    System.out.print("Digite seu CPF:\n==> ");
    String cpf = sc.nextLine();
    Cliente novoCliente = new Cliente(nome, cpf);
    banco.adicionarCliente(novoCliente);
  }

  private static void criarConta(Scanner sc, Banco banco) {
    System.out.print("Digite seu CPF:\n==> ");
    String cpf = sc.nextLine();
    Cliente cliente = banco.buscarClientePorCpf(cpf);
    if (cliente == null) {
      System.out.println("Cliente não encontrado!");
    } else {
      System.out.println("Escolha o tipo de conta:");
      System.out.println("1 - Conta Corrente");
      System.out.println("2 - Conta Poupança");
      int tipoConta = sc.nextInt();
      sc.nextLine();
      System.out.print("Digite o número da conta:\n==> ");
      int numeroConta = sc.nextInt();
      sc.nextLine();
      System.out.print("Digite o saldo inicial:\n==> ");
      double saldo = sc.nextDouble();
      Object conta;
      if (tipoConta == 1) {
        conta = new ContaCorrente(cliente.getNome(), numeroConta, saldo);
      } else {
        if (tipoConta != 2) {
          System.out.println("Tipo de conta inválido.");
          return;
        }

        conta = new ContaPoupanca(cliente.getNome(), numeroConta, saldo);
      }

      cliente.adicionarConta((Conta)conta);
      System.out.println("Conta criada com sucesso!");
    }
  }

  private static void exibirInformacoesCliente(Scanner sc, Banco banco) {
    System.out.print("Digite seu CPF:\n==> ");
    String cpf = sc.nextLine();
    Cliente cliente = banco.buscarClientePorCpf(cpf);
    if (cliente == null) {
      System.out.println("Cliente não encontrado!");
    } else {
      System.out.println("Informações do Cliente:");
      System.out.println("Nome: " + cliente.getNome());
      cliente.exibirContas();
    }

  }

  private static void realizarDeposito(Scanner sc, Banco banco) {
    System.out.print("Digite seu CPF:\n==> ");
    String cpf = sc.nextLine();
    Cliente cliente = banco.buscarClientePorCpf(cpf);

    if (cliente == null) {
      System.out.println("Cliente não encontrado!");
      return;
    }

    System.out.print("Digite o número da conta:\n==> ");
    int numeroConta = sc.nextInt();

    for (Conta conta : cliente.getContas()) {
      if (conta.getNumeroConta() == numeroConta) {
        System.out.print("Digite o valor do depósito:\n==> ");
        double valor = sc.nextDouble();
        conta.depositar(valor);
        System.out.println("Depósito realizado com sucesso!");
        return;
      }
    }

    System.out.println("Conta não encontrada.");
  }


  private static void realizarSaque(Scanner sc, Banco banco) {
    System.out.print("Digite seu CPF:\n==> ");
    String cpf = sc.nextLine();
    Cliente cliente = banco.buscarClientePorCpf(cpf);

    if (cliente == null) {
      System.out.println("Cliente não encontrado!");
      return;
    }

    System.out.print("Digite o número da conta:\n==> ");
    int numeroConta = sc.nextInt();

    for (Conta conta : cliente.getContas()) {
      if (conta.getNumeroConta() == numeroConta) {
        System.out.print("Digite o valor do saque:\n==> ");
        double valor = sc.nextDouble();
        conta.sacar(valor);
        return;
      }
    }

    System.out.println("Conta não encontrada.");
  }

  private static void realizarTransferencia(Scanner sc, Banco banco) {
    System.out.print("Digite o CPF do cliente de origem:\n==> ");
    String cpfOrigem = sc.nextLine();
    Cliente clienteOrigem = banco.buscarClientePorCpf(cpfOrigem);
    if (clienteOrigem == null) {
      System.out.println("Cliente de origem não encontrado!");
    } else {
      System.out.print("Digite o número da conta de origem:\n==> ");
      int numeroContaOrigem = sc.nextInt();
      Conta contaOrigem = buscarContaPorNumero(clienteOrigem, numeroContaOrigem);
      if (contaOrigem == null) {
        System.out.println("Conta de origem não encontrada.");
      } else {
        sc.nextLine();
        System.out.print("Digite o CPF do cliente de destino:\n==> ");
        String cpfDestino = sc.nextLine();
        Cliente clienteDestino = banco.buscarClientePorCpf(cpfDestino);
        if (clienteDestino == null) {
          System.out.println("Cliente de destino não encontrado!");
        } else {
          System.out.print("Digite o número da conta de destino:\n==> ");
          int numeroContaDestino = sc.nextInt();
          Conta contaDestino = buscarContaPorNumero(clienteDestino, numeroContaDestino);
          if (contaDestino == null) {
            System.out.println("Conta de destino não encontrada.");
          } else {
            System.out.print("Digite o valor da transferência:\n==> ");
            double valor = sc.nextDouble();
            banco.transferir(contaOrigem, contaDestino, valor);
          }
        }
      }
    }
  }

  private static Conta buscarContaPorNumero(Cliente cliente, int numeroConta) {
    for (Conta conta : cliente.getContas()) {
      if (conta.getNumeroConta() == numeroConta) {
        return conta;
      }
    }
    return null;
  }

  public static void menuPrincipal() {
    String menu = "\nOperações:\n1 - Criar Cliente\n2 - Criar Conta\n3 - Exibir Informações do Cliente\n4 - Depositar\n5 - Sacar\n6 - Transferir\n9 - Sair\n";
    System.out.print(menu + "\nDigite a opção desejada:\n==> ");
  }

  public static String dadosDeTeste() {
    return "1\nJoão Duarte\n123\n2\n123\n2\n1234\n450\n1\nMariana Dantas\n456\n2\n456\n2\n4567\n500\n4\n123\n1234\n50\n6\n456\n4567\n123\n1234\n50\n3\n123\n3\n456\n9\n";
  }
}
