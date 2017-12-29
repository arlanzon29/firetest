package com.daromar.firebase;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.FirebaseDatabase;

public class FireDiv 
implements IFireControlsCollection,IFireEvent
{
	protected String id="";
	protected IFireControlsCollection parent;
	protected List<IFireControl> controls=new ArrayList<IFireControl>();
	protected String display="block";
	protected FireApp app=null;
	protected IFireEvent eventHandler;
	
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

		String path=this.parent.getPath()+"/"+id;
		
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/Design/"+path+"/Display").setValue(display);

		
		for(IFireControl ctr : controls)
		{
			ctr.InitializeComponent();
		}
		
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
