package nu.o0o.ceplog.processor;

import nu.o0o.ceplog.event.SyslogEvent;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esperio.AdapterInputSource;
import com.espertech.esperio.csv.CSVInputAdapter;
//import org.eclipse.jetty.server.Server;


public class Main {
	final static Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		
		Configuration config = new Configuration();
        config.addEventTypeAutoName(SyslogEvent.class.getPackage().getName());
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);
        String expression = "select message, avg(priority) from SyslogEvent.win:time(30 sec)";
        EPStatement statement = epService.getEPAdministrator().createEPL(expression);
        LogListener listener = new LogListener();
        statement.addListener(listener);
        EPRuntime epRuntime = epService.getEPRuntime();
        AdapterInputSource adapterInputSource = new AdapterInputSource("foo.csv");
        (new CSVInputAdapter(epService, adapterInputSource, "OrderEvent")).start();
        SyslogEvent ev1 = new SyslogEvent(1, 1, "1.2.3.4", "Failure", 2012121);	

        SyslogEvent ev2 = new SyslogEvent(1, 1, "1.2.3.4", "Failure", 212);
        epRuntime.sendEvent(ev1);
        epRuntime.sendEvent(ev2);
        for (int i = 0; i < 10; i++) {  
        	logger.info("sent\n");
        	epRuntime.sendEvent(new SyslogEvent(1,1,"1.4.4.4", "Datat", 1212));
        }
        	
        //}
	}

}
