package nu.o0o.ceplog.processor;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class AnomalyListener implements UpdateListener {

	@Override
	public void update(EventBean[] newEvt, EventBean[] oldEvt) {
		// TODO Auto-generated method stub
		if (newEvt == null) {
			return; // no new events
		}
		EventBean evt = newEvt[0];
		System.out.println("Anomaly detected:" + evt.get("action") +" for src" + evt.get("src") + " " + evt.get("dst") + " rate" + evt.get("cnCnt") + "/" + evt.get("avgCnt"));
		solr.alert("CEPAlert: Anomaly detected " + evt.get("cnCnt") + "/" + evt.get("avgCnt")
				+ " for action=" + evt.get("action") +
				", " + evt.get("src").toString() + " -> " + 
				 evt.get("dst").toString()+ ":" + evt.get("dst_port") , "Packet Drop Anomaly Alert",evt);
	}
	private static final SolrNotifier solr = SolrNotifier.getSolr();

}
