package valueObject;

public class VLogin extends VValueObject{
	
	private static final long serialVersionUID = 1L;
	
	public VLogin() {}
	
	public String getUserId() {
		return this.get(0);
	}
	public String setUserId(String userId) {
		return this.set(0, userId);
	}
	public String getPassword() {
		return this.get(1);
	}
	public String setPassword(String password) {
		return this.set(1, password);
	}	
}
