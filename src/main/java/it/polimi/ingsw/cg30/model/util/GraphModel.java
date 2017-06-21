package it.polimi.ingsw.cg30.model.util;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polimi.ingsw.cg30.model.City;

public class GraphModel {
	/**
	 * the cities of the graph
	 */
	private List<City> cities;
	/**
	 * the edges of the graph
	 */
	private List<Edge> edges;
	/**
	 * the graph
	 */
	private SimpleGraph<City, DefaultEdge> graph;

	/**
	 * 
	 * @param cities
	 *            are the vertices of the graph to build
	 * @param edges
	 *            of the graph
	 * 
	 *            the constructor creates a graph with the method buildGraph
	 * 
	 * @throws NullPointerException
	 *             if the cities or edges are null
	 */
	public GraphModel(List<City> cities, List<Edge> edges)  {

		if (cities == null || edges == null)
			throw new NullPointerException();


		graph = new SimpleGraph<>(DefaultEdge.class);
		
		this.cities = cities;
		this.edges = edges;
		
		buildGraph(cities, edges, graph);
	}

	/**
	 * 
	 * the method builds a graph with these parameters
	 * 
	 * @param cities
	 *            are the vertices of the graph
	 * @param edges
	 *            of the graph
	 * @param graph
	 *            non initialized yet
	 * 
	 * @throws NullPointerException
	 *             if the cities, edges or graph are null
	 * @return the new graph
	 */
	public SimpleGraph<City, DefaultEdge> buildGraph(List<City> cities, List<Edge> edges,
			SimpleGraph<City, DefaultEdge> graph)  {

		if (cities == null || edges == null || graph == null)
			throw new NullPointerException();

		SimpleGraph<City, DefaultEdge> newGraph = graph;

		Graphs.addAllVertices(newGraph, cities);
		setEdges(newGraph, edges);

		return newGraph;
	}

	/**
	 * the method sets the edges in a generic graph
	 * 
	 * @param graph
	 *            to be initialized
	 * @param edges
	 *            to set
	 * @throws NullPointerException if the graph or edges are null
	 */
	public void setEdges(Graph<City, DefaultEdge> graph, List<Edge> edges) {

		if (edges == null || graph == null)
			throw new NullPointerException();
		
		this.edges = edges;

		for (Edge i : edges) {

			if (graph.containsVertex(i.getCity()) && graph.containsVertex(i.getAdjacentCity()))
				graph.addEdge(i.getCity(), i.getAdjacentCity());
		}
	}

	/**
	 * 
	 * @param initCity
	 *            the city which king starts
	 * @param finalCity
	 *            the city which king wants to reach
	 * @return the money to pay to move the king
	 * @throws NullPointerException if the initial city or the final city are null
	 */
	public int getMoneyToPay(City initCity, City finalCity)  {

		if(initCity == null || finalCity == null)
			throw new NullPointerException();
		
		DijkstraShortestPath<City, DefaultEdge> algoritm = new DijkstraShortestPath<>(this.graph, initCity, finalCity);

		return (int) algoritm.getPathLength();
	}

	/**
	 * 
	 * @param graph
	 *            to analyze
	 * @param sourceCity
	 *            city where to start
	 * @return all the reachable vertices from the source city
	 * @throws NullPointerException if the graph or the city are null
	 */
	public List<City> getAllReachableCities(SimpleGraph<City, DefaultEdge> graph, City sourceCity) {
		
		if (sourceCity == null || graph == null)
			throw new NullPointerException();

		BreadthFirstIterator<City, DefaultEdge> visit = new BreadthFirstIterator<>(graph, sourceCity);

		ArrayList<City> adjacents = new ArrayList<>();

		while (visit.hasNext()) {

			City c = visit.next();
			adjacents.add(c);
		}
		return adjacents;
	}

	/**
	 * 
	 * @param color
	 *            of the player
	 * @param allCities
	 *            of the graph
	 * @return all cities with an emporium of this color
	 * @throws NullPointerException if the cities or color are null
	 */
	public List<City> getAllEmporiumCities(String color, List<City> allCities)  {

		if (color == null || allCities == null)
			throw new NullPointerException();
		
		ArrayList<City> emporiumCities = new ArrayList<>();

		for (City c : allCities) {

			for (int i = 0; i < c.getEmporiumsSpace().size(); i++) {

				if (c.getEmporiumsSpace().get(i).getColor() == color)
					emporiumCities.add(c);
			}
		}
		return emporiumCities;
	}
	/**
	 * @return the graph
	 */
	public Graph<City, DefaultEdge> getGraph() {
		return graph;
	}

	/**
	 * @return the cities
	 */
	public List<City> getCities() {
		return cities;
	}

	/**
	 * @return the edges
	 */
	public List<Edge> getEdges() {
		return edges;
	}
}