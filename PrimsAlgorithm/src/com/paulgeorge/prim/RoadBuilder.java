package com.paulgeorge.prim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RoadBuilder {

	int LENGTH = 100000; // this is the x dimension of the play field
	int WIDTH = 100000;  // this is y dimension of the play field
	int HEIGHT = 400;   // this is z dimension of the play field
	int NUMBER_OF_VERTICES = 20;
	
	//This is a map to maintain order easily.
	Map<Integer, Vertex> vertices;
	List<Edge> edges;
	
	public static void main(String[] args) {
		//Make up a bunch of vertices
		RoadBuilder rb = new RoadBuilder();
		rb.initNodes();
		rb.buildRoads();
		System.out.println("\n\n");
		rb.printVertices();
		rb.printEdges();
	}
	
	private void buildRoads() {
		//Start with vertices(0) as root
		ArrayList<Vertex> visited = new ArrayList<>();

		Vertex curr = vertices.get(0);

		curr.setVisited(true);
		visited.add(curr);
		
		int counter = 0;
		while ( anyNotVisited() ) {
			//System.out.println("There are " + countUnvisited() + " unvisited nodes");
			Vertex[] link = findClosestNeighbor(visited);
			Vertex parent = link[0];
			Vertex target = link[1];
			if ( counter == 0 ) {
				System.out.println("Closest node to 0 is " + target.getIndex());
			}
			counter++;
			
			//System.out.println("Linking nodes: " + parent.getIndex() + " with " + target.getIndex());
			visited.add(target);
			target.setVisited(true);
			vertices.get(target.getIndex()).setVisited(true);
			
			Edge e = new Edge(parent.getIndex(), target.getIndex());
			edges.add(e);
		}
	}

	private boolean anyNotVisited() {
		Set<Integer> keys = vertices.keySet();
		for ( Integer key : keys ) {
			if ( !vertices.get(key).hasBeenVisited()) {
				return true;
			}
		}
		return false;
	}


	private Vertex[] findClosestNeighbor( ArrayList<Vertex> visited) {
		Vertex closestParent = null;
		Vertex closestTarget = null;
		double shortestDistance = Double.MAX_VALUE;
		
		for ( Vertex v : visited) {
			Vertex closestNeighbor = v.findClosestUnvisitedVertex(vertices);
			double distance = v.findDistanceFrom(closestNeighbor);
			if ( distance < shortestDistance ) {
				closestParent = v;
				closestTarget = closestNeighbor;
				shortestDistance = distance;
			}
		}
		
		return new Vertex[] {closestParent, closestTarget};
	}
	
	
	/***************************************************************
	 * x,y grid will be LENGTH X WIDTH with a variable height from 0 to HEIGHT
	 * 
	 ****************************************************************/
	private void initNodes() {
		vertices = new HashMap<>();
		//minimalTree = new HashMap<>();
		edges = new ArrayList<>();
		
		//randomly create the predetermined number of vertices
		for ( int x = 0 ; x < NUMBER_OF_VERTICES; x++ ) {
			Vertex v = new Vertex(x, Math.random()*LENGTH, Math.random()*WIDTH, Math.random()*HEIGHT);
			vertices.put(x,v);
		}
	}
	

	private void printEdges() {
		for ( Edge e : edges) {
			System.out.println(e.toString());
		}
	}

	private void printVertices() {
		Set<Integer> keys = vertices.keySet();
		for ( Integer key : keys) {
			Vertex v = vertices.get(key);
			System.out.println(v.toString());
		}
	}

	
	private int countUnvisited() {
		Set<Integer> keys = vertices.keySet();
		int count = 0;
		for ( Integer key : keys ) {
			if ( !vertices.get(key).hasBeenVisited()) {
				count++;
			}
		}
		return count;
		
	}

}
