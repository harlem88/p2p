import java.net.Socket;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class Share {
public Share(){
	
trasf=false;
value=0;	
tabFile=new String[100][2];
index=0;
mappaHost=new TreeMap<String, Integer>();
}
public void setHosts(String ind, int port){
	mappaHost.put(ind, port);
	
	
}
public int getHostPort(String ind){
if(mappaHost.containsKey(ind)){
	return	mappaHost.get(ind);
}
return -1;	
}

public synchronized void setFile( String file, Socket s){

	tabFile[index][0]=file;
	tabFile[index][1]=s.getInetAddress().getHostAddress();
	index++;
	
}
public synchronized void up(){
	value++;
    notify();
}
public synchronized void down() throws InterruptedException{
while(value==0)wait();
value--;
}
public String toString(){
/*	Set<Socket> sock=mappaDir.keySet();
	String stampaFile="*::::::Tabella File:::::::::*";
	for(Socket x:sock){
		String s=mappaDir.get(x);
		stampaFile=stampaFile+s+"*";
	}
	stampaFile=stampaFile+"*";
	*/
	String stampaFile="*::::::Tabella File:::::::::*";
	for(int i=0; i<index; i++){
		stampaFile=stampaFile+tabFile[i][0]+"*";
	   }
	stampaFile=stampaFile+"*";
	
	return stampaFile;
}
public void setTrasf(boolean b){
	trasf=b;
	
	
} 
public boolean getTrasf(){
return 	trasf;
	
	
} 

public String getFile(String file){
	/*Set<Socket> sock=mappaDir.keySet();
	String stampaFile="*::::::Tabella File:::::::::*";
	for(Socket x:sock){
		String s=mappaDir.get(x);
		if(s.equalsIgnoreCase(file)){
		stampaFile=stampaFile+s+":"+x.getInetAddress().getHostAddress()+"*";
		}}
	stampaFile=stampaFile+"*";
	*/
	String stampaFile="*::::::Tabella File:::::::::*";
	for(int i=0;i<index;i++){
	String s=tabFile[i][0];
		if(s.equalsIgnoreCase(file)){
	       int port=mappaHost.get(tabFile[i][1]);	
			stampaFile=stampaFile+s+":"+(tabFile[i][1])+" "+port+"*";
		}
		
	}
	stampaFile=stampaFile+"*";
	
	
	
	
	
	
	
	return stampaFile;
}
public void setIpTrasf(String ip){
ip4=ip;	
	
	
}

public String getIp(){
	return ip4;
	
	
}
private Map<String, Integer> mappaHost;
private String[][]tabFile;

private int value;
private int index;
private String ip4;
private boolean trasf;}
