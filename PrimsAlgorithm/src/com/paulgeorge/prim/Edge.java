package com.paulgeorge.prim;

public class Edge {
	int x;
	int y;
	
	public Edge(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public String toString() {
		return x + "," + y;
	}
}
