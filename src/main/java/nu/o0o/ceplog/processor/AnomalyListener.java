package nu.o0o.ceplog.processor;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class AnomalyListener implements UpdateListener {

	//@Override
	public void update(EventBean[] newEvt, EventBean[] oldEvt) {
		
		System.out.println("Listener");
		if (newEvt != null && oldEvt != null) {
			
		    System.out.println("Got " + newEvt.length + " new events and " + oldEvt.length + "old events");
		    for (int i=0; i< newEvt.length; i++) {
		    	EventBean evt = newEvt[i];
		    	System.out.println("Anomaly detected:"  +" for src" + evt.get("src") + " " + evt.get("dst") + " rate " + evt.get("cnCnt") + "/" + evt.get("avgCnt"));
		    	rep.alert("CEPAlert: Anomaly detected " + evt.get("cnCnt") + "/" + evt.get("avgCnt")
					+	", " + evt.get("src").toString() + " -> " + 
					evt.get("dst").toString()+ ":" + evt.get("dst_port") , "Packet Drop Anomaly Alert",evt);
		   }
		} else {
			System.out.println("new: " + newEvt + " old: " + oldEvt);
		}
		
	}
	private static final AlertNotifier rep = AlertNotifier.getInstance();

}
