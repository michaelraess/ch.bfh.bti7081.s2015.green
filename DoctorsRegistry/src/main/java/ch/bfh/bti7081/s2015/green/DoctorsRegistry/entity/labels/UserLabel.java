package ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.labels;

import org.neo4j.graphdb.Label;

public class UserLabel implements Label {

	@Override
	public String name() {
		return "User";
	}

}
