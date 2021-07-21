import java.util.Queue;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Scanner;
public class discretemath {
    static class Graph{
        public int vertexnum;
        public int edgenum;
        public int[] Vertex=new int[vertexnum+5];
        public int[][] edge=new int[vertexnum+5][vertexnum+5];
        public boolean dir;//是否为有向图，1为有向图
        public int[] mindis=new int[vertexnum+1];
        public Graph(int n,int m,boolean d)
        {
            vertexnum=n;
            edgenum=m;
            dir=d;
            System.out.println("请按照 ’起点 终点 权值 ‘的形式输入边");
            Scanner scan=new Scanner(System.in);
            for(int i=0;i<m;i++)
            {
                int t1=0,t2=0,t3=0;//出现问题
                if(scan.hasNextInt())
                {
                    t1=scan.nextInt();
                    t2=scan.nextInt();
                    t3=scan.nextInt();
                    edge[t1][t2]=t3;
                }
                if(!dir) {edge[t2][t1]=t3;}
            }
        }
    }
    public static void printgraph(Graph g)
    {
    System.out.println("diaoyongl");
    for(int i=1;i<=g.vertexnum;i++) {
        for (int j = 1; j <= g.vertexnum; j++) {
                System.out.print(g.edge[i][j]);
                System.out.print(" ");
        }
        System.out.println(" ");
    }
}
    public static boolean isselfreverse(Graph g)
    {
        boolean flag = true;
        for(int i=1;i<= g.vertexnum;i++) {
            for(int j=1;j<=g.vertexnum;j++)
                if (g.edge[i][j] != 0) {
                    if (g.edge[j][i] != 0) { }
                    else flag = false;
                }
        }
        return flag;
    }
    public static boolean istransitive(Graph g)
    {
    boolean flag =true;
    for(int i=1;i<=g.vertexnum;i++)
    {
        for(int j=1;j<=g.vertexnum;j++) {
            for(int k=1;k<=g.vertexnum;k++)
            {
                if(g.edge[i][k]!=0&&g.edge[k][j]!=0)
                {
                    if(g.edge[i][j]!=0) {}
                    else flag=false;
                }
            }
        }
    }
    return flag;
    }
    public static boolean issymmetry(Graph g)
    {
        boolean flag=true;
        for(int i=1;i<=g.vertexnum;i++)
        {
            for(int j=1;j<=g.vertexnum;j++)
            {
                if(g.edge[i][j]!=0)
                {
               if(g.edge[j][i]==0)     flag=false;
                }
            }
        }
        return flag;
    }
    public static void SSSP(int v,Graph g)//v是源点
    {
         g.mindis=new int[g.vertexnum];
        boolean[] vis=new boolean[g.vertexnum];
        int tmp=0;
        for(int i=1;i<=g.vertexnum;i++)
        {
          if(g.edge[v][i]!=0)  g.mindis[i]=g.edge[v][i];
          else g.mindis[i]=0x77777777;
          vis[i]=false;
        }
        for (int i = 1; i < g.vertexnum; i++)
        {
            int min =0x7777777;
            int temp = 0;
            for (int w = 0; w < g.vertexnum; w++)
            {
                if (!vis[w] && g.mindis[w] < min)//选择最小的输出
                {
                    temp = w;
                    min = g.mindis[w];
                }
            }
            vis[temp] = true;
            for (int w = 0; w < g.vertexnum; w++)
            {
                if (!vis[w] && g.mindis[temp] + g.edge[temp][w] < g.mindis[w])
                {
                    g.mindis[w] = g.mindis[temp] + g.edge[temp][w];
                }
            }
        }
    }
    public static boolean Eulerpath(Graph g) {
        boolean flag = true;
        if (g.dir == true) {
            int[] degin = {0};
            int[] degout = {0};
            for (int i = 1; i <= g.vertexnum; i++) {
                for (int j = 1; j <= g.vertexnum; j++) {
                    if (g.edge[i][j] != 0) {
                        degin[j]++;
                        degout[i]--;
                    }
                }
            }
            for (int i = 1; i <= g.vertexnum; i++) {
                if (degin[i] != degout[i]) {
                    flag = false;
                }
            }
        }
        else {
            int[] deg = new int[g.vertexnum];
            int cnt = 0;
            for (int i = 1; i <= g.vertexnum; i++) {
                for (int j = 1; j <= g.vertexnum; j++) {
                    if (g.edge[i][j] != 0) {
                        deg[i]++;
                        deg[j]++;
                    }
                }
            }
            for (int i = 1; i <= g.vertexnum; i++) {
                if (deg[i] % 2 != 0) {
                    cnt++;
                }
                if (cnt != 0 || cnt != 2) flag = false;
            }
        }
        return flag;
    }
    public static void LST(Graph g)// Kruskal算法（并查集)(以为邻接链表的构图方式）
    {
    }
    public static int Coloring(Graph g) {
        int ans = 0;
        boolean vis[] = new boolean[5];//颜色i是否使用过
        int col[] = new int[g.vertexnum + 5];
        for (int u = 1; u <= g.vertexnum; u++) {
            int t = 1;//临时颜色变量
            for (int i = 1; i <= ans; i++) {
                vis[i] = false;
            }
            for (int v = 1; v < u; ++v) {
                if (g.edge[u][v] != 0 || g.edge[v][u] != 0) vis[col[v]] = true;
            }
            while (t <= ans && vis[t]) {
                ++t;
            }
            col[u] = t;
            if (t > ans) ans++;
        }
        return ans;
    }
    public static void main(String[] args) {
int vnum,edgenum;
boolean dir;
System.out.println("请输入顶点数，边数，是否为有向图");
Scanner scan1=new Scanner(System.in);
vnum=scan1.nextInt();
edgenum=scan1.nextInt();
dir=scan1.nextBoolean();
Graph g= new Graph(vnum,edgenum,dir);
//printgraph(g);
System.out.println("请输入操作：");
        int opt;
        opt=scan1.nextInt();
        switch (opt)
        {
            case 1:
                System.out.print("它是自反的吗？");
                if( isselfreverse(g) )  System.out.println("是");
                else System.out.println("不是");
                System.out.print("它是传递的吗？");
                if( istransitive(g) )  System.out.println("是");
                else System.out.println("不是");
                System.out.print("它是对称的吗？");
                if( issymmetry(g) )  System.out.println("是");
                else System.out.println("不是");
                break;//判断对称，传递，自反性
            case 2:
                Scanner scan2 =new Scanner(System.in);
                int v= scan2.nextInt();
                SSSP(v,g);
                for(int i=1;i<=g.vertexnum;i++)
                {
                    System.out.println(g.mindis[i]);
                }
                break;//输出单源最短路
            case 3:if(Eulerpath(g)) System.out.println("It is an Eulerpath.");
                    else System.out.println("It is not an Eulerpath.");break;//判断欧拉图
            case 4:break;//最小生成树
            case 5:System.out.print(Coloring(g));break;//涂色
        }
return;
    }
}
