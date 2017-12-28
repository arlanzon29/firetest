package com.daromar.firetest.firetest;

import com.daromar.firebase.FireButton;
import com.daromar.firebase.FireDiv;
import com.daromar.firebase.FireLabel;
import com.daromar.firebase.FireTextBox;
import com.daromar.firebase.IButtonClick;

public class PADivAutenticar
extends FireDiv {
	private FireButton btnAceptar;
	private FireTextBox txtUsuario;
	private FireTextBox txtContrasena;
	private FireLabel lblError;
	
	public PADivAutenticar(String id) {
		super(id);
		
		btnAceptar=new FireButton("btnAceptar");
		btnAceptar.setCaption("Aceptar2");
		this.AddControl(btnAceptar);
		
		txtUsuario=new FireTextBox("txtUsuario");
		txtUsuario.setLabel("User");
		this.AddControl(txtUsuario);
		
		
		txtContrasena=new FireTextBox("txtContrasena");
		txtContrasena.setLabel("Password");
		this.AddControl(txtContrasena);
		
		lblError=new FireLabel("lblError");
		this.AddControl(lblError);
		
		PADivAutenticar div=this;
		
		btnAceptar.AddClickListener(new IButtonClick() {

			@Override
			public void Click() {
				String user=txtUsuario.getValue();
				String pass=txtContrasena.getValue();
				
				if (user.compareTo("manager")==0 && pass.compareTo("secure")==0) {
					lblError.setValue("Ok");
					PrimeraApplicacion pa=(PrimeraApplicacion)div.getApp();
					pa.AutenticacionCorrecta();
					
					/*divMenu.setDisplay("block");*/
				}else {
					lblError.setValue("Usuario o contraseña incorrectarrrr");
				}
			}
			
		});
	}

}
