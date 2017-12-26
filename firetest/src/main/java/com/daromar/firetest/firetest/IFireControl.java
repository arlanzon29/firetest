package com.daromar.firetest.firetest;

public interface IFireControl {
	
	public String getId();
	public void setId(String id);
	
	public void InitializeComponent();
	
	public String getValue();
	public void setValue(String value);


	public void setParent(IFireControlsCollection parent);


}
