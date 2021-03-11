//Есть документ со списком URL:
//https://drive.google.com/open?id=1wVBKKxpTKvWwuCzqY1cVXCQZYCsdCXTl
//Вывести топ 10 доменов которые встречаются чаще всего. В документе могут встречается пустые и недопустимые строки.

package chrvk.javaelementary.hw9.task1;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Main {
    public static List<String> readFile(File myFile) {
        List<String> url = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(myFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                url.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getDomainName(String url) {
        if (!url.startsWith("http") && !url.startsWith("https")) {
            url = "http://" + url;
        }
        URL netUrl = null;
        try {
            netUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        assert netUrl != null;
        String host = netUrl.getHost();
        if (host.startsWith("www")) {
            host = host.substring("www".length() + 1);
        }
        return host;
    }

    public static List<Map.Entry<String, Integer>> findTopDomains(List<String> url) {
        Map<String, Integer> map = new HashMap<>();
        for (String domain : url) {
            if (map.containsKey(domain)) {
                int count = map.get(domain);
                count++;
                map.put(domain, count);
            } else {
                map.put(domain, 1);
            }
        }
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));
        return entries;
    }

    public static void main(String[] args) {
        final int outputSize = 10;
        List<String> url;
        File myFile = new File("src/chrvk/JavaElementary/hw9/task1/urls.txt");

        url = readFile(myFile);
        List<String> newUrl = new ArrayList<>();
        for (String s : url) {
            newUrl.add(getDomainName(s));
        }

        List<Map.Entry<String, Integer>> entries = findTopDomains(newUrl);
        for (int i = 0; i < outputSize; i++) {
            System.out.println(entries.get(i + 1));
        }
    }
}
