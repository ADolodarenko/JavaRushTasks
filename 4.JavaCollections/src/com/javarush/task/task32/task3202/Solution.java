package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException
    {
        StringWriter writer = new StringWriter();
        
        if (is == null)
            return writer;
    
        InputStreamReader reader = new InputStreamReader(is);
        
        int c;
        while ((c = reader.read()) != -1)
            writer.write(c);
            
        return writer;
    }
}