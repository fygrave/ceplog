package nu.o0o.ceplog.processor;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class PacksPerSecondStatement {
	private EPStatement statement;
	
	public PacksPerSecondStatement(EPAdministrator admin)
	{
		String stmt = "insert into PacksPerSecond select date,src,dst,dst_port,action,count(*) as cnt from SyslogEvent.win:time_batch(30 sec) where action='Deny' group by action,src,dst";
		statement = admin.createEPL(stmt);
		
	}
	public void addListener(UpdateListener listener) {
		statement.addListener(listener);
	}

}
