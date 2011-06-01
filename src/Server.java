import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.TreeMap;
public class Server {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
int portC=8801;
		Share share=new Share();
     ServerSocket server=new ServerSocket(8880);
     while(true){
    Socket client=server.accept();
    DataOutputStream dataOut=new DataOutputStream(client.getOutputStream());
   String ip=client.getInetAddress().getHostAddress();
    System.out.println("Connection:"+ip);
    
    share.setHosts(ip, portC);
    Servant servant=new Servant(client, share);
    portC++;     
    servant.start();    	 
     }
	}

}
