package com.daromar.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireTextBox
	extends FireControl {
	
	public FireTextBox(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}



	private String label="";
	

	public String getLabel() {
		return label;
	}



	public void setLabel(String label) {
		this.label = label;
	}



	@Override
	public void InitializeComponent() {
		app=this.parent.getApp();

		path=this.parent.getPath()+"/"+id;
		
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/Design/"+path+"/Label").setValue(label);
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/DataSource/"+path).setValue(value);
	}

}
