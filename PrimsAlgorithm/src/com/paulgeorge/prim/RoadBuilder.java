package com.paulgeorge.prim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RoadBuilder {

	int LENGTH = 100; // this is the x dimension of the play field
	int WIDTH = 100;  // this is y dimension of the play field
	int HEIGHT = 4;   // this is z dimension of the play field
	int NUMBER_OF_VERTICES = 10;
	
	//This is a map to maintain order easily.
	List<Vertex> vertices;
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
		vertices.get(0).setVisited(true);
		
		//Keep going as long as there are unvisited nodes
		while ( anyNotVisited() ) {
			Edge e = findShortestEdge();
			vertices.get(e.getTarget().getIndex()).setVisited(true);
			edges.add(e);
		}
	}

	/************************************************************************
	 * 
	 * @return
	 ************************************************************************/
	private boolean anyNotVisited() {
		for ( Vertex v : vertices ) {
			if ( !v.hasBeenVisited()) {
				return true;
			}
		}
		return false;
	}

	/***************************************************************************
	 * 
	 * @param visited
	 * @return
	 ***************************************************************************/
	private Edge findShortestEdge() {
		Vertex closestParent = null;
		Vertex closestTarget = null;
		double shortestDistance = Double.MAX_VALUE;
		
		for ( Vertex v : vertices) {
			//Only look at nodes that have been visited.
			if ( !v.hasBeenVisited()) continue;
			//Find the shortest path to any unvisited nodes
			Edge edge = v.findShortestUnvisitedEdge(vertices);
			if ( edge.getDistance() < shortestDistance ) {
				closestParent = v;
				closestTarget = edge.getTarget();
				shortestDistance = edge.getDistance();
			}
		}
		System.out.println("\n  we chose: " + closestParent.getIndex() + " , " +  closestTarget.getIndex() + "  distance: " + shortestDistance);

		return new Edge(closestParent, closestTarget, shortestDistance);
	}
	
	
	/***************************************************************
	 * x,y grid will be LENGTH X WIDTH with a variable height from 0 to HEIGHT
	 * 
	 ****************************************************************/
	private void initNodes() {
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
		
		//randomly create the predetermined number of vertices
		for ( int x = 0 ; x < NUMBER_OF_VERTICES; x++ ) {
			Vertex v = new Vertex(x, Math.random()*LENGTH, Math.random()*WIDTH, Math.random()*HEIGHT);
			vertices.add(v);
		}
	}
	
	/************************************************************
	 * 
	 ************************************************************/
	private void printEdges() {
		for ( Edge e : edges) {
			System.out.println(e.toString());
		}
	}

	/************************************************************
	 * 
	 ************************************************************/
	private void printVertices() {
		for ( Vertex v : vertices) {
			System.out.println(v.toString());
		}
	}
}
