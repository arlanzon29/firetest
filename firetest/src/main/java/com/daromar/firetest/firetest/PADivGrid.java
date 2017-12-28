package com.daromar.firetest.firetest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.daromar.firebase.FireDiv;
import com.daromar.firebase.FireGrid;
import com.daromar.firebase.FireGridColumn;

public class PADivGrid
extends FireDiv{

	private FireGrid grdFamilias;
	private Connection conn;
	private FireGridColumn colCodigo;
	private FireGridColumn colDescripcion;
	
	public PADivGrid(String id,Connection conn) {
		super(id);
		// TODO Auto-generated constructor stub
		
		this.conn=conn;
		
		grdFamilias=new FireGrid("grdFamilias");
		grdFamilias.setRows(5);
		
		colCodigo=new FireGridColumn("colCodigo");
		colCodigo.setCaption("Código");
		grdFamilias.AddColumn(colCodigo);
		
		colDescripcion=new FireGridColumn("colDescripcion");
		colDescripcion.setCaption("Descripción");
		grdFamilias.AddColumn(colDescripcion);
		//grdFamilias.setResultSet(ExecuteQuery());

		this.AddControl(grdFamilias);
	}

	private ResultSet ExecuteQuery() {
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			
			String sql="";
			
			sql=" SELECT CODE,NAME";
			sql+=" FROM GISAC_OFAM ";
			
			ResultSet rs = statement.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
