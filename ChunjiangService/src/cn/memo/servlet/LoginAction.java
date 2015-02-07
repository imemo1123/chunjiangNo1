package cn.memo.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.memo.handle.UserHandle;
import cn.memo.instance.User;
import cn.memo.json.JsonHandle;


/**
 * Servlet implementation class LoginAction
 */
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public LoginAction() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("�յ�����:login" );
		String result = null;
		BufferedReader br = new BufferedReader(
			new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
		StringBuffer sb =new StringBuffer("");
		String temp;
		while((temp=br.readLine())!=null){
			sb.append(temp);
		}
		br.close();
		result = sb.toString();
		System.out.println("������:" + result);
		User user = JsonHandle.getObject(result, new User().getClass());
		UserHandle userHandle = new UserHandle(user);
		boolean loginStt = userHandle.signIn();
		Map<String, Object>rstMap = new HashMap<String, Object>();
		System.out.println(loginStt);
		if(loginStt){
			System.out.println(1);
			user = userHandle.getUserInfoByMobile();
			System.out.println(user.getId());
			rstMap.put("id", user.getId());
			rstMap.put("name", user.getName());
			rstMap.put("mobile", user.getMobile());
			rstMap.put("salesman", user.getSalesman());
			rstMap.put("type", user.getType());
		}
		rstMap.put("stt", loginStt);
		result= JsonHandle.toJson(rstMap);
		System.out.println("���ر���:" + result);
		PrintWriter pw = response.getWriter();
		pw.write(result);
		pw.flush();
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
