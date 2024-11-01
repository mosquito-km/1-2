package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;

import valueObject.VLogin;
import valueObject.VValueObject;

public class CControl {

	final static private String ip = "localhost";
	final static private int portNum = 8000;;
	
	public CControl() {
		
	}
	public boolean invokeRemoteMethod(Class<?> cClass, Method method, VValueObject vValueObject) {
		
		boolean bResult = false;
		
		try {
			Socket socket = new Socket(ip,portNum);
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println("client:: Client Start");
			//marshaling
			out.println(cClass.getClass().getName());	// class name
			System.out.println("client:: class name-" + cClass.getClass().getName());
			out.println(method.getName());	// method name
			System.out.println("client:: method name-" + method.getName());
			out.println(vValueObject.getClass().getSimpleName());	// parameter class name
			System.out.println("client:: parameter class name-" + vValueObject.getClass().getSimpleName());
			out.println(vValueObject.size());	// parameter class name
			System.out.println("client:: num parameters-" + vValueObject.size());
			for (int i=0; i<vValueObject.size(); i++) {
			out.println(vValueObject.get(i)); 	// parameter value 1
			System.out.println("client:: parameter value-" + vValueObject.get(i));
			}
			
			// reply
			String reply = in.readLine();
			if (reply.equals("true")) {
				bResult = true;
			}
			System.out.println(reply);
			System.out.println("Client End");
			
			out.close();
			in.close();
			socket.close();
			
		} catch (UnknownHostException e) {
			bResult = false;	
			e.printStackTrace();
		} catch (IOException e) {
			bResult = false;
			e.printStackTrace();
		}
		
		
		return bResult;
	}
}
