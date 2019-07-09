/**

 *

 */

package light.mvc.workflow.serviceTask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * 
 * 
 * 
 * 项目名称：lightmvc
 * 
 * 类名称：ServiceTask
 * 
 * 类描述：
 * 
 * 创建人：邓家海
 * 
 * 创建时间：2017年6月4日 下午6:18:11
 * 
 * 修改人：deng
 * 
 * 修改时间：2017年6月4日 下午6:18:11
 * 
 * 修改备注：
 * 
 * @version
 * 
 * 
 * 
 */

public class SecondTestRunningTask implements JavaDelegate {

	// 重写委托的提交方法

	@Override

	public void execute(DelegateExecution execution) throws Exception {

		// receiveTaskprocess

		System.out.println("SecondTestRunningTask is running!");

	}

}