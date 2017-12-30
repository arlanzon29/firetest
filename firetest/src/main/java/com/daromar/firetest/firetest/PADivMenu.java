package com.daromar.firetest.firetest;

import com.daromar.firebase.FireButton;
import com.daromar.firebase.FireDiv;
import com.daromar.firebase.FireEventArg;

public class PADivMenu 
extends FireDiv{

	private FireButton btnGrid;
	
	public PADivMenu(String id) {
		super(id);
		// TODO Auto-generated constructor stub
		
		btnGrid=new FireButton("btnGrid");
		btnGrid.setCaption("Grid");
		btnGrid.AddEventHandler(this);
		this.AddControl(btnGrid);
	}

	@Override
	public void FireEvent(FireEventArg arg) {
		if (arg.getFireControl().getId().equals(btnGrid.getId())){
			if (arg.getEvent().equals("Click")) {
				Familias();
			}
		}
	}
	
	

	private void Familias() {
		if (eventHandler!=null) {
			FireEventArg arg=new FireEventArg(this,"FAMILIAS","");				
			eventHandler.FireEvent(arg);
		}
	}


}
