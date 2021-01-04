#include <iostream>

using namespace std;

// tinh giai thua
int gt(int n){
	int gt=1;
	for(int i=2;i<=n;i++)
		gt=gt*i;
	return gt;
}

// liet ke to hop
void tohop(int x, int &count, int n, int h, int *a, bool *kt, int**c ){
	for(int i=0;i<n;i++)
		if(!kt[i] && (i>a[x-1] || x==0)){
			a[x]=i;
			kt[i]=true;
			if(x == (h-1)){
				for(int j=0;j<h;j++)
					c[count][j] = a[j];
				count++;
			}
			else
				tohop(x+1,count,n,h,a,kt,c);
			kt[i] = false;
		}
}

int main(){

	int sldl;
	cin>>sldl;
	int **data = new int*[sldl];
	for(int i=0;i<sldl;i++){
		data[i] = new int[2];
		cin>>data[i][0]>>data[i][1];
	}
	int N,H;
	cout<<"\n";
	for(int i=0;i<sldl;i++){
		N = data[i][0];
		H = data[i][1];
		int slth = gt(N)/(gt(N-H)*gt(H));
		int **C = new int*[slth];
		for (int j=0;j<slth;j++)
			C[j] = new int[H];
		bool *kt = new bool[N]{};
		int *a = new int[H]{};
		int count = 0;

		tohop(0,count,N,H,a,kt,C);

		int *bit = new int[N]{};
		for (int j = slth-1;j>=0;j--){
			for(int k=0;k<H;k++)
				bit[C[j][k]]=1;
			for(int k=0;k<N;k++)
				cout<<bit[k];
			cout<<"\n";
			for(int k=0;k<H;k++)
				bit[C[j][k]]=0;
		}
		cout<<"\n";

		for (int j=0;j<slth;j++)
			delete []C[j];
		delete []C;
		delete []kt;
		delete []a;
		delete []bit;
	}

	for (int j=0;j<sldl;j++)
			delete []data[j];
		delete []data;

	return 0;

}
