package otpp.workflow.TestNGClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Definition extends RestHelper_ProcessDefinition{
	private String id;
	private String key;
	private String category;
	private String description;
	private String name;
	private String version;
	private String resource;
	private String deploymentId;
	private String diagram;
	private String suspended;
	private List<Instance> instances = new ArrayList<Instance>();
	private Count count = new Count();

	public Definition(){
		super();
	}
	public Definition(String key){
		super();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = getDefinition(key);
		mapDefinitionProperties(map);
	}
	public Definition(String key, String version){
		super();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = getDefinition(key, version);
		mapDefinitionProperties(map);
	}
	public Definition(String a, String b, String name){
		super();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = getDefinition(a, b, name);
		mapDefinitionProperties(map);
	}

	public int count(String component){
		if(component.equals("instance")){
			return count.count("process instance", "processDefinitionId", this.id);
		}
		return 0;
	}
	private void mapDefinitionProperties(HashMap<String, Object> map){
		if(!(map.get("id")==null)){
			this.id = map.get("id").toString();
		}else if(!(map.get("key")==null)){
			this.key = map.get("key").toString();
		}else if(!(map.get("category")==null)){
			this.category = map.get("category").toString();
		}else if(!(map.get("description")==null)){
			this.category = map.get("description").toString();
		}else if(!(map.get("name")==null)){
			this.name = map.get("name").toString();
		}else if(!(map.get("version")==null)){
			this.category = map.get("version").toString();
		}else if(!(map.get("deploymentId")==null)){
			this.deploymentId = map.get("deploymentId").toString();
		}else if(!(map.get("diagram")==null)){
			this.diagram = map.get("diagram").toString();
		}else if(!(map.get("suspended")==null)){
			this.suspended = String.valueOf(map.get("suspended"));
		}
	}
	public String get(String property){
		if(property.equals("definition id")){
			return getId();
		}else if(property.equals("definition key")){
			return getKey();
		}else if(property.equals("category")){
			return getCategory();
		}else if(property.equals("description")){
			return getDescription();
		}else if(property.equals("definition name")){
			return getName();
		}else if(property.equals("version")){
			return getVersion();
		}else if(property.equals("resource")){
			return getResource();
		}else if(property.equals("deployment id")){
			return getDeploymentId();
		}else if(property.equals("diagram")){
			return getDiagram();
		}else if(property.equals("suspended")){
			return getSuspended();
		}else{
			return null;
		}
	}
	public String getId(){
		return this.id;
	}
	public String getKey(){
		return this.id;
	}
	public String getCategory(){
		return this.id;
	}
	public String getDescription(){
		return this.id;
	}
	public String getName(){
		return this.id;
	}
	public String getVersion(){
		return this.id;
	}
	public String getResource(){
		return this.id;
	}
	public String getDeploymentId(){
		return this.id;
	}
	public String getDiagram(){
		return this.id;
	}
	public String getSuspended(){
		return this.id;
	}
	
	
}
