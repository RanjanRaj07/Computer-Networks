
import java.io.*;

class CRC {
	public static void main(String [] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int data[],div[],divisor[],rem[],crc[];
		int databits,divisorbits,totlength;
		System.out.println("enter the number of data bits:");
		databits = Integer.parseInt(br.readLine());
		data=new int[databits];
		System.out.println("enter the data bits");
		for(int i=0;i<databits;i++)
			data[i]=Integer.parseInt(br.readLine());
		System.out.println("enter the numer of bits in the divisor");
		divisorbits=Integer.parseInt(br.readLine());
		divisor=new int[divisorbits];
		System.out.println("enter the divisor bits");
		for(int i=0;i<divisorbits;i++)
			divisor[i]=Integer.parseInt(br.readLine());
		totlength = databits+divisorbits-1;
		div = new int[totlength];
		rem = new int[totlength];
		crc = new int[totlength];
		
		/*--------------CRC GENERATION----------------------*/
		
		for(int i=0;i<data.length;i++)
			div[i]=data[i];
		System.out.println("Dividend after appending 0's are:");
		for(int i=0;i<div.length;i++)
			System.out.print(div[i]);
		System.out.println();
		for(int j=0;j<div.length;j++)
			rem[j]=div[j];
		rem = divide(div,divisor,rem);
		for(int i=0;i<div.length;i++)
			crc[i]=(div[i]^rem[i]);
		
		System.out.println("CRC code:");
		for(int i=0;i<crc.length;i++)
			System.out.print(crc[i]);
		
		/*----------------ERROR DETECTOION------------------*/
		
		System.out.println();
		System.out.println("enter the crc code of "+totlength+" bits");
		for(int i=0;i<crc.length;i++)
			crc[i]=Integer.parseInt(br.readLine());
		for(int j=0;j<crc.length;j++)
			rem[j]=crc[j];
		rem = divide(crc,divisor,rem);
		for(int i=0;i<rem.length;i++) {
			if(rem[i]!=0) {
				System.out.println("error");
				break;
			}
		if(i==rem.length-1)
			System.out.println("No error");
		}
		System.out.println("thank you!!");
	}
		static int []divide (int div[],int divisor[],int rem[]) {
			int cur=0;
			while(true) {
				for(int i=0;i<divisor.length;i++)
					rem[cur+i]=(rem[cur+i]^divisor[i]);
				while(rem[cur]==0 && cur!=rem.length-1)
					cur++;
				if((rem.length-cur)<divisor.length)break;
			}
			return rem;
		}
	}