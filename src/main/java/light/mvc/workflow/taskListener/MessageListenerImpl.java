package light.mvc.workflow.taskListener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;

public class MessageListenerImpl implements TaskListener, ExecutionListener {

	@Override

	public void notify(DelegateTask arg0) {

		System.out.println("Message Event Task Is Running");

	}

	@Override

	public void notify(DelegateExecution arg0) throws Exception {

		// TODO Auto-generated method stub

		System.out.println("Message Event Execution Is Running");

	}

}