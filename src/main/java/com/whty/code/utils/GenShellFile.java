package com.whty.code.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.whty.code.vo.CodeReleaseShell;
import com.whty.code.vo.Server;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;


public class GenShellFile {
	
	private static String BASE_FILE_PATH=GenShellFile.class.getResource("/").getFile();
//	private static String BASE_FILE_PATH="D://";
	
	public static void genShellFile(CodeReleaseShell shell) throws Exception{
		String shellFilePath = BASE_FILE_PATH + shell.getPlatformName() + File.separator + "shell";
		System.out.println("开始执行生成shell。。。");
		File voFile = new File(shellFilePath);
		if(!voFile.exists()){
			voFile.mkdirs();
		}
		// 编译
		CeateFile("compile.tlt",shell,shellFilePath+File.separator+"compile.sh");
		// 发布
		CeateFile("release.tlt",shell,shellFilePath+File.separator+"release.sh");
		for(Server server : shell.getServers()){
			// 备份
			CeateFile("backup.tlt",server,shellFilePath+File.separator+server.getName()+"_backup.sh");
		}
		// 重启
		CeateFile("restart.tlt",shell,shellFilePath+File.separator+"restart.sh");
		System.out.println("生成shell文件成功。。。");
	}
	
	
	public static void CeateFile(String templateName,CodeReleaseShell shell,String filePath) throws Exception{
		Configuration configuration = new Configuration(); 
        configuration.setDirectoryForTemplateLoading(new File(GenShellFile.class.getResource("/").getFile()));  
        configuration.setObjectWrapper(new DefaultObjectWrapper());  
        configuration.setDefaultEncoding("UTF-8");   //这个一定要设置，不然在生成的页面中 会乱码  
        //获取或创建一个模版。  
        Template template = configuration.getTemplate(templateName); 
        Writer writer  = new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8");  
        Map<String,Object> root = new HashMap<String,Object>();
        root.put("shell", shell);
        template.process(root, writer);  
	}
	
	public static void CeateFile(String templateName,Server server,String filePath) throws Exception{
		Configuration configuration = new Configuration(); 
        configuration.setDirectoryForTemplateLoading(new File(GenShellFile.class.getResource("/").getFile()));  
        configuration.setObjectWrapper(new DefaultObjectWrapper());  
        configuration.setDefaultEncoding("UTF-8");   //这个一定要设置，不然在生成的页面中 会乱码  
        //获取或创建一个模版。  
        Template template = configuration.getTemplate(templateName); 
        Writer writer  = new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8");  
        Map<String,Object> root = new HashMap<String,Object>();
        root.put("server", server);
        template.process(root, writer);  
	}
}
