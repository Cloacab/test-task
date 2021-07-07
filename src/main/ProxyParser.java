package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class ProxyParser {
    public static void main(String[] args) {
        String html = get("https://spys.one/proxies/");
        List<String> list = parse(html);
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static List<String> parse(String html) {
        List<String> arrayList = new ArrayList<>();
        Document doc;
        try {
            doc = Jsoup.parse(html);
            String js = doc.select("script").dataNodes().get(2).toString();
            System.out.println(js);
            Element table = doc.select("table").get(1).getElementsByTag("tbody").first();
            int i = 0;
            List<Element> elements = new ArrayList<>();
            elements.addAll(table.getElementsByClass("spy1xx"));
            elements.addAll(table.getElementsByClass("spy1x"));
            for (Element element : elements) {
                try {
                    String host = element.getElementsByClass("spy14").text().split(" ")[0];
                    Element qwe = element.getElementsByClass("spy14").first();
                    String scr = qwe.selectFirst("script").dataNodes().get(0).toString();
                    scr = scr.substring(44, scr.length() - 1);
                    String ip = p(js, scr);
                    arrayList.add(host + ":" + ip);
                } catch (Exception ignored) {
                }
                i++;
            }
            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static String get(String url_str) {
        try {
            URL url = new URL(url_str);
            URLConnection con = url.openConnection();
            HttpURLConnection conn = (HttpURLConnection) con;

            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            //String urlParameters = "xx0=492aaeeac6eae38ba0e5330c7a1b1ae2&xpp=5&tldc=0&xf1=0&xf2=0&xf4=0&xf5=0";
            String urlParameters = "xx0=53fe096de5544e23aee52f1d3e6ada87&xpp=5&tldc=0&xf1=0&xf2=0&xf4=0&xf5=2";
            //String urlParameters = "xx0=8388afc6c6b44b7543a6604ace940b4f&xpp=5&tldc=9&xf1=0&xf2=0&xf4=0&xf5=2";
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            con.setRequestProperty("accept-encoding", "br");
            con.setRequestProperty("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
            con.setRequestProperty("cache-control", "max-age=0");
            con.setRequestProperty("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"90\", \"Google Chrome\";v=\"90\"");
            con.setRequestProperty("sec-ch-ua-mobile", "?0");
            con.setRequestProperty("sec-fetch-dest", "document");
            con.setRequestProperty("sec-fetch-mode", "navigate");
            con.setRequestProperty("sec-fetch-site", "none");
            con.setRequestProperty("sec-fetch-user", "?1");
            con.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36");
            con.setRequestProperty("accept", "text/html,application/xhtml+xml");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setUseCaches(false);
            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.write(postData);
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String p(String js, String port_srt) throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        engine.eval(js);
        Invocable inv = (Invocable) engine;
        return (String) inv.invokeFunction("eval", "\"\"+" + port_srt);
    }
}
