#include <iostream>

using namespace std;

struct edge{
	int u,v;
	int w;
};

int** taodsk(edge *e, int n, int m){

	int **dsk = new int*[n+1];
	for(int i=0;i<=n;i++)
		dsk[i] = new int[n+1]{};
	for(int i=0;i<=n;i++)
		dsk[0][i] = 1;
	for(int i=0;i<m;i++){
		dsk[e[i].u][e[i].v] = 1;
		dsk[e[i].v][e[i].u] = 1;
	}

	return dsk;
}


void KPath(int d, int **dsk, bool *vertex, int *path, int &count, int n, int k){

	for(int i=1;i<=n;i++)
		if(dsk[path[d-1]][i]==1 && !vertex[i]){
			vertex[i]=true;
			path[d]=i;
			if(d==k+1){
				count++;
			}

			else
				KPath(d+1,dsk,vertex,path,count,n,k);
			vertex[i]=false;
		}

}

int main(){
	int n,m,k,count = 0;
	cin>>n>>k;
	cin>>m;
	int **dsk;
	bool vertex[n+1]{};
	int path[k+2]{};
	edge *e = new edge[m];
	for(int i=0;i<m;i++){
		cin>>e[i].u>>e[i].v;
	}
	dsk = taodsk(e,n,m);

	KPath(1,dsk,vertex,path,count,n,k);
	cout<<count/2;

	for(int i=0;i<=n;i++)
		delete [] dsk[i];
	delete []dsk;
	delete []e;
	return 0;
}
