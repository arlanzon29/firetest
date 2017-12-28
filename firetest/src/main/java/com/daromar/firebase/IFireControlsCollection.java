package com.daromar.firebase;

import java.util.ArrayList;
import java.util.List;

public interface IFireControlsCollection 
extends IFireControl{
	
	
	public List<IFireControl> getControls();
	public void AddControl(IFireControl control);
	
	public String getPath();
	public FireApp getApp();
	
}
