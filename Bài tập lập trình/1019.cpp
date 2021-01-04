#include<iostream>

using namespace std;

int denomination[] = {1,5,10,50,100,500};
int size = 6;
const int tong = 1000;

int changing(int N){
	int V = tong - N;
	int count=0;
	for(int i=size-1;i>=0;i--){
		count += V/denomination[i];
		V = V%denomination[i];
	}
	return count;
}

int main(){
	int T;
	cin>>T;
	int *test = new int[T];
	for(int i=0;i<T;i++)
		cin>>test[i];
	for(int i=0;i<T;i++)
		cout<<changing(test[i])<<"\n";
	delete []test;
	return 0;
}
