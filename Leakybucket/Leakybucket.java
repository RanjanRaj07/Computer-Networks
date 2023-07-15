import java.util.*;

public class Leakybucket {
	static int min(int x,int y){
		if(x < y)
			return x;
		else 
			return y;
	}
	public static void main(String[]args) {
		int drop=0,mini,nsec,cap,count=0,i,process;
		int inp[] = new int[25];
		Scanner s = new Scanner(System.in);
		System.out.println("enter bucket size:");
		cap = s.nextInt();
		System.out.println("enter the operation rate:");
		process = s.nextInt();
		System.out.println("enter the number of seconds you want to stimulate:");
		nsec = s.nextInt();
		for(i=0;i<nsec;i++) {
			System.out.println("enter the size of the packet entering at"+(i+1)+"th second");
			inp[i]=s.nextInt();
		}
		System.out.println("\n second|packet recived|packet sent|packet left|packet dropped|\n");
		for(i=0;i<nsec;i++) {
			count+=inp[i];
			if(count>cap) {
				drop = count - cap;
				count=cap;
			}
			System.out.print(i+1);
			System.out.print("\t\t"+inp[i]);
			mini = min(count,process);
			System.out.print("\t\t"+mini);
			count = count-mini;
			System.out.print("\t"+count);
			System.out.print("\t\t"+drop);
			drop = 0;
			System.out.println();
		}
		for(;count!=0;i++) {
			if(count>cap) {
				drop = count - cap;
				count = cap;
			}
			System.out.print(i+1);
			System.out.print("\t\t0");
			mini = min(count,process);
			System.out.print("\t\t"+mini);
			count = count-mini;
			System.out.print("\t"+count);
			System.out.print("\t\t"+drop);
			System.out.println();
		
		}
		s.close();
		
	}

}
