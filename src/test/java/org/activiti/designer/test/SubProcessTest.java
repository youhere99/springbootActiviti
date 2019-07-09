package org.activiti.designer.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;

import boot.spring.SpringBootWithActivitiApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWithActivitiApplication.class)
public class SubProcessTest {

	private static final Logger log = LoggerFactory.getLogger(SubProcessTest.class);

	@Autowired
	RepositoryService repositoryService;

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	TaskService taskService;

	/** 部署流程定义 */
	@Test
	public void deployProcessDefinition() {

		Deployment deployment = repositoryService.createDeployment()// 创建部署对象
				.name("SubProcess演示3")// 声明流程的名称
				.addClasspathResource("diagrams/SubProcess.bpmn").addClasspathResource("diagrams/SubProcess.png")
				.deploy();// 完成部署

		System.out.println("部署ID：" + deployment.getId());// 部署ID:1
		System.out.println("部署时间：" + deployment.getDeploymentTime());// 部署时间

		log.info("部署流程:{}", JSON.toJSONString(deployment));
	}

	/** 启动流程实例 */
	@Test
	public void startProcessInstance() {

		String processDefinitionKey = "SubProcess";// 流程定义的key,也就是bpmn中存在的ID

		// Map<String, Object> variables = new HashMap<String, Object>();
		// variables.put("userid", "xiaoWu,xiaoLiu,xiaoQi");
		Map<String, Object> variables = new HashMap<String, Object>();
		// variables.put("myDate1", "2019-07-07T15:44:00");
		// variables.put("myDate2", "2019-07-07T15:45:00");
		// variables.put("myBean", new MyBeanStartTimerEvent());
		ProcessInstance pi = runtimeService// 管理流程实例和执行对象，也就是表示正在执行的操作
				.startProcessInstanceByKey(processDefinitionKey, variables);//// 按照流程定义的key启动流程实例

		System.out.println("流程实例ID：" + pi.getId());// 流程实例ID：101
		System.out.println("流程实例ID：" + pi.getProcessInstanceId());// 流程实例ID：101
		System.out.println("流程实例ID:" + pi.getProcessDefinitionId());// myMyHelloWorld:1:4

		// log.info("启动流程实例:{}", JSON.toJSONString(pi,
		// SerializerFeature.WriteNullStringAsEmpty));

	}

	/** 查看当前任务办理人的个人任务 */
	@Test
	public void findPersonnelTaskList() {
		String assignee = "xiaoQi";// 当前任务办理人
		List<Task> tasks = taskService// 与任务相关的Service
				.createTaskQuery()// 创建一个任务查询对象
				// .taskAssignee(assignee)
				.taskCandidateUser(assignee).list();
		if (tasks != null && tasks.size() > 0) {
			for (Task task : tasks) {
				System.out.println("任务ID:" + task.getId());
				System.out.println("任务的办理人:" + task.getAssignee());
				System.out.println("任务名称:" + task.getName());
				System.out.println("任务的创建时间:" + task.getCreateTime());
				System.out.println("getTaskDefinitionKey:" + task.getTaskDefinitionKey());
				System.out.println("getProcessDefinitionId:" + task.getProcessDefinitionId());
				System.out.println("流程实例ID:" + task.getProcessInstanceId());
				System.out.println("#####################################");
			}
		}
	}

	/** 完成任务 */
	@Test
	public void completeTask() {
		String taskID = "70002";
		taskService.complete(taskID);
		Map<String, Object> variables2 = new HashMap<String, Object>();
		variables2.put("myDate2", "2019-07-07T15:49:00");
		runtimeService.setVariables("67501", variables2);
		System.out.println("完成任务：" + taskID);

	}

	// 查询流程定义
	@Test
	public void findProcessDifinitionList() {
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
				// 查询条件
				// .processDefinitionKey("myHelloWorld")// 按照流程定义的key
				// .processDefinitionId("helloworld")//按照流程定义的ID
				.orderByProcessDefinitionVersion().desc()// 排序
				// 返回结果
				// .singleResult()//返回惟一结果集
				// .count()//返回结果集数量
				// .listPage(firstResult, maxResults)
				.list();// 多个结果集

		if (list != null && list.size() > 0) {
			for (ProcessDefinition pd : list) {
				System.out.println("流程定义的ID：" + pd.getId());
				System.out.println("流程定义的名称：" + pd.getName());
				System.out.println("流程定义的Key：" + pd.getKey());
				System.out.println("流程定义的部署ID：" + pd.getDeploymentId());
				System.out.println("流程定义的资源名称：" + pd.getResourceName());
				System.out.println("流程定义的版本：" + pd.getVersion());
				System.out.println("########################################################");
			}
		}

	}

