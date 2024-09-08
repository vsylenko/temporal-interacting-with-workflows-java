package asyncactivitycompletion;

import java.util.Base64;
import java.util.concurrent.ExecutionException;

import asyncactivitycompletion.model.TranslationActivityOutput;
import io.temporal.client.ActivityCompletionClient;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;

public class VerifyAndCompleteTranslation {
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

    WorkflowClient client = WorkflowClient.newInstance(service);

    // PART C: Read in the taskToken from args[0] and decode it from Base64
    // to a byte[]
    byte[] taskToken = Base64.getDecoder().decode(args[0]);

    // PART C: Get the translated text from args[1] and use it to create a
    // new TranslationActivityOutput object
    TranslationActivityOutput result = new TranslationActivityOutput(args[1]);

    ActivityCompletionClient activityCompletionClient = client.newActivityCompletionClient();

    // PART C: Call the `complete()` method using the `activityCompletionClient`
    // object from above. The call should contain the `taskToken` and the `result`.
    activityCompletionClient.complete(taskToken, result);

    System.exit(0);
  }
}