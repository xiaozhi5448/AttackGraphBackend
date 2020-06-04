package application.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Edge {
    @Getter
    @Setter
    int src;
    @Getter
    @Setter
    int dst;
    @Getter
    @Setter
    String threatType;


    @Override
    public String toString() {
        return "Edge{" +
                "src=" + src +
                ", dst=" + dst +
                ", threadType='" + threatType + '\'' +
                '}';
    }
}
