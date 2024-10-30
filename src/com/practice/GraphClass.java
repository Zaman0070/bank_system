package com.practice;

import java.util.*;

public class GraphClass {

    static class Edge{
        int src, dest, weight;
        Edge(int src, int dest, int weight){
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

//        graph[0].add(new Edge(0,2,2));
//        graph[1].add(new Edge(1,2,10));
//        graph[1].add(new Edge(1,3,0));
//
//        graph[2].add(new Edge(2,0,2));
//        graph[2].add(new Edge(2,1,10));
//        graph[2].add(new Edge(2,3,-1));
//
//        graph[3].add(new Edge(3,1,0));
//        graph[3].add(new Edge(3,2,-1));
        graph[0].add(new Edge(0,2,2));
        graph[1].add(new Edge(1,0,2));
        graph[2].add(new Edge(2,3,2));
        graph[3].add(new Edge(3,0,2));
//        graph[4].add(new Edge(4,1,-1));

    }

    public static void bfs(ArrayList<Edge> graph[],int v,boolean[] visited, int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()){
            int node = queue.poll();
            if(!visited[node]){
                visited[node] = true;
                System.out.println(node);
            }
            for (Edge edge : graph[node]) {
                if(!visited[edge.dest]){
                    queue.add(edge.dest);
                }
            }
        }

    }

    public static void dfs(ArrayList<Edge>[] graph , int currevt, boolean[] visited){
        if(visited[currevt]){
            return;
        }
        visited[currevt] = true;
        System.out.println(currevt);
        for (Edge edge : graph[currevt]) {
            dfs(graph,edge.dest,visited);
        }

    }

    // all paths from source to destination
    public static void allPaths(ArrayList<Edge>[] graph, int curr, int destination, boolean[] visited, String path){
        if(curr==destination){
            System.out.println(path);
            return;
        }
        visited[curr] = true;
        for (Edge edge : graph[curr]) {
            if(!visited[edge.dest]){
                allPaths(graph,edge.dest,destination,visited,path+edge.dest);
            }
        }
        visited[curr] = false;
    }





    //////// Graph Class Questions ///////////


    // cycle detection in undirected graph and directed graph

    public static boolean isCycle(ArrayList<Edge>[] graph, int curr, boolean[] visited, boolean[] recStack){
        if(recStack[curr]){
            return true;
        }
        if(visited[curr]){
            return false;
        }
        visited[curr] = true;
        recStack[curr] = true;
        for (Edge edge : graph[curr]) {
            if(isCycle(graph,edge.dest,visited,recStack)){
                return true;
            }
        }
        recStack[curr] = false;
        return false;
    }


    // topological sort
    // directed acyclic graph only works
    public static void topologicalSort(ArrayList<Edge>[] graph, int curr, boolean[] visited, Stack<Integer> stack){
        visited[curr] = true;
        for (Edge edge : graph[curr]) {
            if(!visited[edge.dest]){
                topologicalSort(graph,edge.dest,visited,stack);
            }
        }
        stack.push(curr);
    }

    public static void topSort(ArrayList<Edge>[] graph, int v ){
        boolean[] visited = new boolean[v];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < v; i++) {
            if(!visited[i]){
                topologicalSort(graph,i,visited,stack);
            }
        }
        while (!stack.isEmpty()){
            System.out.println(stack.pop()+"");
        }
    }

    // cycle detection in directed graph
    public static boolean isCycleDirected(ArrayList<Edge>[] graph, int curr, boolean[] visited,int parent){
        visited[curr] = true;
        for (Edge edge : graph[curr]) {
            if(!visited[edge.dest]){
                if(isCycleDirected(graph,edge.dest,visited,curr)){
                    return true;
                }
            }else if(edge.dest!=parent){
                return true;
            }
        }
        return false;

    }

    // shortest path in weighted graph

//    public static class Pair{
//        int node;
//        int dis;
//        Pair(int node, int dis){
//            this.node = node;
//            this.dis = dis;
//        }

