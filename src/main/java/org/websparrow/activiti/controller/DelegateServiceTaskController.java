package org.websparrow.activiti.controller;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activiti")
public class DelegateServiceTaskController {

	@Autowired
	private RuntimeService runtimeService;

	@GetMapping("service/delegate")
	public String startTheProcess() {

		runtimeService.startProcessInstanceByKey("java-delegate-service-task-process");

		return "Check the console log...";
	}
}
