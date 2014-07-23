package otpp.workflow.TestNGClient;

import java.util.HashMap;

public class Variable extends RestHelper_Variable{
	private String id;
	private String name;
	private String type;
	private Object value;
	private String processInstanceId;
	private String executionId;
	private String caseInstanceId;
	private String taskId;
	private String activityInstanceId;
	private String errorMessage;
	
	public Variable(){
		super();
	}
	public Variable(HashMap<String, Object> map, String instId, String varId) {
		super();
		this.name = varId;
		this.type = map.get("type").toString();
		this.value = map.get("value");
		this.processInstanceId = instId;
		Variable(getVariableInstance());
	}
	private void Variable(HashMap<String, Object> map) {
		System.out.println(map);
		this.id = map.get("id").toString();
		this.executionId = map.get("executionId").toString();
		if (!(map.get("caseInstanceId")==null)){
			this.caseInstanceId = map.get("caseInstanceId").toString();
		}
		if (!(map.get("taskId")==null)){
			this.taskId = map.get("taskId").toString();
		}
		this.activityInstanceId = map.get("activityInstanceId").toString();
		if (!(map.get("errorMessage")==null)){
			this.errorMessage = map.get("errorMessage").toString();
		}
	}
	public Variable(HashMap<String, Object> map, String instId){
		super();
	}
	public Variable(HashMap<String,Object> map){
		super();
		this.id = map.get("id").toString();
		this.name = map.get("name").toString();
		this.type = map.get("type").toString();
		this.value = map.get("value");
		this.processInstanceId = map.get("processInstanceId").toString();
		this.executionId = map.get("executionId").toString();
		this.caseInstanceId = map.get("caseInstanceId").toString();
		this.taskId = map.get("taskId").toString();
		this.activityInstanceId = map.get("activityInstanceId").toString();
		this.errorMessage = map.get("errorMessage").toString();
	}
	public HashMap<String, Object> getVariableInstance(){
		return getVariableInstance(this.processInstanceId, this.name);
	}
	
	public Object get(String property){
		if(property.equals("id")){
			return getId();
		}else if(property.equals("name")){
			return getName();
		}else if(property.equals("type")){
			return getType();
		}else if(property.equals("value")){
			return getValue();
		}else if(property.equals("process instance id")){
			return getProcessInstanceId();
		}else if(property.equals("execution id")){
			return getExecutionId();
		}else if(property.equals("case instance id")){
			return getCaseInstanceId();
		}else if(property.equals("task id")){
			return getTaskId();
		}else if(property.equals("activity instance id")){
			return getActivityInstanceId();
		}else if(property.equals("error message")){
			return getErrorMessage();
		}else{
			return null;
		}
	}
	public String getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public String getType(){
		return this.type;
	}
	public Object getValue(){
		return this.value;
	}
	public String getProcessInstanceId(){
		return this.processInstanceId;
	}
	public String getExecutionId(){
		return this.executionId;
	}
	public String getCaseInstanceId(){
		return this.caseInstanceId;
	}
	public String getTaskId(){
		return this.taskId;
	}
	public String getActivityInstanceId(){
		return this.activityInstanceId;
	}
	public String getErrorMessage(){
		return this.errorMessage;
	}
}
