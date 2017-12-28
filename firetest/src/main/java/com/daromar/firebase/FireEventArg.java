package com.daromar.firebase;

public class FireEventArg {
	private IFireControl fireControl;
	private String event;
	
	public FireEventArg(IFireControl control,String event) {
		this.fireControl=control;
		this.event=event;
	}
	public IFireControl getFireControl() {
		return fireControl;
	}
	public void setFireControl(IFireControl fireControl) {
		this.fireControl = fireControl;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
}
