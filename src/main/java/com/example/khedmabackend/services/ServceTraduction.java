package com.example.khedmabackend.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Collection;
import java.util.Properties;
import java.util.Scanner;

public class ServceTraduction {

    public static String traduire(String description) throws IOException, InterruptedException, ParseException {

        Thread.sleep(100);
        FileWriter fileWriter=new FileWriter("description.txt");
        fileWriter.write(description);
        fileWriter.close();
        Thread.sleep(100);
        Process p= Runtime.getRuntime().exec("cmd /c python TraductionPython.py");


        Scanner sc=new Scanner(p.getInputStream());

        sc.tokens().forEach(System.out::println);
        Scanner scanner=new Scanner(new File("description.txt"));

//        Properties properties=new Properties();

//        properties.load(new FileInputStream("description.json"));




//        properties.
        return scanner.nextLine();
    }


}
