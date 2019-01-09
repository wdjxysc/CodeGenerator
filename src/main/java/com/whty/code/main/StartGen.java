package com.whty.code.main;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.whty.code.utils.GenJavaFile;
import com.whty.code.utils.GenShellFile;
import com.whty.code.utils.XMLUtils;
import com.whty.code.vo.Column;
import com.whty.code.vo.Table;


public class StartGen
{

    public static void main(String[] args)
        throws Exception
    {
        // System.out.println("加载配置文件:"+StartGen.class.getResource("/").getFile()+File.separator+args[0]);
        // if(args[1].equals("java")){
        // GenJavaFile.genJavaFile(XMLUtils.loadXML(StartGen.class.getResource("/").getFile()+File.separator+args[0]));
        // }
        // else{
        // GenShellFile.genShellFile(XMLUtils.loadShellXML(StartGen.class.getResource("/").getFile()+File.separator+args[0]));
        // }
        // GenJavaFile.genJavaFile(XMLUtils.loadXML("D://t_template_value.xml"));

        // 表名，类名，包名
        String tableName = "t_user";
        System.out.println("读取表信息:" + tableName);

        Table table = new Table();
        table.setTableName(tableName);
        table.setClassName("User");
        table.setPackageName("com.rc.orange.service.login");
        List<Column> temp = new ArrayList<Column>();
        Class.forName("com.mysql.jdbc.Driver");// 指定连接类型
       // Connection conn = DriverManager.getConnection("jdbc:mysql://39.104.73.79/shangli",
        //    "shangli", "whtyEdu@123");// 获取连接
//        Connection conn = DriverManager.getConnection("jdbc:mysql://39.104.73.79/zhxy_app",
//         "zhxy", "pass4zhxy");//获取连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://45.78.52.19/injoker_orange",
                "wdj", "xysc");//获取连接
        PreparedStatement ps = conn.prepareStatement(
            "select column_name,data_type,column_comment from information_schema.columns where table_name = '"
                                                     + tableName + "'");// 准备执行语句
        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            Column tmepColumn = new Column();
            tmepColumn.setColumn(rs.getString("column_name"));
            if (rs.getString("data_type").equals("bigint"))
            {
                tmepColumn.setType("Long");
            }
            else if (rs.getString("data_type").equals("varchar"))
            {
                tmepColumn.setType("String");
            }
            else if (rs.getString("data_type").equals("datetime")||"timestamp".equals(rs.getString("data_type")))
            {
                tmepColumn.setType("Date");
            } else if (rs.getString("data_type").equals("tinyint"))
            {
                tmepColumn.setType("boolean");
            }
            else
            {
                tmepColumn.setType(rs.getString("data_type"));
            }

            
            
            if (rs.getString("data_type").equals("bigint"))
            {
                tmepColumn.setJdbcType("DECIMAL");
            }
            else if (rs.getString("data_type").equals("varchar"))	
            {
                tmepColumn.setJdbcType("VARCHAR");
            }
            else if (rs.getString("data_type").equals("int"))
            {
                tmepColumn.setJdbcType("DECIMAL");
            }
            else if (rs.getString("data_type").equals("datetime"))
            {
                tmepColumn.setJdbcType("TIMESTAMP");
            }
            else if (rs.getString("data_type").equals("date"))
            {
                tmepColumn.setJdbcType("TIMESTAMP");
            }
            else
            {
                tmepColumn.setJdbcType(rs.getString("data_type").toUpperCase());
            }

            /*
            String attributes = "";
            if (rs.getString("column_name").indexOf("_") != -1)
            {
                String temp1 = rs.getString("column_name");
                StringBuffer stringbuffer = new StringBuffer();
                stringbuffer.append(temp1.substring(0, temp1.lastIndexOf("_")));
                if (temp1.lastIndexOf("_") + 2 <= temp1.length())
                {
                    stringbuffer.append(temp1.substring(temp1.lastIndexOf("_") + 1,
                        temp1.lastIndexOf("_") + 2).toUpperCase());
                    stringbuffer.append(
                        temp1.substring(temp1.lastIndexOf("_") + 2, temp1.length()));
                }
                attributes = stringbuffer.toString();
            }
            else
            {
                attributes = rs.getString("column_name");
            }
            */
            String attributes = replaceUpper(rs.getString("column_name"));
            tmepColumn.setAttributes(attributes);
            tmepColumn.setDescription(rs.getString("column_comment"));
            temp.add(tmepColumn);
        }
        table.setColumns(temp);
        GenJavaFile.genJavaFile(table);
    }
    
    public static String replaceUpper(String column){
        if(column.indexOf("_")!=-1){
            int index = column.indexOf("_");
            String  temp = column.substring(0,index);
            if(index+2<column.length()){
                temp+=column.substring(index+1,index+2).toUpperCase();
                temp+=column.substring(index+2);
                return replaceUpper(temp);
            }
        }
        return column;
    }

}