	// 删除流程定义
	@Test
	public void deleteProcessDifinition() {
		// 部署对象ID
		String deploymentId = "25001";
		repositoryService// 获取流程定义和部署对象相关的Service
				.deleteDeployment(deploymentId, true);

		System.out.println("删除成功~~~");// 使用部署ID删除流程定义,true表示级联删除
	}

	// 查看流程定义的资源文件
	@Test
	public void viewPng() throws IOException {
		// 部署ID
		String deploymentId = "27501";
		// 获取的资源名称
		List<String> list = repositoryService.getDeploymentResourceNames(deploymentId);
		// 获得资源名称后缀.png
		String resourceName = "";
		if (list != null && list.size() > 0) {
			for (String name : list) {
				if (name.indexOf(".png") >= 0) {// 返回包含该字符串的第一个字母的索引位置
					resourceName = name;
				}
			}
		}

		// 获取输入流，输入流中存放.PNG的文件
		InputStream in = repositoryService.getResourceAsStream(deploymentId, resourceName);

		// 将获取到的文件保存到本地
		FileUtils.copyInputStreamToFile(in, new File("C:\\Users\\xzzz\\Desktop\\" + resourceName));

		System.out.println("文件保存成功！");
	}

	/** 设置流程变量 */
	@Test
	public void setProcessVariables() {
		String processInstanceId = "32501";// 流程实例ID
		String assignee = "zhangsan";// 任务办理人
		// 查询当前办理人的任务ID
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)// 使用流程实例ID
				.taskAssignee(assignee)// 任务办理人
				.singleResult();

		// 设置流程变量【基本类型】
		taskService.setVariable(task.getId(), "请假people", assignee);
		taskService.setVariableLocal(task.getId(), "请假days", 3);
		taskService.setVariable(task.getId(), "请假日期date", new Date());

	}

	/** 查询正在执行的组任务列表 */
	@Test
	public void findGroupCandidate() {
		// 任务ID
		String taskId = "32504";
		List<IdentityLink> list = taskService//
				.getIdentityLinksForTask(taskId);
		if (list != null && list.size() > 0) {
			for (IdentityLink identityLink : list) {
				System.out.println("任务ID：" + identityLink.getTaskId());
				System.out.println("流程实例ID：" + identityLink.getProcessInstanceId());
				System.out.println("用户ID：" + identityLink.getUserId());
				System.out.println("工作流角色ID：" + identityLink.getGroupId());
				System.out.println("#########################################");
			}
		}
	}

	/** 将个人任务从一个人分配给另一个人 */
	@Test
	public void setAssignee() {
		// 指定任务的办理人
		String userId = "zhuanban2";
		// 任务ID
		String taskId = "32504";
		taskService.setAssignee(taskId, userId);
	}

	/** 将组任务指定个人任务(拾取任务) */
	@Test
	public void claim() {
		// 任务ID
		String taskId = "75005";
		// 个人任务的办理人
		String userId = "renling2";
		taskService.claim(taskId, userId);
		// taskService.setOwner(taskId, "xiaoBa");
	}

	/** 将个人任务再回退到组任务（前提：之前这个任务是组任务） */
	@Test
	public void setAssignee2() {
		// 任务ID
		String taskId = "50005";
		taskService//
				.setAssignee(taskId, null);
	}

	/** 向组任务中添加成员 */
	@Test
	public void addGroupUser() {
		// 任务ID
		String taskId = "50005";
		// 新增组任务的成员
		String userId = "xiaoBa";
		taskService//
				.addCandidateUser(taskId, userId);
	}

	/** 向组任务中删除成员 */
	@Test
	public void deleteGroupUser() {
		// 任务ID
		String taskId = "50005";
		// 新增组任务的成员
		String userId = "xiaoBa";
		taskService.deleteCandidateUser(taskId, userId);
	}

	/**
	 * 指定代办人
	 */
	@Test
	public void delegateTask() {
		String taskId = "32504";
		String loginName = "0003";
		taskService.delegateTask(taskId, loginName);
	}

	/**
	 * 正在运行的任务表中被委派人办理任务后任务会回到委派人 ，历史任务表中也一样,只是多了一个人进行审批
	 */
	@Test
	public void resolveTask() {
		String taskId = "2511";
		Map<String, Object> map = new HashMap<>();
		taskService.resolveTask(taskId, map);
	}

}
