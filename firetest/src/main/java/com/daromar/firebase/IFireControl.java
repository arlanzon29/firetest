package com.daromar.firebase;

public interface IFireControl {
	
	public String getId();
	public void setId(String id);
	
	public void InitializeComponent();
	public void ResetComponent();
	
	public String getValue();
	public void setValue(String value);


	public void setParent(IFireControlsCollection parent);


	public void AddEventHandler(IFireEvent eventHandler);
}
