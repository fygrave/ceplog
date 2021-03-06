package nu.o0o.ceplog.processor;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class AnomalyDetectStatement {
	private EPStatement statement;
	
	public AnomalyDetectStatement(EPAdministrator admin)
	{
		String stmt = "select date,src,dst,dst_port, avg(cnt) as avgCnt, cnt as cnCnt from PacksPerSecond.win:time(30 sec) group by src,dst having cnt > avg(cnt) * 2 ";
		statement = admin.createEPL(stmt);
		
	}
	public void addListener(UpdateListener listener) {
		statement.addListener(listener);
	}


}
