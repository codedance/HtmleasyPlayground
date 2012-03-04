package com.googlecode.htmleasy.playground;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.common.collect.ImmutableMap;
import com.googlecode.htmleasy.RedirectException;
import com.googlecode.htmleasy.ViewSet;
import com.googlecode.htmleasy.ViewWith;
import com.googlecode.htmleasy.playground.domain.boats.Boat;
import com.googlecode.htmleasy.playground.domain.boats.PowerBoat;
import com.googlecode.htmleasy.playground.domain.boats.SailingBoat;

/**
 * Demonstrates polymorphic views. Notice how simple the method logic is. All
 * URL mapping and view section is done via annotations. The view is selected
 * based on the return class of the method.
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

    
    
    // Example 3:
    
    @GET @Path("/")
    @ViewWith("/soy/playground.boats.BoatList")
    public Map<String, ?> listBoats() {
	// FUTURE: Paginate with nice URLs.
	return ImmutableMap.of("boats", fakeBoatDatabase, "numberOfBoats", fakeBoatDatabase.size());
    }

    
    @GET
    @Path("/{id}")
    public Boat viewBoat(@PathParam("id") int id) {
	
	// Fetch the boat from the (fake)database.  The item returned will either be a SailingBoat or PowerBoat POJO.
	// The view (template) used to render the boat is set via the ViewWith annotation on the returned POJO.
	
	return fakeBoatDatabase.get(id);
    }
    
    
    
    // Example 4:
    
    @GET @Path("/members/")
    @ViewWith("/soy/playground.boats.MembersBoatList")
    public Map<String, ?> listBoatsForMembers() {
	
	// Check that we're a member and only show if we are! TODO:  Implement with a RestEasy Intercepter.
	
	// FUTURE: Paginate with nice URLs.
	return ImmutableMap.of("boats", fakeBoatDatabase, "numberOfBoats", fakeBoatDatabase.size());
    }

    
    @GET
    @Path("/members/{id}")
    @ViewSet ({
	@ViewWith(ifClass=SailingBoat.class, value="/soy/playground.boats.MembersSailingBoatView"),
	@ViewWith(ifClass=PowerBoat.class, value="/soy/playground.boats.MembersPowerBoatView")
    })
    public Boat viewBoatsWithMembersInfo(@PathParam("id") int id) {
	
	// Same as the viewBoat() method above, however our views are overridden on this method to show the 
	// special members view which will display pricing information.
	
	// TODO: ...check that we're a member first!
	return fakeBoatDatabase.get(id);
    }
    
    
    // Because our service is designed to list boats we'll set up a few user-friendly redirects. e.g. 
    //		/boats => /boats/
    
    @GET
    public void redirectToBoatsList() {
	throw new RedirectException(getClass(), "listBoats");
    }
    
    @GET @Path("/members")
    public void redirectToMembersList() {
	throw new RedirectException(getClass(), "listBoatsForMembers");
    }
    
    

    /**
     * We'll simulate a database with some static data. The data is a list of
     * Boats of different types (classes).
     */
    private void makeFakeBoatDatabase() {

	int id = 0;

	SailingBoat australiaII = new SailingBoat();
	australiaII.setId(id);
	australiaII.setLengthOverAll(19.21);
	australiaII.setName("Australia II");
	australiaII.setNumberOfCrew(11);
	australiaII.setSailArea(175.0);
	australiaII.setDescription("Australia II (KA 6) is the Australian 12-metre-class challenge racing yacht that " +
			"won the 1983 America's Cup. Skippered by John Bertrand, she was the first successful Cup " +
			"challenger, ending a 132-year tenure (with 26 successful defenses) by the New York Yacht Club."
			);
	australiaII.setPrice("Priceless! - it's in a museum.");
	fakeBoatDatabase.add(id, australiaII);
	id++;

	SailingBoat usa17 = new SailingBoat();
	usa17.setId(id);
	usa17.setName("BMW Oracle Racing");
	usa17.setLengthOverAll(34.5);
	usa17.setSailArea(630.0);
	usa17.setNumberOfCrew(12);
	usa17.setDescription("USA 17 is a 90 ft (27 m) LWL, 90 ft (27 m) beam, sloop rigged one-off racing sail " +
			"trimaran built by the USA sailing team BMW Oracle Racing for use in a Deed of Gift " +
			"challenge for the 33rd America's Cup.");
	usa17.setPrice("$0 - Discounted. Free to a good home!");
	fakeBoatDatabase.add(id, usa17);
	id++;

	PowerBoat octopus = new PowerBoat();
	octopus.setId(id);
	octopus.setName("Octopus");
	octopus.setLengthOverAll(126.2);
	octopus.setEnginePower(19200);
	octopus.setMaxSpeedKnots(20.0);
	octopus.setPrice("$200,000,000+");
	octopus.setDescription("Octopus is a 414 foot (126 m) megayacht owned by Paul Allen, the co-founder of " +
			"Microsoft. Delivered in 2003, it was believed to be the biggest such yacht at the time " +
			"of its construction.");
	fakeBoatDatabase.add(id, octopus);
	id++;

    }

}
