import java.util.Scanner;
public class Bellmanford {
	private int D[],num_ver;
	public static final int max_value = 999;
	
	public Bellmanford (int num_ver) {
		this.num_ver = num_ver;
		D = new int [num_ver+1];
	}
	
	public void BellmanfordEvaluation(int source,int A[][]) {
		for(int n=1;n<=num_ver;n++)
			D[n] = A[source][n];
		D[source]=0;
		for(int node=1;node<=num_ver-1;node++) {
			for(int sn=1;sn<=num_ver;sn++) {
				for(int dn=1;dn<=num_ver;dn++) {
					if( A[sn][dn] != max_value) {
						if(D[dn]>D[sn]+A[sn][dn])
							D[dn]=D[sn]+A[sn][dn];
			         }
				}
			}
		}
		for(int sn=1;sn<=num_ver;sn++) {
			for(int dn=1;dn<=num_ver;dn++) {
				if(A[sn][dn]!=max_value) {
					if(D[dn]>D[sn]+A[sn][dn])
						System.out.println("graph conatain negative edge cycle");
				}
			}
		}
		for(int vertex=1;vertex<=num_ver;vertex++)
			System.out.println("distance of source "+source+" to "+vertex+" is "+D[vertex]);
		
	}
	public static void main(String[]args) {
		int num_ver = 0,source;
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the number of vertices");
		num_ver = sc.nextInt();
		int A[][] = new int[num_ver+1][num_ver+1];
		System.out.println("enter the adjacency matrix");
		for(int sn=1;sn<=num_ver;sn++) {
			for(int dn=1;dn<=num_ver;dn++) {
				A[sn][dn]=sc.nextInt();
				if(sn==dn) {
					A[sn][dn]=0;
					continue;
				}
				if(A[sn][dn] == 0)  A[sn][dn]=max_value;
			}
		}
		System.out.println("enter the source vertex");
		source = sc.nextInt();
		Bellmanford b = new Bellmanford(num_ver);
		b.BellmanfordEvaluation(source, A);
		sc.close();
	}

}
