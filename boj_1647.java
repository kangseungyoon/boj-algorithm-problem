import java.util.*;

public class boj_1647{
    static class Edge implements Comparable<Edge>{
        int s;
        int e;
        int w;

        Edge(int s, int e, int w) {
            this.s=s;
            this.e=e;
            this.w=w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int answer = 0;
        int max_value = Integer.MIN_VALUE;
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        for (int i = 0; i < m; i++) {
            int s = sc.nextInt() - 1;
            int e = sc.nextInt() - 1;
            int w = sc.nextInt();
            pq.add(new Edge(s, e, w));
        }

        for (int i = 0; i < m; i++) {
            Edge edge = pq.poll();
            int a = edge.s;
            int b = edge.e;
            if (!isCycle(parent, a, b)) {
                union(parent, a, b);
                answer += edge.w;
                max_value = Math.max(max_value, edge.w);
            }
        }
        System.out.println(answer - max_value);
    }

    public static int find(int[] parent, int x) {
        if (parent[x] < 0) {
            return x;
        } else {
            return parent[x] = find(parent, parent[x]);
        }
    }

    public static void union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);
        if(x==y) return;
        if (parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        } else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }

    public static boolean isCycle(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);
        return x==y;
    }
}

//public class boj_1647 {
//    static class Edge implements Comparable<Edge>{
//        int s;
//        int e;
//        int w;
//
//        Edge(int s, int e, int w) {
//            this.s = s;
//            this.e = e;
//            this.w = w;
//        }
//
//        @Override
//        public int compareTo(Edge o) {
//            return this.w - o.w;
//        }
//    }
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//        ArrayList<Edge>[] graph=new ArrayList[n];
//        boolean[] visit = new boolean[n];
//
//        for(int i=0;i<n;i++)
//            graph[i]=new ArrayList<>();
//
//        for (int i = 0; i < m; i++) {
//            int s = sc.nextInt() - 1;
//            int e = sc.nextInt() - 1;
//            int w = sc.nextInt();
//            graph[s].add(new Edge(s, e, w));
//            graph[e].add(new Edge(e, s, w));
//        }
//
//        System.out.println(solution(graph,visit,0));
//        sc.close();
//    }
//
//    public static int solution(ArrayList<Edge>[] graph, boolean[] visit, int start) {
//        PriorityQueue<Edge> pq = new PriorityQueue<>();
//        Queue<Integer> que = new LinkedList<>();
//        int max_value = Integer.MIN_VALUE;
//        int answer = 0;
//        que.add(start);
//        while (!que.isEmpty()) {
//            int cur = que.poll();
//            visit[cur] = true;
//
//            for (Edge edge : graph[cur])
//                if(!visit[edge.e])
//                    pq.add(edge);
//
//            while (!pq.isEmpty()) {
//                Edge edge = pq.poll();
//                if (!visit[edge.e]) {
//                    answer += edge.w;
//                    max_value = Math.max(max_value, edge.w);
//                    que.add(edge.e);
//                    break;
//                }
//            }
//        }
//        return answer-max_value;
//    }
//}
