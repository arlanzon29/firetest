package com.daromar.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireButton 
implements IFireControl {
	
	private String id="";
	
	private IButtonClick click=null;
	private IFireControlsCollection parent=null;
	private String value="0";
	private FireApp app=null;
	
	
	private String caption="";
	
	public FireButton(String id) {
		this.id=id;
					
		
	}
	
	public void RaiseEventClick() {
     	click.Click();
     	this.setValue("0");
	}
	
	public void AddClickListener(IButtonClick click) {
		this.click=click;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(String value) {
		this.value=value;
		
		
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
	public void setParent(IFireControlsCollection parent) {
		// TODO Auto-generated method stub
		this.parent=parent;
	}

	
}
