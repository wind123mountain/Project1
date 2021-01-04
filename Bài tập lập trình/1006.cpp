#include <iostream>

using namespace std;

int l0 = 0;

void lietke(int x, int n, int k, int i, int *a, int *count){

    for(int val=0; val<2;val++){
        if(val == 0 && l0 == i-1){
            continue;
        }
        else{
            a[x]=val;
            if(val == 0)
                l0++;
            else
                l0 = 0;
            if(x == (n-1)){
                *count = *count+1;
                if(*count == k){
                    for(int j=0;j<n;j++)
                        cout<<a[j]<<" ";
                    cout<<"\n";
                    exit(0);
                }
            }
            else{
                lietke(x+1,n,k,i,a,count);
            }
        }
    }
}

int main()
{
    int n,k,i,c=0;
    int *count;
    count=&c;
    cin>>n;
    cin>>k;
    cin>>i;
    int *a = new int[n];
    lietke(0,n,k,i,a,count);
    if(*count != k)
        cout<<"-1"<<"\n";
    delete []a;
    return 0;
}
