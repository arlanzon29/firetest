package com.daromar.firetest.firetest;

public class PrimeraApplicacion extends FireApp {
	

	
	private Button btnAceptar;
	private TextBox txtUsuario;
	private TextBox txtContrasena;
	private Label lblError;
	
	
	public PrimeraApplicacion(String basePath) {
		super(basePath);
		
		btnAceptar=new Button(this,"btnAceptar");
		txtUsuario=new TextBox(this,"txtUsuario");
		txtContrasena=new TextBox(this,"txtContrasena");
		lblError=new Label(this,"lblError");
		
		this.InitializeApp();
		
		/*lblError.setValue("");
		txtUsuario.setValue("");
		txtContrasena.setValue("");
		btnAceptar.setValue("0");*/
				
		
		btnAceptar.AddClickListener(new IButtonClick() {

			@Override
			public void Click() {
				String user=txtUsuario.getValue();
				String pass=txtContrasena.getValue();
				
				if (user.compareTo("manager")==0 && pass.compareTo("secure")==0) {
					lblError.setValue("Ok");
				}else {
					lblError.setValue("Usuario o contraseña incorrecta");
				}
			}
			
		});
		

		
	}
	
	
}
