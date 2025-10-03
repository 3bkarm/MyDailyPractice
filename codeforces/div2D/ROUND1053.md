## [Grid Counting](https://codeforces.com/contest/2150/problem/B)
```cpp
#include <bits/stdc++.h>

using namespace std;

#define int long long
#define bint __int128
#define _3bkarm cin.tie(NULL); cout.tie(NULL); ios::sync_with_stdio(false);

const int N = 1000'001, mod = 998244353;

int fact[N], invFact[N], inv[N];

void build() {
    fact[0] = inv[1] = fact[1] = invFact[0] = invFact[1] = 1;
    for (int i = 2; i < N; ++i) {
        fact[i] = fact[i - 1] * i % mod;
        inv[i] = mod - (inv[mod % i] * (mod / i) % mod);
        invFact[i] = inv[i] * invFact[i - 1] % mod;
    }
}

int nCr(int n, int r) {
    if (r > n)  return 0;
    return fact[n] * invFact[n - r] % mod * invFact[r] % mod;
}

void getShitDone() {
    int n;
    cin >> n;

    vector<int> a(n + 1);
    for (int i = 1; i <= n; ++i) {
        cin >> a[i];
    }

    for (int i = n / 2 + n % 2 + 1; i <= n; ++i) {
        if (a[i] > 0) {
            cout << 0;
            return;
        }
    }

    int ans = 1, have = 0;
    for (int r = n / 2 + n % 2; r >= 1; --r) {
        if (2 * r > n) {
            ++have;
        } else {
            have += 2;
        }
        if (have < a[r]) {
            cout << 0;
            return;
        }
        ans = ans * nCr(have, a[r]) % mod;
        have -= a[r];
    }

    if (have == 0) {
        cout << ans;
    } else {
        cout << 0;
    }
}

signed main() {
    _3bkarm

    build();

    int ts = 1;
    cin >> ts;
    while (ts--) {
        getShitDone();
        if (ts != 0) cout << '\n';
    }

    return 0;
}
```
