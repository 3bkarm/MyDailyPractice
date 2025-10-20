## [Catshock](https://codeforces.com/contest/2154/problem/D)
```cpp
#include <bits/stdc++.h>

using namespace std;

#define int long long
#define bint __int128
#define _3bkarm cin.tie(NULL); cout.tie(NULL); ios::sync_with_stdio(false);

int n;
vector< vector<int> > adj;

vector<bool> group;
void dfs(int v, int p) {
    for (int u : adj[v]) {
        if (u == p) {
            continue;
        }
        group[u] = not group[v];
        dfs(u, v);
    }
}

void getShitDone() {
    cin >> n;

    adj.assign(n, {});
    vector<int> degree(n);
    for (int i = 0, u, v; i < n - 1; ++i) {
        cin >> u >> v;
        --u, --v;
        ++degree[u];
        ++degree[v];
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    group.assign(n, false);
    dfs(0, 0);

    queue<int> q;
    for (int v = 0; v < n; ++v) {
        if (degree[v] == 1) {
            q.push(v);
        }
    }

    vector<int> ans;
    bool currentGroup = false;
    vector<bool> done(n);
    while ( not q.empty() ) {
        int v = q.front();
        q.pop();
        if (v == n - 1) {
            continue;
        }
        done[v] = true;
        if (currentGroup == group[v]) {
            ans.push_back(-1);
            currentGroup = not currentGroup;
        }
        ans.push_back(v);
        ans.push_back(-1);
        currentGroup = not currentGroup;
        for (int u : adj[v]) {
            if (done[u]) {
                continue;
            }
            --degree[u];
            if (degree[u] == 1) {
                q.push(u);
            }
        }
    }

    for (int v = 0; v < n - 1; ++v) {
        if (not done[v]) {
            if (currentGroup == group[v]) {
                ans.push_back(-1);
            }
            ans.push_back(v);
            break;
        }
    }

    cout << ans.size() << '\n';
    for (int v : ans) {
        if (v == -1) {
            cout << 1 << '\n';
        } else {
            cout << 2 << ' ' << v + 1 << '\n';
        }
    }
}

signed main() {
    _3bkarm

    int ts = 1;
    cin >> ts;
    while (ts--) {
        getShitDone();
//        if (ts != 0) {
//            cout << '\n';
//        }
    }

    return 0;
}
```
