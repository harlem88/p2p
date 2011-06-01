import java.net.*;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.io.*;

public class ClientHandler extends Thread{
	public ClientHandler(Socket s, Map<String, File> f) throws IOException{
     this.client=s;
     in=new BufferedReader(new InputStreamReader(client.getInputStream()));
     out=new DataOutputStream(client.getOutputStream());
	value=0;	
	fileMap=f;}
	
	public void run(){
while(true){try {
			String ind = in.readLine();
			if (ind.equalsIgnoreCase("server")) {
				String portSer = in.readLine();
				int port = Integer.parseInt(portSer);
				ServerSocket server = new ServerSocket(port);
				Socket client2 = server.accept();
				//System.out.println(client2.getInetAddress().getHostAddress());

				BufferedReader dataIn2 = new BufferedReader(
						new InputStreamReader(client2.getInputStream()));
				DataOutputStream dataOut2 = new DataOutputStream(client2
						.getOutputStream());
				
				String file=dataIn2.readLine();
                if(fileMap.containsKey(file)){
                	File f=fileMap.get(file);
                	
                	
                	FileInputStream newF=new FileInputStream(f);
                	
                	long numBytes=f.length();
                	byte[] byteInvio=new byte[1024];//=new Byte((byte) numBytes) ;
                	
                	while(newF.read(byteInvio)>0){
                		dataOut2.flush();
                    	dataOut2.write(byteInvio);
                	
                	}
                 //   dataOut2.writeBytes("finito :"+client2.getInetAddress().getHostAddress());
                	dataOut2.flush();
                	dataOut2.close();
                	
//                	
                    
                    System.out.println("invio eff");
                	
                
                    
                 server.close();   
                }
  
				
               
				
			}

			else if (ind.startsWith("*")) {
				stampaTab(ind);

			}

			else
				System.out.println(ind);

			// out.writeBytes("file inviato\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
		
		
		
		
		
		
		
		
	}
	
public synchronized void attesa() throws InterruptedException{
	while(value==0) wait();
	value--;
	
	
}	
	public  void stampaTab(String s){
		
		StringTokenizer ss=new StringTokenizer(s);
		while(ss.hasMoreTokens()){
			System.out.println(ss.nextToken("*"));
			
			
		}
	}
	

private Socket client;
private BufferedReader in;
private int value;
private DataOutputStream out;
private Map<String ,File>fileMap;}
