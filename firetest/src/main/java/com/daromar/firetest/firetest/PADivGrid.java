package com.daromar.firetest.firetest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.daromar.firebase.FireButton;
import com.daromar.firebase.FireDiv;
import com.daromar.firebase.FireEventArg;
import com.daromar.firebase.FireGrid;
import com.daromar.firebase.FireGridColumn;
import com.daromar.firebase.FireTextBox;

public class PADivGrid
extends FireDiv{

	
	private Connection conn;
	
	private FireButton btnConsultarFamilias;

	private FireButton btnAtras;
	private FireButton btnAdelante;
	private FireTextBox txtPosicion;

	private FireGrid grdFamilias;
	private FireGridColumn colCodigo;
	private FireGridColumn colDescripcion;
	
	public PADivGrid(String id,Connection conn) {
		super(id);
		// TODO Auto-generated constructor stub
		
		this.conn=conn;
		
		btnConsultarFamilias=new FireButton("btnConsultarFamilias");
		btnConsultarFamilias.setCaption("Consultar");
		btnConsultarFamilias.AddEventHandler(this);
		this.AddControl(btnConsultarFamilias);
		
		grdFamilias=new FireGrid("grdFamilias");
		grdFamilias.setRows(5);
		
		colCodigo=new FireGridColumn("colCodigo");
		colCodigo.setCaption("Código");
		grdFamilias.AddColumn(colCodigo);
		
		colDescripcion=new FireGridColumn("colDescripcion");
		colDescripcion.setCaption("Descripción");
		grdFamilias.AddColumn(colDescripcion);
		this.AddControl(grdFamilias);
		
		
		btnAtras=new FireButton("btnAtras");
		btnAtras.setCaption("<");
		btnAtras.AddEventHandler(this);
		this.AddControl(btnAtras);
		
		btnAdelante=new FireButton("btnAdelante");
		btnAdelante.setCaption(">");
		btnAdelante.AddEventHandler(this);
		this.AddControl(btnAdelante);
		
		txtPosicion=new FireTextBox("txtPosicion");
		this.AddControl(txtPosicion);
	}
	
	
	@Override
	public void FireEvent(FireEventArg arg)  {
		if (arg.getFireControl().getId().equals(btnConsultarFamilias.getId()) && arg.getEvent().equals("Click")) {
			grdFamilias.setPage(1);
			txtPosicion.setValue(""+grdFamilias.getPage());
			Consultar();
		}else if (arg.getFireControl().getId().equals(btnAtras.getId()) && arg.getEvent().equals("Click")){
			Atras();
		}else if (arg.getFireControl().getId().equals(btnAdelante.getId()) && arg.getEvent().equals("Click")){
			Adelante();
		}
		
		
	}
	
	private void Atras() {
		if (grdFamilias.getPage()>0) {
			grdFamilias.setPage(grdFamilias.getPage()-1);
			Consultar();
			txtPosicion.setValue(""+grdFamilias.getPage());
		}
	}

	private void Adelante() {
		grdFamilias.setPage(grdFamilias.getPage()+1);
		Consultar();
		txtPosicion.setValue(""+grdFamilias.getPage());
	}
	
	private void Consultar() {
		try {
			ResultSet rs=ExecuteQuery();
			int linea=0;
			
			 while(rs.next() && linea<5)
			 {
				 colCodigo.setValue(linea,rs.getString("CODE"));
				 colDescripcion.setValue(linea,rs.getString("NAME"));
				 linea+=1;
			 }
			 
			 for (int i=linea;i<5;i++) {
				 colCodigo.setValue(i,"");
				 colDescripcion.setValue(i,"");
				 
			 }
			
		}catch(Exception ex)
		{
			
		}
	
	}
	private ResultSet ExecuteQuery() {
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			
			String sql="";
			
			sql=" SELECT CODE,NAME";
			sql+=" FROM GISAC_OFAM ";
			sql+=" ORDER BY CODE";
			sql+=" LIMIT 5 OFFSET " +((grdFamilias.getPage()-1)*5);
			ResultSet rs = statement.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
