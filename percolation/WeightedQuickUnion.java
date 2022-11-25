public class WeightedQuickUnion {

    private int[] list;
    private int[] size;

    public WeightedQuickUnion(int n) {
        list = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            list[i] = i;
            size[i] = 1;
        }
    }

    private int root(int p) {
        while (list[p] != p) {
            list[p] = list[list[p]];
            p = list[p];
        }
        return p;
    }

    private int size(int p){
        return size[root(p)];
    }

    public void union(int a, int b) {
        int z = root(a);
        int x = root(b);
        if(size(x) > size(z)){
            list[z] = x;
            size[x] += size[z];
        }
        else{
            list[x] = z;
            size[z] += size[x];
        }
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

}
