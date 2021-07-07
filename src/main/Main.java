package main;

import java.io.InputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

public class Main {



    static String username = "S51M8P";
    static String password = "xRCKFT";
    static String proxyHost = "81.176.239.100";
    static int proxyPort = 8000;

    static String proHost = "109.224.6.142";
    static int proPort = 1080;

    static URI uri1 = URI.create("https://www.adidas.ru/");
    static URI uri2 = URI.create("https://stackoverflow.com/questions/3304006/persistent-httpurlconnection-in-java");

    static URL url1;
    static URL url2;
    static URL url3;

    static {
        try {
            url1 = new URL("https://stackoverflow.com:433/questions/3304006/persistent-httpurlconnection-in-java");
            url2 = new URL("https://docs.oracle.com/javase/7/docs/technotes/guides/jweb/networking/proxie_config.html");
            url3 = new URL("https://www.ozon.ru/product/elektrogril-delonghi-cgh1030d-161611701/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {

        // system properties to work with both http and https
        System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "false");
        System.setProperty("jdk.http.auth.proxying.disabledSchemes", "false");

        // solution for http proxy requests for java 11
        HttpClient httpClient = HttpClient.newBuilder()
                .proxy(ProxySelector.of(new InetSocketAddress(proxyHost, proxyPort)))
                .authenticator(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password.toCharArray());
                    }
                })
                .followRedirects(HttpClient.Redirect.NORMAL)
                .version(HttpClient.Version.HTTP_1_1)
                .build();
//
        HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder();
//        for (Map.Entry<String , String> entry : AdidasConfig.adidas.entrySet()) {
//            httpRequestBuilder.setHeader(entry.getKey(), entry.getValue());
//        }

//        HttpRequest httpRequest = httpRequestBuilder
//                .uri(uri2)
//                .GET()
//                .timeout(Duration.ofMillis(10_000))
////                .headers(OzonConfig.cartHeaders.entrySet().toArray().)
////                .POST(HttpRequest.BodyPublishers.ofString("[{\"id\":161611701,\"quantity\":1}]"))
////                .headers()
//                .build();
//
//        System.out.println(httpRequest.headers());


//        httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
//                .thenApply(response -> {
//                    System.out.println("Async request:");
//                    System.out.println(response.statusCode());
//                    return response;
//                })
//                .thenApply(HttpResponse::headers)
//                .thenAccept(System.out::println);
//        Thread.sleep(4000);
//        System.out.println(httpRequest.headers());
//        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.statusCode());
//        System.out.println(response.headers());
//        System.out.println(response.body());


        // solution for both http and https requests for java 8

//        StringBuilder cookies = new StringBuilder();
//        for (Map.Entry<String , String> entry : OzonConfig.cartHeaders.entrySet()) {
//            cookies.append(entry.getKey() + "=" + entry.getValue()).append(";");
//        }
        
        ProxiedHttpsConnection proxiedHttpsConnection = new ProxiedHttpsConnection(url1, proxyHost, proxyPort, username, password);
        proxiedHttpsConnection.setRequestMethod("GET");
        for (Map.Entry<String , String> entry : OzonConfig.cartHeaders.entrySet()) {
            proxiedHttpsConnection.addRequestProperty(entry.getKey(), entry.getValue());
        }
        proxiedHttpsConnection.connect();
        try (InputStream inputStream = proxiedHttpsConnection.getInputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) >= 0) {
                System.out.write(buffer, 0, length);
            }
        }
        System.out.println(proxiedHttpsConnection.getHeaderFields());
    }
}
