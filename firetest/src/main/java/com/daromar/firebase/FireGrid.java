package com.daromar.firebase;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.firebase.database.FirebaseDatabase;

public class FireGrid
extends FireControl{
	
	public FireGrid(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	private int rows=5;
	private ResultSet resultSet=null;
	private List<FireGridColumn> columns=new ArrayList<FireGridColumn>();
	private int page=0;

	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	@Override
	public void InitializeComponent() {
		// TODO Auto-generated method stub
		app=this.parent.getApp();

		String path=this.parent.getPath()+"/"+id;
		
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/Design/"+path+"/Rows").setValue("5");	
		
		for(FireGridColumn col: this.columns)
		{
			col.InitializeComponent(app.getBasePath()+"/Design/"+path+"/Columns/");
		}
	}

	public void AddColumn(FireGridColumn column) {
		columns.add(column);
		column.setParent(this);
	}
}
