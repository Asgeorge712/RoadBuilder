package com.paulgeorge.prim;

/******************************************
 * 
 * @author Paul
 *
 ******************************************/
public class Edge {
	Vertex parent;
	Vertex target;
	double distance;
	
	public Edge(Vertex x, Vertex y, double d) {
		this.parent = x;
		this.target = y;
		this.distance = d;
	}
	
	public Vertex getParent() {
		return parent;
	}
	
	public Vertex getTarget() {
		return target;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public String toString() {
		return parent.getIndex() + "," + target.getIndex(); // + " --- " + this.distance;
	}
}
