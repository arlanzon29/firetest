package com.daromar.firetest.firetest;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



/**
 * Hello world!
 *
 */
public class App 
{
	
	public void ConectarFirebase() throws Exception{
		 FileInputStream serviceAccount = new FileInputStream("C:/Users/daniel.rodriguez/git/firetest/firetest/firebase/gamonal29-5da0fbfe8866.json");

	        FirebaseOptions options = new FirebaseOptions.Builder()
	          .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
	          .setDatabaseUrl("https://gamonal29.firebaseio.com/")
	          .build();

	        FirebaseApp.initializeApp(options);
	        
	        
	       
	}
    public static void main( String[] args ) throws ClassNotFoundException
    {
        System.out.println( "Hello World!" );
        
        
       App aplicacion =new App();
       try {
    	   aplicacion.ConectarFirebase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error " +e.getMessage());
		}
       
       PrimeraApplicacion app=new PrimeraApplicacion("app3","c:/borrar/app3.db");
    //  PrimeraApplicacion app2=new PrimeraApplicacion("app2");
       
     
      
       
       
       
       while(true) {
    	   try {
			Thread.sleep(1 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       }
       
    }
}
