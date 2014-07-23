package otpp.workflow.TestNGClient;

import java.util.HashMap;
import java.util.List;

public class Activity {
	private String activityInstanceId;
	private String activityDefinitionId;
	private String activityType;
	private String parentActivityInstanceId;
	private String activity_processInstanceId;
	private String activity_processDefinitionId;
	private String name;
	private List<String> executionIds;

	public Activity(HashMap<String,String> activity){
		this.activityInstanceId = activity.get("id");
		this.activityDefinitionId = activity.get("activityId");
		this.activityType = activity.get("activityType");
		this.parentActivityInstanceId = activity.get("parentActivityInstanceId");
		this.activity_processInstanceId = activity.get("processInstanceId");
		this.activity_processDefinitionId = activity.get("processDefinitionId");
		this.name = activity.get("name");
		}

	public Instance getCalledProcess(){
		Definition definition_calledProcess = new Definition(this.name, null, null);
		RestHelper_ProcessInstance restHelper = new RestHelper_ProcessInstance();
		Instance calledProcess = new Instance(restHelper.getInstance(definition_calledProcess.getKey(), this.activity_processInstanceId ,null, null).get(0), definition_calledProcess);
		return calledProcess;
	}
	public String get(String property){
		if(property.equals("id")){
			return getId();
		}else if(property.equals("activity id")){
			return getActivityKey();
		}else if(property.equals("activity type")){
			return getActivityType();
		}else if(property.equals("process instance id")){
			return getProcessInstanceId();
		}else if(property.equals("process definition id")){
			return getProcessDefinitionId();
		}else if(property.equals("parent activity instance id")){
			return getParentActivityInstanceId();
		}else if(property.equals("name")){
			return getName();
		}else{
			return null;
		}
	}
	public String getId(){
		return this.activityInstanceId;
	}
	public String getActivityKey(){
		return this.activityDefinitionId;
	}
	public String getActivityType(){
		return this.activityType;
	}
	public String getParentActivityInstanceId(){
		return this.parentActivityInstanceId;
	}
	public String getProcessInstanceId(){
		return this.activity_processInstanceId;
	}
	public String getProcessDefinitionId(){
		return this.activity_processDefinitionId;
	}
	public List<String> getExecutionIds(){
		return this.executionIds;
	}
	public String getName(){
		return this.name;
	}
}
