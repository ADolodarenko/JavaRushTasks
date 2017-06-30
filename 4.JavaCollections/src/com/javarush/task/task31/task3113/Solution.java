package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution
{
    static int countFiles = 0;
    static int countDir = 0;
    static long dirSize;

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path directory = Paths.get(reader.readLine());
        
        reader.close();
    
        if (!Files.isDirectory(directory))
        {
            System.out.println(directory.toAbsolutePath().toString() + " - не папка");
            return;
        }
    
        Files.walkFileTree(directory, new MyFileVisitor());
        System.out.println("Всего папок - " + (countDir - 1));
        System.out.println("Всего файлов - " + countFiles);
        System.out.println("Общий размер - " + dirSize);
    }
    
    private static class MyFileVisitor extends SimpleFileVisitor<Path>
    {
    
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
        {
            if (Files.isDirectory(dir))
                countDir++;
            
            return FileVisitResult.CONTINUE;
        }
    
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
        {
            if (!Files.isDirectory(file))
            {
                countFiles++;
                dirSize += Files.size(file);
            }
            
            return FileVisitResult.CONTINUE;
        }
    }
}
