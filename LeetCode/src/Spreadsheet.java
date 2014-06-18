import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Spreadsheet {
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		int nCol = input.nextInt();
		int nRow = input.nextInt();
		input.nextLine();
		Expr array[][] = new Expr[nRow][nCol];
		Map<Pair, Set<Pair>> edgeSet = new HashMap<Pair, Set<Pair>>();
		for (int i = 0; i < nRow && input.hasNextLine(); ++i) {
			for (int j = 0; j < nCol && input.hasNextLine(); ++j) {
				final String curEl = input.nextLine().trim();
				if (curEl.isEmpty()) {
					continue;
				}
				if (curEl.split(" ").length == 1) {
					if (curEl.charAt(0) >= 'A' && curEl.charAt(0) <= 'Z') {
						array[i][j] = new VarExpr(curEl, i, j, edgeSet);
					} else {
						array[i][j] = new ConstantExpr(curEl, i, j);
					}
				} else {
					array[i][j] = new PostFixExpr(curEl, i, j, edgeSet);
				}
			}
		}
		input.close();
		Double result[][] = new Double[nRow][nCol];
		DirectedGraph<Expr> graph = new DirectedGraph<Expr>();
		for (Entry<Pair, Set<Pair>> el : edgeSet.entrySet()) {
			if (el.getValue() != null) {
				for (Pair dest : el.getValue()) {
					graph.addEdge(array[el.getKey().x][el.getKey().y],
							array[dest.x][dest.y]);
				}
			}
		}
		List<Expr> topologicallySorted = graph.topologicalSort();
		if (edgeSet.size() != 0 && topologicallySorted == null) {
			System.out.println("Cycle found exiting.");
			return;
		}
		for (Expr expr : topologicallySorted) {
			expr.execute(result);
		}

		System.out.println(nCol + " " + nRow);
		for (int i = 0; i < nRow; ++i) {
			for (int j = 0; j < nCol; ++j) {
				if (result[i][j] == null) {
					result[i][j] = array[i][j].execute(result);
				}
				System.out.println(String.format("%.5f", result[i][j]));
			}
		}
	}
}

abstract class Token {
	protected final String lexem;
	protected final int row;
	protected final int col;

	Token(final String lexem, final int row, final int col) {
		this.lexem = lexem;
		this.row = row;
		this.col = col;
	}
}

abstract class Expr extends Token {

	protected double value;

	Expr(String lexem, int row, int col) {
		super(lexem, row, col);
	}

	abstract double execute(Double[][] mappings);

	protected void updateEdgeSet(Pair src, Pair dest,
			Map<Pair, Set<Pair>> edgeSet) {
		Set<Pair> list = edgeSet.get(src);
		if (list == null) {
			list = new HashSet<Pair>();
			edgeSet.put(src, list);
		}
		list.add(dest);
	}

	@Override
	public int hashCode() {
		return (17 + 31 * row) * 31 + col;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof Expr) {
			Expr e = (Expr) obj;
			return e.row == this.row && e.col == this.col;
		}
		return false;
	}

	@Override
	public String toString() {
		return " i " + row + " ,j " + col + " " + " value " + value;
	}
}

class ConstantExpr extends Expr {

	public ConstantExpr(String lexem, int row, int col) {
		super(lexem, row, col);
		try {
			this.value = Double.parseDouble(lexem);
		} catch (Exception e) {
			System.out.println("Can't parse " + lexem + " as double");
		}
	}

	@Override
	double execute(Double[][] mappings) {
		mappings[row][col] = this.value;
		return this.value;
	}

}

class VarExpr extends Expr {

	private int srcRow = -1;
	private int srcCol = -1;

	public VarExpr(String lexem, int row, int col, Map<Pair, Set<Pair>> edgeSet) {
		super(lexem, row, col);
		Pair aPair = PostFixExpr.parse(lexem);
		this.srcRow = aPair.x;
		this.srcCol = aPair.y;
		updateEdgeSet(aPair, new Pair(row, col), edgeSet);
	}

	@Override
	double execute(Double[][] mappings) {
		value = mappings[row][col] = mappings[srcRow][srcCol];
		return mappings[row][col];
	}
}

class PostFixExpr extends Expr {
	private List<Object> parsedExpr = new ArrayList<Object>();
	private static Set<String> operators;

	public PostFixExpr(String var, int row, int col,
			Map<Pair, Set<Pair>> edgeSet) {
		super(var, row, col);
		if (operators == null) {
			operators = new HashSet<String>();
			operators.add("+");
			operators.add("-");
			operators.add("*");
			operators.add("/");
		}
		String strTokens[] = var.split(" ");
		for (String strToken : strTokens) {
			strToken = strToken.trim();
			if (!operators.contains(strToken)) {
				if (strToken.charAt(0) >= 'A' && strToken.charAt(0) <= 'Z') {
					Pair aPair = parse(strToken);
					updateEdgeSet(aPair, new Pair(row, col), edgeSet);
					parsedExpr.add(aPair);
				} else {
					parsedExpr.add(Double.parseDouble(strToken));
				}
			} else {
				parsedExpr.add(strToken);
			}
		}
	}

	public static Pair parse(final String str) {
		int row = str.charAt(0) - 'A';
		int col = Integer.parseInt(str.substring(1)) - 1;
		return new Pair(row, col);
	}

	@Override
	double execute(Double[][] mappings) {
		Stack<Double> result = new Stack<Double>();
		for (Object el : parsedExpr) {
			if (el instanceof Pair) {
				Pair p = (Pair) el;
				result.push(mappings[p.x][p.y]);
				continue;
			}
			if (el instanceof String) {
				Double right = result.pop();
				Double left = result.pop();
				result.push(applyOperation((String) el, left, right));
				continue;
			}
			result.push((Double) el);
		}
		value = mappings[row][col] = result.pop();
		return mappings[row][col];
	}

	private Double applyOperation(String el, Double left, Double right) {
		if (el.equals("+")) {
			return left + right;
		}
		if (el.equals("-")) {
			return left - right;
		}
		if (el.equals("*")) {
			return left * right;
		}
		if (el.equals("/")) {
			return left / right;
		}
		throw new RuntimeException("Opertation " + el + " not supported");
	}
}

class Pair {
	int x;
	int y;

	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o instanceof Pair) {
			Pair p = (Pair) o;
			return p.x == this.x && p.y == this.y;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + x;
		return result * 31 + y;
	}
}

class DirectedGraph<T> {

	private Map<T, List<T>> adjList;

	DirectedGraph() {
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

	public List<T> getUnivSource() {
		List<T> univSources = new ArrayList<T>();
		univSources.addAll(adjList.keySet());
		for (T key : adjList.keySet()) {
			univSources.removeAll(adjList.get(key));
		}
		return univSources;
	}

	public List<T> topologicalSort() {
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
		if (totalInDegree != 0) {
			return null;
		}
		return topSorted;
	}
}
