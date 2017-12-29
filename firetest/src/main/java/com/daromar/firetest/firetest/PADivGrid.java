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
		
		grdFamilias=new FireGrid("grdFamilias",conn);
		grdFamilias.setTableName("GISAC_OFAM");
		grdFamilias.setRows(5);
		
		colCodigo=new FireGridColumn("colCodigo");
		colCodigo.setCaption("Código");
		colCodigo.setColumnName("CODE");
		grdFamilias.AddColumn(colCodigo);
		
		colDescripcion=new FireGridColumn("colDescripcion");
		colDescripcion.setCaption("Descripción");
		colDescripcion.setColumnName("NAME");
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
			txtPosicion.setValue("1");
			grdFamilias.ExecuteNewQuery();
		}else if (arg.getFireControl().getId().equals(btnAtras.getId()) && arg.getEvent().equals("Click")){
			Atras();
		}else if (arg.getFireControl().getId().equals(btnAdelante.getId()) && arg.getEvent().equals("Click")){
			Adelante();
		}
		
		
	}
	
	private void Atras() {
		if (grdFamilias.getPage()>0) {
			grdFamilias.setPage(grdFamilias.getPage()-1);
			txtPosicion.setValue(""+grdFamilias.getPage());
		}
	}

	private void Adelante() {
		grdFamilias.setPage(grdFamilias.getPage()+1);
		txtPosicion.setValue(""+grdFamilias.getPage());
	}
	
}
