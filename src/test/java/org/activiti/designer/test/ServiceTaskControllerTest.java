package org.activiti.designer.test;

import java.util.HashMap;

import org.activiti.engine.RuntimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import boot.spring.SpringBootWithActivitiApplication;
import boot.spring.service.ActivitiService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWithActivitiApplication.class)
public class ServiceTaskControllerTest {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private ActivitiService activitiService;

	@Test
	public void deplomentActiviti() {
		String folderPath = "diagrams";
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "自动化");// 名称
		map.put("id", "delegate-service-process");// id
		map.put("category", "serviceTask");// 类别
		activitiService.devlopActiviti(folderPath, map);
	}

	@Test
	public void deplomentActiviti2() {
		String folderPath = "diagrams";
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "自动化");// 名称
		map.put("id", "expression-service-task-process");// id
		map.put("category", "expression-service-task-process");// 类别
		activitiService.devlopActiviti(folderPath, map);
	}

	@Test
	public String startDelegateTheProcess() {

		runtimeService.startProcessInstanceByKey("java-delegate-service-task-process");

		return "Check the console log...";
	}

	@Test
	public String startExpressionTheProcess() {

		runtimeService.startProcessInstanceByKey("method-expression-service-task-process");

		return "Check the console log...";
	}
}
