package main;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    static String username = "S51M8P";
    static String password = "xRCKFT";
    static String proxyHost = "81.176.239.100";
    static int proxyPort = 8000;

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

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

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI("http://info.cern.ch"))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());

        // solution for both http and https requests for java 8
        URL url1 = new URL("https://stackoverflow.com:433/questions/3304006/persistent-httpurlconnection-in-java");
        URL url2 = new URL("https://docs.oracle.com/javase/7/docs/technotes/guides/jweb/networking/proxie_config.html");
        URL url3 = new URL("https://ria.ru/20210702/shpionazh-1739511523.html");
        ProxiedHttpsConnection proxiedHttpsConnection = new ProxiedHttpsConnection(url2, proxyHost, proxyPort, username, password);
        proxiedHttpsConnection.setRequestMethod("GET");
        proxiedHttpsConnection.setRequestProperty("User-Agent", "curl/7.64.1");
        try (InputStream inputStream = proxiedHttpsConnection.getInputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) >= 0) {
                System.out.write(buffer, 0, length);
            }
        }
    }
}
