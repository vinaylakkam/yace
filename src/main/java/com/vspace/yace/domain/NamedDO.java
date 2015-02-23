package com.vspace.yace.domain;

/**
 * Simple JavaBean domain object adds a name property to <code>BaseDO</code>.
 * Used as a base class for objects needing these properties.
 *
 * @author Vinay Lakkam
 */
public class NamedDO extends IdentityDO {

	private String name;
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.getName();
	}

}