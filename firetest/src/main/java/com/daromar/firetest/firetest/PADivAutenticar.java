package com.daromar.firetest.firetest;

import com.daromar.firebase.FireButton;
import com.daromar.firebase.FireDiv;
import com.daromar.firebase.FireEventArg;
import com.daromar.firebase.FireLabel;
import com.daromar.firebase.FireTextBox;
import com.daromar.firebase.IButtonClick;
import com.daromar.firebase.IFireEvent;

public class PADivAutenticar
extends FireDiv {
	private FireButton btnAceptar;
	private FireTextBox txtUsuario;
	private FireTextBox txtContrasena;
	private FireLabel lblError;
	
	public PADivAutenticar(String id) {
		super(id);
		
		btnAceptar=new FireButton("btnAceptar");
		btnAceptar.setCaption("Aceptar");
		btnAceptar.AddEventHandler(this);
		this.AddControl(btnAceptar);
		
		txtUsuario=new FireTextBox("txtUsuario");
		txtUsuario.setLabel("User");
		this.AddControl(txtUsuario);
		
		
		txtContrasena=new FireTextBox("txtContrasena");
		txtContrasena.setLabel("Password");
		this.AddControl(txtContrasena);
		
		lblError=new FireLabel("lblError");
		this.AddControl(lblError);
		
	}
	
	
	@Override
	public void FireEvent(FireEventArg arg) {
		if (arg.getFireControl().getId().equals(btnAceptar.getId())){
			if (arg.getEvent().equals("Click")) {
				Autenticar();
			}
		}
	}
	
	private void Autenticar() {
		String user=txtUsuario.getValue();
		String pass=txtContrasena.getValue();
		
		if (user.compareTo("manager")==0 && pass.compareTo("secure")==0) {
			lblError.setValue("Ok");
			if (eventHandler!=null) {
				FireEventArg arg=new FireEventArg(this,"AUTENTICAR","");				
				eventHandler.FireEvent(arg);
			}
			
		}else {
			lblError.setValue("Usuario o contraseña incorrectar");
		}
	}

}
