package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.stateful;

import com.vaadin.ui.AbstractOrderedLayout;

public class StateContext {
	
	private DRStates drState;
	
	public StateContext() {
		this.setState(new CreatePatientView());
	}

	public void setState(DRStates drState) {
		this.drState = drState;
	}
	
	public void invokeView(AbstractOrderedLayout v) {
		drState.selectView(this, v);
	}
	
}
