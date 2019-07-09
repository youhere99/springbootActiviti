package org.websparrow.activiti.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class JavaDelegateService implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		System.out.println("Java Delegate Service Task executed successfully.");

		System.out.println("And the Sum of 2 + 2= " + (2 + 2));

	}
}
