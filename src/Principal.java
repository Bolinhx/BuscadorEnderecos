import Models.EnderecoViaCep;
import Models.Enderecos;
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

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        while(!busca.equalsIgnoreCase("sair")) {

            System.out.println("informe o CEP com 8 digitos(somente numeros): ");
            busca = read.nextLine();

            if(busca.equalsIgnoreCase("sair")){
                break;
            }


            String endereco = "https://viacep.com.br/ws/" + busca + "/json/";
            //System.out.println(endereco);


            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                //System.out.println(json);

                EnderecoViaCep enderecoViaCep = gson.fromJson(json, EnderecoViaCep.class);
                //System.out.println(enderecoViaCep);

                Enderecos meuEndereco = new Enderecos(enderecoViaCep);
                //System.out.println(meuEndereco);
                if(enderecoViaCep.erro()){
                    System.out.println("Cep inexistente, digite um cep valido");
                }else {
                    enderecos.add(meuEndereco);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(enderecos);

        FileWriter write = new FileWriter("enderecos.json");
        write.write(gson.toJson(enderecos));
        write.close();
        System.out.println("O programa fechou corretamente");
    }
}
