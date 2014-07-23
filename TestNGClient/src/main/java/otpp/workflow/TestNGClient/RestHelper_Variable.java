package otpp.workflow.TestNGClient;

import java.util.HashMap;

public class RestHelper_Variable extends RestHelper_Common{
	private final static String getVariable = "/variable-instance";
	private final static String getVariable_single_byId = "/variable-instance/{id}";
	private final static String getCount = "/count";
	
	private final static String variable_name = "variableName={variableName}";
	private final static String variable_nameLike = "variableNameLike={variableNameLike}";
	private final static String variable_processInstanceIdIn = "processInstanceIdIn={processInstanceIdIn}";
	private final static String variable_executionIdIn = "executionIdIn={executionIdIn}";
	private final static String variable_caseInstanceIdIn = "caseInstanceIdIn={caseInstanceIdIn}";
	private final static String variable_caseExecutionIdIn = "caseExecutionIdIn={caseExecutionIdIn}";
	private final static String variable_taskIdIn = "taskIdIn={taskIdIn}";
	private final static String variable_activityInstanceIdIn = "activityInstanceIdIn={activityInstanceIdIn}";
	private final static String variable_varibleValues = "variableValues={variableValues}";
	
	public HashMap<String, Object> getVariableInstance(
			String processInstanceId, String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
}