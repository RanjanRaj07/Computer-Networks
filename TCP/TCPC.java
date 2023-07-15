import java.net.*;
import java.io.*;

public class TCPC {
	public static void main (String[]args) throws Exception{
		Socket sock = new Socket("127.0.0.1",4000);
		System.out.println("client is online\n");
		System.out.println("enter the file name to request\n");
		BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
		String fname = keyRead.readLine();
		OutputStream ostream = sock.getOutputStream();
		PrintWriter pwrite = new PrintWriter(ostream,true);
		pwrite.println(fname);
		InputStream istream = sock.getInputStream();
		BufferedReader socketRead = new BufferedReader(new InputStreamReader(istream));
		String str ;
		System.out.println("file is recived!!!!!");
		System.out.println("content of the file is :");
		while((str=socketRead.readLine())!=null) {
			System.out.println(str);
		}
		sock.close();
		pwrite.close();
		socketRead.close();
		keyRead.close();
	}
	
}
