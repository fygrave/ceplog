package nu.o0o.ceplog.processor;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
//import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.espertech.esperio.http.EsperIOHTTPAdapterPlugin;
import java.util.Properties;


public class HttpAdapter {
	
    public void run() throws Exception {

        String port = "8084";
        boolean isNio = true;

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
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);
        PacksPerSecondStatement pksPerSec = new PacksPerSecondStatement(epService.getEPAdministrator());
        pksPerSec.addListener(new RateUpdateListener());
        
        AnomalyDetectStatement aDeSt = new AnomalyDetectStatement(epService.getEPAdministrator());
        aDeSt.addListener(new AnomalyListener());
        //String expression = "select src,dst from SyslogEvent.win:time(30 sec)";
        //EPStatement statement = epService.getEPAdministrator().createEPL(expression);

        //MyListener listener = new MyListener();
        //statement.addListener(listener);
 //        SupportHTTPClient client = new SupportHTTPClient();
 //       client.request(8083, "sendevent", "stream", "AccessLogEvent", "date", "mydate");
//
  //      epService.destroy();

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
        	// compute stats
        }
    }
	
	

}
