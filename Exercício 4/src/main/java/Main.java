import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

/**
 * @author Daniella Emily Cornélio da Silva
 * @version 1.0, 04/05/2025
 */

public class Main {
    private static final String subscriptionKey = "YOUR_SUBSCRIPTION_KEY";
    private static final String endpoint = "API_ENDPOINT";
    
    String textToAnalyze = "Desde que entrei na faculdade de Computação, percebi que o curso vai muito além de aprender linguagens de programação. É um exercício constante de lógica, paciência e criatividade. Cada matéria apresenta um novo universo, mas algumas, como Algoritmos e Estruturas de Dados, realmente testam nossa forma de pensar.\n"
        + "\n"
        + "No início, encarar códigos que manipulam listas encadeadas ou algoritmos de ordenação parecia assustador. Mas com o tempo, aprendi a enxergar a beleza por trás dessas estruturas: são como peças de um quebra-cabeça que, quando encaixadas corretamente, revelam soluções elegantes para problemas complexos.\n"
        + "\n"
        + "Há dias em que tudo flui, em que o código compila de primeira e o sentimento de conquista é enorme. Mas também há aqueles momentos em que nada funciona, e a frustração parece inevitável. Nessas horas, aprendi que insistir e revisar os fundamentos faz toda a diferença.\n"
        + "\n"
        + "O contato com colegas que compartilham os mesmos desafios e a troca de ideias durante os estudos tornaram o processo mais leve. Não se trata apenas de aprender a programar, mas de desenvolver raciocínio crítico e resiliência para resolver problemas reais.\n"
        + "\n"
        + "Com isso, vejo que cada desafio enfrentado está me preparando para ser um profissional mais completo. A jornada é longa, mas a cada linha de código, me aproximo mais dos meus objetivos.";

        HttpClient client = HttpClient.newHttpClient();
        
        Gson gson = new Gson();

        Map<String, Object> document = new HashMap<>();
        document.put("language", "pt");
        document.put("id", "1");
        document.put("text", textToAnalyze);

        Map<String, Object> documents = new HashMap<>();
        documents.put("documents", new Object[]{document});

        String json = gson.toJson(documents);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endpoint + "/text/analytics/v3.0/sentiment"))
            .header("Content-Type", "application/json")
            .header("Ocp-Apim-Subscription-Key", subscriptionKey)
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(System.out::println)
            .join();
    }
}