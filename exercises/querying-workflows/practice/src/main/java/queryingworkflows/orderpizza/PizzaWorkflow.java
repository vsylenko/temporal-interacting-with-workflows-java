package queryingworkflows.orderpizza;

import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import queryingworkflows.model.OrderConfirmation;
import queryingworkflows.model.PizzaOrder;

@WorkflowInterface
public interface PizzaWorkflow {

  @WorkflowMethod
  OrderConfirmation orderPizza(PizzaOrder order);

  @SignalMethod
  void fulfillOrderSignal(boolean bool);

  // PART A: Define the `orderStatus()` method signature here. It should
  @QueryMethod
  String orderStatus();

}
