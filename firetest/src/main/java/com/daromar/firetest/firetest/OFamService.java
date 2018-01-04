package com.daromar.firetest.firetest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OFamService {
	private String lastError="";
	
	public OFAM GetByKey(String Code,Connection conn) {
		try
		{
			
			String sql="";
			
			sql=" SELECT CODE,NAME";
			sql+=" FROM GISAC_OFAM ";
			sql+=" where code=?";
			
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, Code);
			
			p.setQueryTimeout(30);  // set timeout to 30 sec.
					
			ResultSet rs = p.executeQuery();
			
			if (rs.next()) {
				OFAM cls=new OFAM();
				cls.setCode(Code);
				cls.setName(rs.getString("NAME"));
				
				return cls;
			}else {
				lastError="Elemento no encontrado";
				return null;
			}
	
		}catch(Exception ex) {
			lastError="Error inesperado " + ex.getMessage();
			return null;
		}
	}
	
	public boolean Update(OFAM familia,Connection conn) {
		try {
			String sql="";
			
			sql=" UPDATE GISAC_OFAM";
			sql+=" SET NAME=?";
			sql+=" where code=?";
			
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, familia.getName());
			p.setString(2,familia.getCode());
			
			p.setQueryTimeout(30);  // set timeout to 30 sec.
					
			p.executeUpdate();
			
			return true;
			
		}catch(Exception ex){
			lastError="Error inesperado " +ex.getMessage();
			return false;
		}
	}

}
