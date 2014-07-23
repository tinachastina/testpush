package otpp.workflow.TestNGClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class RestHelper_ProcessDefinition extends RestHelper_Common{
	private final static String getDefinition = "/process-definition";
	private final static String getDefinition_single_byId = "/process-definition/{id}";
	private final static String getDefinition_single_byKey = "/process-defintion/key/{key}";
	private final static String getCount = "/count";
	
	
	private final static String definition_name = "name={name}";
	private final static String definition_nameLike = "nameLike={nameLike}";
	private final static String definition_deploymentId = "deploymentId={deploymentId}";
	private final static String definition_key = "key={key}";
	private final static String definition_keyLike = "keyLike={keyLike}";
	private final static String definition_category = "category={category}";
	private final static String definition_categoryLike = "categoryLike={categoryLike}";
	private final static String definition_ver = "ver={ver}";
	private final static String definition_latest = "latest={latest}";
	private final static String definition_resourceName = "resourceName={resourceName}";
	private final static String definition_resourceNameLike = "resourceNameLike={resourceNameLike}";
	private final static String definition_startableBy = "startableBy={startable}";
	private final static String definition_active = "active={active}";
	private final static String definition_suspended = "suspended={suspended}";
	private final static String definition_incidentId = "incidentId={incidentId}";
	private final static String definition_incidentType = "incidentType={incidentType}";
	private final static String definition_incidentMessage = "incidentMessage={incidentMessage}";
	private final static String definition_incidentMessageLike = "incidentMessageLike={incidentMessageLike}";

	public HashMap<String, Object> getDefinition(String key){
		String url = getServiceurl()+getDefinition+by+definition_key;
		List<HashMap<String, Object>> map = new ArrayList<HashMap<String,Object>>();
		map = getMapperForJsonArray().getForObject(url, List.class, key);
		return map.get(0);
	}
	public HashMap<String,Object> getDefinition(String key, String version){
		String url = getServiceurl()+getDefinition+by+definition_key+and+definition_ver;
		List<HashMap<String, Object>> map = new ArrayList<HashMap<String,Object>>();
		map = getMapperForJsonArray().getForObject(url, List.class, key, version);
		return map.get(0);
	}
	public HashMap<String,Object> getDefinition(String a, String b, String name){
		String url = getServiceurl()+getDefinition+by+definition_name;
		List<HashMap<String, Object>> map = new ArrayList<HashMap<String,Object>>();
		map = getMapperForJsonArray().getForObject(url, List.class,name);
		return map.get(0);
	}
	
	public int getCount(HashMap<String, String> parameters){
		String url = getServiceurl()+getDefinition+getCount+by;
		List<String> properties = new ArrayList<String>();
		properties = (List<String>) parameters.keySet();
		Iterator<String> keys = properties.iterator();
		while(keys.hasNext()){
			String key = keys.next();
			url.concat(key).concat("={").concat(key).concat("}");
		}
		int count = getMapperForJsonArray().getForObject(url, Integer.class, parameters);
		return count;
	}
}
