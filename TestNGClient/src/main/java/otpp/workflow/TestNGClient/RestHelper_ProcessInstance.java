package otpp.workflow.TestNGClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;

public class RestHelper_ProcessInstance extends RestHelper_Common{
	    private final static String getInstance = "/process-instance";
	    private final static String getDefinition = "/process-definition";
	    private final static String getCount = "/count";
	    // process instance query parameters
		private final static String process_getVariable = "/{id}/variables/{varId}";
		private final static String process_getVariables = "/{id}/variables";
		private final static String process_getActivity = "/{id}/activity-instances";
		private final static String process_Start = "/{id}/start";
		private final static String process_delete = "/process-instance/{id}";
		
		private final static String process_DefinitionId = "processDefinitionId={definitionId}";
		private final static String process_SuperProcessInstanceId = "superProcessInstance={superProcessInstanceId}";
		private final static String process_InstanceIds = "processInstanceIds={id}";
		private final static String process_BusinessKey = "businessKey={businessKey}";
		private final static String process_DefinitionKey = "processDefinitionKey={definitionKey}";
		private final static String process_SubProcessInstanceId = "subProcessInstance={subProcessInstanceId}";
		private final static String process_Variables = "variables={vars}";
		
		/**
		 * 
		 * @param id
		 * @param variables
		 * @return
		 */
		public List<HashMap<String,String>> startProcess(String id, HashMap<String,HashMap<String, HashMap<String,Object>>> variables){
	    	String url = getServiceurl()+getDefinition+process_Start;
	        List<HashMap<String,String>> map = new ArrayList<HashMap<String,String>>();
	        map = getMapperForJsonObject().postForObject(url, variables, List.class, id);
	        return map;
		}
		/**
		 * 
		 * @return
		 */
		public List<HashMap<String,String>> getInstances(){
			String url = getServiceurl()+getInstance;
			List<HashMap<String,String>> map = getMapperForJsonArray().getForObject(url, List.class);
			return map;
		}
		/**
		 * 
		 * @param definitionId
		 * @return
		 */
		public List<HashMap<String,String>> getInstance(String defId){
			String url = getServiceurl()+getInstance+by+process_DefinitionId;
			List<HashMap<String,String>> map = getMapperForJsonArray().getForObject(url, List.class, defId);
			return map;
		}
		/**
		 * 
		 * @param definitionId
		 * @param instanceId
		 * @return
		 */
		public ResponseEntity<List> getInstance(String defId, String instId){
			String url = getServiceurl()+getInstance+by+process_DefinitionId+and+process_InstanceIds;
			ResponseEntity<List> map = getMapperForJsonArray().getForEntity(url, List.class, defId, instId);
			return map;
		}
		/**
		 * 
		 * @param definitionId
		 * @param instanceId
		 * @param superProcessInstanceId
		 * @return
		 */
		public List<HashMap<String,String>> getInstance(String defId, String instId, String supProcessInstanceId){
			String url = getServiceurl()+getInstance+by+process_DefinitionId+and+process_InstanceIds+and+process_SuperProcessInstanceId;
			List<HashMap<String,String>> map = getMapperForJsonArray().getForObject(url, List.class, defId, instId, supProcessInstanceId);
			return map;
		}
		/**
		 * 
		 * @param defId
		 * @param supProcessInstanceId
		 * @param a
		 * @param b
		 * @return
		 */
		public List<HashMap<String,String>> getInstance(String defKey, String supProcessInstanceId, String a, String b){
			String url = getServiceurl()+getInstance+by+process_DefinitionKey+and+process_SuperProcessInstanceId;
			List<HashMap<String,String>> map = getMapperForJsonArray().getForObject(url, List.class, defKey, supProcessInstanceId);
			return map;
		} 
		/**
		 * 
		 * @param id
		 * @return
		 */
		public List<Variable> getVariables(String id){
			String url = getServiceurl()+getInstance+process_getVariables;
			List<HashMap<String, HashMap<String,Object>>> map = getMapperForJsonObject().getForObject(url, List.class, id);
			Iterator<String> keys = map.get(0).keySet().iterator();
			List<Variable> variabless = new ArrayList<Variable>();
			while(keys.hasNext()){
				String key = keys.next();
				Variable a = new Variable(map.get(0).get(key), id, key);
				variabless.add(a);
			}
			return variabless;
		}
		/**
		 * 
		 * @param id
		 * @param varId
		 * @return
		 */
		public Variable getVariable(String id, String varId){
			String url = getServiceurl()+getInstance+process_getVariable;
			List<HashMap<String,Object>> map = getMapperForJsonObject().getForObject(url, List.class, id, varId);
			Variable variable = new Variable(map.get(0), id, varId);
			return variable;
		}
		/**
		 * Get activity tree for a given process instance
		 * @param instId
		 * @return
		 */
		public List<HashMap<String, ArrayList>> getActivities(String instId){
			String url = getServiceurl()+getInstance+process_getActivity;
	        List<HashMap<String, ArrayList>> map = getMapperForJsonObject().getForObject(url, List.class, instId);
			return map;
		}
		/**
		 * 
		 * @param instId
		 * @param activityId
		 * @return
		 */
		public Activity getActivity(String instId, String activityId){
			List<HashMap<String, ArrayList>> map = getActivities(instId);
	        Iterator<HashMap<String, String>> child = getChildActivityInstances(map);
	        while(child.hasNext()){
	        	HashMap<String,String> c = child.next();
	        	if(c.get("activityId").equals(activityId)){
	        		Activity activity = new Activity(c);
	        		return activity;
	        	}
	        }
	        return null;
		}
		/**
		 * 
		 * @param map
		 * @return
		 */
		private Iterator<HashMap<String, String>> getChildActivityInstances(List<HashMap<String, ArrayList>> map){
			 List<HashMap<String, String>> childActivityInstances = map.get(0).get("childActivityInstances");
		     Iterator<HashMap<String, String>> child = childActivityInstances.iterator();
		     return child;
		}
		/**
		 * 
		 * @param instId
		 * @return
		 */
		public List<Activity> getCallActivities(String instId){
			List<HashMap<String, ArrayList>> map = getActivities(instId);
	        Iterator<HashMap<String, String>> child = getChildActivityInstances(map);
	        List<Activity> callActivities = new ArrayList<Activity>();
	        
	        while(child.hasNext()){
	        	HashMap<String,String> activity = child.next();
	        	if(activity.get("activityType").contains("callActivity")){
	        		Activity callActivity = new Activity(activity);
	        		callActivities.add(callActivity);
	        	}
	        }       
	        return callActivities;
		}
		/**
		 * 
		 * @param instId
		 * @param activityId
		 * @return
		 */
		public Activity getCallActicity(String instId, String activityId){
			Iterator<Activity> callActivities = getCallActivities(instId).iterator();
			while(callActivities.hasNext()){
				Activity activity = callActivities.next();
				if(activity.getActivityKey().equals(activityId)){
					return activity;
				}
			}
			return null;
		}
		/**
		 * 
		 * @param instId
		 * @return
		 */
		public List<Task> getTasks(String instId){
			List<HashMap<String, ArrayList>> map = getActivities(instId);
	        Iterator<HashMap<String, String>> child = getChildActivityInstances(map);
	        List<Task> tasks = new ArrayList<Task>();
	        while(child.hasNext()){
	        	HashMap<String,String> activity = child.next();
	        	if(activity.get("activityType").contains("Task")){
	        		Task task = new Task(activity);
	        		tasks.add(task);
	        	}
	        }
	        return tasks;
		}
		public Task getTask(String instId, String taskName){
			Iterator<Task> tasks = getTasks(instId).iterator();
			while(tasks.hasNext()){
				Task task = tasks.next();
				if(task.getName().equals(taskName)){
					return task;
				}
			}
			return null;
		}
		/**
		 * 
		 * @param instId
		 * @return
		 */
		public List<Activity> getSubProcesses(String instId){
			List<HashMap<String, ArrayList>> map = getActivities(instId);
	        Iterator<HashMap<String, String>> child = getChildActivityInstances(map);
	        List<Activity> subProcesses = null;
	        while(child.hasNext()){
	        	HashMap<String,String> activity = child.next();
	        	if(activity.get("activityType").contains("subProcess")){
	        		Activity subProcess = new Activity(activity);
	        		subProcesses.add(subProcess);
	        	}
	        }
	        return subProcesses;
		}
		public Activity getSubProcess(String instId, String activityId){
			Iterator<Activity> subProcesses = getSubProcesses(instId).iterator();
			while(subProcesses.hasNext()){
				Activity activity = subProcesses.next();
				if(activity.getActivityKey().equals(activityId)){
					return activity;
				}
			}
			return null;
		}
		public int getCount(HashMap<String, String> parameters){
			String url = getServiceurl()+getInstance+getCount+by;
			List<String> properties = new ArrayList<String>();
			Iterator<String> keys = parameters.keySet().iterator();
			while(keys.hasNext()){
				String key = keys.next();
				url.concat(key).concat("={").concat(key).concat("}");
			}
			int count = getClient().getForObject(url, Integer.class, parameters);
			return count;
		}
		/**
		 * 
		 * @param id
		 */
		public void deleteProcessInstance(String id){
			String url = getServiceurl()+process_delete;
			getMapperForJsonArray().delete(url, id);
		}
		
}