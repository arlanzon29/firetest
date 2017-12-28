package com.daromar.firebase;

import com.daromar.firetest.firetest.IFireControlsCollection;
import com.google.firebase.database.FirebaseDatabase;

public class FireGrid
implements IFireControl{
	
	private String id;
	private IFireControlsCollection parent=null;
	private FireApp app=null;
	
	
	public FireGrid(String id) {
		this.id=id;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "";
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

	@Override
	public void setValue(String value) {
		app=this.parent.getApp();

		String path=this.parent.getPath()+"/"+id;
		
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/Design/"+path+"/Rows").setValue("5");
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/DataSource/"+id).setValue(value);
		
	}

	@Override
	public void setParent(IFireControlsCollection parent) {
		// TODO Auto-generated method stub
		this.parent=parent;
		
	}
}
