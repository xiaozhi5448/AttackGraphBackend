
import matplotlib as mpl
import matplotlib.pyplot as plt
import networkx as nx





if __name__ == '__main__':
    G = nx.DiGraph()

    # topology construction logic
    G.add_node('s1', desc='other', size=1000)
    G.add_node('s2', desc='host2')
    G.add_node('s3', desc='host3')
    G.add_edge('s1', 's2', name='redis')
    G.add_edge('s1', 's3', name='ssh weak password')
    G.add_edge('s2', 's3', name='ssh weak password')

    # draw graph with labels
    pos = nx.spring_layout(G)

    node_labels = nx.get_node_attributes(G, 'desc')
    print(node_labels)
    node_labels = {
        's1':'host1, user1',
        's2': 'host2, user2',
        's3': 'host3, user3'
    }
    nx.draw_networkx_labels(G, pos, labels=node_labels)
    # edge_labels = nx.get_edge_attributes(G, 'name')
    edge_labels = {
        ('s1', 's2'): "threat 1",
        ('s1', 's3'): "threat 2",
        ('s2', 's3'): "threat 3"
    }
    print(edge_labels)
    nx.draw_networkx_edge_labels(G, pos, labels=edge_labels)
    nx.draw(G, pos)
    plt.show()