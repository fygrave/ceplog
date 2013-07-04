package nu.o0o.ceplog.processor;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;

import com.espertech.esper.client.EventBean;
import static org.elasticsearch.node.NodeBuilder.*;

public class AlertNotifier implements Runnable{
	static private Client sclient = null;

	static private AlertNotifier _instance = null;
	private AlertNotifier() {
	 try {
		 Node node = nodeBuilder().node();
		 sclient = node.client();

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 System.out.println("Alerter Interface initialized");
	}
	
	public static AlertNotifier getInstance() {
		if (_instance == null) {
			_instance = new AlertNotifier();
			(new Thread(_instance)).start();
		}
		return _instance;
	}
	public void alert(String msg, String cls, EventBean evt) {
		System.out.println("Logging " + msg);
		
		Map <String, Object> json = new HashMap<String, Object>();
		json.put("alert", "true");
		json.put("src", evt.get("src"));
		json.put("dst", evt.get("dst"));
		json.put("dst_port", evt.get("dst_port"));
		json.put("message", msg);
		json.put("classify", cls);
		json.put("sensor", "cep");
		json.put("severity", 5); // configurable?
		json.put("severityID", 5);
		json.put("alert", true);
		json.put("origin", "cep_engine");
		json.put("date", evt.get("date"));
		
		//try {
		//	IndexResponse response = sclient.prepareIndex("alert", "alert-type")
		//	        .setSource(json)
		//	        .execute()
		//	        .actionGet();
		//	System.out.println("event added " + response );
		//} catch (Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//} 
		//System.out.println("event solr completed");
		
	}

	//@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10 * 1000);
				
				//System.out.println("Alerter alive");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		}
		
	}
	private static final Log log = LogFactory.getLog(AlertNotifier.class);

}
