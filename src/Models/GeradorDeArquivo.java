package Models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeradorDeArquivo {

    public void salvaJson(List<Enderecos> enderecos) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        FileWriter write = new FileWriter("enderecos.json");
        write.write(gson.toJson(enderecos));
        write.close();
    }
}
