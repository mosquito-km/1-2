package control;

import valueObject.VLogin;

public class CLogin extends CControl implements ILogin {

	public boolean login(VLogin vLogin) {
		Class<?> cClass = this.getClass();
		boolean bResult = this.invokeRemoteMethod(
				"CLogin", "login", vLogin);
		return bResult;
	}
}
