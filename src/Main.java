import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class Main {
	public static void main(String[] args) throws IOException, JSONException {
		String realm = args[0];
		String charactername = args[1];
		String slottype = args[2];
		
		URL apiurl = new URL("http://us.battle.net/api/wow/character/"+realm+"/"+charactername+"?fields=items");
		URLConnection uc = apiurl.openConnection();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		
		String result = new String();
		
		while(br.ready()){
			result += br.readLine();
		}
		br.close();
		
		JSONObject job = new JSONObject(result);
		JSONObject items = job.getJSONObject("items");
		
		JSONObject slot = items.getJSONObject(slottype);
		String slotname = slot.getString("name");
		
		System.out.println("The item is: " + slotname + "\nhttp://www.wowhead.com/item="+slot.getInt("id") + "/");
	}
}