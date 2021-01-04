#include <iostream>
#include <bits/stdc++.h>

using namespace std;
struct edge{
	int u,v;
	int w;
};
#define MAX 100
int n;
int cmin = INT_MAX;
int best = INT_MAX;
int curr = 0;
int mark[MAX];
int x[MAX];

int** tao_mtk(edge *e, int n, int m){
	int **mtk = new int*[n+1];
	for(int i=0;i<=n;i++)
		mtk[i] = new int[n+1]{};
	for(int i=0;i<m;i++){
		mtk[e[i].u][e[i].v] =e[i].w;
	}
	return mtk;
}

void TRY(int k, int **mtk){
    for(int i=2; i<=n; i++){
        if(mark[i] == 0 && mtk[x[k-1]][i] != 0){
           x[k] = i;
           mark[i] = 1;
           curr += mtk[x[k-1]][x[k]];
           if(curr+cmin*(n-k+1)>best){
                curr -= mtk[x[k-1]][x[k]];
                mark[i]=0;
                continue;
           }
           if(k==n){
                int sum = curr + mtk[x[k]][x[1]];
                best = min(best,sum);

           }else{
                TRY(k+1,mtk);
           }
            curr -= mtk[x[k-1]][x[k]];
            mark[i]=0;
        }
    }
}

int main(){
    int m;
	cin>>n>>m;
	int **mtk;
	edge *e = new edge[m];
	for(int i=0;i<m;i++){
		cin>>e[i].u>>e[i].v>>e[i].w;
		if(e[i].w !=0 && e[i].w<cmin)
			cmin = e[i].w;
	}
	mtk = tao_mtk(e,n,m);
    x[1] = 1;
    TRY(2,mtk);
    cout<<best;
    for(int i=0;i<=n;i++)
		delete [] mtk[i];
	delete []mtk;
	delete []e;
    return 0;
}

