package org.websparrow.activiti.controller;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activiti")
public class ExpressionServiceTaskController {

	@Autowired
	private RuntimeService runtimeService;

	@GetMapping("service/expression")
	public String startTheProcess() {

		runtimeService.startProcessInstanceByKey("method-expression-service-task-process");

		return "Check the console log...";
	}
}
