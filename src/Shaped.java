
import java.io.*;

public class Shaped {
    static Boolean[][] grid;
    static UnionFind[] ufdsH;
    static UnionFind[] ufdsW;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int numOfTests = Integer.parseInt(br.readLine()); 
        int height;
        int width;
        String[] temp;
        
        for (int i = 0; i < numOfTests; i++) {
            temp = br.readLine().split(" ");
            height = Integer.parseInt(temp[0]);
            width = Integer.parseInt(temp[1]);
            ufdsH = new UnionFind[width];
            ufdsW = new UnionFind[height];
            grid = new Boolean[height][width];
            for (int j = 0; j < height; j++) {
                temp = br.readLine().split(" ");
                for (int k = 0; k < width; k++) {
                    if (temp[k].equals("1")) grid[j][k] = true;
                    else grid[j][k] = false;
                }
            }
            for (int j = 0; j < width; j++) {
                ufdsH[j] = new UnionFind(height);
                for (int k = 1; k < height; k++) {
                    if (grid[k][j] && grid[k - 1][j]) {
                        ufdsH[j].unionSet(k, k - 1);
                    }
                }
            }
            
            pw.println("Case #" + (i + 1) + ": " + process(height, width));
        }
        pw.close();
    }

    public static int process(int height, int width) {
        int widthLen;
        int counter = 0;
        for (int i = 0; i < height; i++) {
            for (int left = 0; left < width - 1; left++) {
                if (!grid[i][left]) continue;
                for (int right = left + 1; right < width; right++) {
                    if (!grid[i][right]) break;
                    widthLen = right - left + 1;
                    if (widthLen < 2) continue;
                    // up left
                    if (i - (widthLen * 2 - 1) >= 0) {
                        if (ufdsH[left].isSameSet(i, i - (widthLen * 2 - 1))) counter ++;
                        if (ufdsH[right].isSameSet(i, i - (widthLen * 2 - 1))) counter ++;
                    }
                    
                    // down left

                    if (i + (widthLen * 2 - 1) < height) {
                        if (ufdsH[left].isSameSet(i, i + (widthLen * 2 - 1))) counter ++;
                        if (ufdsH[right].isSameSet(i, i + (widthLen * 2 - 1))) counter ++;
                    }

                    // Another
                    if (widthLen >= 4 && widthLen % 2 == 0 && (i - (widthLen / 2 - 1) >= 0 || i + (widthLen / 2 - 1) < height)) {
                        // up left
                        if (i - (widthLen / 2 - 1) >= 0) {
                            if (ufdsH[left].isSameSet(i, i - (widthLen / 2 - 1))) counter ++;
                            if (ufdsH[right].isSameSet(i, i - (widthLen / 2 - 1))) counter ++;
                        }
                        
                        // down left
                        if (i + (widthLen / 2 - 1) < height) {
                            if (ufdsH[left].isSameSet(i, i + (widthLen / 2 - 1))) counter ++;
                            if (ufdsH[right].isSameSet(i, i + (widthLen / 2 - 1))) counter ++;
                        }
                    }
                }
            }
        }
        return counter;
    }
}



class UnionFind {
    public int[] p;
    public int[] rank;
    public int numSets;

    public UnionFind(int N) {
        p = new int[N];
        rank = new int[N];
        numSets = N;
        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 0;
        }
    }

    public int findSet(int i) {
        if (p[i] == i)
            return i;
        else {
            p[i] = findSet(p[i]);
            return p[i];
        }
    }

    public Boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            numSets--;
            int x = findSet(i), y = findSet(j);
            // rank is used to keep the tree short
            if (rank[x] > rank[y])
                p[y] = x;
            else {
                p[x] = y;
                if (rank[x] == rank[y])
                    rank[y] = rank[y] + 1;
            }
        }
    }

    public int numDisjointSets() {
        return numSets;
    }
}

/*
2
6 4
1 0 0 0
1 0 0 1
1 1 1 1
1 0 1 0
1 0 1 0
1 1 1 0

1 0 0 0
1 0 0 1
1 1 1 1
1 0 1 0
1 0 1 0
1 1 1 0

*/