package com.daromar.firetest.firetest;

import com.daromar.firebase.FireDiv;
import com.daromar.firebase.FireGrid;
import com.daromar.firebase.FireGridColumn;

public class PADivGrid
extends FireDiv{

	private FireGrid grdFamilias;

	private FireGridColumn colCodigo;
	private FireGridColumn colDescripcion;
	
	
	public PADivGrid(String id) {
		super(id);
		// TODO Auto-generated constructor stub
		
		grdFamilias=new FireGrid("grdFamilias");
		
		colCodigo=new FireGridColumn("colCodigo");
		colCodigo.setCaption("Código");
		grdFamilias.AddColumn(colCodigo);
		
		colDescripcion=new FireGridColumn("colDescripcion");
		colDescripcion.setCaption("Descripción");
		grdFamilias.AddColumn(colDescripcion);
			
		
		this.AddControl(grdFamilias);
	}

}
