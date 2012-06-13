package nu.o0o.ceplog.processor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

import com.espertech.esper.client.EventBean;

public class SolrNotifier implements Runnable{
	static private SolrServer solr_instance = null;
	static private SolrNotifier _instance = null;
	private SolrNotifier() {
	 try {
		solr_instance = new CommonsHttpSolrServer("http://localhost:8983/solr");
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 System.out.println("Solr Interface initialized");
	}
	
	public static SolrNotifier getSolr() {
		if (_instance == null) {
			_instance = new SolrNotifier();
			(new Thread(_instance)).start();
		}
		return _instance;
	}
	public void alert(String msg, String cls, EventBean evt) {
		System.out.println("Logging " + msg);
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", UUID.randomUUID());
		doc.addField("src", evt.get("src"));
		doc.addField("dst", evt.get("dst"));
		doc.addField("dst_port", evt.get("dst_port"));
		doc.addField("message", msg);
		doc.addField("classify", cls);
		doc.addField("sensor", "cep");
		doc.addField("severity", 5); // configurable?
		doc.addField("severityID", 5);
		doc.addField("alert", true);
		doc.addField("origin", "cep_engine");
		doc.addField("date", evt.get("date"));
		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		docs.add(doc);
		try {
			solr_instance.add(docs);
			System.out.println("event added to solr");
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("event solr completed");
		
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10 * 1000);
				solr_instance.commit();
				log.info("Solr commit");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SolrServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private static final Log log = LogFactory.getLog(SolrNotifier.class);

}
