package com.daromar.firebase;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireApp
implements IFireControlsCollection{
	private String basePath="";
	private List<IFireControl> controls=new ArrayList<IFireControl>();
	private IFireEvent eventHandler=null;
	
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
		control.setParent(this);
	}

	public void Read(FireButton btn) {
		DatabaseReference ref= FirebaseDatabase.getInstance().getReference(basePath+"/DataSource");

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
	                		 SetControlValue(ap,key,value);
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
	
	private void SetControlValue(IFireControlsCollection controls,String key,String value) {
		for(IFireControl ctr : controls.getControls())
		{
			if (ctr instanceof IFireControlsCollection) {
				SetControlValue((IFireControlsCollection) ctr,key,value);
			}else {
				if (ctr.getId().compareTo(key)==0) {
					ctr.setValue(value);
				}
			}
		}
	}
	
	protected void InitializeApp() {
		/*esto se debe modifcar*/
		FirebaseDatabase.getInstance().getReference(basePath).removeValue();
		
		for(IFireControl ctr : controls)
		{
			ctr.InitializeComponent();
		}
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void InitializeComponent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParent(IFireControlsCollection parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public List<IFireControl> getControls() {
		// TODO Auto-generated method stub
		return controls;
	}

	@Override
	public FireApp getApp() {
		// TODO Auto-generated method stub
		return this;
	}
	

	@Override
	public void AddEventHandler(IFireEvent eventHandler) {
		// TODO Auto-generated method stub
		this.eventHandler=eventHandler;
	}
}
