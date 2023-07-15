import java.net.*;
import java.io.*;

public class TCPS {
	public static void main(String [] args)throws Exception {
		ServerSocket servsock = new ServerSocket(4000);
		System.out.println("server is ready for connection");
		Socket sock = servsock.accept();
		System.out.println("connection is successful..");
		InputStream istream = sock.getInputStream();
		BufferedReader fileRead = new BufferedReader(new InputStreamReader(istream));
		String fname = fileRead.readLine();
		BufferedReader contentRead = new BufferedReader(new FileReader(fname));
		OutputStream ostream = sock.getOutputStream();
		PrintWriter pwrite = new PrintWriter(ostream,true);
		String str;
		while((str=contentRead.readLine())!=null) {
			pwrite.println(str);
		}
		sock.close();
		servsock.close();
		pwrite.close();
		fileRead.close();
		contentRead.close();
		System.out.print("file sent successfully..........");
	}

}
