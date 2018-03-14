/**
 * 
 */
package org.edu2act.java.bookshop.beans;

import java.io.Serializable;

/**
 * @author Administrator
 * 
 */
public class BookType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int type_id;
	private String type_name;

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int typeId) {
		type_id = typeId;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String typeName) {
		type_name = typeName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
