package com.javarush.task.task27.task2707;

/*
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isNormalLockOrder(final Solution solution, final Object o1, final Object o2) throws Exception {
        Thread.State state1;
        Thread.State state2;
    
        Thread threadO1 = new Thread()
        {
            @Override
            public void run()
            {
                solution.someMethodWithSynchronizedBlocks(o1, o2);
            }
        };
    
        Thread threadO2 = new Thread()
        {
            @Override
            public void run()
            {
                synchronized (o2)
                {
                    while (!interrupted());
                }
            }
        };
    
        synchronized (o1)
        {
            threadO1.start();
            Thread.sleep(80);
            state1 = threadO1.getState();
            
            threadO2.start();
            Thread.sleep(80);
            state2 = threadO2.getState();
            
            threadO1.interrupt();
            threadO2.interrupt();
            
            if (state1 == Thread.State.BLOCKED && state2 != Thread.State.BLOCKED)
                return true;
            else
                return false;
        }
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isNormalLockOrder(solution, o1, o2));
    }
}
