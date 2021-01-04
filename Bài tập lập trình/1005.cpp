#include<iostream>
#include<math.h>

using namespace std;

void combSort(double *a, int n){
	int gap = n;
	float shrink = 1.3;
	bool shorted = false;

	while(!shorted){
		gap = floor(gap/shrink);

		if(gap<1){
			gap = 1;
			shorted = true;
		}

		for(int i=0;i<n-gap;i++)
			if(a[i]>a[i+gap]){
				double temp = a[i];
				a[i]= a[i+gap];
				a[i+gap] = temp;
				shorted = false;
			}
	}
}

int main(){
	int n;
	cin>>n;
	double *a = new double[n];
	for(int i = 0;i<n;i++)
		cin>>a[i];

	combSort(a, n);

	for(int i=0;i<n;i++)
		printf("%.2lf ",a[i]);
    delete []a;

	return 0;
}
