package Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

public class Graph<T> {

	private Map<T, List<T>> adjList;

	Graph() {
		adjList = new HashMap<T, List<T>>();
	}

	public boolean addEdge(T src, T dest) {
		List<T> value = adjList.get(src);
		if (value != null) {
			if (value.contains(dest)) {
				return false;
			}
			value.add(dest);
			return true;
		}
		value = new ArrayList<T>();
		value.add(dest);
		adjList.put(src, value);
		return true;
	}

	public List<T> topologicalSort() {
		currentFinishTime = 1;
		Map<T, Integer> nodeToFinishTime = new HashMap<T, Integer>();
		Set<T> visited = new HashSet<T>();
		for (T root : getUnivSource()) {
			helper(root, nodeToFinishTime, visited);
		}
		List<T> topoOrder = new ArrayList<T>();
		if (isCycle(nodeToFinishTime)) {
			return null;
		}
		for (Entry<T, Integer> entry : sortMap(nodeToFinishTime)) {
			topoOrder.add(entry.getKey());
		}
		return topoOrder;
	}

	public List<T> topoSort() {
		Map<T, Integer> nodeToIndegree = new HashMap<T, Integer>();
		int totalInDegree = 0;
		for (Entry<T, List<T>> entry : adjList.entrySet()) {
			List<T> list = entry.getValue();
			if (list != null) {
				for (T node : list) {
					Integer inDegree = nodeToIndegree.get(node);
					if (inDegree == null) {
						inDegree = new Integer(1);
					} else {
						++inDegree;
					}
					nodeToIndegree.put(node, inDegree);
				}
				totalInDegree += list.size();
			}
		}
		// System.out.println("1 " + totalInDegree);
		List<T> topSorted = new ArrayList<T>();
		List<T> univSrcs = new LinkedList<T>(getUnivSource());
		while (!univSrcs.isEmpty()) {
			T node = univSrcs.remove(0);
			topSorted.add(node);
			List<T> outNodes = adjList.get(node);
			if (outNodes != null) {
				for (T anOutNode : outNodes) {
					Integer inDegree = nodeToIndegree.get(anOutNode);
					if (inDegree == 1) {
						nodeToIndegree.put(node, 0);
						univSrcs.add(anOutNode);
					} else {
						nodeToIndegree.put(anOutNode, --inDegree);
					}
				}
				totalInDegree -= outNodes.size();
				adjList.put(node, null);
			}
		}
		// System.out.println("us " + topSorted);
		// System.out.println("1 " + totalInDegree);
		if (totalInDegree != 0) {
			return null;
		}
		return topSorted;

	}

	private boolean isCycle(Map<T, Integer> nodeToFinishTime) {
		for (Entry<T, List<T>> entry : adjList.entrySet()) {
			List<T> list = entry.getValue();
			if (list != null) {
				for (T node : list) {
					int srcTime = nodeToFinishTime.get(entry.getKey());
					int destTime = nodeToFinishTime.get(node);
					if (destTime >= srcTime) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private void helperItr(T root, List<T> dfsOrder, Set<T> visited) {
		if (root == null) {
			return;
		}
		Stack<T> stack = new Stack<T>();
		stack.push(root);
		while (!stack.isEmpty()) {
			root = stack.pop();
			visited.add(root);
			dfsOrder.add(root);
			if (adjList.get(root) != null) {
				for (T adj : adjList.get(root)) {
					if (!visited.contains(adj)) {
						stack.push(adj);
						// visited.add(adj);
						// helper(adj, nodeToFinishTime, visited);
					}
				}
			}
		}
	}

	private List<Entry<T, Integer>> sortMap(Map<T, Integer> nodeToFinishTime) {
		List<Entry<T, Integer>> entries = new ArrayList<Entry<T, Integer>>(
				nodeToFinishTime.entrySet());
		Collections.sort(entries, new Comparator<Entry<T, Integer>>() {
			public int compare(Entry<T, Integer> o1, Entry<T, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		return entries;
	}

	private int currentFinishTime = 1;

	public List<T> getUnivSource() {
		List<T> univSources = new ArrayList<T>();
		univSources.addAll(adjList.keySet());
		for (T key : adjList.keySet()) {
			univSources.removeAll(adjList.get(key));
		}
		return univSources;
	}

	private void helper(T root, Map<T, Integer> nodeToFinishTime, Set<T> visited) {
		if (root == null) {
			return;
		}
		visited.add(root);
		if (adjList.get(root) != null) {
			for (T adj : adjList.get(root)) {
				if (!visited.contains(adj)) {
					helper(adj, nodeToFinishTime, visited);
				}
			}
		}
		nodeToFinishTime.put(root, currentFinishTime++);
	}

	public static void main(String[] args) {
		Graph<Integer> graph = createGraph1();
		System.out.println(graph.getUnivSource());
		System.out.println(graph.topologicalSort());
		System.out.println(graph.topoSort());
	}

	static Graph<Integer> createGraph1() {
		Graph<Integer> graph = new Graph<Integer>();
		graph.addEdge(1, 2);
		graph.addEdge(1, 4);
		graph.addEdge(2, 3);
		graph.addEdge(4, 3);
		graph.addEdge(6, 4);
		graph.addEdge(6, 7);
		graph.addEdge(4, 5);
		return graph;
	}

	// cycle
	static Graph<Integer> createGraph2() {
		Graph<Integer> graph = new Graph<Integer>();
		graph.addEdge(1, 2);
		graph.addEdge(4, 1);
		graph.addEdge(2, 3);
		// graph.addEdge(3, 1);
		graph.addEdge(3, 4);
		graph.addEdge(6, 4);
		graph.addEdge(6, 7);
		graph.addEdge(4, 5);
		graph.addEdge(5, 7);
		return graph;
	}

	// self-loop
	static Graph<Integer> createGraph3() {
		Graph<Integer> graph = new Graph<Integer>();
		graph.addEdge(1, 2);
		graph.addEdge(1, 4);
		graph.addEdge(2, 3);
		graph.addEdge(4, 3);
		graph.addEdge(4, 4);
		graph.addEdge(6, 4);
		graph.addEdge(6, 7);
		graph.addEdge(4, 5);
		return graph;
	}

}
