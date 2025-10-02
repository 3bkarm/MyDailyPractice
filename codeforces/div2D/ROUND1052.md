## [Max Sum OR (Hard Version)](https://codeforces.com/contest/2146/problem/D2)
```cpp
#include <bits/stdc++.h>

using namespace std;

#define int long long
#define bint __int128
#define _3bkarm cin.tie(NULL); cout.tie(NULL); ios::sync_with_stdio(false);

int get(int x) {
    int ans = 1;
    while (ans <= x) {
        ans *= 2;
    }
    return ans - 1;
}

void getShitDone() {
    int l, r;
    cin >> l >> r;

    int n = r - l + 1;
    vector<bool> vis(n);
    vector<int> a(n);
    for (int i = 0; i < n; ++i) {
        a[i] = l + i;
    }

    int target = get(r);

    while (target > 0) {
        for (int x = l; x <= r; ++x) {
            int ix = x - l;
            if (vis[ix]) {
                continue;
            }
            int y = (target ^ x);
            if (y < l or y > r) {
                continue;
            }
            int iy = y - l;
            if (vis[iy]) {
                continue;
            }
            vis[ix] = vis[iy] = true;
            a[ix] = y, a[iy] = x;
        }
        target = (target + 1) / 2 - 1;
    }

    int sum = 0;
    for (int i = 0; i < n; ++i) {
        sum += ( a[i] | (l + i) );
    }

    cout << sum << '\n';
    for (int i = 0; i < n; ++i) {
        cout << a[i] << ' ';
    }
}

signed main() {
    _3bkarm

    int ts = 1;
    cin >> ts;
    while (ts--) {
        getShitDone();
        if (ts != 0) cout << '\n';
    }

    return 0;
}
```
