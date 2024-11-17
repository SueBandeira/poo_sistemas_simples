package sistema_bancario;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
  private String nome;
  private String cpf;
  private List<Conta> contaList;

  public Cliente(String nome, String cpf) {
    this.nome = nome;
    this.cpf = cpf;
    this.contaList = new ArrayList<>();
  }

  public String getNome() {
    return nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void adicionarConta(Conta conta) {
    contaList.add(conta);
  }

  public void exibirContas() {
    if (contaList.isEmpty()) {
      System.out.println("Nenhuma conta associada a este cliente.");
    } else {
      for (Conta conta : contaList) {
        conta.exibirDetalhesConta();
      }
    }
  }

  public List<Conta> getContas() {
    return contaList;
  }
}
