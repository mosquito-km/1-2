package mainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import valueObject.VUser;

public class Main {
	private PLoginDialog pLoginDialog;
	private PMainFrame pMainFrame;

	public Main() {
		this.pLoginDialog = new PLoginDialog(new ActionHandler());
	}
	private void intialize() {
		this.pLoginDialog.initialize();
	}
	
	private void validateUser(Object source) {
		VUser vUser = this.pLoginDialog.validateUser(source);
		if (vUser != null) {				
			this.pMainFrame = new PMainFrame();
			this.pMainFrame.initialize(vUser);
		}
		this.pLoginDialog.dispose();
	}
	
	// LoginDialog "OK" and "Cancel" Button Event Handler
	public class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			validateUser(event.getSource());
		}
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.intialize();		
	}
}
