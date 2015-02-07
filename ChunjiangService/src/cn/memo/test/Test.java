package cn.memo.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletInputStream;

import org.json.simple.JSONObject;


public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String param = "memo";
		URL url = new URL("http://http://192.168.2.123:8080/login");
		//HttpClient client = new HttpClient();  
		HttpURLConnection httpurlconnection = null;
		httpurlconnection = (HttpURLConnection) url.openConnection();
	     httpurlconnection.setDoOutput(true);
	     httpurlconnection.setDoInput(true);
	     httpurlconnection.setRequestMethod("POST");
	     JSONObject jo = new JSONObject();
	     jo.put("name", param);
	     DataOutputStream out = new DataOutputStream(httpurlconnection.getOutputStream());  
	     out.writeChars(jo.toString());
	     BufferedReader br = new BufferedReader(
					new InputStreamReader((ServletInputStream) httpurlconnection.getInputStream(), "utf-8"));
	     StringBuffer sb =new StringBuffer("");
			String temp;
			while((temp=br.readLine())!=null){
				sb.append(temp);
			}
			br.close();
			String result = sb.toString();
			System.out.print(result);
	     
	}

}
