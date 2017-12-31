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
implements IFireControlsCollection,ValueEventListener{
	private String basePath="";
	private List<IFireControl> controls=new ArrayList<IFireControl>();
	private IFireEvent eventHandler=null;
	private FireWebEvent event=null;
	
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


	protected void InitializeApp() {
		/*esto se debe modifcar*/
		FirebaseDatabase.getInstance().getReference(basePath).removeValue();
		FirebaseDatabase.getInstance().getReference(basePath+"/Status").setValue("on");
		
		FirebaseDatabase.getInstance().getReference(basePath+"/Event/App/FireControl").setValue("");
		FirebaseDatabase.getInstance().getReference(basePath+"/Event/App/Event").setValue("");
		FirebaseDatabase.getInstance().getReference(basePath+"/Event/App/Argument").setValue("");
		
		for(IFireControl ctr : controls)
		{
			ctr.InitializeComponent();
		}
		
		 FirebaseDatabase database = FirebaseDatabase.getInstance();
	     DatabaseReference ref = database.getReference(basePath+"/Event/App");
	     ref.addValueEventListener(this);
	}

	@Override
	public void onDataChange(DataSnapshot snapshot) {
		// TODO Auto-generated method stub

		event=snapshot.getValue(FireWebEvent.class);
		
		if (event.Event.equals("Reset")) {
			this.ResetApp();
		}
		
		event.FireControl="";
		event.Event="";
		event.Argument="";
		
		FirebaseDatabase.getInstance().getReference(basePath+"/Event/App").setValue(event);
	}

	@Override
	public void onCancelled(DatabaseError error) {
		// TODO Auto-generated method stub
		
	}
	
	protected void ResetApp() {
		FirebaseDatabase.getInstance().getReference(basePath+"/Event/App/FireControl").setValue("");
		FirebaseDatabase.getInstance().getReference(basePath+"/Event/App/Event").setValue("");
		FirebaseDatabase.getInstance().getReference(basePath+"/Event/App/Argument").setValue("");
		
		for(IFireControl ctr : controls)
		{
			ctr.ResetComponent();
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
	
	public void ResetComponent() {
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
