package sistema_bancario;

public class ContaCorrente extends Conta {
  private double limiteChequeEspecial = 200.0;

  ContaCorrente(String titular, int numeroConta, double saldo) {
    super(titular, numeroConta, saldo);
  }

  void exibirDetalhesConta() {
    System.out.println("Conta Corrente - Titular: " + this.getTitular());
    System.out.println("Número da Conta: " + this.getNumeroConta());
    System.out.println("Saldo: " + this.getSaldo());
    System.out.println("Limite do Cheque Especial: " + this.limiteChequeEspecial);
  }

  void sacar(double valor) {
    if (valor <= 0) {
      System.out.println("Valor de saque inválido.");
    } else {
      if (valor <= this.getSaldo() + this.limiteChequeEspecial) {
        if (valor <= this.getSaldo()) {
          super.sacar(valor);
        } else {
          double valorUsadoDoLimite = valor - this.getSaldo();
          super.sacar(this.getSaldo());
          this.limiteChequeEspecial -= valorUsadoDoLimite;
          System.out.println("Usando cheque especial para completar o saque.");
        }
        System.out.println("Saque realizado com sucesso. Saldo atual: " + getSaldo() + ", Limite do cheque especial restante: " + this.limiteChequeEspecial);
      } else {
        System.out.println("Saque não permitido: valor excede o limite do cheque especial.");
      }

    }
  }
}