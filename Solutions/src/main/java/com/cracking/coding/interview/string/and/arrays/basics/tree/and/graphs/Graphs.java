package com.cracking.coding.interview.string.and.arrays.basics.tree.and.graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Graphs {
    public static class Graph{
        private int V;
        private LinkedList<Integer> adj[];
        public Graph(int v){
            V = v;
            adj = new LinkedList[v];
            for(int i=0; i<v; i++){
                adj[i] = new LinkedList<>();
            }
        }
        public void addEdge(int source, int destination){
            adj[source].add(destination);
        }

        public int bfs(int source, int destination){
            Queue<Integer> q = new LinkedList<>();
            boolean visited[] = new boolean[adj.length];
            int parent[] = new int[adj.length];
            q.add(source);
            parent[source] = -1;
            visited[source] = true;
            while(!q.isEmpty()){
                int curr = q.poll();
                if(curr == destination){
                    break;
                }
                for(int neighbour: adj[curr]){
                    if(!visited[neighbour]){
                        visited[neighbour] = true;
                        q.add(neighbour);
                        parent[neighbour] = curr;
                    }
                }
            }
            int curr = destination;
            int distance = 0;
            String msg = String.valueOf(curr);
            while(parent[curr] != -1){
                curr = parent[curr];
                distance++;
                msg += " -> " + curr;
            }
            System.out.println("Distance from " + msg + " : " + distance);
            return distance;
        }

        /**
         * DFS using recursion
         * @param source
         * @param destination
         * @return
         */
        public boolean dfs(int source, int destination){
            boolean visited[] = new boolean[adj.length];
            Stack<Integer> stack = new Stack<>();
            stack.add(source);
            visited[source] = true;
            if(!stack.isEmpty()){
                int curr = stack.pop();
                if(curr == destination){
                    return true;
                }
                for(int neighbours : adj[source]){
                    if(!visited[neighbours]){
                        visited[neighbours]  = true;
                        stack.push(neighbours);
                    }
                }
            }
            return false;
        }

        public boolean dfsUtil(int source, int destination, boolean visited[]){
            if(source ==  destination) return true;
            for(int neighbours: adj[source]){
                if(!visited[neighbours]){
                    visited[neighbours] = true;
                    boolean isConnected = dfsUtil(neighbours, destination, visited);
                    if(!isConnected) {
                        return false;
                    }
                }
            }
            return true;
        }

        public boolean dfsRecursion(int source, int destination){
            boolean visited[] = new boolean[adj.length];
            visited[source] = true;
            return dfsUtil(source, destination, visited);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices");
        int v = sc.nextInt();
        System.out.println("Enter number of edges");
        int e = sc.nextInt();
        Graph graph = new Graph(v);
        System.out.println("Enter the edges: <to> <from>");
        for(int i=0; i<e; i++){
            int source = sc.nextInt();
            int destination = sc.nextInt();
            graph.addEdge(source, destination);
        }
        sc.close();
        System.out.println(graph.dfsRecursion(0,3));
    }
}
