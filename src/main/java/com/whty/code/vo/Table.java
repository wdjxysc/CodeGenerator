package com.whty.code.vo;

import java.util.List;

public class Table {

	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 类名
	 */
	private String className;
	/**
	 * 属性对应关系
	 */
	private List<Column> columns;
	/**
	 * 包名
	 */
	private String packageName;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
}
