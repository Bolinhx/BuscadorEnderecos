import Models.ConsultaCep;
import Models.EnderecoViaCep;
import Models.Enderecos;
import Models.GeradorDeArquivo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException {
        Scanner read = new Scanner(System.in);
        String busca = "";
        List<Enderecos> enderecos = new ArrayList<>();



        while(!busca.equalsIgnoreCase("sair")) {

            System.out.println("informe o CEP com 8 digitos(somente numeros): ");
            busca = read.nextLine();

            if(busca.equalsIgnoreCase("sair")){
                break;
            }
            //System.out.println(endereco);
            try {
                ConsultaCep consultaCep = new ConsultaCep();
                consultaCep.buscaEndereco(busca);

                EnderecoViaCep meuEnderecoViaCep = consultaCep.buscaEndereco(busca);
                Enderecos meuEndereco = new Enderecos(meuEnderecoViaCep);
                System.out.println(meuEndereco);

                    if(meuEnderecoViaCep.erro()){
                        System.out.println("Digite um endereco valido");
                    }else{
                        enderecos.add(meuEndereco);
                    }


            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Finalizando aplicacao");
            }
        }
        GeradorDeArquivo gerador = new GeradorDeArquivo();
        gerador.salvaJson(enderecos);
        System.out.println(enderecos);

        System.out.println("O programa fechou corretamente");
    }
}
