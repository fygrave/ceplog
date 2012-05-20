package nu.o0o.ceplog.event;

public class SyslogEvent {
	private String message;
	private int date;
	private int priority;
	private int severity;
	private String origin;
	
	public SyslogEvent() {
	
	}
	
	public SyslogEvent(int _priority,int  _severity,String _origin, String _message, int _date ) {
		message = _message;
		priority = _priority;
		severity = _severity;
		origin = _origin;
		date = _date;
	}
	public void setDate(int _date) {
		date = _date;
	}
	public void setMessage(String _message) {
		message = _message;
	}
	public int getDate(){
		return date;
	}
	public String getMessage() {
		return message;
	}
	public int getPriority() {
		return priority;
	}
	public int getSeverity() {
		return severity;
	}
	public String getOrigin() {
		return origin;
	}
	//@Override
	public String toString() {
		return "SyslogEvent: " + getDate() + " " + getPriority() + " " + getSeverity() + " " +
	      getOrigin() + " " + getMessage();
	}


}
