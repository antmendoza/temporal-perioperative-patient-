package perioperative;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface UserTask {

    @ActivityMethod
    void waitForSurgeonConsultation();

}
