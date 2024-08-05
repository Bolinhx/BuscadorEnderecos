package Models;

public class Enderecos {
    String cep;
    String nome;
    String bairro;
    String cidade;
    String uf;
    boolean erro;

    @Override
    public String toString() {
        return "Enderecos{" +
                "cep='" + cep + '\'' +
                ", nome='" + nome + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }

    public Enderecos(String cep, String nome, String bairro, String cidade, String uf, boolean erro) {
        this.cep = cep;
        this.nome = nome;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.erro = erro;
    }

    public Enderecos(EnderecoViaCep enderecoViaCep){
        this.cep = enderecoViaCep.cep();
        this.nome = enderecoViaCep.logradouro();
        this.bairro = enderecoViaCep.bairro();
        this.cidade = enderecoViaCep.localidade();
        this.uf = enderecoViaCep.uf();
        this.erro = enderecoViaCep.erro();
    }

}
