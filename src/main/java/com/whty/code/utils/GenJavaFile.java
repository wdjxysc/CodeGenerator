package com.whty.code.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.whty.code.vo.Table;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;


public class GenJavaFile {
	
//	private static String BASE_FILE_PATH=GenJavaFile.class.getResource("/").getFile();
	private static String BASE_FILE_PATH="D://code_tool_generate/";
	/**
	 * 生成java代码
	 * @param table
	 */
	public static void genJavaFile(Table table) throws Exception{
		String dtoFilePath = BASE_FILE_PATH + table.getTableName() + File.separator + "bean";
		String doFilePath = BASE_FILE_PATH + table.getTableName() + File.separator + "entity";
		String serviceFilePath = BASE_FILE_PATH + table.getTableName() + File.separator + "service";
		String serviceImplFilePath = BASE_FILE_PATH + table.getTableName() + File.separator + "service" + File.separator +"impl";
		String mappingFilePath = BASE_FILE_PATH + table.getTableName() + File.separator + "mapper";
		
		System.out.println("开始执行生成java文件。。。");
		// 生成dto
		File dtoFile = new File(dtoFilePath);
		if(!dtoFile.exists()){
			dtoFile.mkdirs();
		}
		CeateFile("bean.tlt",table,dtoFilePath+File.separator+table.getClassName()+"DTO.java");
		
		// 生成do
		File daoFile = new File(doFilePath);
		if(!daoFile.exists()){
			daoFile.mkdirs();
		}
		CeateFile("entity.tlt",table,daoFile+File.separator+table.getClassName()+"DO.java");
		
		// 生成service
		File serviceFile = new File(serviceFilePath);
		if(!serviceFile.exists()){
			serviceFile.mkdirs();
		}
		CeateFile("service.tlt",table,serviceFilePath+File.separator+table.getClassName()+"Service.java");
		
		// 生成mapping
		File mappingFile = new File(mappingFilePath);
		if(!mappingFile.exists()){
			mappingFile.mkdirs();
		}
		CeateFile("mapping.tlt",table,mappingFilePath+File.separator+table.getClassName()+"Mapper.xml");
		
		// 生成serviceImpl
		File serviceImplFile = new File(serviceImplFilePath);
		if(!serviceImplFile.exists()){
			serviceImplFile.mkdirs();
		}
		CeateFile("serviceImpl.tlt",table,serviceImplFilePath+File.separator+table.getClassName()+"ServiceImpl.java");
		System.out.println("生成java文件成功。。。");
		
	}
	
	
	public static void CeateFile(String templateName,Table table,String filePath) throws Exception{
		Configuration configuration = new Configuration(); 
        configuration.setDirectoryForTemplateLoading(new File(GenJavaFile.class.getResource("/").getFile()));  
        configuration.setObjectWrapper(new DefaultObjectWrapper());  
        configuration.setDefaultEncoding("UTF-8");   //这个一定要设置，不然在生成的页面中 会乱码  
        //获取或创建一个模版。  
        Template template = configuration.getTemplate(templateName); 
        Writer writer  = new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8");  
        Map<String,Object> root = new HashMap<String,Object>();
        root.put("table", table);
        template.process(root, writer);  
	}
}
