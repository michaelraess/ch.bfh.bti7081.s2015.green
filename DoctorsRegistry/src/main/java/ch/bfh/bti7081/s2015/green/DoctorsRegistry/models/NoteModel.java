package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models;

import org.neo4j.graphdb.Node;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Note;

public class NoteModel extends DefaultModel {

	private final String LABEL = "Note";
	
	public NoteModel() {
		super();
		
	}
	
	public void addNoteToAppointment(int appntmntId, String notes) {
		int nextId = this.getLastIdFor(LABEL) + 1;
		
		String queryString = String.format("CREATE (n:%s {id : %d, notes : '%s', appointmentId : %d})", 
				LABEL, nextId, notes, appntmntId);
		
		this.getQueryEngine().query(queryString, null).to(Node.class);
	}
	
	public Note getMedicationForAppointment(int appntmnId) {
		String queryString = String.format("MATCH (n:%s) WHERE n.appointmentId = %d RETURN n", 
				LABEL, appntmnId);
		
		Node resNote = this.getQueryEngine().query(queryString, null).to(Node.class).singleOrNull();
		
		return resNote == null ? null : this.resolveNoteFields(resNote);
	}
	
	private Note resolveNoteFields(Node n) {
		Note a = new Note();
		
		if(n.hasProperty("id")) {
			a.setId((int)n.getProperty("id"));
		}
		
		if(n.hasProperty("notes")) {
			a.setNoteText((String)n.getProperty("notes"));
		}
		
		if(n.hasProperty("appointmentId")) {
			a.setAppointmentId((int)n.getProperty("appointmentId"));
		}
		
		return a;
	}

}
