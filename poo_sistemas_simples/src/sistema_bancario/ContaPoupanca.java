package sistema_bancario;

public class ContaPoupanca extends Conta {
  private double taxaJurosMensal = 0.589;

  ContaPoupanca(String titular, int numeroConta, double saldo) {
    super(titular, numeroConta, saldo);
  }

  void exibirDetalhesConta() {
    System.out.println("Conta Poupança - Titular: " + this.getTitular());
    System.out.println("Número da Conta: " + this.getNumeroConta());
    System.out.println("Saldo: " + this.getSaldo());
    System.out.println("Taxa de juros mensal: " + this.taxaJurosMensal);
  }

  void aplicarRendimento() {
    if (this.getSaldo() > 0 && this.taxaJurosMensal > 0) {
      double rendimento = this.getSaldo() * this.taxaJurosMensal / 100;
      this.depositar(rendimento);
      System.out.println("Rendimento aplicado: " + rendimento);
      System.out.println("Novo saldo: " + this.getSaldo());
    } else
      System.out.println("Não foi possível aplicar rendimento.");
  }
}