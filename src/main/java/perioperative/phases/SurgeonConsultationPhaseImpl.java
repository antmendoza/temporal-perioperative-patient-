package perioperative.phases;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import perioperative.Patient;
import perioperative.UserTask;

import java.time.Duration;
import java.util.Objects;

public class SurgeonConsultationPhaseImpl implements SurgeonConsultationPhase {
    private Patient patient;

    final ActivityOptions options = ActivityOptions.newBuilder()
            .setScheduleToCloseTimeout(Duration.ofSeconds(2))
            .build();

    private final UserTask userTask = Workflow.newActivityStub(UserTask.class, options);
    private CompleteSurgeonConsultationRequest surgeonConsultationTask;


    @Override
    public SurgeonConsultationPhaseResponse start(final Patient patient) {
        this.patient = patient;


        userTask.waitForSurgeonConsultation();
        Workflow.await(() -> !Objects.isNull(surgeonConsultationTask));


        return new SurgeonConsultationPhaseResponse();
    }

    @Override
    public void completeSurgeonConsultation(final CompleteSurgeonConsultationRequest surgeonConsultationTask) {
        this.surgeonConsultationTask = surgeonConsultationTask;
    }

}
