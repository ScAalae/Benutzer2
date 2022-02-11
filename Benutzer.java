
public class Benutzer {
	private  int id ;
	private  String vorname ;
	private  String nachname; 
	private  String email;
	private String createdOn;
	
	public Benutzer(int id) {
		this.id=id;
	}
	public Benutzer(int id,String vorname, String nachname, String email, String createdOn) {
		this.id=id;
		this.vorname=vorname;
		this.nachname=nachname;
		this.email=email;
		this.createdOn=createdOn;
	}
	
	public Benutzer(String vorname, String nachname, String email, String createdOn) {
		
		this.vorname=vorname;
		this.nachname=nachname;
		this.email=email;
		this.createdOn=createdOn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
	
}
