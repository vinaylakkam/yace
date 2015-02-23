package com.vspace.yace.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;

// TODO: @RooJavaBean is not working; check y
@RooJavaBean 
public class User extends IdentityDO{
	
	Integer assoId;
	String cName;
	String fName;
	String lName;
	String eMail;
	String role;
	String systemIP;
   
	 
	public Integer getAssoId() {
		return assoId;
	}

	public void setAssoId(Integer assoId) {
		this.assoId = assoId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSystemIP() {
		return systemIP;
	}

	public void setSystemIP(String systemIP) {
		this.systemIP = systemIP;
	}

	public boolean isAdmin(){
		return "ADMIN".equalsIgnoreCase(role);
	}
}
