package com.daromar.firebase;

import com.google.firebase.database.FirebaseDatabase;

public class FireControl implements IFireControl {
	
	protected String id="";
	
	protected IFireEvent eventHandler=null;
	protected IFireControlsCollection parent=null;
	protected String value="";
	protected FireApp app=null;
	protected String path="";
	

	public FireControl(String id) {
		this.id=id;
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id=id;
	}

	@Override
	public void InitializeComponent() {
		// TODO Auto-generated method stub		
	}


	public void ResetComponent() {
		// TODO Auto-generated method stub		
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public void setValue(String value) {
		// TODO Auto-generated method stub
		this.value=value;
		
		if (!app.isReading()) {
			FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/DataSource/"+path).setValue(value);
			
		}
	}

	@Override
	public void setParent(IFireControlsCollection parent) {
		// TODO Auto-generated method stub
		this.parent=parent;
	}
	@Override
	public void AddEventHandler(IFireEvent eventHandler) {
		// TODO Auto-generated method stub
		this.eventHandler=eventHandler;
	}

	public void RaiseFireEvent(FireWebEvent event) {
		if (this.eventHandler!=null) {
			FireEventArg arg=new FireEventArg(this,event.Event,event.Argument);
			this.eventHandler.FireEvent(arg);
		}
	}
}
