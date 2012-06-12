package nu.o0o.ceplog.event;

public class SyslogEvent {
	private String message;
	private String originalMessage;
	private int prival;
	private int facilityID;
	private int severityID;
	private String facility;
	private int severity;
	private String action;
	private String type;
	private String time;
	private String host;
	private String src;
	private String dst;
	private int src_port;
	private int dst_port;
	private String classification;
	private String sensor;
	private long date;
	private int priority;
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
	public void setDate(long _date) {
		date = _date;
	}
	public void setMessage(String _message) {
		message = _message;
	}
	public long getDate(){
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

	public String getOriginalMessage() {
		return originalMessage;
	}

	public void setOriginalMessage(String originalMessage) {
		this.originalMessage = originalMessage;
	}

	public int getPrival() {
		return prival;
	}

	public void setPrival(int prival) {
		this.prival = prival;
	}

	public int getFacilityID() {
		return facilityID;
	}

	public void setFacilityID(int facilityID) {
		this.facilityID = facilityID;
	}

	public int getSeverityID() {
		return severityID;
	}

	public void setSeverityID(int severityID) {
		this.severityID = severityID;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getSensor() {
		return sensor;
	}

	public void setSensor(String sensor) {
		this.sensor = sensor;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getSrc_port() {
		return src_port;
	}

	public void setSrc_port(int src_port) {
		this.src_port = src_port;
	}

	public int getDst_port() {
		return dst_port;
	}

	public void setDst_port(int dst_port) {
		this.dst_port = dst_port;
	}


}
