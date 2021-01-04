#include <iostream>

using namespace std;

struct edge {
    int u, v, w;
};
#define N 10005
// Disjoint Set
int cha[N], hang[N];

int find(int u) {
    if (cha[u] != u)
		cha[u] = find(cha[u]);
    return cha[u];
}

bool join(int u, int v) {
    u = find(u);
	v = find(v);
    if (u == v)
		return false;
    if (hang[u] == hang[v])
		hang[u]++;
    if (hang[u] < hang[v])
		cha[u] = v;
    else
		cha[v]=u;
    return true;
}

int main() {
    int n, m;
	cin>>n>>m;
    edge e[m];
    for(int i=0;i<m;i++){
    	cin>>e[i].u>>e[i].v>>e[i].w;
	}
	// đưa cạnh về dạng u<v
	for(int i=0;i<m;i++){
    	if(e[i].u > e[i].v){
    		int c = e[i].u;
    		e[i].u = e[i].v;
    		e[i].v = c;
		}
	}
    edge temp;
    //cạnh (u1,v1) đi trước cạnh (u2,v2) nếu u1<u2 hoặc (u1=u2 và v1<v2).
    for(int i = 0; i < m - 1; i++){
        for(int j = i + 1; j < m; j++){
            if(e[i].w > e[j].w){
                temp = e[i];
                e[i] = e[j];
                e[j] = temp;
            }
            if(e[i].w == e[j].w && (e[i].u > e[j].u || (e[i].u == e[j].u  && e[i].v > e[j].v ))){
            		temp = e[i];
                	e[i] = e[j];
                	e[j] = temp;
			}
        }
    }
    for (int i=1; i<=n; i++) {
        cha[i] = i;
        hang[i] = 0;
    }
    //kruskal
    edge khungcay[n-1];
    int mst_weight = e[0].w;
    int count=1;
    cha[e[0].v] = e[0].u;
    hang[e[0].u] = 1;
    khungcay[0]=e[0];
    for(int i=1;i<m;i++){
    	if(join(e[i].u,e[i].v)){
    		mst_weight = mst_weight + e[i].w;
    		khungcay[count]=e[i];
    		count++;
		}
		if(count==n-1)
			break;
	}
	for(int i = 0; i < n - 2; i++){
        for(int j = i + 1; j < n-1; j++){
            if(khungcay[i].u > khungcay[j].u || (khungcay[i].u == khungcay[j].u  && khungcay[i].v > khungcay[j].v)){
            		temp = khungcay[i];
                	khungcay[i] = khungcay[j];
                	khungcay[j] = temp;
			}
        }
	}
	cout << mst_weight<<"\n";
	for(int i=0;i<n-1;i++){
    	cout<<khungcay[i].u<<" "<<khungcay[i].v<<" ";
	}
    return 0;
}
