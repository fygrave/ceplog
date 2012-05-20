package nu.o0o.ceplog.processor;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class LogListener implements UpdateListener {
	 final static Logger logger = LoggerFactory.getLogger(UpdateListener.class);

	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        EventBean event = newEvents[0];
        Object average = event.get("avg(priority)");
        Object itemName = ((Map) event.getUnderlying()).get("message");
        logger.info("upon arrival of {} the average stood at {}", itemName, average);
    }
	

}
