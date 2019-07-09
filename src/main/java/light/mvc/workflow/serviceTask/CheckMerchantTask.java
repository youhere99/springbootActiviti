package light.mvc.workflow.serviceTask;

import java.util.HashMap;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckMerchantTask implements TaskListener {

	private final Logger log = LoggerFactory.getLogger(CheckMerchantTask.class);

	@Override
	@SuppressWarnings("unchecked")
	public void notify(DelegateTask delegateTask) {
		log.info("i am CheckMerchantTask.");
		System.out.println("in : " + delegateTask.getVariables());
		((HashMap<String, Object>) delegateTask.getVariables().get("in")).put("previous", "CheckMerchantTask");
	}
}