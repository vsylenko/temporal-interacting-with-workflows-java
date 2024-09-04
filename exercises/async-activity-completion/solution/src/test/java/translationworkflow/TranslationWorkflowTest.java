package translationworkflow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import asyncactivitycompletion.TranslationActivitiesImpl;
import asyncactivitycompletion.TranslationWorkflow;
import asyncactivitycompletion.TranslationWorkflowImpl;
import asyncactivitycompletion.model.TranslationWorkflowInput;
import asyncactivitycompletion.model.TranslationWorkflowOutput;
import io.temporal.testing.TestWorkflowEnvironment;
import io.temporal.testing.TestWorkflowExtension;
import io.temporal.worker.Worker;

public class TranslationWorkflowTest {

  @RegisterExtension
  public static final TestWorkflowExtension testWorkflowExtension = 
      TestWorkflowExtension.newBuilder()
          .setWorkflowTypes(TranslationWorkflowImpl.class)
          .setDoNotStart(true)
          .build();

  @Test
  public void testSuccessfulTranslation(TestWorkflowEnvironment testEnv, Worker worker,
      TranslationWorkflow workflow) {

    worker.registerActivitiesImplementations(new TranslationActivitiesImpl());
    testEnv.start();

    TranslationWorkflowOutput output =
        workflow.sayHelloGoodbye(new TranslationWorkflowInput("Pierre", "fr"));

    assertEquals("Bonjour, Pierre", output.getHelloMessage());
    assertEquals("Au revoir, Pierre", output.getGoodbyeMessage());
  }
}
