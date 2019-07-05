package com;

import java.net.URL;
import java.net.URLConnection;

public class Demo {
    public static void main(String[] args) throws Exception{
        
        URL url = new URL("http://localhost:8080/SpringMVC_Web/mvc/control/testHeader");
        URLConnection yc = url.openConnection();
        yc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
        yc.getInputStream();
    }
}
