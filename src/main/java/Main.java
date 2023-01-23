import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {

    public static ObjectMapper mapper = new ObjectMapper();
    public static String apiKey = "Ibia80n47Y8JbYITS86deTTqQG3FzN1fMarxzQBt";

    public static void main(String[] args) throws RuntimeException, IOException {

// Создаем HTTP запрос
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
        HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=" + apiKey);
        CloseableHttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

// Создаем объект LinkObject, заранее зная, что коллекцию объектов создать не получится
        JsonNode jsonNode = mapper.readTree(body);
        LinkObject linkObject = mapper.readValue(jsonNode.toString(), LinkObject.class);

// Получаем адрес на медиаобъект NASA
        String hdurl = linkObject.getHdurl();

// Преобразуем в последовательность байтов
        byte[] bytes = responseByte(hdurl);

// Записываем файл на компьютер
        try (FileOutputStream fos = new FileOutputStream(linkObject.getPath());
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(bytes, 0, bytes.length);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static byte[] responseByte(String uri) throws IOException {
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final HttpUriRequest httpGet = new HttpGet(uri);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            return response.getEntity().getContent().readAllBytes();
        }
    }
}
