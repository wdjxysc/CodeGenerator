package com.whty.code.vo;

public class Column {
	
	/**
	 * 列名
	 */
	private String column;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 属性
	 */
	private String attributes;
	
	/**
	 * 属性类型
	 */
	private String type;
	
	private String jdbcType;
	
	public String getJdbcType() {
		return jdbcType;
	}
	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
