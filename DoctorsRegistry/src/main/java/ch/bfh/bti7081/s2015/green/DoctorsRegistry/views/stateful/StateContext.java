package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.stateful;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;

public class StateContext {
	
	private DRStates drState;
	private int caseId = -1;
	
	public StateContext() {
		this.setState(new CreatePatientView());
	}

	public void setState(DRStates drState) {
		this.drState = drState;
	}
	
	public void invokeView(AbstractOrderedLayout v) {
		for(int i=0; i < v.getComponentCount(); i++) {
			Component c = v.getComponent(i);
			c.setVisible(false);
		}
		drState.selectView(this, v);
	}

	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}
	
}
