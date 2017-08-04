package com.paulgeorge.prim;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;

public class Vertex {
	private boolean visited;
	private int index;
	private double x;
	private double y;
	private double z;
	//private Map<Vertex, Double> neighbors;
	
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

	public Vertex findClosestUnvisitedVertex(Map<Integer, Vertex> vertices) {
		Set<Integer> keys = vertices.keySet();
		double shortestDistance = Double.MAX_VALUE;
		Vertex closest = null;
		for ( Integer i : keys ) {
			Vertex v = vertices.get(i);
			if ( !v.hasBeenVisited() && this.findDistanceFrom(v) < shortestDistance ) {
				closest = v;
			}
		}
		return closest;
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
