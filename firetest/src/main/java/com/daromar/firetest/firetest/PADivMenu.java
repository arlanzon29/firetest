package com.daromar.firetest.firetest;

import com.daromar.firebase.FireButton;
import com.daromar.firebase.FireDiv;
import com.daromar.firebase.FireEventArg;

public class PADivMenu 
extends FireDiv{

	private FireButton btnGrid;
	private FireButton btnDesconectar;
	
	
	public PADivMenu(String id) {
		super(id);
		// TODO Auto-generated constructor stub
		
		btnGrid=new FireButton("btnGrid");
		btnGrid.setCaption("Grid");
		btnGrid.AddEventHandler(this);
		this.AddControl(btnGrid);

		btnDesconectar=new FireButton("btnDesconectar");
		btnDesconectar.setCaption("Desconectar");
		btnDesconectar.AddEventHandler(this);
		this.AddControl(btnDesconectar);

	}

	@Override
	public void FireEvent(FireEventArg arg) {
		
		if (arg.getFireControl().getId().equals(btnGrid.getId())){
			if (arg.getEvent().equals("Click")) {
				Familias();
			}
		}else if (arg.getFireControl().getId().equals(btnDesconectar.getId())){
			if (arg.getEvent().equals("Click")) {
				Desconectar();
			}
		}
	}
	
	
	private void Desconectar() {
		if (eventHandler!=null) {
			FireEventArg arg=new FireEventArg(this,"DESCONECTAR","");				
			eventHandler.FireEvent(arg);
		}
	}
	private void Familias() {
		if (eventHandler!=null) {
			FireEventArg arg=new FireEventArg(this,"FAMILIAS","");				
			eventHandler.FireEvent(arg);
		}
	}


}
