package com.daromar.firebase;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.FirebaseDatabase;

public class FireGrid
implements IFireControl{
	
	private String id;
	private int rows=5;
	


	private IFireControlsCollection parent=null;
	private FireApp app=null;
	private List<FireGridColumn> columns=new ArrayList<FireGridColumn>();

	

	public FireGrid(String id) {
		this.id=id;
	}
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id=id;
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

	@Override
	public void setValue(String value) {	
		FirebaseDatabase.getInstance().getReference(app.getBasePath()+"/DataSource/"+id).setValue(value);
	}

	@Override
	public void setParent(IFireControlsCollection parent) {
		// TODO Auto-generated method stub
		this.parent=parent;
		
	}
	
	public void AddColumn(FireGridColumn column) {
		columns.add(column);
		column.setParent(this);
	}
}
