package com.daromar.firetest.firetest;

import com.daromar.firebase.FireAppSQLLite;
import com.daromar.firebase.FireButton;
import com.daromar.firebase.FireDiv;
import com.daromar.firebase.FireEventArg;
import com.daromar.firebase.FireLabel;
import com.daromar.firebase.FireTextBox;
import com.daromar.firebase.IButtonClick;
import com.daromar.firebase.IFireControl;
import com.daromar.firebase.IFireEvent;

public class PrimeraApplicacion extends FireAppSQLLite 
implements IFireEvent {
	

	private PADivAutenticar divAutenticar;
	private PADivMenu divMenu;
	private PADivGrid divGrid;
	
	
	public PrimeraApplicacion(String basePath,String sqllite) {
		super(basePath,sqllite);
		
		divAutenticar=new PADivAutenticar("divAutenticar");
		divAutenticar.AddEventHandler(this);
		this.AddControl(divAutenticar);
		
		divMenu=new PADivMenu("divMenu");
		divMenu.setDisplay("none");
		divMenu.AddEventHandler(this);
		this.AddControl(divMenu);
		
		
		divGrid=new PADivGrid("divGrid",this.connection);
		divGrid.setDisplay("none");
		this.AddControl(divGrid);
		
		
		this.InitializeApp();
	}


	@Override
	public void FireEvent(FireEventArg arg) {
		// TODO Auto-generated method stub
		if (arg.getFireControl().getId().equals(divAutenticar.getId()) && arg.getEvent().equals(("Autenticar"))){
			divAutenticar.setDisplay("none");
			divMenu.setDisplay("block");
		}
		else if (arg.getFireControl().getId().equals(divMenu.getId())) {
			if (arg.getEvent().equals("FAMILIAS")) {
				divMenu.setDisplay("none");
				divGrid.setDisplay("block");
			}
		}
		
	}
}
