package com.daromar.firetest.firetest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireApp {
	private String basePath="";
	private List<IFireControl> controls=new ArrayList<IFireControl>();
	
	
	public boolean isReading() {
		return reading;
	}

	public void setReading(boolean reading) {
		this.reading = reading;
	}

	private boolean reading=false;
	
	
	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	
	public FireApp(String basePath) {
		this.basePath=basePath;
	}
	
	public void AddControl(IFireControl control) {
		controls.add(control);
	}

	public void Read(Button btn) {
		DatabaseReference ref= FirebaseDatabase.getInstance().getReference(basePath);

		FireApp ap=this;
		
		  ref.addListenerForSingleValueEvent(new ValueEventListener() {
	            @Override
	            public void onDataChange(DataSnapshot dataSnapshot) {
	              ap.setReading(true);
	                for (DataSnapshot child : dataSnapshot.getChildren()) { 
	                	 long numChildren = child.getChildrenCount();
	                	 if (numChildren==0) {
	                		 String key=child.getKey();
	                		 String value=(String)child.getValue();
	                		 SetControlValue(key,value);
	                	 }	 
	                } 	
	                ap.setReading(false);
	                btn.RaiseEventClick();
	            }
	
				@Override
				public void onCancelled(DatabaseError arg0) {
					 
					
				}
	        });
	        
	}
	
	private void SetControlValue(String key,String value) {
		for(IFireControl ctr : controls)
		{
			if (ctr.getPath().compareTo(key)==0) {
				ctr.setValue(value);
			}
		}
	}
	
	protected void InitializeApp() {
	
		FirebaseDatabase.getInstance().getReference(basePath).setValue(this);
	}
}
