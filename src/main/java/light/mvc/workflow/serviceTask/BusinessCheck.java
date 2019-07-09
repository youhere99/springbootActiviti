package light.mvc.workflow.serviceTask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusinessCheck implements JavaDelegate {

	private final Logger log = LoggerFactory.getLogger(CheckBankTask.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// varOutFromMainprocess<->varInSubprocess
		String varInSubprocess = (String) execution.getVariable("varInSubprocess");
		log.info("in subprocess get(varInSubprocess): " + varInSubprocess);

		log.info("variavles=" + execution.getVariables());
		execution.setVariable("s:bc", "Subprocess:BusinessCheck");
		log.info("I am BusinessCheck in subprocess.");

		execution.setVariable("varInSubprocess", "BBBB");
		log.info("in subprocess set(varInSubprocess): " + varInSubprocess);
	}
}