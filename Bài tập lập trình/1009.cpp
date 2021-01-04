#include <iostream>

using namespace std;

void HoanViK(int x, int n, int k, int *a, int *kt, int *temp){
    for(int i=0;i<n;i++){
        if(kt[i] == 0){
            a[x]=i+1;
            kt[i]=1;
            if(x == n-1){
                *temp=*temp+1;
                if(*temp == k){
                    for(int j=0;j<n;j++)
                    cout<<a[j]<<" ";
                    cout<<"\n";
                    exit(0);
                }
            }
            else{
                HoanViK(x+1,n,k,a,kt,temp);
            }
            kt[i] = 0;
        }
    }
}

int main()
{
    int n,k,count = 0;
    cin>>n>>k;
    int *temp = &count;
    temp = &count;
    int *a = new int[n];
    int *kt = new int[n]{};

    HoanViK(0,n,k,a,kt,temp);
    if(count != k)
        cout<<"-1"<<"\n";

    delete []a;
    delete []kt;
    return 0;
}
