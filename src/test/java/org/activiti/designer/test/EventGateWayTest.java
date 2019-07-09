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
public class EventGateWayTest {

	@Autowired
	private ActivitiService activitiService;

	@Autowired
	private RuntimeService runtimeService;

	@Test
	public void deplomentActiviti() {
		String folderPath = "diagrams";
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "EventGateWay");// 名称
		map.put("id", "EventGateWay");// id
		map.put("category", "EventGateWay流程");// 类别
		activitiService.devlopActiviti(folderPath, map);
	}

	@Test
	public void startActiviti() {

		runtimeService.startProcessInstanceByKey("EventGateWay");
	}

	/** 触发信号事件 */

	@Test
	public void completeSingal() {
		// List<Execution> executions =
		// runtimeService.createExecutionQuery().signalEventSubscriptionName("Singal").list();
		//
		// for (Execution e : executions) {
		// runtimeService.signalEventReceived("Singal", e.getId());
		//
		// }
		runtimeService.signalEventReceived("Singal");
	}

	/** 触发消息事件 */

	@Test
	public void completeMessage() {

		// List<Execution> executions =
		// runtimeService.createExecutionQuery().messageEventSubscriptionName("Message")
		// .list();
		//
		// for (Execution e : executions) {
		//
		runtimeService.messageEventReceived("Message", "75003");
		//
		// }
		// runtimeService.startProcessInstanceByMessage("Message");

	}
}
