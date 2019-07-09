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
public class ScriptTaskControllerTest {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private ActivitiService activitiService;

	@Test
	public void deplomentActiviti() {
		String folderPath = "diagrams";
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "自动化");// 名称
		map.put("id", "groovy-script-task-process");// id
		map.put("category", "groovy-script-task-process");// 类别
		activitiService.devlopActiviti(folderPath, map);
	}

	@Test
	public void deplomentActiviti2() {
		String folderPath = "diagrams";
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "自动化");// 名称
		map.put("id", "js-script-task-process");// id
		map.put("category", "js-script-task-process");// 类别
		activitiService.devlopActiviti(folderPath, map);
	}

	@Test
	public String groovyScritTask() {

		runtimeService.startProcessInstanceByKey("groovy-script-task-process");

		return "check the console log...";
	}

	@Test
	public String jsScritTask() {

		runtimeService.startProcessInstanceByKey("js-script-task-process");

		return "check the console log...";
	}
}
