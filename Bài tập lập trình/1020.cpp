#include <iostream>
#include<algorithm>

using namespace std;

bool ss(int a, int b){
	return a>b;
}

int main(){
	int day = 0;
	int n;
	cin>>n;
	int *a  = new int[n];
	for(int i=0;i<n;i++)
		cin>>a[i];
	sort(a,a+n,ss);
	for(int i=0;i<n;i++){
		if(day<i+a[i])
			day = i+a[i];
	}
	cout<<day+2;
	return 0;
}
