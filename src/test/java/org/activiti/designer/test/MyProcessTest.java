package org.activiti.designer.test;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import boot.spring.SpringBootWithActivitiApplication;
import boot.spring.service.ActivitiService;
import light.mvc.workflow.taskListener.ExectuionListenerDemo;
import light.mvc.workflow.taskListener.TaskListenerDemo;

/**
 * 监听流程引擎是否启动成功
 * 
 * @author wsylp
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWithActivitiApplication.class)
public class MyProcessTest {

	@Autowired
	private ActivitiService activitiService;

	@Test
	public void deplomentActiviti() {
		String folderPath = "diagrams";
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "MyProcess流程开始与结束监听器");// 名称
		map.put("id", "MyProcess");// id
		map.put("category", "MyProcess流程");// 类别
		activitiService.devlopActiviti(folderPath, map);
	}

	@Test
	public void startActiviti() {
		String processDefinitionKey = "MyProcess";
		// String orgCode = "070067801";
		// 设置变量
		HashMap<String, Object> map = new HashMap<>();
		// map.put("createLoginName", "0003");
		// map.put("orgCode", orgCode);

		map.put("myprocessListener", new ExectuionListenerDemo());
		map.put("mytaskListener", new TaskListenerDemo());
		map.put("user", "mpc");
		activitiService.startActivitiAndFinsh(processDefinitionKey, map);
		// activitiService.startActiviti(processDefinitionKey, map);
	}

}
