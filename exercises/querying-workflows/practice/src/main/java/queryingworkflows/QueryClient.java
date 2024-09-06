package queryingworkflows;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import queryingworkflows.orderpizza.PizzaWorkflow;


public class QueryClient {
  public static void main(String[] args) throws Exception {

    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

    WorkflowClient client = WorkflowClient.newInstance(service);

    PizzaWorkflow workflow = client.newWorkflowStub(PizzaWorkflow.class, "pizza-workflow-order-XD001");

    // PART B: Perform the query using the `orderStatus()` method. Print out the results
    String status = workflow.orderStatus();
    System.out.println("Order status: " + status);

    System.exit(0);
  }
}