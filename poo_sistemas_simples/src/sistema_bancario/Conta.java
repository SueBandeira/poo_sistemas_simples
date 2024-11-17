package sistema_bancario;

abstract class Conta {
  private String titular;
  private int numeroConta;
  private double saldo;

  Conta(String titular, int numeroConta, double saldo) {
    this.titular = titular;
    this.numeroConta = numeroConta;
    this.saldo = saldo;
  }

  void depositar(double valor) {
    if (valor > 0) {
      this.saldo += valor;
    } else {
      System.out.println("Valor de depósito inválido.");
    }

  }

  void sacar(double valor) {
    if (this.saldo >= valor && valor > 0) {
      this.saldo -= valor;
    } else {
      System.out.println("Saque inválido ou saldo insuficiente.");
    }

  }

  void consultarSaldo() {
    System.out.println("Saldo: " + this.saldo);
  }

  public String getTitular() {
    return this.titular;
  }

  public int getNumeroConta() {
    return this.numeroConta;
  }

  public double getSaldo() {
    return this.saldo;
  }

  abstract void exibirDetalhesConta();
}
