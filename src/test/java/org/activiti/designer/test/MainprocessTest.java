package org.activiti.designer.test;

import java.util.HashMap;
import java.util.Map;

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
public class MainprocessTest {

	@Autowired
	private ActivitiService activitiService;

	@Autowired
	private RuntimeService runtimeService;

	@Test
	public void deplomentActiviti() {
		String folderPath = "diagrams";
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "消息Mainprocess");// 名称
		map.put("id", "Mainprocess");// id
		map.put("category", "Mainprocess流程");// 类别
		// map.put("tenantId", "http://www.shareniu.com/article/35.htm");
		activitiService.devlopActiviti(folderPath, map);
	}

	@Test
	public void deplomentActiviti2() {
		String folderPath = "diagrams";
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "消息CheckSubprocess");// 名称
		map.put("id", "CheckSubprocess");// id
		map.put("category", "CheckSubprocess流程");// 类别
		// map.put("tenantId", "http://www.shareniu.com/article/35.htm");
		activitiService.devlopActiviti(folderPath, map);
	}

	@Test
	public void startActiviti() {
		Map<String, Object> variables = new HashMap<String, Object>();
		Map<String, Object> subVariables = new HashMap<String, Object>();
		variables.put("protocol", "UM32");
		variables.put("repository", "10.10.38.99:/home/shirdrn/repository");
		variables.put("in", subVariables);
		variables.put("out", new HashMap<String, Object>());
		runtimeService.startProcessInstanceByKey("Mainprocess", variables);
		System.out.println("流程实例数量：" + runtimeService.createProcessInstanceQuery().count());
	}

}
