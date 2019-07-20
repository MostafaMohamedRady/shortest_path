package com.algorythma.shipping.service;

import com.algorythma.shipping.model.Neighbor;
import com.algorythma.shipping.model.Person;
import com.algorythma.shipping.model.ShippingScenario;
import com.algorythma.shipping.util.AppUtil;
import java.io.File;
import java.util.*;

public class Dijkstra {

	/**
	 * compute all paths from specific source (person)
	 * put the shortest distance for every target (person) from source person
	 * @param source
	 */
	public static void computePaths(Person source) {
		source.setMinHardness(0.);
		PriorityQueue<Person> vertexQueue = new PriorityQueue<Person>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Person startingPoint = vertexQueue.poll();

			// Visit each neighbor exiting source
			if (startingPoint.getNetwork() != null) {
				for (Neighbor e : startingPoint.getNetwork()) {
					Person friend = e.getFriend();
					double hardness = e.getHardness();
					double hardnessFromStartPoint = startingPoint.getMinHardness() + hardness;
					if (hardnessFromStartPoint < friend.getMinHardness()) {
						vertexQueue.remove(friend);

						friend.setMinHardness(hardnessFromStartPoint);
						friend.setPrevious(startingPoint);
						vertexQueue.add(friend);
					}
				}
			}

		}
	}

	/**
	 * get shortest path to specific target (person)
	 * @param target
	 * @return list of shortest path
	 */
	public static List<Person> getShortestPathTo(Person target) {
		List<Person> path = new ArrayList<>();
		for (Person vertex = target; vertex != null; vertex = vertex.getPrevious())
			path.add(vertex);

		Collections.reverse(path);
		return path;
	}






}
