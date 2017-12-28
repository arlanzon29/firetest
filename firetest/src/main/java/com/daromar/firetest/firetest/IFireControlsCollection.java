package com.daromar.firetest.firetest;

import java.util.ArrayList;
import java.util.List;

import com.daromar.firebase.FireApp;
import com.daromar.firebase.IFireControl;

public interface IFireControlsCollection 
extends IFireControl{
	
	
	public List<IFireControl> getControls();
	public void AddControl(IFireControl control);
	
	public String getPath();
	public FireApp getApp();
	
}
