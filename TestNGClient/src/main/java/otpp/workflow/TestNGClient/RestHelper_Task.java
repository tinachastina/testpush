package otpp.workflow.TestNGClient;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestHelper_Task extends RestHelper_Common{
	
	private final static String getTask = "/task";
	private final static String task_ProcessInstanceBusinessKey = "processInstanceBusinessKey={businessKey}";
	private final static String task_ProcessInstanceBusinessKeyLike = "processInstanceBusinessKeyLike={businessKeyLike}";
	private final static String task_ProcessInstanceId = "processInstanceId={processInstanceId}";
	private final static String task_ProcessDefinitionKey = "processDefinitionKey={definitionKey}";
	private final static String task_ProcessDefinitionName = "processDefinitionName={definitionName}";
	private final static String task_ProcessDefinitionNameLike = "processDefinitionNameLike={definitionNameLike}";
	private final static String task_ExecutionId = "executionId={executionId}";
	private final static String task_ActivityInstanceIdIn = "activityInstanceIdIn={activityInstanceIdIn}";
	private final static String task_Assignee = "assignee={assignee}";
	private final static String task_AssigneeLike = "assignee={assigneeLike}";
	private final static String task_Owner = "owner={owner}";
	private final static String task_CandidateGroup = "candidateGroup={candidateGroup}";
	private final static String task_CandidateUser = "candidateUser={candidateUser}";
	private final static String task_InvolvedUser = "involvedUser={involvedUser}";
	private final static String task_Unassigned = "unassigned={unassigned}";
	private final static String task_DefinitionKey = "taskDefinitionKey={taskDefKey}";
	private final static String task_DefinitionKeyLike = "taskDefinitionKeyLike={taskDefKeyLike}";
	private final static String task_Name = "name={name}";
	private final static String task_NameLike = "nameLike={nameLike}";
	private final static String task_Description = "description={description}";
	private final static String task_Priority ="priority={priority}";
	private final static String task_MaxPriority = "maxPriority={maxPriority}";
	private final static String task_MinPriority = "minPriority={minPriority}";
	private final static String task_Due = "due={due}";
	private final static String task_DueAfter = "dueAfter={dueAfter}";
	private final static String task_DueBefore = "dueBefore={dueBefore}";
	private final static String task_FollowUp = "followUp={followUp}";
	private final static String task_FollowUpAfter = "followUpAfter={followUpAfter}";
	private final static String task_FollorUpBefore = "followUpBefore={followUpBefore}";
	private final static String task_Created = "created={created}";
	private final static String task_CreatedAfter = "createdAfter={createdAfter}";
	private final static String task_CreatedBefore = "createdBefore={createdBefore}";
	private final static String task_DelegationState = "delegationState={delegationState}";
	private final static String task_CandidateGroups = "candidateGroups={candidateGroups}";
	private final static String task_Active = "active={active}";
	private final static String task_Suspended = "suspended={suspended}";
	private final static String task_Variables = "taskVariables={taskVar}";
	private final static String task_ProcessInstanceVariables = "processVariables={processVar}";
	
	private final static String task_Claim = "/task/{id}/claim";
	private final static String task_Complete = "/task/{id}/complete";
	private final static String task_SetAssignee = "/task/{id}/assignee";
	
	/**
	 * 
	 * @param instId
	 * @return
	 */
	public List<HashMap<String,String>> getTask(String instId){
		String url = getServiceurl()+getTask+by+task_ProcessInstanceId;
		List<HashMap<String,String>> map = getMapperForJsonArray().getForObject(url, List.class, instId);
		return map;
	}
	/**
	 * 
	 * @param processId
	 * @param taskDef
	 * @return
	 */
	public HashMap<String,String> getTask(String processId, String taskDef){
		String url = getServiceurl()+getTask+by+task_ProcessInstanceId+and+task_DefinitionKey;
		ResponseEntity<List> entity = getMapperForJsonArray().getForEntity(url, List.class, processId, taskDef);
		HashMap<String, String> task = (HashMap<String, String>) entity.getBody().get(0);
		return task;
	}

	/**
	 * 
	 * @param id
	 * @param userId
	 * @return
	 */
	public HttpStatus claim(String id, String userId){
		String url = getServiceurl()+task_Claim;
		HashMap<String,String> request = new HashMap<String,String>();
		request.put("userId",userId);
		ResponseEntity<List> map = getMapperForJsonArray().postForEntity(url, request, List.class, id);
		return map.getStatusCode();
	}
	/**
	 * 
	 * @param id
	 * @param userId
	 * @return
	 */
	public HttpStatus setAssignee(String id, String userId){
		String url = getServiceurl()+task_SetAssignee;
		HashMap<String,String> request = new HashMap<String,String>();
		request.put("userId",userId);
		ResponseEntity<List> map = getMapperForJsonArray().postForEntity(url, request, List.class, id);
		return map.getStatusCode();
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public HttpStatus complete(String id){
		String url = getServiceurl()+task_Complete;
		HashMap<String,Object> request = new HashMap<String,Object>();
		ResponseEntity<List> map = getMapperForJsonObject().postForEntity(url, request, List.class, id);
		return map.getStatusCode();
	}
}
