#include <iostream>
#include <math.h>

using namespace std;

int main()
{
	int n, f;

	int count, test;
	cin>>test;
	double *V = new double[test];

	double min, max, mid;
	double len[10001];

	int t=0;
	while(t<test)
	{
		cin>>n>>f;
		for(int i = 0; i < n; i++)
		{
			cin>>len[i];
			len[i] *= len[i];
		}
		min = 0.0; max = 1000.0;
		while(min + 1e-7< max)
		{
			mid = (max+min)/2;
			count = 0;
			for(int i = 0; i < n; i++)
			{
				count += len[i]/mid;
			}
			if(count < f+1) // f nguoi ban va minh
				max = mid;
			else
				min = mid;
		}
		V[t] = mid*M_PI;
		t++;
	}
	cout<<"\n";
	for(int i =0;i<test;i++)
		printf("%.6lf\n",V[i]);
	delete []V;
	return 0;
}
