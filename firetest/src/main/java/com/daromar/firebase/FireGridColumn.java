package com.daromar.firebase;

import com.google.firebase.database.FirebaseDatabase;

public class FireGridColumn {
	private String id="";
	private FireGrid parent;
	private String caption;
	
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
	}
	
	public void InitializeComponent(String path) {
		FirebaseDatabase.getInstance().getReference(path+id+"/Caption").setValue(caption);	
	}
}
