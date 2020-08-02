package LinearDS;

/**
 * Implementing Union-Find data structure using array
 * @author theandrocoder
 */
public class UnionFindMain {
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(20);
        uf.printIdArray();
        uf.unify(1,2);
        uf.unify(3,4);
        uf.unify(1,4);
        uf.unify(4,5);
        uf.unify(5,19);
        uf.unify(12,15);
        uf.unify(0,15);
        uf.unify(1,0);
        uf.unify(12,13);
        uf.printIdArray();
    }
}
class UnionFind{
    // no of elements in Union find
    private int size;
    // no of elements in each component
    private int[] sz;
    // id[i] points to the parent of i
    private int[] id;
    // track the num of components in union find
    private int numComponents;

    public UnionFind(int size){
        if(size<=0)throw new IllegalArgumentException("size should be non-zero and non-negative");
        this.size=size;
        this.numComponents=size;
        sz=new int[size];
        id=new int[size];

        for (int i = 0; i <size ; i++) {
            id[i]=i;
            sz[i]=1;
        }
    }

    /**
     * Find which component/set 'p' belongs to, takes amortized constant time .. alpha(n)
     * @param p find set of node 'p'
     * @return component/set to which 'p' belongs
     */
    public int find(int p){
        //find thr root of p
        int root=p;
        while(root!=id[root])
            root=id[root];
        // path compression leading back to the root
        while(p!=root){
            int next=id[p];
            id[p]=root;
            p=next;
        }
        return root;
    }

    /**
     * If two components belong to same set or not
     * @param p : first component
     * @param q : second component
     * @return true if both item belong to same set otherwise returns false
     */
    public boolean connected(int p,int q){
        return find(p)==find(q);
    }

    public int componentSize(int p){
        return sz[find(p)];
    }

    public int size(){
        return this.size;
    }

    public int components(){
        return numComponents;
    }

    /**
     * Unify the sets containing p and q
     * @param p : first compoennt
     * @param q : second component
     */
    public void unify(int p, int q){
        int root1=find(p);
        int root2 = find(q);
        // They are already in same group
        if(root1==root2)return;

        if(sz[root1]<sz[root2]){
            sz[root2]+=sz[root1];
            id[root1]=root2;
        }else{
            sz[root1]+=sz[root2];
            id[root2]=root1;
        }
        numComponents--;
    }

    public void printIdArray(){
        for (int i = 0; i <size ; i++) {
            System.out.print(id[i] + " ");
        }
        System.out.println();
    }

}
