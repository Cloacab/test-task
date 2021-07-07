package main;

import java.util.HashMap;

public class AdidasConfig {

    public static HashMap<String , String > adidas = new HashMap<>();

    static {
        adidas.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,/;q=0.8,application/signed-exchange;v=b3;q=0.9");
        adidas.put("accept-encoding","br");
        adidas.put("accept-language","ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        adidas.put("content-type","application/json;charset=UTF-8");
        adidas.put("checkout-authorization","null");
        adidas.put("dnt","1");
        adidas.put("origin","https://www.adidas.ru/%22");
        adidas.put("referer","https://www.adidas.ru/cart%22");
        adidas.put("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"90\", \"Google Chrome\";v=\"90\"");
        adidas.put("sec-ch-ua-mobile","?0");
        adidas.put("sec-fetch-dest","empty");
        adidas.put("sec-fetch-mode","cors");
        adidas.put("sec-fetch-site","same-origin");
        adidas.put("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36");
        adidas.put("glassversion", "1f96064");
    }
}
