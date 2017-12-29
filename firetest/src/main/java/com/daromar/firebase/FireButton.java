package com.daromar.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireButton 
extends FireControl {
	
	private String caption="";
	
	
	public FireButton(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}


	
	
	
	public void RaiseEventClick() {
		if (this.eventHandler!=null) {
			FireEventArg arg=new FireEventArg(this,"Click");
			this.eventHandler.FireEvent(arg);
		}
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	
	@Override
	public void InitializeComponent() {
		app=this.parent.getApp();

		String path=this.parent.getPath()+"/"+id;
		
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/Design/"+path+"/Caption").setValue(caption);
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/DataSource/"+id).setValue(value);
		
		
		
		FireButton but=this;
		 FirebaseDatabase database = FirebaseDatabase.getInstance();
	     DatabaseReference ref = database.getReference(app.getBasePath()+"/DataSource/"+id);
		    
	        ref.addValueEventListener(new ValueEventListener() {
	            @Override
	            public void onDataChange(DataSnapshot dataSnapshot) {
	                String res = (String) dataSnapshot.getValue();
	               
	                if (res.compareTo("1")==0 && eventHandler!=null){
	                	app.Read(but);
	                }
	            }

				@Override
				public void onCancelled(DatabaseError arg0) {
					// TODO Auto-generated method stub
					
				}
	        });
	}

}
