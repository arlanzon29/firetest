package com.daromar.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireLabel 
extends FireControl {
	public FireLabel(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}




	private String value="";
	
	

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
	public void InitializeComponent() {
		app=this.parent.getApp();

		path=this.parent.getPath()+"/"+id;
		
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/DataSource/"+path).setValue(value);
	}



}
