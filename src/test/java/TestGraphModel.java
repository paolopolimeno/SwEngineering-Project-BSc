import static org.junit.Assert.*;



import java.util.List;
import java.util.ArrayList;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.City;
import it.polimi.ingsw.cg30.model.Emporium;
import it.polimi.ingsw.cg30.model.util.Edge;
import it.polimi.ingsw.cg30.model.util.GraphModel;

public class TestGraphModel {

	
	Edge e1,e2,e3,e4,e5,e6,e7,e8,e9,e10;
	City c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11;
	GraphModel graph;
	ArrayList<City> cities;
	ArrayList<Edge> edges;
	
	@Before
	public void setUp(){
		
		cities=new ArrayList<>();
		edges=new ArrayList<>();

		c1=new City();
		c2=new City();
		c3=new City();
		c4=new City();
		c5=new City();
		c6=new City();
		c7=new City();
		c8=new City();
		c9=new City();
		c10=new City();
		c11=new City();
		
		cities.add(c1);
		cities.add(c2);
		cities.add(c3);
		cities.add(c4);
		cities.add(c5);
		cities.add(c6);
		cities.add(c7);
		cities.add(c8);
		cities.add(c9);
		cities.add(c10);
		cities.add(c11);
		
		e1=new Edge(c1,c2);
		e2=new Edge(c1,c3);
		
		e3=new Edge(c2,c10);
		e4=new Edge(c3,c4);
		e5=new Edge(c3,c5);
		
		e6=new Edge(c4,c8);
		e7=new Edge(c4,c9);
		
		e8=new Edge(c5,c6);
		e9=new Edge(c5,c7);
		
		edges.add(e1);
		edges.add(e2);
		edges.add(e3);
		edges.add(e4);
		edges.add(e5);
		edges.add(e6);
		edges.add(e7);
		edges.add(e8);
		edges.add(e9);
		
		graph= new GraphModel(cities,edges);
		c1.getEmporiumsSpace().add(new Emporium("black",1));
		c3.getEmporiumsSpace().add(new Emporium("black",1));
		c4.getEmporiumsSpace().add(new Emporium("black",1));
		c6.getEmporiumsSpace().add(new Emporium("black",1));
		c8.getEmporiumsSpace().add(new Emporium("black",1));
			
		
	}
	
	@Test
	
	public void testGetAllEmporiumCities(){
		
		List<City> citiesWithEmporium = graph.getAllEmporiumCities("black",cities);
		assertTrue(citiesWithEmporium.contains(c1)&&citiesWithEmporium.contains(c8)&&citiesWithEmporium.contains(c3) &&citiesWithEmporium.contains(c4) &&citiesWithEmporium.contains(c6) && citiesWithEmporium.size()==5);

	}
	
	

	@Test
	
	public void testGetAllReachableCitiesAfterGetGraphWithEmporium(){
		
		List<City> citiesWithEmporium = graph.getAllEmporiumCities("black",cities);
		List<Edge> edgesWithEmporium=edges;
		SimpleGraph newGraph = new SimpleGraph<>(DefaultEdge.class);
		newGraph=new GraphModel(cities,edges).buildGraph(citiesWithEmporium, edgesWithEmporium, newGraph);
		
		List<City> linkedCitiesForBonus = new GraphModel(cities,edges).getAllReachableCities(newGraph,c1);
		assertTrue(linkedCitiesForBonus.contains(c1)&&linkedCitiesForBonus.contains(c3)&&linkedCitiesForBonus.contains(c4)&&linkedCitiesForBonus.contains(c8)&&linkedCitiesForBonus.size()==4);

		
	}
		

	
}
