package game;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class UserDA {
	public static String userName = "";
	public static String password = "";
	static Connection aConnection = null;
	static Statement aStatement = null;
	public static UserDA userda; //单例模式
	public static boolean NotInit = true;
	static final String DB_URL = "jdbc:mysql://139.99.107.83:3306/?characterEncoding=gbk&useSSL=true&serverTimezone=GMT%2B8";
	
	public UserDA() {
		initialize();
		userda = this;
	}
	
	public static void initialize() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String USER = "z8y9ak6";
			String PASS = "RLkqTHU6EV51uDn8";
			
			System.out.println("connect to datebase...\n");
			aConnection = DriverManager.getConnection(DB_URL,USER,PASS);
			aStatement = aConnection.createStatement();
			
		} catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
	}

	//OK get data from leaderboard1
	public static void getLeaderBoard(ArrayList<String> name, ArrayList<Integer> score) {
		String sql = "SELECT * from z8y9ak6.leaderboard";
		
		try {
			ResultSet rs = aStatement.executeQuery(sql);
	        int temp_score = 0;
	        String temp_name = "";
			while (rs.next()) { // 判断是否还有下一个数据  
				temp_score = rs.getInt("score");
				temp_name = rs.getString("info");
				name.add(temp_name);
				score.add(temp_score);
	        }
			
		} catch (SQLException e) {  
	        System.out.println("查询数据库失败 :" + e.getMessage());  
	    }
	}
	
	//OK
	public static boolean login(String username, String password) {
		if(username.isEmpty()||password.isEmpty()) {
			return false;
		}
		
		String sql = "SELECT * from z8y9ak6.user WHERE name=\"" + username + "\" AND password=\"" + password +"\"";
		try {
			ResultSet rs = aStatement.executeQuery(sql);
	        while (rs.next()) { // 判断是否还有下一个数据  
	        	
	    		UserDA.userName = username; //将参数赋值给静态变量
	    		UserDA.password = password;
	    		
	            return true ;
	        }
			
		} catch (SQLException e) {  
	        System.out.println("查询数据库失败 :" + e.getMessage());  
	    }
		return false;
	}
	
	//OK
	public static boolean checkUserName(String username) {
		String sql = "SELECT * from z8y9ak6.user WHERE name=\""+username+"\"";
		
		try {
			ResultSet rs = aStatement.executeQuery(sql);
	        while (rs.next()) { // 判断是否还有下一个数据  
	            return true ;
	        }
			
		} catch (SQLException e) {  
	        System.out.println("查询数据库失败 :" + e.getMessage());  
	    }
		return false;
	}
	
	//OK
	public static void addUser(String username, String password) {
		String sql = "INSERT INTO z8y9ak6.user (name,password) VALUES (\""+username+"\",\""+password+"\")";
		
		try {
			int rs = aStatement.executeUpdate(sql);
			if(rs == 1) {
				System.out.println("插入数据成功"+"\n");
			}	
		} catch (SQLException e) {  
	        System.out.println("插入数据失败 :" + e.getMessage());  
	    }
        
	}
	
	//OK TODO:replace the username
	public static void addLog(String gameMood) {
		String temp_username = "torres";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = new String(df.format(new Date()));
		String log = date + " "+gameMood;
		String sql = "INSERT INTO log (name,log) VALUES (\""+temp_username+"\",\""+log+"\")";
		
		try {
			int rs = aStatement.executeUpdate(sql);
			if(rs == 1) {
				System.out.println("插入数据成功"+"\n");
			}	
		} catch (SQLException e) {  
	        System.out.println("插入数据失败 :" + e.getMessage());  
	    }
	}
	
	//OK
	public static void addScore(int score) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = new String(df.format(new Date()));
		String info = UserDA.userName + " " + date;
		
		String sql = "INSERT INTO z8y9ak6.leaderboard (info,score) VALUES (\""+info+"\",\""+score+"\")";
		
		try {
			int rs = aStatement.executeUpdate(sql);
			if(rs == 1) {
				System.out.println("插入数据成功"+"\n");
			}	
		} catch (SQLException e) {  
	        System.out.println("插入数据失败 :" + e.getMessage());  
	    }
		
		
	}
	
	// for test DB
	public static void main(String[] args) {
		
		UserDA da = new UserDA();
//		UserDA.addUser("torres", "123456");
		
//		System.out.println("check one:"+UserDA.checkUserName("torres")); 
//		System.out.println("check two:"+UserDA.checkUserName("tordasdres")); 
		
//		UserDA.addLog("normal mood");
//		UserDA.addScore("score mood",699);
		
		System.out.println("login:"+UserDA.login("torres","123456")); 
//		UserDA.addScore(1);
//		UserDA.addScore(2);
//		UserDA.addScore(3);
//		
//		ArrayList<String> info = new ArrayList<String>();
//		ArrayList<Integer> score = new ArrayList<Integer>();
//		UserDA.getLeaderBoard(info, score);
//		
//		for(int i = 0 ;i < info.size() ; i++) {
//			System.out.println(info.get(i) +"  "+score.get(i));
//		}
//		
//		for(int i=0; i<score.size()-1; i++) {
//			for(int j=0;j<score.size()-1-i;j++){
//				if(score.get(j) < score.get(j+1)) {
//					
//					int temp = score.get(j);
//					score.remove(j);
//					score.add(j,score.get(j));
//					score.remove(j+1);
//					score.add(j+1,temp);
//					
//					String temp_str = info.get(j);
//					info.remove(j);
//					info.add(j,info.get(j));
//					info.remove(j+1);
//					info.add(j+1,temp_str);
//					
//				}
//			}
//		}
//		System.out.println("after sort:\n\n");
//		for(int i = 0 ;i < info.size() ; i++) {
//			System.out.println(info.get(i) +"  "+score.get(i));
//		}
		
	}
	
}
