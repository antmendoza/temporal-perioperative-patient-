package perioperative;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.testing.TestWorkflowRule;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import perioperative.phases.SurgeonConsultationPhaseImpl;
import perioperative.phases.CompleteSurgeonConsultationRequest;
import perioperative.phases.SurgeonConsultationPhaseResponse;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class PerioperativePatientWorkflowTest {

    @Rule
    public TestWorkflowRule testWorkflowRule = TestWorkflowRule.newBuilder()
            .setWorkflowTypes(PerioperativePatientImpl.class, SurgeonConsultationPhaseImpl.class)
            .setDoNotStart(true)
            .build();

    @After
    public void cleanUp() {
        testWorkflowRule.getTestEnvironment()
                .shutdown();
    }

    @Test
    public void testOneUserTask() {


        final UserTask userTask = new UserTaskImpl();
        testWorkflowRule.getWorker()
                .registerActivitiesImplementations(userTask);
        testWorkflowRule.getTestEnvironment()
                .start();

        final PerioperativePatient workflow = testWorkflowRule.getWorkflowClient()
                .newWorkflowStub(PerioperativePatient.class, WorkflowOptions.newBuilder()
                        .setTaskQueue(testWorkflowRule.getTaskQueue())
                        .build());

        final Patient patient = new Patient(UUID.randomUUID().toString());
        WorkflowClient.start(workflow::start, patient);


        // connect to WF and get result using untyped:
        WorkflowStub untyped = WorkflowStub.fromTyped(workflow);
        untyped.signal("completeSurgeonConsultation", new CompleteSurgeonConsultationRequest());
        PerioperativeResponse result = untyped.getResult(PerioperativeResponse.class);


        final PerioperativeResponse expected = new PerioperativeResponse();
        expected.setSurgeonConsultationPhaseResponse(new SurgeonConsultationPhaseResponse());
        assertEquals(expected, result);
    }



    @Test
    public void testUpdatePatientTask() {


        final UserTask userTask = new UserTaskImpl();
        testWorkflowRule.getWorker()
                .registerActivitiesImplementations(userTask);
        testWorkflowRule.getTestEnvironment()
                .start();

        final PerioperativePatient workflow = testWorkflowRule.getWorkflowClient()
                .newWorkflowStub(PerioperativePatient.class, WorkflowOptions.newBuilder()
                        .setTaskQueue(testWorkflowRule.getTaskQueue())
                        .build());


        final Patient patient = new Patient(UUID.randomUUID()
                .toString());
        WorkflowClient.start(workflow::start, patient);

        final Patient.PatientDetails patientDetails = new Patient.PatientDetails("Antonio", "Mendoza");

        workflow.updatePatient(patientDetails);
        final Patient actualPatient =   workflow.queryPatient();

        final Patient expectedPatient = patient.withPatientDetails(patientDetails);

        assertEquals(expectedPatient, actualPatient);
    }

}
