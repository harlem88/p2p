import java.io.*;
import java.net.*;
public class Servant extends Thread{
public Servant(Socket s, Share share) throws IOException{
client=s;
this.share=share;
dataOut=new DataOutputStream(client.getOutputStream());
dataIn=new BufferedReader(new InputStreamReader(client.getInputStream()));
}


public void run() {
		try {
			dataOut.writeBytes("Benvenuto in Napster\n");
			dataOut.writeBytes("Carica file\n");
			String file = dataIn.readLine();
			while (!file.equalsIgnoreCase("load file close")) {
				share.setFile(file, client);
				file = dataIn.readLine();
			} 
			ServantHandler s=new ServantHandler(client, share);
	         s.start();
	     	
	    	boolean transf=share.getTrasf();
	     	while (true) {
			
				if (transf) {
					String ip = share.getIp();
					
					if (client.getInetAddress().getHostAddress()
							.equalsIgnoreCase(ip)){

						dataOut.writeBytes("server\n");
					int port = share.getHostPort(client.getInetAddress()
							.getHostAddress());
					
					dataOut.writeBytes(port + "\n");
               share.setTrasf(false);
					}				}
				transf=share.getTrasf();
				}
	
	
	
	
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	
}

private Share share;
private Socket client;
private DataOutputStream dataOut;
private BufferedReader dataIn;
}