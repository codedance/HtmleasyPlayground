package com.googlecode.htmleasy.playground;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.common.collect.ImmutableMap;
import com.googlecode.htmleasy.ViewSet;
import com.googlecode.htmleasy.ViewWith;
import com.googlecode.htmleasy.playground.domain.boats.Boat;
import com.googlecode.htmleasy.playground.domain.boats.PowerBoat;
import com.googlecode.htmleasy.playground.domain.boats.SailingBoat;

/**
 * Demonstrates polymorphic views.  Notice how simple the method logic is. 
 * All URL mapping and view section is done via annotations. The view
 * is selected based on the return class of the method.
 * 
 * @author Chris
 *
 */
@Path("/boats")
public class Boats {
	
	private List<Boat> fakeBoatDatabase = new ArrayList<Boat>();

	public Boats() {
		makeFakeBoatDatabase();
	}
	
	@GET @ViewWith("/soy/playground.boats.boatList")
	public Map<String, ?> listBoats() {
		// FUTURE: Paginate with nice URLs.
		return ImmutableMap.of("boats", fakeBoatDatabase,
							   "numberOfBoats", fakeBoatDatabase.size()
							   );
	}
	
	@GET @Path("/{id}")
	@ViewSet({
        @ViewWith(ifClass=PowerBoat.class, value="/soy/playground.boats.powerBoatView"),
        @ViewWith(ifClass=SailingBoat.class, value="/soy/playground.boats.sailingBoatView"),
     })
	public Boat viewBoat(@PathParam("id") int id) {
		// The view rendered with be selected based on the class return type.
		return fakeBoatDatabase.get(id);
	}
	
	
	/**
	 * We'll simulate a database with some static data.  The data is a list of Boats
	 * of different types (classes).
	 */
	private void makeFakeBoatDatabase() {
		
		int id = 0;
		
		SailingBoat australiaII = new SailingBoat();
		australiaII.setId(id);
		australiaII.setLengthOverAll(19.21);
		australiaII.setName("Australia II");
		australiaII.setNumberOfCrew(11);
		australiaII.setSailArea(175.0);
		fakeBoatDatabase.add(id, australiaII);
		id++;
		
		SailingBoat usa17 = new SailingBoat();
		usa17.setId(id);
		usa17.setName("BMW Oracle Racing");
		usa17.setLengthOverAll(34.5);
		usa17.setSailArea(630.0);
		usa17.setNumberOfCrew(12);
		fakeBoatDatabase.add(id, usa17);
		id++;
		
		PowerBoat octopus = new PowerBoat();
		octopus.setId(id);
		octopus.setName("Octopus");
		octopus.setLengthOverAll(126.2);
		octopus.setEnginePower(19200);
		octopus.setMaxSpeedKnots(20.0);
		fakeBoatDatabase.add(id, octopus);
		id++;
		
	}

}
