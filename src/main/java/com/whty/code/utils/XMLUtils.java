package com.whty.code.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.whty.code.vo.CodeReleaseShell;
import com.whty.code.vo.Column;
import com.whty.code.vo.Server;
import com.whty.code.vo.Table;

public class XMLUtils {
	
	/**
	 * 加载xml
	 * @param xmlPath
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Table loadXML(String xmlPath) throws Exception{
		Table table = new Table();
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new File(xmlPath));
		Element root = document.getRootElement(); 
		table.setTableName(root.attributeValue("name"));
		table.setClassName(root.attributeValue("className"));
		table.setPackageName(root.attributeValue("packageName"));
		List<Element> columns = root.elements("column");
		List<Column> temp = new ArrayList<Column>();
		for (Element column : columns) {
			Column tmepColumn = new Column();
			tmepColumn.setColumn(column.attributeValue("name"));
			tmepColumn.setType(column.attributeValue("type"));
			tmepColumn.setJdbcType(column.attributeValue("jdbcType"));
			tmepColumn.setAttributes(column.getText());
			tmepColumn.setDescription(column.attributeValue("description"));
			temp.add(tmepColumn);
		}
		table.setColumns(temp);
		return table;
	}
	
	/**
	 * 加载xml
	 * @param xmlPath
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static CodeReleaseShell loadShellXML(String xmlPath) throws Exception{
		CodeReleaseShell shell = new CodeReleaseShell();
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new File(xmlPath));
		Element root = document.getRootElement(); 
		shell.setCodeVersion(root.attributeValue("codeVersion"));
		shell.setPlatformName(root.attributeValue("platformName"));
		shell.setSourcePath(root.attributeValue("sourcePath"));
		List<Element> servers = root.elements("server");
		List<Server> temp = new ArrayList<Server>();
		for (Element server : servers) {
			Server tmepServer = new Server();
			tmepServer.setName(server.attributeValue("name"));
			tmepServer.setIp(server.elementText("ip"));
			tmepServer.setUsername(server.elementText("username"));
			tmepServer.setPassword(server.elementText("password"));
			tmepServer.setServerPath(server.elementText("serverPath"));
			temp.add(tmepServer);
		}
		shell.setServers(temp);
		return shell;
	}
}
