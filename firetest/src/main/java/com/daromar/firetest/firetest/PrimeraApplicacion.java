package com.daromar.firetest.firetest;

import com.daromar.firebase.FireAppSQLLite;
import com.daromar.firebase.FireButton;
import com.daromar.firebase.FireDiv;
import com.daromar.firebase.FireLabel;
import com.daromar.firebase.FireTextBox;
import com.daromar.firebase.IButtonClick;

public class PrimeraApplicacion extends FireAppSQLLite {
	

	private PADivAutenticar divAutenticar;
	private FireDiv divMenu;
	private PADivGrid divGrid;
	
	
	public PrimeraApplicacion(String basePath,String sqllite) {
		super(basePath,sqllite);
		
		divAutenticar=new PADivAutenticar("divAutenticar");
		this.AddControl(divAutenticar);
		
		
		divMenu=new FireDiv("divMenu");
		divMenu.setDisplay("none");
		this.AddControl(divMenu);
		
		divGrid=new PADivGrid("divGrid");
		divGrid.setDisplay("none");
		this.AddControl(divGrid);
		
		
		this.InitializeApp();
	}
	
	public void AutenticacionCorrecta() {
		divAutenticar.setDisplay("none");
		divMenu.setDisplay("block");
	}
	
	
}
