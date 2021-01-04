#include<iostream>

using namespace std;

int weight[50],v[50];

int max(int a, int b){
	if(a>=b)
		return a;
	else
		return b;
}

int A(int i, int j){
	if(i==0||j==0){
		return 0;
	}
	if(weight[i-1] > j)
		return A(i-1,j);
	else
		return max(A(i-1,j), v[i-1] + A(i-1,j-weight[i-1]));

}
int main()
{
	int n,b;
	cin>>n>>b;
	for(int i=0;i<n;i++)
	  cin>>weight[i]>>v[i];
	int max = A(n,b);
	cout<<max;
	return 0;
}



