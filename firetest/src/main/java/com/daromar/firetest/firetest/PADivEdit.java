package com.daromar.firetest.firetest;

import java.sql.Connection;

import com.daromar.firebase.FireButton;
import com.daromar.firebase.FireDiv;
import com.daromar.firebase.FireEventArg;
import com.daromar.firebase.FireTextBox;

public class PADivEdit 
extends FireDiv{
	
	private FireTextBox txtCode;
	private FireTextBox txtName;
	private FireButton btnAceptar;
	private FireButton btnCancelar;
	
	
	private OFAM familia;
	private OFamService servicio=new OFamService();
	
	private Connection conn;
	
	public PADivEdit(String id,Connection conn) {
		super(id);
		// TODO Auto-generated constructor stub
		
		txtCode=new FireTextBox("txtCode");
		txtCode.setLabel("Código");
		this.AddControl(txtCode);

		txtName=new FireTextBox("txtName");
		txtName.setLabel("Descripción");
		this.AddControl(txtName);
		
		btnAceptar=new FireButton("btnAceptar");
		btnAceptar.setCaption("Aceptar");
		btnAceptar.AddEventHandler(this);
		this.AddControl(btnAceptar);
		
		btnCancelar=new FireButton("btnCancelar");
		btnCancelar.setCaption("Cancelar");
		btnCancelar.AddEventHandler(this);
		this.AddControl(btnCancelar);
		
	
		this.conn=conn;
	}
	
	public void Get(String code ) {
		familia=servicio.GetByKey(code, conn);
		
		this.txtCode.setValue(familia.getCode());
		this.txtName.setValue(familia.getName());
	}
	
	public boolean Update() {
		familia.setName(this.txtName.getValue());
		if (servicio.Update(familia, conn)) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void FireEvent(FireEventArg arg)  {
		if (arg.getFireControl().getId().equals(btnCancelar.getId()) && arg.getEvent().equals("Click")) {
			FireEventArg arg2=new FireEventArg(this,"CANCELAR","");				
			eventHandler.FireEvent(arg2);		
		}else if (arg.getFireControl().getId().equals(btnAceptar.getId()) && arg.getEvent().equals("Click")){
			if (Update()) {
				FireEventArg arg2=new FireEventArg(this,"ACEPTAR",familia.getCode());				
				eventHandler.FireEvent(arg2);				
			}
		}
	}
}
