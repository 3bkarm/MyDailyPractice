package LeetCode;

//https://leetcode.com/problems/design-movie-rental-system?envType=daily-question&envId=2025-09-21

import java.util.*;

class LC1912 {
    private HashMap< Integer, HashMap<Integer, Integer> > MoviesInShop;
    private HashMap< Integer, TreeSet<int[]> > ShopsHaveMovie;
    private TreeSet<int[]> RentedMovies;

    public LC1912(int n, int[][] entries) {
        this.MoviesInShop = new HashMap<>();
        this.ShopsHaveMovie = new HashMap<>();
        this.RentedMovies = new TreeSet<>( (a, b) -> {
            for (int i = 0; i < 3; ++i) {
                if (a[i] != b[i]) {
                    return a[i] - b[i];
                }
            }
            return 0;
        } );
        for (int[] entry : entries) {
            int shop = entry[0];
            int movie = entry[1];
            int price = entry[2];
            if ( !this.MoviesInShop.containsKey(shop) ) {
                this.MoviesInShop.put( shop, new HashMap<>() );
            }
            this.MoviesInShop.get(shop).put(movie, price);
            if ( !this.ShopsHaveMovie.containsKey(movie) ) {
                this.ShopsHaveMovie.put( movie, new TreeSet<>( (a, b) -> {
                    for (int i = 0; i < 2; ++i) {
                        if (a[i] != b[i]) {
                            return a[i] - b[i];
                        }
                    }
                    return 0;
                } ) );
            }
            this.ShopsHaveMovie.get(movie).add(new int[]{ price, shop });
        }
    }

    public List<Integer> search(int movie) {
        if ( !ShopsHaveMovie.containsKey(movie) ) {
            return new ArrayList<>();
        }
        TreeSet<int[]> Shops = ShopsHaveMovie.get(movie);
        ArrayList<Integer> ans = new ArrayList<>();
        int iterations = Math.min( 5, Shops.size() );
        for (int[] Shop : Shops) {
            if (iterations == 0) {
                break;
            }
            ans.add(Shop[1]);
            --iterations;
        }
        return ans;
    }

    public void rent(int shop, int movie) {
        int price = MoviesInShop.get(shop).get(movie);
        ShopsHaveMovie.get(movie).remove(new int[]{ price, shop });
        RentedMovies.add(new int[]{ price, shop, movie });
    }

    public void drop(int shop, int movie) {
        int price = MoviesInShop.get(shop).get(movie);
        ShopsHaveMovie.get(movie).add(new int[]{ price, shop });
        RentedMovies.remove(new int[]{ price, shop, movie });
    }

    public List<List<Integer>> report() {
        if ( RentedMovies.isEmpty() ) {
            return new ArrayList<>();
        }
        ArrayList< List<Integer> > ans = new ArrayList<>();
        int iterations = Math.min( 5, RentedMovies.size() );
        for (int[] movie : RentedMovies) {
            if (iterations == 0) {
                break;
            }
            ArrayList<Integer> t = new ArrayList<>();
            t.add(movie[1]);
            t.add(movie[2]);
            ans.add(t);
            --iterations;
        }
        return ans;
    }

}

/**
 * Your LeetCode.Design.MovieRentingSystem object will be instantiated and called as such:
 * LeetCode.Design.MovieRentingSystem obj = new LeetCode.Design.MovieRentingSystem(n, entries);
 * List<Integer> param_1 = obj.search(movie);
 * obj.rent(shop,movie);
 * obj.drop(shop,movie);
 * List<List<Integer>> param_4 = obj.report();
 */