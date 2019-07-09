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
public class TimerTaskTest {

	@Autowired
	private ActivitiService activitiService;

	@Autowired
	private RuntimeService runtimeService;

	@Test
	public void deplomentActiviti() {
		String folderPath = "diagrams";
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "消息timerStartEvent");// 名称
		map.put("id", "timerStartEvent");// id
		map.put("category", "timerStartEvent流程");// 类别
		// map.put("tenantId", "http://www.shareniu.com/article/35.htm");
		activitiService.devlopActiviti(folderPath, map);
	}

	@Test
	public void startActiviti() {
		// 定时启动
		// runtimeService.startProcessInstanceByKey("timerStartEvent");
	}

}
