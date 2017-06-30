package com.javarush.task.task31.task3102;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> result = new ArrayList<>();
    
        File rootDirectory = new File(root);
        
        if (rootDirectory.isDirectory())
        {
            Queue<File> directories = new LinkedList<>();
            directories.add(rootDirectory);
            
            while (!directories.isEmpty())
            {
                File element = directories.poll();
                
                for (File item : element.listFiles())
                {
                    if (item.isDirectory())
                        directories.add(item);
                    else if (item.isFile())
                        result.add(item.getAbsolutePath());
                }
            }
        }
        
        return result;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> fileNames = null;
        
        String line;
        while (!"exit".equals(line = reader.readLine()))
        {
            fileNames = getFileTree(line);
            
            printFileNames(fileNames);
        }
        
        reader.close();
    }
    
    public static void printFileNames(List<String> names)
    {
        for (String name : names)
            System.out.println(name);
    }
}
