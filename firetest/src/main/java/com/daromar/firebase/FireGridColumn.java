package com.daromar.firebase;

import com.google.firebase.database.FirebaseDatabase;

public class FireGridColumn {
	private String id="";
	private FireGrid parent;
	private String caption;
	private String [] values;
	
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public FireGridColumn(String id) {
		this.id=id;
	}

	public FireGrid getParent() {
		return parent;
	}

	public void setParent(FireGrid parent) {
		this.parent = parent;
		values=new String[parent.getRows()];
		for (int i=0;i<values.length;i++) {
			values[i]="";
		}
	}
	
	public void InitializeComponent(String path) {
		FirebaseDatabase.getInstance().getReference(path+id+"/Caption").setValue(caption);
		
		for (int i=0;i<values.length;i++) {
			FirebaseDatabase.getInstance().getReference(path+id+"/Cell"+i).setValue(values[i]);	
		}
	}
}
