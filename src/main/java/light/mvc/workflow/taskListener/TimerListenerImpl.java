package light.mvc.workflow.taskListener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;

public class TimerListenerImpl implements TaskListener, ExecutionListener {

	@Override

	public void notify(DelegateTask arg0) {

		System.out.println("Timer Event Task Is Running");

	}

	@Override

	public void notify(DelegateExecution arg0) throws Exception {

		// TODO Auto-generated method stub

		System.out.println("Timer Event Execution Is Running");

	}

}