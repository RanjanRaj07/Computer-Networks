
public class debug {
	public static void main(String [] args) {
		int A[][] = {{0 ,999, -2, 999},{ 4 ,0 ,-3 ,999},{999 ,999 ,0 ,2 },{999 ,-1 ,999 ,0}};
		int num_ver=4;
		int max_value = 999,source=1;
		int D[] = new int[num_ver+1];
		for(int n=1;n<=num_ver;n++)
			D[n] = A[source][n];
		D[source]=0;
		for(int node=1;node<=num_ver-1;node++) {
			for(int dn=1;dn!=source&&dn<=num_ver;dn++) {
				for(int sn=1;sn<=num_ver;sn++) {
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
					if(D[dn]<D[sn]+A[sn][dn])
						System.out.println("graph conatain negative edge cycle");
				}
			}
		}
	}

}
