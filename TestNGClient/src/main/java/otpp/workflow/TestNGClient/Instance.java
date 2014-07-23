package otpp.workflow.TestNGClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Instance extends RestHelper_ProcessInstance{

	private String id;
	private String definitionId;
	private String businessKey;
	private String ended;
	private String suspended;
	private HashMap<String, HashMap<String, HashMap<String, Object>>> payload;
	private Definition definition;
	private List<Activity> callActivities = new ArrayList<Activity>();
	private List<Activity> subProcesses = new ArrayList<Activity>();
	private List<Task> tasks = new ArrayList<Task>();
	private List<Variable> variables = new ArrayList<Variable>();
	private List<Activity> activities = new ArrayList<Activity>();
	
	public Instance(){
		
	}
	/**
	 * subprocess
	 * @param list
	 * @param definition
	 */
	public Instance(HashMap<String, String> list, Definition definition){
		this.definition = definition;
		mapInstanceProperties(list);
		mapActivityTree();
		mapVariables();
	}
	/**
	 * 
	 * @param processId
	 */
	public Instance(String processDefinitionkey){
		definition = new Definition(processDefinitionkey);
		payload=new HashMap<String, HashMap<String, HashMap<String, Object>>>();

		this.definitionId = definition.getId();
		payload.put("variables",
				new HashMap<String, HashMap<String, Object>>());
	}
	/**
	 * 
	 * @param processId
	 * @param version
	 */
	public Instance(String processDefinitionKey, String version){
		payload=new HashMap<String, HashMap<String, HashMap<String, Object>>>();
		definition = new Definition(processDefinitionKey, version);
		this.definitionId = definition.getId();
		payload.put("variables",
				new HashMap<String, HashMap<String, Object>>());
	}
	/**
	 * 
	 * @param name
	 * @param value
	 * @param type
	 */
	@SuppressWarnings("unchecked")
	public void setVariable(String name, Object value, String type){
		payload.get("variables").put(name,new HashMap<String, Object>());
		payload.get("variables").get(name).put("value", value);
		payload.get("variables").get(name).put("type", type);
	}
	public Definition getDefinition(){
		return this.definition;
	}

	public List<Task> getTasks(){
		return this.tasks;
	}
	public List<Activity> getSubProcesses(){
		return this.subProcesses;
	}
	public List<Activity> getCallActivities(){
		return this.callActivities;
	}
	public List<Variable> getVariables(){
		return getVariables(this.id);
	}
	public Task getTask(String taskName){
		return getTask(this.id, taskName);
	}
	public Activity getCallActivity(String activityId){
		return getCallActicity(this.id, activityId);
	}
	public Activity getSubProcess(String activityId){
		return getSubProcess(this.id, activityId);
	}
	public Variable getVariable(String variableName){
		return getVariable(this.id, variableName);
	}
	public String get(String property){
		if(property.equals("id")){
			return getId();
		}
		else if(property.equals("definition id")){
			return getDefinitionId();
		}
		else if(property.equals("business key")){
			return getBusinessKey();
		}
		else if(property.equals("ended")){
			return getEnded();
		}
		else if(property.equals("suspended")){
			return getSuspended();
		}
		else {
			return null;
		}
	}
	public String getId(){
		return this.id;
	}
	public String getDefinitionId(){
		return this.definitionId;
	}
	public String getBusinessKey(){
		return this.businessKey;
	}
	public String getEnded(){
		return this.ended;
	}
	public String getSuspended(){
		return this.suspended;
	}
	/**
	 * 
	 * @return
	 */
	public Boolean startProcess(){
		List<HashMap<String,String>> instance = new ArrayList<HashMap<String,String>>();
		instance = startProcess(this.definitionId, payload);
		mapInstanceProperties(instance.get(0));
		mapActivityTree();
		mapVariables();
		return checkProperties();
	}
	
	
	private void mapActivityTree(){
		this.callActivities = getCallActivities(this.id);
		this.tasks = getTasks(this.id);
		this.subProcesses = getSubProcesses(this.id);
	}
	private void mapVariables(){
		
	}
	public void deleteProcess(){
		deleteProcessInstance(this.id);
	}
	/**
	 * 
	 * @param map
	 */
	private void mapInstanceProperties(HashMap<String,String> map){
		this.id = map.get("id");
		this.definitionId = map.get("definitionId");
		this.businessKey = map.get("businessKey");
		this.ended = String.valueOf(map.get("ended"));
		this.suspended = String.valueOf(map.get("suspended"));
	}
	/**
	 * 
	 * @return
	 */
	private Boolean checkProperties(){
		return Boolean.valueOf(this.ended)==false&Boolean.valueOf(this.suspended)==false;
	}
}