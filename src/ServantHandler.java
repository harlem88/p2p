import java.net.*;
import java.io.*;

public class ServantHandler extends Thread{
public ServantHandler(Socket s, Share share) throws IOException{
	servant=s;
    this.share=share; 
	dataOut=new DataOutputStream(servant.getOutputStream());
	 dataOut.flush();   
	 dataIn=new BufferedReader(new InputStreamReader(servant.getInputStream()));
	    }

public void run(){
while(true){	
	try {
		String ric = dataIn.readLine();
		
	if (ric.equalsIgnoreCase("get")) {
		dataOut.writeBytes(share.toString()+"\n");
		dataOut.writeBytes("Inserisci il file da cercare:\n");
		
		ric = dataIn.readLine();
		String g=share.getFile(ric);
		
		dataOut.writeBytes(g+"\n");
		dataOut.writeBytes("inserisci ip client che ha il file\n");
		String ip4=dataIn.readLine();
		dataOut.writeBytes("inserisci la porta:\n");
		share.setIpTrasf(ip4);
		
		share.setTrasf(true);
	    
	
	}}
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
}
	}
		




private Socket servant;
private BufferedReader dataIn;
private DataOutputStream dataOut;
private Share share;}
