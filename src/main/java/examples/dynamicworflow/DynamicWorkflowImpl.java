package examples.dynamicworflow;

import io.temporal.common.converter.EncodedValues;
import io.temporal.workflow.DynamicWorkflow;

public class DynamicWorkflowImpl implements DynamicWorkflow {


    @Override
    public Object execute(final EncodedValues args) {

        final String clientId = args.get(0, String.class);
        return new DynamicWorkflowResponse(clientId);
    }

}
