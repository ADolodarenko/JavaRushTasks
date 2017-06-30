package com.javarush.task.task28.task2802;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/
public class Solution {

    public static void main(String[] args) {
        class EmulateThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulateThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulateThreadFactoryTask());

        thread.start();
        thread2.start();
    }
    
    public static class AmigoThreadFactory implements ThreadFactory
    {
        public static AtomicInteger factoryNumber = new AtomicInteger(0);
        
        public AtomicInteger threadNumber = new AtomicInteger(0);
        private int factoryId;
        
        public AmigoThreadFactory()
        {
            factoryId = factoryNumber.incrementAndGet();
        }
    
        @Override
        public Thread newThread(Runnable r)
        {
            Thread result = new Thread(r);
            result.setDaemon(false);
            result.setPriority(Thread.NORM_PRIORITY);
            
            String name = String.format("%s-pool-%d-thread-%d",
                    result.getThreadGroup().getName(),
                    factoryId, threadNumber.incrementAndGet());
            result.setName(name);
            
            return result;
        }
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }
}
