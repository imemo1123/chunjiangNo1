package cn.memo.handle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.memo.instance.News;
import cn.memo.sql.SQLConnection;

public class NewsHandle {
	public static List<Map<String,String>> getNewsList() {
		List<Map<String,String>> newsList = new ArrayList<Map<String,String>>();
		String sqlString = "select id,title,picture from news";
		SQLConnection conn = new SQLConnection();
		newsList = conn.queryMulData(sqlString, News.getTabNames());
		
		List<Map<String,String>> newsList1 = new ArrayList<Map<String,String>>();
		for (int i = 0; i < newsList.size(); i++) {
			Map<String,String> m = newsList.get(i);
			String path = (String) m.get("picture");
			String src = MyHandle.getBASE64fromImgPath(path);
			m.put("picture", src);
			newsList1.add(m);
		}
		return newsList1;
	}
	
	public static Map<String, String> getNewsDetail(String id){
		Map<String, String> news = new HashMap<String, String>();
		String sqlString = "select id,title,content,picture from news  where id='"+id+"';";
		SQLConnection conn = new SQLConnection();
		news = conn.querySingleData(sqlString, News.getDetailTabNames());
		String path = (String) news.get("picture");
		String src = MyHandle.getBASE64fromImgPath(path);
		news.put("picture", src);
		return news;
	}
}
