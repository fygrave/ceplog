package nu.o0o.ceplog.processor;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
//import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.espertech.esperio.http.EsperIOHTTPAdapterPlugin;
import com.espertech.esperio.http.config.ConfigurationHTTPAdapter;
import com.espertech.esperio.http.config.Request;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class HttpAdapter {
	
	 private final static String ENGINE_URI = "CEP0000";
	 private EPRuntime cepRT;
	 private EPServiceProvider epService;

	
    public void run() throws Exception {

        String port = "8084";
        boolean isNio = true;
        
        ConfigurationHTTPAdapter adapterConfig = new ConfigurationHTTPAdapter();

        Request requestOne = new Request();
        requestOne.setStream("PacksPerSecond");
        requestOne.setUri("http://localhost:8085/root");
        adapterConfig.getRequests().add(requestOne);

        

        String esperIOHTTPConfig = "<esperio-http-configuration>\n" +
                "\t<service name=\"service1\" port=\"" + port + "\" nio=\"" + isNio + "\"/>\n" +
                "\t<get service=\"service1\" pattern=\"*\"/>\n" +
                "</esperio-http-configuration>";

        System.out.println();
        System.out.println(esperIOHTTPConfig);
        System.out.println();
        


        Configuration config = new Configuration();
        
        config.addPluginLoader("EsperIOHTTPAdapter", EsperIOHTTPAdapterPlugin.class.getName(), new Properties(), esperIOHTTPConfig);

        config.addEventTypeAutoName("nu.o0o.ceplog.event");
        // debug
        config.getEngineDefaults().getLogging().setEnableExecutionDebug(true);
        config.getEngineDefaults().getLogging().setEnableTimerDebug(false);
        
        //EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);
        epService = EPServiceProviderManager.getProvider(ENGINE_URI, config);
        
        
        //PacksPerSecondStatement pksPerSec = new PacksPerSecondStatement(epService.getEPAdministrator());
        //pksPerSec.addListener(new RateUpdateListener());
        
        //AnomalyDetectStatement aDeSt = new AnomalyDetectStatement(epService.getEPAdministrator());
        //aDeSt.addListener(new AnomalyListener());
        //aDeSt.addListener(new MyListener());
        //pksPerSec.addListener(new MyListener());
        readRules(new AnomalyListener());
        cepRT = epService.getEPRuntime();
       
        
        //String expression = "select src,dst from SyslogEvent.win:time(30 sec)";
        //EPStatement statement = epService.getEPAdministrator().createEPL(expression);

        //MyListener listener = new MyListener();
        //statement.addListener(listener);
 //        SupportHTTPClient client = new SupportHTTPClient();
 //       client.request(8083, "sendevent", "stream", "AccessLogEvent", "date", "mydate");
//
  //      epService.destroy();

    }

    private void readRules(UpdateListener lst) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("rules.cep"));
		
        try {
            String line = br.readLine();

            while (line != null) {
            	EPStatement statement = epService.getEPAdministrator().createEPL(line);
            	statement.addListener(lst);
                line = br.readLine();
            }
            
        } finally {
            br.close();
        }
		
	}

	public class MyListener implements UpdateListener {
        public void update(EventBean[] newEvents, EventBean[] oldEvents) {
            EventBean event = newEvents[0];
            System.out.println("got events " + newEvents.length);
            System.out.println("evt " + event);
        }
    }

    public static void main(String[] args) throws Exception {
        HttpAdapter adpt = new HttpAdapter();
        adpt.run();
        while (true) {
        	Thread.sleep(1000);
        	System.out.println("Events " + adpt.getProcessed());
        	// compute stats
        }
    }

	public long getProcessed() {
		// TODO Auto-generated method stub
		return cepRT.getNumEventsEvaluated();
	}
	
	

}
