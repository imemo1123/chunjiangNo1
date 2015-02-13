package cn.memo.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletInputStream;

import org.json.simple.JSONObject;

import com.sina.sae.util.SaeUserInfo;


public class Test {
	public static void main(String[] args) throws Exception {
		String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_memoandfriends";
		String username=SaeUserInfo.getAccessKey();
		String password=SaeUserInfo.getSecretKey();
		String driver="com.mysql.jdbc.Driver";
		Class.forName(driver).newInstance();
		Connection con=DriverManager.getConnection(url,username,password);
	}
}
