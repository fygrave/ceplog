package nu.o0o.ceplog.processor;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class RateUpdateListener implements UpdateListener {

	@Override
	public void update(EventBean[] newEvt, EventBean[] oldEvt) {
		// TODO Auto-generated method stub
		if (newEvt.length > 0) {
			logRate(newEvt);
		}

	}
	private void logRate(EventBean[] evt) {
		
		System.out.println("total " + evt.length + " events");
		for (int i=0; i< evt.length; i++) {
		System.out.println("Rate for " + evt[i].get("src").toString() + " -> " + 
		 evt[i].get("dst").toString()+ ":" + evt[i].get("dst_port") 
		 + " cnt " + evt[i].get("cnt") + " action " + evt[i].get("action"));
		solr.alert("CEPAlert: rate exeeds " + evt[i].get("cnt") + " for action=" + evt[i].get("action") +
				", " + evt[i].get("src").toString() + " -> " + 
				 evt[i].get("dst").toString()+ ":" + evt[i].get("dst_port") , "Rate exceeds Alert", evt[i]);
		}
		
	}
	private static final SolrNotifier solr = SolrNotifier.getSolr();


}
