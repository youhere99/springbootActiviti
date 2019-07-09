package light.mvc.workflow.serviceTask;

import java.util.HashMap;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckBankTask implements TaskListener {

	private final Logger log = LoggerFactory.getLogger(CheckBankTask.class);

	@Override
	@SuppressWarnings("unchecked")
	public void notify(DelegateTask delegateTask) {
		log.info("i am CheckBankTask.");
		System.out.println("in : " + delegateTask.getVariables());
		((HashMap<String, Object>) delegateTask.getVariables().get("in")).put("next", "CheckBankTask");
		((HashMap<String, Object>) delegateTask.getVariables().get("out")).put("reponse",
				"subprocess:CheckBankTask->CheckMerchantTask");
	}
}