package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Vladimir_Putin_January_2017.jpg/250px-Vladimir_Putin_January_2017.jpg", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);
        Path tempFile = Files.createTempFile("tmp_", ".tmp");
    
        InputStream input = url.openStream();
        Files.copy(input, tempFile);
    
        Path destFile = Paths.get(downloadDirectory.toString() + "/" + urlString.substring(urlString.lastIndexOf("/") + 1));
        
        Files.move(tempFile, destFile);
        
        return destFile;
    }
}
