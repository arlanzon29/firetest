package com.daromar.firetest.firetest;

public class PrimeraApplicacion extends FireApp {
	

	private FireDiv divAutenticar;
	private FireDiv divMenu;
	
	private FireButton btnAceptar;
	private FireTextBox txtUsuario;
	private FireTextBox txtContrasena;
	private FireLabel lblError;
	
	
	public PrimeraApplicacion(String basePath) {
		super(basePath);
		
		divAutenticar=new FireDiv("divAutenticar");
		this.AddControl(divAutenticar);
		
		
		btnAceptar=new FireButton("btnAceptar");
		btnAceptar.setCaption("Aceptar");
		divAutenticar.AddControl(btnAceptar);
		
		txtUsuario=new FireTextBox("txtUsuario");
		txtUsuario.setLabel("User");
		divAutenticar.AddControl(txtUsuario);
		
		
		txtContrasena=new FireTextBox("txtContrasena");
		txtContrasena.setLabel("Password");
		divAutenticar.AddControl(txtContrasena);
		
		lblError=new FireLabel("lblError");
		divAutenticar.AddControl(lblError);
		
		divMenu=new FireDiv("divMenu");
		divMenu.setDisplay("none");
		this.AddControl(divMenu);
		
		this.InitializeApp();
		

				
		
		btnAceptar.AddClickListener(new IButtonClick() {

			@Override
			public void Click() {
				String user=txtUsuario.getValue();
				String pass=txtContrasena.getValue();
				
				if (user.compareTo("manager")==0 && pass.compareTo("secure")==0) {
					lblError.setValue("Ok");
					divAutenticar.setDisplay("none");
					divMenu.setDisplay("block");
				}else {
					lblError.setValue("Usuario o contraseña incorrectarrrr");
				}
			}
			
		});
		

		
	}
	
	
}
