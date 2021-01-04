#include <iostream>

using namespace std;

void ToHopK(int x, int n, int m, int k, int *a, int *kt, int *temp){
    for(int i=1;i<=n;i++){
        if(kt[i] == 0 && i>a[x-1]){
            a[x]=i;
            kt[i]=1;
            if(x == m){
                *temp=*temp+1;
                if(*temp == k){
                    for(int j=1;j<=m;j++)
                    cout<<a[j]<<" ";
                    cout<<"\n";
                    exit(0);
                }
            }
            else{
                ToHopK(x+1,n,m,k,a,kt,temp);
            }
            kt[i]=0;
        }
    }
}

int main()
{
    int n,m,k,count = 0;
    cin>>n>>m>>k;
    int *temp = &count;
    temp = &count;
    int *a = new int[n+1];
    a[0]=0;
    int *kt = new int[n+1]{};

    ToHopK(1,n,m,k,a,kt,temp);
    if(count != k)
        cout<<"-1"<<"\n";

    delete []a;
    delete []kt;
    return 0;
}
