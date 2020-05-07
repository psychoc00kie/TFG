package server;

public class ClientDB {
	
	public ClientDB() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClientDB(int threadId, int uId, String clientUserName, String country, double lat, double lon) {
		super();
		this.threadId = threadId;
		this.uId = uId;
		this.clientUserName = clientUserName;
		this.country = country;
		this.lat = lat;
		this.lon = lon;
	}
	public int threadId;
	public int getThreadId() {
		return threadId;
	}
	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getClientUserName() {
		return clientUserName;
	}
	public void setClientUserName(String clientUserName) {
		this.clientUserName = clientUserName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public int uId = 0; 
	public String clientUserName = "";
	public String country;
	public double lat = 0;		// latitude coordenate 
	public double lon = 0;		// longitude coordenate  
	
}

