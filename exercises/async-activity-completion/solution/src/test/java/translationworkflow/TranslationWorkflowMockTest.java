package translationworkflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import asyncactivitycompletion.TranslationActivities;
import asyncactivitycompletion.TranslationWorkflow;
import asyncactivitycompletion.TranslationWorkflowImpl;
import asyncactivitycompletion.model.TranslationActivityInput;
import asyncactivitycompletion.model.TranslationActivityOutput;
import asyncactivitycompletion.model.TranslationWorkflowInput;
import asyncactivitycompletion.model.TranslationWorkflowOutput;
import io.temporal.testing.TestWorkflowEnvironment;
import io.temporal.testing.TestWorkflowExtension;
import io.temporal.worker.Worker;

public class TranslationWorkflowMockTest {

  @RegisterExtension
  public static final TestWorkflowExtension testWorkflowExtension = 
      TestWorkflowExtension.newBuilder()
          .setWorkflowTypes(TranslationWorkflowImpl.class)
          .setDoNotStart(true)
          .build();

  @Test
  public void testSuccessfulTranslationWithMocks(TestWorkflowEnvironment testEnv, Worker worker,
      TranslationWorkflow workflow) {

    TranslationActivities mockedActivities =
        mock(TranslationActivities.class, withSettings().withoutAnnotations());
    when(mockedActivities.translateTerm(new TranslationActivityInput("hello", "fr")))
        .thenReturn(new TranslationActivityOutput("Bonjour"));
    when(mockedActivities.translateTerm(new TranslationActivityInput("goodbye", "fr")))
        .thenReturn(new TranslationActivityOutput("Au revoir"));
    
    worker.registerActivitiesImplementations(mockedActivities);
    testEnv.start();

    TranslationWorkflowOutput output =
        workflow.sayHelloGoodbye(new TranslationWorkflowInput("Pierre", "fr"));

    assertEquals("Bonjour, Pierre", output.getHelloMessage());
    assertEquals("Au revoir, Pierre", output.getGoodbyeMessage());
  }
}
