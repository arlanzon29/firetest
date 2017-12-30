package com.daromar.firebase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireDiv 
implements IFireControlsCollection,IFireEvent,ValueEventListener
{
	protected String id="";
	protected IFireControlsCollection parent;
	protected List<IFireControl> controls=new ArrayList<IFireControl>();
	protected String display="block";
	protected FireApp app=null;
	protected IFireEvent eventHandler;
	private FireWebEvent event=null;
	protected String path;
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(String value) {
		// TODO Auto-generated method stub
		
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
		
		
		if (this.app!=null) {
			String path=this.parent.getPath()+"/"+id;
			
			FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/Design/"+path+"/Display").setValue(display);
		}

	}

	@Override
	public void InitializeComponent() {
		// TODO Auto-generated method stub
		app=this.parent.getApp();

		path=this.parent.getPath()+"/"+id;
		
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/Design/"+path+"/Display").setValue(display);

		
		
		for(IFireControl ctr : controls)
		{
			ctr.InitializeComponent();
		}

		/* Creo la zona de eventos */
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/Event/"+path+"/FireControl").setValue("");
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/Event/"+path+"/Event").setValue("");
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/Event/"+path+"/Argument").setValue("");
		
		 FirebaseDatabase database = FirebaseDatabase.getInstance();
	     DatabaseReference ref = database.getReference(app.getBasePath()+"/Event"+path);
	     ref.addValueEventListener(this);
	}

	@Override
	public void onDataChange(DataSnapshot snapshot) {
		// TODO Auto-generated method 
		
		String ref=snapshot.getRef().toString();
		
		if (ref.endsWith(app.getBasePath()+"/Event"+path)) {	/* Event */
			event=snapshot.getValue(FireWebEvent.class);
			
			
			if (!event.Event.equals("")) {
				DatabaseReference refe= FirebaseDatabase.getInstance().getReference(this.app.getBasePath()+"/DataSource/"+id);
				
				refe.addListenerForSingleValueEvent(this);
			}
			
		}else { /* Get Value */
			app.setReading(true);
            for (DataSnapshot child : snapshot.getChildren()) { 
            	 long numChildren = child.getChildrenCount();
            	 if (numChildren==0) {
            		 String key=child.getKey();
            		 String value=(String)child.getValue();
            		 SetControlValue(key,value);
            	 }	 
            } 	
            app.setReading(false);
         
            /* Lanzo el evento */
            for(IFireControl ctr : controls)
    		{
    			if (ctr.getId().compareTo(event.FireControl)==0) {
    				FireControl cont=(FireControl)ctr;
    				cont.RaiseFireEvent(event);
    				
    				event.FireControl="";
    				event.Event="";
    				event.Argument="";
    				
    				FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/Event/"+path).setValue(event);		
    			}
    		}
		}
		
			
	}


	private void SetControlValue(String key,String value) {
		for(IFireControl ctr : controls)
		{
			if (ctr.getId().compareTo(key)==0) {
				ctr.setValue(value);
			}
		}
	}
	
	@Override
	public void onCancelled(DatabaseError error) {
				
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
	public List<IFireControl> getControls() {
		// TODO Auto-generated method stub
		return controls;
	}

	@Override
	public void AddControl(IFireControl control) {
		// TODO Auto-generated method stub
		controls.add(control);
		control.setParent(this);
	}


	public FireDiv(String id) {
		this.id=id;
	}

	@Override
	public void setParent(IFireControlsCollection parent) {
		// TODO Auto-generated method stub
		this.parent=parent;
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return parent.getPath()+"/"+id;
	}

	@Override
	public FireApp getApp() {
		// TODO Auto-generated method stub
		return parent.getApp();
	}

	@Override
	public void AddEventHandler(IFireEvent eventHandler) {
		// TODO Auto-generated method stub
		this.eventHandler=eventHandler;
	}

	@Override
	public void FireEvent(FireEventArg arg) {
		// TODO Auto-generated method stub
		
	}


}
