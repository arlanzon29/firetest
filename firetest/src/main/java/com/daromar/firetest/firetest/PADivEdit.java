package com.daromar.firetest.firetest;

import java.sql.Connection;

import com.daromar.firebase.FireDiv;
import com.daromar.firebase.FireTextBox;

public class PADivEdit 
extends FireDiv{
	
	private FireTextBox txtCode;
	private FireTextBox txtName;


	public PADivEdit(String id,Connection conn) {
		super(id);
		// TODO Auto-generated constructor stub
		
		txtCode=new FireTextBox("txtCode");
		txtCode.setLabel("Código");
		this.AddControl(txtCode);

		txtName=new FireTextBox("txtName");
		txtName.setLabel("Descripción");
		this.AddControl(txtName);
		
	}
	
	public void Get(String code ) {
		this.txtCode.setValue(code);
		this.txtName.setValue("Descripcion "+code);
	}

}
