import java.net.*;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.io.*;
public class Client {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Inserisci ip:");
		BufferedReader inClient=new BufferedReader(new InputStreamReader(System.in));
		String ip=inClient.readLine();
		
		Socket client=new Socket(ip, 8880);
		DataOutputStream dataOut=new DataOutputStream(client.getOutputStream());
		BufferedReader dataIn=new BufferedReader(new InputStreamReader(client.getInputStream()));
        Map<String ,File>fileMap=new TreeMap<String, File>();		
		
		
		System.out.println(dataIn.readLine());
		System.out.println(dataIn.readLine());
		
		System.out.println("Premere 0 x interrompere il caricamento");
		String file=inClient.readLine();

		while(!file.equalsIgnoreCase("0")){
			File f=new File(file);
			if(f.exists()){
			fileMap.put(file, f);
			dataOut.writeBytes(file+"\n");
			
		}
			else System.out.println("file inesistente");
				
			file=inClient.readLine();}
		dataOut.writeBytes("load file close\n");
		String richiesta="";
		ClientHandler cc=new ClientHandler(client, fileMap);
    	cc.start();
		while(true){
		  if(richiesta.equalsIgnoreCase("y")){
			  dataOut.writeBytes("get\n");
				//file
				file=inClient.readLine();
				
				dataOut.writeBytes(file+"\n");
				//gestite nel client Handler String s=dataIn.readLine();		
				//stampaTab(s);
				
			//ip
				String ip4=inClient.readLine();
				dataOut.writeBytes(ip4+"\n");
				//porta
				String portS=inClient.readLine();
			   int portt=Integer.parseInt(portS);
			   

			    
			    
			    Socket client2=new Socket(client.getInetAddress().getHostAddress(),portt);
		        BufferedReader dataIn2=new BufferedReader(new InputStreamReader(client2.getInputStream()));
		        DataOutputStream dataOut2=new DataOutputStream(client2.getOutputStream());
		        dataOut2.writeBytes(file+'\n'); 
		       // String nomeFile=dataIn.readLine();
		       
		        FileOutputStream f=new FileOutputStream(file);
		        
		        BufferedOutputStream receiveFile=new BufferedOutputStream(f);
		        BufferedInputStream inServer=new BufferedInputStream(client2.getInputStream());
		        byte [] num=new byte[1024];
		        while(inServer.read(num)>0){
		        	receiveFile.flush();
		            receiveFile.write(num);
		        	
		        }
		       
		        receiveFile.flush();
		        receiveFile.close();
		        f.close();
		     
		        
		        
			    
			    
		  }
		    
		 else dataOut.writeBytes("nget\n");
		
	
		//if(s.equalsIgnoreCase("server")){
		
						
			
			
		//	}
		//	else System.out.println(s);
			
			
	       	System.out.println("Per cercare un file ? y o n");
			richiesta=inClient.readLine();
			}
	
	}
public static void stampaTab(String s){
	
	StringTokenizer ss=new StringTokenizer(s);
	while(ss.hasMoreTokens()){
		System.out.println(ss.nextToken("*"));
		
		
	}
	
	
}
}
