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


	

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	
	@Override
	public void InitializeComponent() {
		app=this.parent.getApp();

		path=this.parent.getPath()+"/"+id;
		
		ResetComponent();
	}

	@Override
	public void ResetComponent() {
				
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/Design/"+path+"/Caption").setValue(caption);
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/DataSource/"+path).setValue(value);
	}

}
