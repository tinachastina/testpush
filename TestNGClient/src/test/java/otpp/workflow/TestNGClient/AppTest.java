package otpp.workflow.TestNGClient;

import org.testng.annotations.*;
import org.testng.Assert;

/**
 * Unit test for simple App.
 */
public class AppTest extends Assert
{
	private static final String PROCESS_ID_ENROLLMENT = "pension-plan-enrollment";
	private static final String PROCESS_VER_ENROLLMENT = "1";
	private static final String PROCESS_ID_DOC_REVIEW = "document-review";
	private static final String PROCESS_VER_DOC_REVIEW = "1";
	private static final String TASK_ID_DOC_REVIEW_CALL_ACTIVITY = "CallActivity_DocumentReview";
	private static final String TASK_ID_DOC_REVIEW_USER_TASK = "documentReviewUserTask";
	private static final String ASSIGNEE_DOC_REVIEW_USER_TASK = "RECORDS";
	@Test
	public void generalTestCase_case1(){
		Instance p = new Instance("pension-plan-enrollment");
		p.setVariable("name", "Tina", "String");
		p.setVariable("age",22,"integer");
		p.setVariable("awake",false,"Boolean");
		p.setVariable("RecordsReview", true, "Boolean");
		assertTrue(p.startProcess(), "start process");
		assertEquals(p.get("definition id"), p.getDefinition().get("definition id"), "id is correct");
		
	}
}
