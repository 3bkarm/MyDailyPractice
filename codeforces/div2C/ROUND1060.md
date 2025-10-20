## [No Cost Too Great (Hard Version)](https://codeforces.com/contest/2154/problem/C2)
```cpp
#include <bits/stdc++.h>

using namespace std;

#define int long long
#define bint __int128
#define _3bkarm cin.tie(NULL); cout.tie(NULL); ios::sync_with_stdio(false);

const int N = 200'002;

bool isPrime[N];
int primeDivisor[N];
vector<int> primes;

void sieve() {
    isPrime[0] = isPrime[1] = false;
    for (int i = 2; i < N; ++i) isPrime[i] = true;
    for (int i = 2; i < N; i++) {
        if (isPrime[i]) {
            primeDivisor[i] = i;
            primes.push_back(i);
        }
        for (auto p : primes) {
            if (1LL * i * p >= N) break;
            isPrime[i * p] = false;
            primeDivisor[i * p] = p;
            if (i % p == 0) break;
        }
    }
}

vector<int> primeFactors(int val) {
    vector<int> pf;
    while (val > 1) {
        if (pf.empty() or primeDivisor[val] != pf.back())
            pf.push_back(primeDivisor[val]);
        val /= primeDivisor[val];
    }
    return pf;
}

const int inf = 1000'000'000'000'000'000;

void getShitDone() {
    int n;
    cin >> n;

    vector<int> a(n);
    for (int i = 0; i < n; ++i) {
        cin >> a[i];
    }

    vector<int> b(n);
    for (int i = 0; i < n; ++i) {
        cin >> b[i];
    }

    vector< pair<int, int> > t(n);
    for (int i = 0; i < n; ++i) {
        t[i] = { b[i], a[i] };
    }
    sort( t.begin(), t.end() );

    for (int i = 0; i < n; ++i) {
        a[i] = t[i].second;
        b[i] = t[i].first;
    }

    map<int, int> mp;
    for (int i = 0; i < n; ++i) {
        vector<int> pf = primeFactors(a[i]);
        for (int p : pf) {
            ++mp[p];
        }
    }

    for (auto [p, f] : mp) {
        if (f > 1) {
            cout << 0;
            return;
        }
    }

    int ans = inf;
    for (int i = 0; i < n; ++i) {
        ++a[i];
        vector<int> pf = primeFactors(a[i]);
        for (int p : pf) {
            if (mp[p] + 1 > 1) {
                ans = min(ans, b[i]);
            }
        }
        --a[i];
    }

    if (a[0] % 2 == 1 and a[1] % 2 == 1) {
        ans = min(ans, b[0] + b[1]);
    }

    for (int i = 1; i < n; ++i) {
        vector<int> pf = primeFactors(a[i]);
        for (int p : pf) {
            int f = (a[0] + p - 1) / p;
            int d = f * p - a[0];
            ans = min(ans, d * b[0]);
        }
    }

    cout << ans;
}

signed main() {
    _3bkarm

    sieve();

    int ts = 1;
    cin >> ts;
    while (ts--) {
        getShitDone();
        if (ts != 0) {
            cout << '\n';
        }
    }

    return 0;
}
```
