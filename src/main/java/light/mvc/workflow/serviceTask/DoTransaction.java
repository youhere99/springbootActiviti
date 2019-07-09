package light.mvc.workflow.serviceTask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoTransaction implements JavaDelegate {

	private final Logger log = LoggerFactory.getLogger(Initialization.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// varInSubprocess<->varOutFromSubprocess
		String varOutFromSubprocess = (String) execution.getVariable("varOutFromSubprocess");
		log.info("in mainprocess get(varOutFromSubprocess): " + varOutFromSubprocess);

		log.info("variavles=" + execution.getVariables());
		execution.setVariable("m:dt", "Mainprocess:DoTransaction");
		log.info("I am DoTransaction in mainprocess.");
	}
}