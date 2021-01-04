#include <iostream>
#include <queue>
#include <bits/stdc++.h>
#include <algorithm>
#include <vector>

using namespace std;

struct comp{
    bool operator()(const pair<long,long long> &l,const pair<long,long long> &r ){
        return l.second > r.second;
    }
};

int main(){
	long long n,l1,l2;
	long long sum = -LONG_LONG_MAX;
	cin>>n>>l1>>l2;

	priority_queue<pair<long,long long>, vector<pair<long,long long>>, comp> heap;//tang dan

	long long ts = 0;

	vector<pair<long,long long>> a;
	a.resize(n+1);
	a[0].first = 0;
	a[0].second = 0;
	for(long i =1; i<=n; i++){
	    long long k;
        k = i;
        ts += k;
        a[i].first = i;
        a[i].second = ts;
	}
	long l = n-l2;
	long r = n-l1;
	for(long i = l; i<= r; i++)
        heap.push(a[i]);

	for(long i = n; i >= l1; i--){
        pair<long,long long> smin = heap.top();
        r = i-l1;
        if(smin.first > r){
            heap.pop();
            i++;
            continue;
        }
        sum = max(sum, a[i].second - smin.second);
        heap.push(a[i-l2-1]);
	}

	cout<<sum;
	return 0;
}



//#include<iostream>
//#include <bits/stdc++.h>
//
//using namespace std;
//
//int main(){
//	int n,l1,l2;
//	int sum;
//	sum = -INT_MAX;
//	cin>>n>>l1>>l2;
//
//	int *a = new int[n];
//	for(int i=0;i<n;i++)
//	cin>>a[i];
//
//	for(int i=l1;i<=l2;i++){
//		for(int j=0;j<=n-i;j++){
//			int s=0;
//			for(int k=j;k<j+i;k++)
//				s=s+a[k];
//			if (s>sum)
//			sum=s;
//		}
//	}
//	cout<<sum;
//	delete []a;
//	return 0;
//}
