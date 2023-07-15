import java.io.*;
import java.net.*;
public class UDPC {
	public static void main(String[]args)throws UnknownHostException,IOException,SocketException {
		byte[] senddata = new byte[16];
		byte[] receivedata = new byte[16];
		DatagramSocket mysocket = new DatagramSocket();
		System.out.println("client is running......");
		System.out.println("enter teh messege to send to server......");
		BufferedReader informuser = new BufferedReader(new InputStreamReader(System.in));
		InetAddress myip = InetAddress.getByName("localhost");
		String data = informuser.readLine();
		senddata = data.getBytes();
		DatagramPacket sendpacket = new DatagramPacket(senddata,senddata.length,myip,9000);
		mysocket.send(sendpacket);
	    DatagramPacket receivepacket = new DatagramPacket(receivedata,receivedata.length);
	    mysocket.receive(receivepacket);
	    String datatodisplay = new String(receivepacket.getData());
		System.out.println("messege from server---"+datatodisplay);
		mysocket.close();
	    
	}

}
