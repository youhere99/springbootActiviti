package light.mvc.workflow.taskListener;

import java.io.Serializable;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;

/**
 * 
 * 任务监听器，实现TaskListener接口
 * 
 */
public class TaskListenerDemo implements Serializable, TaskListener {
	private Expression arg;

	public Expression getArg() {
		return arg;
	}

	public void setArg(Expression arg) {
		this.arg = arg;
	}

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("任务监听器:" + arg.getValue(delegateTask));
	}

}