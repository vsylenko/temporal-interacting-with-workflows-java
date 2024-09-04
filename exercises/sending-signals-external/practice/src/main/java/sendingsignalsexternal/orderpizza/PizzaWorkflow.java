package sendingsignalsexternal.orderpizza;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import sendingsignalsexternal.model.OrderConfirmation;
import sendingsignalsexternal.model.PizzaOrder;

@WorkflowInterface
public interface PizzaWorkflow {

  @WorkflowMethod
  OrderConfirmation orderPizza(PizzaOrder order);

  @SignalMethod
  void fulfillOrderSignal(boolean bool);

}
