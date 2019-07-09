package org.websparrow.activiti.controller;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activiti")
public class ScriptTaskController {

	@Autowired
	private RuntimeService runtimeService;

	@GetMapping("script-task/groovy")
	public String groovyScritTask() {

		runtimeService.startProcessInstanceByKey("groovy-script-task-process");

		return "check the console log...";
	}

	@GetMapping("script-task/js")
	public String jsScritTask() {

		runtimeService.startProcessInstanceByKey("js-script-task-process");

		return "check the console log...";
	}
}
