package com.daromar.firebase;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.firebase.database.FirebaseDatabase;

public class FireGrid
extends FireControl{
	
	Connection conn;
	
	public FireGrid(String id,Connection conn) {
		super(id);
		// TODO Auto-generated constructor stub
		this.conn=conn;
	}

	private int rows=5;
	private ResultSet resultSet=null;
	private List<FireGridColumn> columns=new ArrayList<FireGridColumn>();
	private int page=0;
	private String tableName="";

	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		this.ExecuteQuery();
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
	
	public void ExecuteNewQuery() {
		this.page=1;
		this.ExecuteQuery();	
	}
	
	private void ExecuteQuery() {
		try {
			ResultSet rs=this.createResultSet();
			int linea=0;
			
			 while(rs.next() && linea<this.rows )
			 {
				for(FireGridColumn col: this.columns)
				{
					 col.setValue(linea,rs.getString(col.getColumnName()));
				}
				linea+=1;
			 }
			 
			 for (int i=linea;i<this.rows ;i++) {
				 for(FireGridColumn col: this.columns)
					{
						 col.setValue(i,"");
					} 
			 }
			
		}catch(Exception ex)
		{
			
		}
	
	}
	
	
	private ResultSet createResultSet() {
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			
			String sql="";
			
			sql=" SELECT CODE,NAME";
			sql+=" FROM GISAC_OFAM ";
			sql+=" ORDER BY CODE";
			sql+=" LIMIT "+ this.rows+" OFFSET " +((this.getPage()-1)*this.rows);
			ResultSet rs = statement.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
