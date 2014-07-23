package otpp.workflow.TestNGClient;
import java.util.HashMap;

public class Count {
	private RestHelper_ProcessInstance processInstanceRestHelper = new RestHelper_ProcessInstance();
	private RestHelper_ProcessDefinition processDefinitionRestHelper = new RestHelper_ProcessDefinition();
	
	public int count(String element, String p1, String v1){
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put(p1, v1);
		return getCount(element, parameters);
	}

	public int count(String element, String p1, String v1,String p2, String v2){
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put(p1, v1);
		parameters.put(p2, v2);
		return getCount(element, parameters);
		}
	public int count(String element, String p1, String v1, String p2, String v2, String p3, String v3){
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put(p1, v1);
		parameters.put(p2, v2);
		parameters.put(p3, v3);
		return getCount(element, parameters);
	}
	public int count(String element, String p1, String v1, String p2, String v2, String p3, String v3, String p4, String v4){
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put(p1, v1);
		parameters.put(p2, v2);
		parameters.put(p3, v3);
		parameters.put(p4, v4);
		return getCount(element, parameters);
	}
	public int count(String element, String p1, String v1, String p2, String v2, String p3, String v3, String p4, String v4, String p5, String v5){
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put(p1, v1);
		parameters.put(p2, v2);
		parameters.put(p3, v3);
		parameters.put(p4, v4);
		parameters.put(p5, v5);
		return getCount(element, parameters);
	}
	public int count(String element, String p1, String v1, String p2, String v2, String p3, String v3, String p4, String v4, String p5, String v5, String p6, String v6){
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put(p1, v1);
		parameters.put(p2, v2);
		parameters.put(p3, v3);
		parameters.put(p4, v4);
		parameters.put(p5, v5);
		parameters.put(p6, v6);
		return getCount(element, parameters);
	}
	private int getCount(String element, HashMap<String, String> parameters){
		if (element.equals("process instance")){
			return processInstanceRestHelper.getCount(parameters);
		}else if(element.equals("process definition")){
			return processDefinitionRestHelper.getCount(parameters);
		}else{
			return 0;
		}
	}
}

