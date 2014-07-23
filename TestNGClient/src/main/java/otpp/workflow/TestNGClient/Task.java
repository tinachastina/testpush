package otpp.workflow.TestNGClient;

import java.util.HashMap;

public class Task extends Activity{
	private String id;
	private String name;
	private String assignee;
	private String created;
	private String due;
	private String followUp;
	private String delegationState;
	private String description;
	private String executionId;
	private String owner;
	private String parentTaskId;
	private String priority;
	private String processDefinitionId;
	private String processInstanceId;
	private String taskDefinitionKey;
	private RestHelper_Task taskRestHelper = new RestHelper_Task();
	
	public Task(HashMap<String, String> activity) {
		super(activity);
		HashMap<String, String> map = taskRestHelper.getTask(super.getProcessInstanceId(), super.getActivityKey());
		mapTaskProperties(map);
	}
	private void mapTaskProperties(HashMap<String,String> map){
		this.id = map.get("id");
		this.name = map.get("name");
		this.assignee = map.get("assignee");
		this.created = map.get("created");
		this.due = map.get("due");
		this.followUp = map.get("followUp");
		this.delegationState = map.get("delegationState");
		this.description = map.get("description");
		this.executionId = map.get("executionId");
		this.owner = map.get("owner");
		this.parentTaskId = map.get("parentTaskId");
		this.priority = map.get("priority");
		this.processDefinitionId = map.get("processDefinitionId");
		this.processInstanceId = map.get("processInstanceId");
		this.taskDefinitionKey = map.get("taskDefinitionKey");
	}
	public String get(String property){
		if(property.equals("task id")){
			return getId();
		}else if(property.equals("task name")){
			return getName();
		}else if(property.equals("assignee")){
			return getAssignee();
		}else if(property.equals("created")){
			return getCreated();
		}else if(property.equals("due")){
			return getDue();
		}else if(property.equals("followUp")){
			return getFollowUp();
		}else if(property.equals("delegation state")){
			return getDelegationState();
		}else if(property.equals("description")){
			return getDescription();
		}else if(property.equals("execution id")){
			return getExecutionId();
		}else if(property.equals("owner")){
			return getOwner();
		}else if(property.equals("parent task id")){
			return getParentTaskId();
		}else if(property.equals("priority")){
			return getPriority();
		}else if(property.equals("process definition id")){
			return getProcessDefinitionId();
		}else if(property.equals("task definition key")){
			return getTaskDefinitionKey();
		}else{
			return null;
		}
	}
	/**
	 * The id of the task.
	 */
	public String getId(){
		return this.id;
	}
	/**
	 * The tasks name
	 */
	public String getName(){
		return this.id;
	}
	/**
	 * The user assigned to this task.
	 * @return
	 */
	public String getAssignee(){
		return this.id;
	}
	/**
	 * The time the task was created. Format yyyy-MM-dd'T'HH:mm:ss
	 * @return
	 */
	public String getCreated(){
		return this.id;
	}
	/**
	 * The due date for the task. Format yyyy-MM-dd'T'HH:mm:ss
	 * @return
	 */
	public String getDue(){
		return this.id;
	}
	/**
	 * The follow-up date for the task. Format yyyy-MM-dd'T'HH:mm:ss
	 * @return
	 */
	public String getFollowUp(){
		return this.id;
	}
	/**
	 * The delegation state of the task. Corresponds to the DelegationState enum in the engine. 
	 * Possible values are RESOLVED and PENDING
	 * @return
	 */
	public String getDelegationState(){
		return this.id;
	}
	/**
	 * The task description
	 * @return
	 */
	public String getDescription(){
		return this.id;
	}
	/**
	 * The id of the execution the task belongs to
	 * @return
	 */
	public String getExecutionId(){
		return this.id;
	}
	/**
	 * The owner of the task
	 * @return
	 */
	public String getOwner(){
		return this.id;
	}
	/**
	 * The id of the parent task, if this task is a subtask.
	 * @return
	 */
	public String getParentTaskId(){
		return this.id;
	}
	/**
	 * The priority of the task
	 * @return
	 */
	public String getPriority(){
		return this.priority;
	}
	/**
	 * The id of the process definition this task belongs to
	 */
	public String getProcessDefinitionId(){
		return this.processDefinitionId;
	}
	/**
	 * The id of the process instance this task belongs to
	 */
	public String getProcessInstanceId(){
		return this.processInstanceId;
	}
	/**
	 * The task definition key
	 * @return
	 */
	public String getTaskDefinitionKey(){
		return this.taskDefinitionKey;
	}
}
