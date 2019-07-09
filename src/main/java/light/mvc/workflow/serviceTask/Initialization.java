package light.mvc.workflow.serviceTask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Initialization implements JavaDelegate {

	private final Logger log = LoggerFactory.getLogger(Initialization.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		log.info("variavles=" + execution.getVariables());
		execution.setVariable("m:i", "Mainprocess:Initialization");
		log.info("I am Initialization in mainprocess.");

		execution.setVariable("varOutFromMainprocess", "AAAA");
		log.info("in mainprocess set(varOutFromMainprocess): " + execution.getVariable("varOutFromMainprocess"));
	}
}