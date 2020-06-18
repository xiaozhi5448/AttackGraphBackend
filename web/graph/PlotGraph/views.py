from django.shortcuts import render

# Create your views here.
import logging
import json
import networkx as nx
import matplotlib.pyplot as plt
from django.conf import settings
import os
from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt
logging.basicConfig(level=logging.INFO,
                    format='%(asctime)s  %(filename)s %(lineno)d: %(levelname)s  %(message)s')

plt.rcParams['font.sans-serif']=['SimHei']
plt.rcParams['axes.facecolor'] = 'xkcd:mint'
@csrf_exempt
def plot_attack_graph(request):
    if request.method == 'POST':
        plt.figure(figsize=(10, 10))
        logging.debug('get request with body: {}'.format(request.body.decode('utf-8')))
        logging.info(type(request.body))
        graph_data = json.loads(request.body, encoding='utf8')
        nodes = [{
            'nodeId': privilege_node['id'],
            'nodeName': '{}\n{}\n{}'.format(privilege_node['deviceName'],
                                          privilege_node['serviceName'],
                                          privilege_node['privilege'].split('-')[-1])
        } for privilege_node in graph_data['nodes']]
        logging.info(nodes)
        node_size = [1000 for _ in range(len(nodes))]
        graph = nx.DiGraph()
        for node in nodes:
            graph.add_node(node['nodeId'], name=node['nodeName'])

        for edge in graph_data['edges']:
            graph.add_edge(edge['src'], edge['dst'], name=edge['threatType'])

        pos = nx.spring_layout(graph)
        node_labels = {node['nodeId']: node['nodeName']  for node in nodes}
        edge_labels = {
            (edge['src'], edge['dst']): edge['threatType']
            for edge in graph_data['edges']
        }
        nx.draw_networkx_labels(graph, pos, node_labels)
        nx.draw_networkx_edge_labels(graph, pos, edge_labels)
        nx.draw_networkx(graph, pos, arrowstyle='->', arrawsize=80, node_size=node_size,
                         width=3,
                         with_labels=False,
                         font_size=24,
                         font_color='n',
                         node_color="yellow")
        ax = plt.gca()
        ax.spines['top'].set_visible(False)
        ax.spines['right'].set_visible(False)
        ax.spines['bottom'].set_visible(False)
        ax.spines['left'].set_visible(False)
        # ax.set_facecolor()
        output_filepath = os.path.join('static/img/', 'graph.png')
        plt.savefig(output_filepath)
        res = {
            'figPath': output_filepath
        }
        return HttpResponse(json.dumps(res, ensure_ascii=False), content_type="application/json;charset=utf8")
