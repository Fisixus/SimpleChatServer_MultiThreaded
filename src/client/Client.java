package client;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    public static void main(String[] args) throws IOException {
        new Client("127.0.0.1", 4000);
    }

    public Client(String host, int port) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 1; i < 2; i++) {
            WorkerThreadClient workerThread = new WorkerThreadClient(host,port);
            executor.submit(workerThread);
        }
        executor.shutdown();
        while(!(executor.isTerminated())){}
    }

}


