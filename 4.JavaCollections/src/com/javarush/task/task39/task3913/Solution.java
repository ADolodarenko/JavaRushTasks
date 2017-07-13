package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("/home/n_alex/WORK/Java/Projects/JavaRush/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task39/task3913/logs/"));
        System.out.println(logParser.execute("get ip for event = \"WRITE_MESSAGE\" and date between \"11.02.2013 00:00:00\" and \"11.12.2017 23:59:59\""));
    }
}