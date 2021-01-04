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
	long long n;
	long long sum = -LONG_LONG_MAX;
	cin>>n;

	priority_queue<pair<long,long long>, vector<pair<long,long long>>, comp> heap;//tang dan

	long long ts = 0;

	vector<pair<long,long long>> a;
	a.resize(n);

	for(long i =0; i<n; i++){
	    long long k;
        cin>>k;
        ts += k;
        heap.push({i,ts});
        a[i].first = i;
        a[i].second = ts;
	}


	for(long i = n-1; i != -1; i--){
        pair<long,long long> smin = heap.top();
        if(smin.second > 0)
            sum = max(sum, a[i].second);
        else
            sum = max(sum, a[i].second - smin.second);
        if( a[i].first == smin.first)
            heap.pop();
	}

	cout<<sum;
	return 0;
}
