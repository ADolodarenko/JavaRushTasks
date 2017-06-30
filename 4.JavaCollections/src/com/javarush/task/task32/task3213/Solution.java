package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader, -3));  //Hello Amigo

    }

    public static String decode(StringReader reader, int key) throws IOException
    {
        if (reader == null)
            return "";
    
        StringWriter writer = new StringWriter();
        
        int c;
        while ((c = reader.read()) != -1)
            writer.write((char)(c + key));
        
        String result = writer.toString();

        if (result == null)
            result = "";
        
        return result;
    }

}
