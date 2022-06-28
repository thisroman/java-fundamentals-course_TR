package com.bobocode.lesson12;

import lombok.SneakyThrows;

import java.io.*;
import java.net.Socket;

public class DemoApp {

    @SneakyThrows
    public static void main(String[] args) {
        try(Socket soket = new Socket("93.175.204.87", 8080)) {
            //for (int i = 0; i < 10; i++) {


                OutputStream outputStream = soket.getOutputStream();

                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream));

                printWriter.println("GET /hello?name=Roman HTTP/1.1");
                printWriter.println("Host: 188.163.115.149");
                printWriter.println("");
                printWriter.flush();

                BufferedReader bufRead = new BufferedReader(new InputStreamReader(soket.getInputStream()));
                String outStr;

                while((outStr = bufRead.readLine()) != null){
                    System.out.println(outStr);
                }

                bufRead.close();
                printWriter.close();
           // }
        }
    }

}
