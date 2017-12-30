package com.daromar.firebase;

public class FireEventArg {
	private IFireControl fireControl;
	private String event;
	private String argument;

	public FireEventArg(IFireControl control,String event,String argument) {
		this.fireControl=control;
		this.event=event;
		this.argument=argument;
	}
	public String getArgument() {
		return argument;
	}
	public void setArgument(String argument) {
		this.argument = argument;
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
