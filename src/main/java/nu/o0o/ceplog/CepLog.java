package nu.o0o.ceplog;
import nu.o0o.ceplog.processor.HttpAdapter;

public class CepLog
{


    public static void main(String[] args) throws Exception {
        HttpAdapter proc = new HttpAdapter();
        proc.run();
        while (true) {
        	Thread.sleep(10000);
        	System.out.println("Stats: Events " + proc.getProcessed());
        }
    }
}
