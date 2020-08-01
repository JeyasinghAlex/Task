package Task.ThreadPool;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    private final int poolSize;
    private final LinkedBlockingQueue<FileHandler> queue;

    public ThreadPool(int poolSize, LinkedBlockingQueue<FileHandler> queue) {
        this.poolSize = poolSize;
        this.queue = queue;
    }

    public void execute() {
        ThreadPoolHandler handler = null;
        for (int i = 0; i < poolSize; i++) {
            handler = new ThreadPoolHandler();
            handler.start();
        }
    }

    private class ThreadPoolHandler extends Thread {
        @Override
        public void run() {
            Runnable task = null;
            while (true) {
                synchronized (queue) {
                    if (queue.isEmpty()) {
                        break;
                    }
                    task = queue.poll();
                }
                try {
                    task.run();
                } catch (RuntimeException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}