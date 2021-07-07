package main;

import java.util.HashMap;

public class CitilinkConfig {
    public static HashMap<String, String> cartHeaders = new HashMap<>();

    static{
        cartHeaders.put("pragma", "no-cache");
        cartHeaders.put("cache-control", "no-cache");
        cartHeaders.put("sec-ch-ua", "\" Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"91\", \"Chromium\";v=\"91\"");
        cartHeaders.put("accept", "*/*");
        cartHeaders.put("x-requested-with", "XMLHttpRequest");
        cartHeaders.put("sec-ch-ua-mobile", "?0");
        cartHeaders.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        cartHeaders.put("sec-fetch-site", "same-origin");
        cartHeaders.put("sec-fetch-mode", "cors");
        cartHeaders.put("sec-fetch-dest", "empty");
        cartHeaders.put("referer", "https://www.citilink.ru/catalog/");
        cartHeaders.put("accept-encoding", "br");
        cartHeaders.put("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
    }
}