//        @Override
//        public int compareTo(Pair o) {
//            return this.dis-o.dis;
//        }
//    }
//    public static void shortestPath(ArrayList<Edge>[] graph, int src){
//        PriorityQueue<Pair> pq = new PriorityQueue<>();
//        int[] dis = new int[graph.length];
//        Arrays.fill(dis,Integer.MAX_VALUE);
//        dis[src] = 0;
//        pq.add(new Pair(src,0));
//        while (!pq.isEmpty()){
//            Pair pair = pq.poll();
//            for (Edge edge : graph[pair.node]) {
//                if(dis[edge.dest]>dis[edge.src]+edge.weight){
//                    dis[edge.dest] = dis[edge.src]+edge.weight;
//                    pq.add(new Pair(edge.dest,dis[edge.dest]));
//                }
//            }
//        }
//        for (int i = 0; i < dis.length; i++) {
//            System.out.println(i+" "+dis[i]);
//        }
//    }

    // bellman ford algorithm
    public static void bellmanFord(ArrayList<Edge>[] graph, int src,int v){
        int[] dis = new int[v];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[src] = 0;
        for (int i = 0; i < v-1; i++) {
            for (int j = 0; j < v; j++) {
                for (Edge edge : graph[j]) {
                    if(dis[edge.dest]>dis[edge.src]+edge.weight){
                        dis[edge.dest] = dis[edge.src]+edge.weight;
                    }
                }
            }
        }
        for (int i = 0; i < v; i++) {
            System.out.println(i+" "+dis[i]);
        }
    }

    // minimum spanning tree undirected graph and every node(edge) is connected to each other and cost is minimum
  public static class Pair{
        int node;
        int cost;
        Pair(int node, int cost){
            this.node = node;
            this.cost = cost;
        }

        @Override
        public boolean equals(Object o){
            if(this==o){
                return true;
            }
            if(o==null || getClass()!=o.getClass()){
                return false;
            }
            Pair pair = (Pair) o;
            return cost==pair.cost;
        }


    }

    public static void prims(ArrayList<Edge>[] graph, int src, int v){  // ElogV
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->a.cost-b.cost);
        boolean[] visited = new boolean[v];
        int[] parent = new int[v];
        int[] key = new int[v];
        Arrays.fill(key,Integer.MAX_VALUE);
        pq.add(new Pair(src,0));
        key[src] = 0;
        while (!pq.isEmpty()){
            Pair pair = pq.poll();
            visited[pair.node] = true;
            for (Edge edge : graph[pair.node]) {
                if(!visited[edge.dest] && key[edge.dest]>edge.weight){
                    key[edge.dest] = edge.weight;
                    pq.add(new Pair(edge.dest,key[edge.dest]));
                    parent[edge.dest] = pair.node;
                }
            }
        }
        for (int i = 1; i < v; i++) {
            System.out.println(parent[i]+" - "+i);
        }
    }

    // kosaraju's algorithm
    public static void topSort2(ArrayList<Edge>[] graph, int curr, boolean[] visited, Stack<Integer> stack){
        visited[curr] = true;
        for (Edge edge : graph[curr]) {
            if(!visited[edge.dest]){
                topSort2(graph,edge.dest,visited,stack);
            }
        }
        stack.push(curr);
    }

    public static void kosaraju(ArrayList<Edge>[] graph, int v){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            if(!visited[i]){
                topSort2(graph,i,visited,stack);
            }
        }
        ArrayList<Edge>[] reverseGraph = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            reverseGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < v; i++) {
            for (Edge edge : graph[i]) {
                reverseGraph[edge.dest].add(new Edge(edge.dest,edge.src,edge.weight));
            }
        }
        Arrays.fill(visited,false);
        while (!stack.isEmpty()){
            int node = stack.pop();
            if(!visited[node]){
                dfs(reverseGraph,node,visited);
                System.out.println();
            }
        }

    }

    //get bridges in graph
        public static void getBridge(ArrayList<Edge>[] graph, int v){
            int[] disc = new int[v];
            int[] low = new int[v];
            int[] parent = new int[v];
            boolean[] visited = new boolean[v];
            for (int i = 0; i < v; i++) {
                if(!visited[i]){
                    getBridgeUtil(graph,i,disc,low,parent,visited,0,v);
                }
            }
        }

        public static void getBridgeUtil(ArrayList<Edge>[] graph, int curr, int[] disc, int[] low, int[] parent, boolean[] visited, int time, int v){
            visited[curr] = true;
            disc[curr] = low[curr] = time;
            time++;
            for (Edge edge : graph[curr]) {
                if(!visited[edge.dest]){
                    parent[edge.dest] = curr;
                    getBridgeUtil(graph,edge.dest,disc,low,parent,visited,time,v);
                    low[curr] = Math.min(low[curr],low[edge.dest]);
                    if(low[edge.dest]>disc[curr]){
                        System.out.println(curr+" "+edge.dest);
                    }
                }else if(edge.dest!=parent[curr]){
                    low[curr] = Math.min(low[curr],disc[edge.dest]);
                }
            }
        }

        // tarjan's algorithm (strongly connected components in graph, kosaraju's algorithm is used for finding strongly connected components)
    // ancestor of a node is the node which is visited before the current node
    // low[u] = min(disc[u],low[v]) where v is the ancestor of u
    // O(V+E)
    public static void tarjan(ArrayList<Edge>[] graph, int v){
        int[] disc = new int[v];
        int[] low = new int[v];
        boolean[] visited = new boolean[v];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < v; i++) {
            if(!visited[i]){
                tarjanUtil(graph,i,disc,low,visited,stack);
            }
        }
    }

    public static void tarjanUtil(ArrayList<Edge>[] graph, int curr, int[] disc, int[] low, boolean[] visited, Stack<Integer> stack){
        visited[curr] = true;
        disc[curr] = low[curr] = curr;
        stack.push(curr);
        for (Edge edge : graph[curr]) {
            if(!visited[edge.dest]){
                tarjanUtil(graph,edge.dest,disc,low,visited,stack);
                low[curr] = Math.min(low[curr],low[edge.dest]);
            }else if(stack.contains(edge.dest)){
                low[curr] = Math.min(low[curr],disc[edge.dest]);
            }
        }
        if(disc[curr]==low[curr]){
            while (stack.peek()!=curr){
                System.out.println(stack.pop());
            }
            System.out.println(stack.pop());
        }
    }


    public static void main(String[] args){
        int vertices = 4;
        ArrayList<Edge>[] graph = new ArrayList[vertices];
        createGraph(graph);

        // print 2's neighbours
        for (Edge edge : graph[2]) {
            System.out.println(edge.src + " " + edge.dest+ " " + edge.weight);
        }
        boolean[] visited = new boolean[vertices];
//
//        for (int i = 0; i < vertices; i++) {
//            if(!visited[i]){
//                bfs(graph,vertices,visited,i);
//            }
//        }
        dfs(graph,0,visited);
        System.out.println("----------------");
        allPaths(graph,0,1,visited,"0");
        System.out.println("----------------");

        boolean[] visited1 = new boolean[vertices];
        boolean[] recStack = new boolean[vertices];
        for (int i = 0; i < vertices; i++) {
            if (!visited1[i]){
                System.out.println(isCycle(graph,0,visited1,recStack));
                if (isCycle(graph,0,visited1,recStack)){
                    System.out.println("Cycle is present");
                    break;
                }
            }
        }

        System.out.println("----------------");
        topSort(graph,vertices);
        System.out.println("----------------");
        System.out.println(isCycleDirected(graph,0,visited1,-1));
        System.out.println("----------------");
//        shortestPath(graph,0);
        System.out.println("----------------");
        bellmanFord(graph,0,vertices);
        System.out.println("----------------prime");
        prims(graph,0,vertices);

    }
}
