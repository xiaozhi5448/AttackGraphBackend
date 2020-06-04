package application.graph;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Graph {
    @Getter
    HashSet<Edge> edges;
    @Getter
    HashSet<PrivilegeNode> nodes;

    public Graph(){
        this.edges = new HashSet<>();
        this.nodes = new HashSet<>();
    }

    public PrivilegeNode findNodeById(int id){
        for(PrivilegeNode node: this.nodes){
            if(node.getId() == id){
                return node;
            }
        }
        return null;
    }

    public void clear(){
        this.edges.clear();
        this.nodes.clear();
    }

    @Override
    public String toString() {
        return "Graph{" +
                "edges=" + edges +
                ", nodes=" + nodes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return Objects.equals(edges, graph.edges) &&
                Objects.equals(nodes, graph.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(edges, nodes);
    }
}
