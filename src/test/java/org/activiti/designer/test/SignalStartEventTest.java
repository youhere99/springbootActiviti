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
public class SignalStartEventTest {

	@Autowired
	private ActivitiService activitiService;

	@Autowired
	private RuntimeService runtimeService;

	@Test
	public void deplomentActiviti() {
		String folderPath = "diagrams";
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "消息SignalStartEvent");// 名称
		map.put("id", "SignalStartEvent");// id
		map.put("category", "SignalStartEvent流程");// 类别
		// map.put("tenantId", "http://www.shareniu.com/article/35.htm");
		activitiService.devlopActiviti(folderPath, map);
	}

	/** 触发信号事件 */

	@Test
	public void completeMessage() {

		// List<Execution> executions =
		// runtimeService.createExecutionQuery().signalEventSubscriptionName("sigal").list();
		//
		// for (Execution e : executions) {
		//
		// runtimeService.signalEventReceived("sigal", e.getId());
		//
		// }
		runtimeService.signalEventReceived("sigal");

	}
}
