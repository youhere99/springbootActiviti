package light.mvc.workflow.serviceTask;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectMessageTask implements TaskListener {

	private final Logger log = LoggerFactory.getLogger(CollectMessageTask.class);

	@Override
	@SuppressWarnings("unchecked")
	public void notify(DelegateTask delegateTask) {
		log.info("i am CollectMessageTask.");
		System.out.println("out : " + delegateTask.getVariables().get("out"));
		System.out.println("all : " + delegateTask.getVariables());
	}
}