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
		divAutenticar.setDefaultDisplay("block");
		divAutenticar.AddEventHandler(this);
		this.AddControl(divAutenticar);
		
		divMenu=new PADivMenu("divMenu");
		divMenu.setDefaultDisplay("none");
		divMenu.AddEventHandler(this);
		this.AddControl(divMenu);
		
		
		divGrid=new PADivGrid("divGrid",this.connection);
		divGrid.setDefaultDisplay("none");
		divGrid.AddEventHandler(this);
		this.AddControl(divGrid);
		
		
		this.InitializeApp();
	}


	@Override
	public void FireEvent(FireEventArg arg) {
		// TODO Auto-generated method stub
		if (arg.getFireControl().getId().equals(divAutenticar.getId()) && arg.getEvent().equals(("AUTENTICAR"))){
			divAutenticar.setDisplay("none");
			divMenu.setDisplay("block");
		}
		else if (arg.getFireControl().getId().equals(divMenu.getId())) {
			if (arg.getEvent().equals("FAMILIAS")) {
				divMenu.setDisplay("none");
				divGrid.setDisplay("block");
			}else if (arg.getEvent().equals("DESCONECTAR")) {
				this.ResetApp();
				divAutenticar.setDisplay("block");
				divMenu.setDisplay("none");

			}
		}
		else if (arg.getFireControl().getId().equals(divGrid.getId())) {
			if (arg.getEvent().equals("REGRESAR")) {
				divMenu.setDisplay("block");
				divGrid.setDisplay("none");
			}
		}
		
	}
}
