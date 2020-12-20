package jsonfiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Temperature {
	
	public static void max_Temp(JSONArray arr) {
		double max=0;
		String s="";
		for(Object obj:arr) {
			if(obj instanceof JSONObject) {
				JSONObject jobj=(JSONObject)obj;
				JSONObject job=(JSONObject)jobj.get("main");
				JSONArray jarray=(JSONArray)jobj.get("weather");
				JSONObject jarrayobj=(JSONObject)jarray.get(0);
				//System.out.println(jarrayobj.get("main"));
				if(jarrayobj.get("main").toString().contains("Rain")) {
					System.out.println(jobj.get("dt_txt"));
				}
				//System.out.println(job.get("temp_max").getClass().getName());
				if(job.get("temp_max").getClass().getName().equals("java.lang.Long")) {
					continue;
				}
				//  System.out.println((Double)job.get("temp_max"));
				double val=(Double)job.get("temp_max");
				if(val>max) {
					max=val;
					s=jobj.get("dt_txt").toString();
				}
			}
		}
		System.out.println("S---->"+s);
		System.out.println("Max: "+max);
	}
	public static void giveDate(JSONObject obj,String s) {
		String str=obj.get("dt_txt").toString();
		if(str.equals(s)) {
			JSONObject jmain=(JSONObject)obj.get("main");
			System.out.println("temp_min: "+jmain.get("temp_min"));
			System.out.println("temp_max: "+jmain.get("temp_max"));
			System.out.println("humidity: "+jmain.get("humidity"));
			JSONArray jarr=(JSONArray)obj.get("weather");
			JSONObject wea=(JSONObject)jarr.get(0);
			System.out.println("Weather_Main: "+wea.get("main"));
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		String input=sc.nextLine();
		JSONParser parser=new JSONParser();
		try(FileReader fr=new FileReader("I:\\Work\\TrainingPlan\\src\\jsonfiles\\data.json")){
			JSONArray jarr=(JSONArray)parser.parse(fr);
			System.out.println(jarr.size());
			//System.out.println(jarr);
			for(Object obj:jarr) {
				if(obj instanceof JSONObject) {
					JSONObject jobj=(JSONObject)obj;
					giveDate(jobj,input);
					//if(jobj.get("dt_txt"))
					//System.out.println(jobj);
				}
			}
			max_Temp(jarr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
