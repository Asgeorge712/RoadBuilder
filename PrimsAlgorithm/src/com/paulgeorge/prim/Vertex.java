package com.paulgeorge.prim;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

public class Vertex {
	private boolean visited;
	private int index;
	private double x;
	private double y;
	private double z;
	
	public Vertex(int i, double x, double y, double z) {
		this.index = i;
		this.x = x;
		this.y = y;
		this.z = z;
		this.visited = false;
	}

	public int getIndex() {
		return index;
	}

	public double findDistanceFrom(Vertex other) {
		double dist = Math.sqrt(Math.pow(this.x - other.getX(),2) + Math.pow(this.y-other.getY(),2) + Math.pow(this.z - other.getZ(),2));
		return dist;
	}

	public Edge findShortestUnvisitedEdge(List<Vertex> vertices) {
		double shortestDistance = Double.MAX_VALUE;
		Vertex closest = null;
		for ( Vertex v : vertices ) {
			//Only look at unvisited nodes
			if ( v.hasBeenVisited()) continue;
			
			double thisDistance = this.findDistanceFrom(v);
			if ( thisDistance < shortestDistance ) {
				shortestDistance = thisDistance;
				closest = v;
			}
		}
		System.out.println("For " + this.getIndex() + " - found closest: " + closest.getIndex() + "  - distance is: " + shortestDistance);
		return new Edge(this, closest, shortestDistance);
	}
	
	public void setVisited(boolean v) {
		this.visited = v;
	}
	
	public boolean hasBeenVisited() {
		return visited;
	}
	
	
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		return df.format(x) + "," + df.format(y) + "," + df.format(z);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getZ() {
		return z;
	}
}
