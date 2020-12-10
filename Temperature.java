import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;
import com.google.gson.reflect.*;
import java.util.ArrayList;

public class Temperature {
	
	public static Map<String, Object> jsonToMap(String str){
		Map<String,Object> map=new Gson().fromJson(
				str,new TypeToken<HashMap<String,Object>>(){}.getType()
				);
		return map;
	}
	public static void MaxI(ArrayList l,ArrayList l2) {
		System.out.println(Collections.max(l));
		System.out.println(Collections.min(l2));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String API_KEY="b6907d289e10d714a6e88b30761fae22";
		String Location="London,us";
		String urlString="https://samples.openweathermap.org/data/2.5/forecast/hourly?q="+Location+"&appid="+API_KEY+"&units=imperial";
		try {
			StringBuilder result=new StringBuilder();
			URL url=new URL(urlString);
			URLConnection conn=url.openConnection();
			BufferedReader rd=new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while((line=rd.readLine())!=null) {
				result.append(line);
			}
			rd.close();
			System.out.println(result);
			Map<String,Object> respMap=jsonToMap(result.toString());
			Map<String,Object> mainMap=jsonToMap(respMap.get("main").toString());
			Map<String,Object> windMap=jsonToMap(respMap.get("wind").toString());
			ArrayList<Double> tem_max=new ArrayList<>();
			ArrayList<Double> tem_min=new ArrayList<>();
			System.out.println("curr temp: "+mainMap.get("temp"));
			tem_max.add(mainMap.get("temp").get("temp_max"));
			tem_min.add(mainMap.get("temp").get("temp_min"));
			System.out.println("current humidity: "+mainMap.get("humidity"));
			System.out.println("Wind Speeds: "+windMap.get("speed"));
			System.out.println("Wind Angle: "+windMap.get("deg"));
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		

	}

}
