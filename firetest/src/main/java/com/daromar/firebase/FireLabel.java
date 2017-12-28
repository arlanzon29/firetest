package com.daromar.firebase;

import com.daromar.firetest.firetest.IFireControlsCollection;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireLabel 
implements IFireControl {
	private String value="";
	private String id;
	private IFireControlsCollection parent=null;
	private FireApp app;
	
	public FireLabel(String id) {
		this.id=id;
		
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
			 FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/DataSource/"+id).setValue(value);
			
		}
	}



	
	@Override
	public void InitializeComponent() {
		app=this.parent.getApp();

		
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/DataSource/"+id).setValue(value);
	}



	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}



	@Override
	public void setId(String value) {
		// TODO Auto-generated method stub
		id=value;
	}



	@Override
	public void setParent(IFireControlsCollection parent) {
		// TODO Auto-generated method stub
		this.parent=parent;
	}	
}
