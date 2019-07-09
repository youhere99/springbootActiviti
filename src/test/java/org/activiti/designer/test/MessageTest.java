package org.activiti.designer.test;

import java.util.HashMap;
import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import boot.spring.SpringBootWithActivitiApplication;
import boot.spring.service.ActivitiService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWithActivitiApplication.class)
public class MessageTest {

	@Autowired
	private ActivitiService activitiService;

	@Autowired
	private RuntimeService runtimeService;

	@Test
	public void deplomentActiviti() {
		String folderPath = "diagrams";
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "消息messageStartEvent");// 名称
		map.put("id", "messageStartEvent");// id
		map.put("category", "messageStartEvent流程");// 类别
		// map.put("tenantId", "http://www.shareniu.com/article/35.htm");
		activitiService.devlopActiviti(folderPath, map);
	}

	@Test
	public void startActiviti() {
		runtimeService.startProcessInstanceByMessage("messages");
		System.out.println("流程实例数量：" + runtimeService.createProcessInstanceQuery().count());
	}

	/** 触发消息事件 */

	@Test
	public void completeMessage() {

		List<Execution> executions = runtimeService.createExecutionQuery().messageEventSubscriptionName("messages")
				.list();

		for (Execution e : executions) {

			runtimeService.messageEventReceived("messages", e.getId());

		}

	}
}
