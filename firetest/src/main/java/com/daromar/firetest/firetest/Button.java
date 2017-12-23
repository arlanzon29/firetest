package com.daromar.firetest.firetest;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Button 
implements IFireControl {
	
	
	private IButtonClick click=null;
	private FireApp app;
	private String value;
	private String path;
	
	public Button(FireApp app,String path) {
		this.app=app;
		this.path=path;
		
		this.app.AddControl(this);
		
		Button but=this;
		 FirebaseDatabase database = FirebaseDatabase.getInstance();
	     DatabaseReference ref = database.getReference(app.getBasePath()+"/"+path);
		    
	        ref.addValueEventListener(new ValueEventListener() {
	            @Override
	            public void onDataChange(DataSnapshot dataSnapshot) {
	                String res = (String) dataSnapshot.getValue();
	               
	                if (res.compareTo("1")==0 && click!=null){
	                	app.Read(but);
	                }
	            }

				@Override
				public void onCancelled(DatabaseError arg0) {
					// TODO Auto-generated method stub
					
				}
	        });
	}
	
	public void RaiseEventClick() {
     	click.Click();
     	this.setValue("0");
	}
	
	public void AddClickListener(IButtonClick click) {
		this.click=click;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public void setValue(String value) {
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
