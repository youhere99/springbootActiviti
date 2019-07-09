package light.mvc.workflow.serviceTask;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 节点监听器，任务监听器，连线监听器
 * 
 * @author wsylp
 *
 */
public class MyTaskListener implements TaskListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyTaskListener.class);

	private static final long serialVersionUID = 6200534335483960408L;

	private Expression arg;

	public Expression getArg() {
		return arg;
	}

	public void setArg(Expression arg) {
		this.arg = arg;
	}

	@Override
	public void notify(DelegateTask delegateTask) {

		// 实现TaskListener中的方法
		String eventName = delegateTask.getEventName();
		LOGGER.info("任务监听器:{}", arg.getValue(delegateTask));
		if ("create".endsWith(eventName)) {
			LOGGER.info("create=========");
		} else if ("assignment".endsWith(eventName)) {
			LOGGER.info("assignment========");
		} else if ("complete".endsWith(eventName)) {

			LOGGER.info("complete===========");
		} else if ("delete".endsWith(eventName)) {
			LOGGER.info("delete=============");
		}

	}

}
