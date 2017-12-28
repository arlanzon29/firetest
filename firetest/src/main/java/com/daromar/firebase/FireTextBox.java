package com.daromar.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireTextBox
	implements IFireControl {
	
	private String value="";
	private String label="";
	private String id="";
	private FireApp app=null;
	private IFireControlsCollection parent=null;
	
	public FireTextBox(String id) {
		this.id=id;
	}
	


	public String getLabel() {
		return label;
	}



	public void setLabel(String label) {
		this.label = label;
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

		String path=this.parent.getPath()+"/"+id;
		
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/Design/"+path+"/Label").setValue(label);
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
		this.id=value;
	}



	@Override
	public void setParent(IFireControlsCollection parent) {
		// TODO Auto-generated method stub
		this.parent=parent;
	}
}
