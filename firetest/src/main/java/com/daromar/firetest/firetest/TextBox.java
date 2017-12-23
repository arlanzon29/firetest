package com.daromar.firetest.firetest;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TextBox
	implements IFireControl {
	
	private FireApp app;
	private String value;
	private String path;
	
	public TextBox(FireApp app,String path) {
		this.app=app;
		this.path=path;
		
		this.app.AddControl(this);
		
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
			 FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/"+path).setValue(value);
			
		}
	}



	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return path;
	}

}
