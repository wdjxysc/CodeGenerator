package com.whty.code.main;


import com.whty.code.utils.GenJavaFile;
import com.whty.code.vo.Column;
import com.whty.code.vo.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StartGenNew
{
    static String url = "jdbc:mysql://rm-uf6w41brqml6e29lxto.mysql.rds.aliyuncs.com:3306";
    static String username = "zhxy_user";
    static String password = "pass4zhxy_user";
    static Connection conn;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");// 指定连接类型
            conn = DriverManager.getConnection(url,username,password);//获取连接
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    public static void generateTable(String tableStr) throws Exception{
        // 表名，类名，包名
        String[] arr = tableStr.split("\\|");
        String tableName = arr[0];
        System.out.println("读取表信息:" +tableName);

        Table table = new Table();
        table.setTableName(tableName);
        table.setClassName(arr[1]);
        table.setPackageName("com.whty.zhxy.service.userManage");
        List<Column> temp = new ArrayList<Column>();

        PreparedStatement ps = conn.prepareStatement(
                "select column_name,data_type,column_comment from information_schema.columns where table_name = '"
                        + tableName + "'");// 准备执行语句
        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            Column tmepColumn = new Column();
            tmepColumn.setColumn(rs.getString("column_name"));
            switch (rs.getString("data_type")) {
                case "decimal":
                case "bigint":
                    tmepColumn.setType("Long");
                    break;
                case "text":
                case "varchar":
                case "char":
                    tmepColumn.setType("String");
                    break;
                case "datetime":
                case "timestamp":
                    tmepColumn.setType("Date");
                    break;
                case "tinyint":
                    tmepColumn.setType("boolean");
                    break;
                default:
                    tmepColumn.setType(rs.getString("data_type"));
                    break;
            }

            switch (rs.getString("data_type")) {
                case "bigint":
                case "int":
                    tmepColumn.setJdbcType("DECIMAL");
                    break;
                case "varchar":
                    tmepColumn.setJdbcType("VARCHAR");
                    break;
                case "datetime":
                case "date":
                    tmepColumn.setJdbcType("TIMESTAMP");
                    break;
                default:
                    tmepColumn.setJdbcType(rs.getString("data_type").toUpperCase());
                    break;
            }

            String column_name = rs.getString("column_name").toLowerCase();
            String attributes = replaceUpper(column_name);
            tmepColumn.setAttributes(attributes);
            tmepColumn.setDescription(rs.getString("column_comment"));
            temp.add(tmepColumn);
        }
        table.setColumns(temp);
        GenJavaFile.genJavaFile(table);
    }

    public static void main(String[] args)
        throws Exception
    {
        List<String> tableList = new ArrayList<>();

        /* 基础表 */
//        tableList.add("T_ORGA_PHASE|AamOrgaPhase");//学段表
//        tableList.add("T_GROUP_IDENTITY_INFO|AamGroupIdentityInfo");//学段表
//        tableList.add("T_GROUP_IDENTITY_INFO|AamGroupIdentityInfo");//学段表


        /* 用户信息相关表 */
//        tableList.add("T_USER|AamUser");//用户表
//        tableList.add("T_USER_DEL|AamUserDel");//用户删除记录表
//        tableList.add("T_USER_GROUP_REL|AamUserGroupRel");//用户群组关系表
//        tableList.add("T_USER_GROUP_IDENTITY|AamUserGroupIdentity");//用户群组身份
//        tableList.add("T_BIND_PAERNT|AamBindParent");//绑定家长
//        tableList.add("T_MICROB|AamMicrob");//用户关注
//        tableList.add("T_ACCOUNT|AamAccount");//帐号表
//        tableList.add("T_ACCOUNT_REPEAT|AamAccountRepeat");//重复账号表
//        tableList.add("T_ACCOUNT_DEL|AamAccountDel");//账号删除记录表
//        tableList.add("T_USER_LOGO|AamUserLogo");//用户头像信息表
//        tableList.add("T_RESEARCHER_SUBJECT|AamResearcherSubject");//教研员学科信息表
//        tableList.add("T_TEACHER_SUBJECT|AamTeacherSubject");//教师学科管理
//        tableList.add("T_TEACHER_FOCUS_MATERIALS|AamTeacherFocusMaterials");//教师学科管理
//        tableList.add("T_TEACHER_MAJOR_SUBJECT|AamTeacherMajorSubject");//教师主教学科表

        /* 机构班级相关表 */
        tableList.add("T_ORGANIZATION|AamOrganization");//组织机构信息表
        tableList.add("T_CLASS_UPGRADE_PERIOD|AamClassUpgradePeriod");//统一学校班级升级时间表
        tableList.add("T_ORGA_DEPT_TREE|AamOrgaDeptTree");//学校机构组织部门树形关系表
        tableList.add("T_ORGA_DEPT|AamOrgaDept");//学校机构组织部门表
        tableList.add("T_ORGA_DEPT_USER|AamOrgaDeptUser");//学校机构组织部门表用户表
        tableList.add("T_CLASS_INFO|AamClassInfo");//班级信息
        tableList.add("T_CLASS_PRO_TYPE_REL|AamClassProTypeRel");//职教班级类型关系表
        tableList.add("T_SCHOOL_AUTH_INFO|AamSchoolAuthInfo");//智慧校园开通
        tableList.add("T_SCHOOL_AUTH_USER_INFO|AamSchoolAuthUserInfo");//智慧校园开通体验用户表
        tableList.add("T_SCHOOL_GROUP_REL|AamSchoolGroupRel");//集团分校关系表
        tableList.add("T_BUREAU_OF_EDU|AamBureauOfEdu");//主机构映射表


        /*  */



        for (String tableItem: tableList){
            generateTable(tableItem);
        }
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
