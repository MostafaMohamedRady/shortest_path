package com.algorythma.shipping.service;

import com.algorythma.shipping.model.Neighbor;
import com.algorythma.shipping.model.Person;
import com.algorythma.shipping.model.ShippingScenario;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NetworkGenerator {
	
	private File fileName;
	private List<ShippingScenario> shippingScenarios;
	private Map<String, Person> personsMap;

	public NetworkGenerator(File fileName) {
		this.fileName = fileName;
	}

	public Map<String, Person> getPersonsMap() {
		if(personsMap == null) {
			generatePersonsAndScenariosFromCSV();
		}
		return personsMap;
	}

	public List<ShippingScenario> getShippingScenarios(){
		if(shippingScenarios == null) {
			generatePersonsAndScenariosFromCSV();
		}
		return shippingScenarios;
	}

	/***
	 * generate person list and test scenario list from scv file
	 * read data from file (line by line)
	 * if line start with @ :- add to scenario test
	 * if line start with name :- create person and network
	 */
	private void generatePersonsAndScenariosFromCSV() {
		shippingScenarios = new ArrayList<>();
		personsMap = new HashMap<>();

//		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(fileName.toPath(), StandardCharsets.UTF_8)) {

			// read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

            	if(line.startsWith("@")) {
            		addShippingScenario(line);
            	}else {
            		addPersonNetwork(line);
            	}

            	// read next line before looping
                // if end of file reached, line would be null
            	line = br.readLine();
            }
		}catch (IOException ex) {
            ex.printStackTrace();
        }
	}

	/***
	 * get scenario test from line and convert to ShippingScenario object
	 * @param line
	 */
	private void addShippingScenario(String line) {
		String[] scenarioInfo = line.split(",");
		double cost = Double.POSITIVE_INFINITY;
		Person person = getPersonObject(scenarioInfo[1]);

		if(!scenarioInfo[3].equals("~")) {
			cost = Double.valueOf(scenarioInfo[3]).doubleValue();
		}
		ShippingScenario scenario = new ShippingScenario(person, scenarioInfo[2].split("x"), cost);

		//add the scenario to scenarios list
		shippingScenarios.add(scenario);
		
	}

	/***
	 * get person and network object from line
	 * @param line
	 */
	private void addPersonNetwork(String line) {
		String[] networkInfo = line.split(",");
		
		//get person from the first element in array (person name)
		Person person = getPersonObject(networkInfo[0]);
		//create network array
		Neighbor[] network= new Neighbor[networkInfo.length-1];
		
		for(int i=1; i<networkInfo.length; i++) {
			String[] neighborInfo = networkInfo[i].split(":");
			Person neighborPerson = getPersonObject(neighborInfo[0]);		// get the neighbor's person object
			Neighbor neighbor = new Neighbor(neighborPerson, Double.valueOf(neighborInfo[1]).doubleValue());
			network[i-1] = neighbor;
		}
		person.setNetwork(network);
		
	}

	/**
	 * create or get person from persons map by name
	 * check if person already created before
	 * Add person to the map
	 * @param personName
	 * @return
	 */
	private Person getPersonObject(String personName) {
		Person person = null;
		// check if person already created before 
		if(personsMap.get(personName) !=null) {
			person = personsMap.get(personName);
		}else {
			person = new Person(personName);
			//add new person to the map
			personsMap.put(personName, person);
		}
		return person;
	}
	
	
}
