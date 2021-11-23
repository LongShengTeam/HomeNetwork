import com.alibaba.druid.sql.visitor.functions.If;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipInputStream;

public class activitytest01 {
    @Test
    public void createTable1() {//新建db_table  succcess
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println("-------------执行结束------------");
    }

    @Test
    public void createTable2() {//新建db_table  succcess
        ProcessEngineConfiguration processEngineConfigurationFromResource =
                ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        processEngineConfigurationFromResource.setXmlEncoding("utf-8");
        processEngineConfigurationFromResource.setDatabaseSchemaUpdate("true");
        ProcessEngine processEngine = processEngineConfigurationFromResource.buildProcessEngine();
        System.out.println("-------------执行结束------------");
    }

    @Test
    public void publishFlow1() {//部署流程  succcess
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        DeploymentBuilder deployment = repositoryService.createDeployment();
        deployment.key("ql202111181441holidayflow")
                .addClasspathResource("bpmnFile/holiday.bpmn")
                .addClasspathResource("bpmnFile/holiday.xml")
                .name("ql202111181441holidayflow")
                .deploy();
        System.out.println("-------------执行结束------------");
    }

    @Test
    public void publishFlowByZIP() throws FileNotFoundException {//部署流程  succcess
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        DeploymentBuilder deployment = repositoryService.createDeployment();
        InputStream inputStream = new FileInputStream("src\\test\\resources\\bpmnFile\\202111180907.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        deployment.key("ql2021111909161holidayflow")
                .addZipInputStream(zipInputStream)
                .name("202111190916")
                .deploy();

        System.out.println("-------------执行结束------------");
    }

    @Test
    public void queryTaskNow1() {//查询当前任务  succcess  未启动流程实例，查询不到数据
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = defaultProcessEngine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> taskList =
                taskQuery.processDefinitionKey("myProcess_1").list();//  ql202111181441holidayflow
        if (taskList != null && taskList.size() > 0) {
            for (Task task : taskList) {
                System.out.print("   当前任务key:" + task.getName());
                System.out.print("   当前任务id:" + task.getId());
                System.out.print("   当前任务执行人:" + task.getAssignee());
                System.out.print("   当前任务详情:" + task.getDescription());
                System.out.print("   当前任务实例id:" + task.getProcessInstanceId());
                System.out.println();
            }
        } else {
            System.out.println("沒有查询到相应的任务");
        }
        System.out.println("-------------执行结束------------");
    }

    @Test
    public void startupFlowInstance() {//啟動流程實例 succcess
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1");
        System.out.println("-------------执行结束------------");
    }

    @Test
    public void queryTaskNow2() {//查询当前任务  succcess  启动流程实例，查詢
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = defaultProcessEngine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> taskList =
                taskQuery.processDefinitionKey("myProcess_1").list();//  ql202111181441holidayflow
        if (taskList != null && taskList.size() > 0) {
            for (Task task : taskList) {
                System.out.print("   当前任务key:" + task.getName());
                System.out.print("   当前任务id:" + task.getId());
                System.out.print("   当前任务执行人:" + task.getAssignee());
                System.out.print("   当前任务详情:" + task.getDescription());
                System.out.print("   当前任务实例id:" + task.getProcessInstanceId());
                System.out.println();
            }
        } else {
            System.out.println("沒有查询到相应的任务");
        }
        System.out.println("-------------执行结束------------");
    }

    @Test
    public void startup1task() {//启动一个任务  succcess
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = defaultProcessEngine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> taskList =
                taskQuery.processDefinitionKey("myProcess_1").list();//  ql202111181441holidayflow
        if (taskList != null && taskList.size() > 0) {
            taskService.complete(taskList.get(0).getId());
        } else {
            System.out.println("沒有查询到相应的任务");
        }
        System.out.println("-------------执行结束------------");
    }

    @Test
    public void startupalltask() {//启动所有任务  succcess
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = defaultProcessEngine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> taskList =
                taskQuery.processDefinitionKey("myProcess_1").list();//  ql202111181441holidayflow
        if (taskList != null && taskList.size() > 0) {
            for (Task task : taskList) {
                taskService.complete(task.getId());
                System.out.println("执行了任务id是：" + task.getId() + "的任务");
            }

        } else {
            System.out.println("沒有查询到相应的任务");
        }
        System.out.println("-------------执行结束------------");
    }

    @Test
    public void deleterep() {//刪除流程 succcess
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        List<ProcessDefinition> myProcess_1 =
                repositoryService.createProcessDefinitionQuery().processDefinitionKey("myProcess_1").list();

        repositoryService.deleteDeployment(myProcess_1.get(0).getDeploymentId(), true);
        System.out.println("-------------执行结束------------");
    }

    @Test
    public void queryTask() {//查询某任务 succcess
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = defaultProcessEngine.getTaskService();
        List<Task> myProcess_11 = taskService.createTaskQuery().processDefinitionKey("myProcess_1").list();

        if (myProcess_11 != null && myProcess_11.size() > 0) {
            for (Task task : myProcess_11) {
                System.out.println("当前任务ID：" + task.getId());
                System.out.println("当前任务实例ID：" + task.getProcessInstanceId());
                System.out.println("当前任务执行人：" + task.getAssignee());
                System.out.println("当前任务描述：" + task.getDescription());
            }
        }

        System.out.println("-------------执行结束------------");
    }

    @Test
    public void queryFlowAll() {//查询全部流程 succcess
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        processDefinitionQuery.orderByProcessDefinitionKey().asc().listPage(10, 50);


        System.out.println("-------------执行结束------------");
    }

    @Test
    public void queryFlow_BPMN_And_PNG1() throws IOException {//查询全部流程    1 activity的api  jdbc对clob数据的读取
        //jdbc对clob数据的读取
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> myProcess_1ist = processDefinitionQuery.processDefinitionKey("myProcess_1").list();
        InputStream bpmnAsStream = null;
        InputStream pngAsStream = null;
        FileOutputStream bpmnOutputStream = null;
        FileOutputStream pngOutputStream = null;
        if (myProcess_1ist != null && myProcess_1ist.size() > 0) {
            System.out.println("-----------查询到流程，流程不为空");
            for (ProcessDefinition pdt : myProcess_1ist) {
                String deploymentId = pdt.getDeploymentId();
                String diagramResourceName = pdt.getDiagramResourceName();//png
                String bpmnName = pdt.getResourceName();//bpmn
                System.out.println("数据库png文件名为："+diagramResourceName+"  bpmn文件名为："+bpmnName);
                bpmnAsStream = repositoryService.getResourceAsStream(deploymentId, bpmnName);
                pngAsStream = repositoryService.getResourceAsStream(deploymentId, diagramResourceName);

                File file1 = new File("E:/bpmnexport/202111191421/bpmnExport");

                File file2 = new File("E:/bpmnexport/202111191421/bpmnExport");

                if(!file1.exists()){
                    file1.mkdirs();
                    File file3 = new File("E:/bpmnexport/202111191421/bpmnExport/holidaybpmnExport.bpmn");
                    file3.createNewFile();
                }
                if(!file2.exists()){
                    file2.mkdirs();
                    File file4 = new File("E:/bpmnexport/202111191421/bpmnExport/holidaypngExport.png");
                    file4.createNewFile();
                }
                bpmnOutputStream = new FileOutputStream("E:/bpmnexport/202111191421/bpmnExport/holidaybpmnExport.bpmn");
                pngOutputStream = new FileOutputStream("E:/bpmnexport/202111191421/bpmnExport/holidaypngExport.png");
                IOUtils.copy(bpmnAsStream, bpmnOutputStream);
                IOUtils.copy(pngAsStream, pngOutputStream);
                System.out.println("----------------执行完毕一次");
            }
        }
        if(bpmnAsStream != null){
            bpmnAsStream.close();
        }
        if(pngAsStream != null){
            pngAsStream.close();
        }
        if(bpmnOutputStream != null){
            bpmnOutputStream.close();
        }
        if(pngOutputStream != null){
            pngOutputStream.close();
        }

        // BpmnModel bpmnModel = repositoryService.getBpmnModel("deploymentId");
        // InputStream deploymentId1 = repositoryService.getProcessDiagram("deploymentId");

        /*
        InputStream processDiagram = repositoryService.getProcessDiagram("holiday.png");
        byte[] byte8_1 = new byte[8];
        //Writer writer
        int read = processDiagram.read(byte8_1);
        */
        System.out.println("-------------执行结束------------");
    }

    @Test
    public void queryFlowHis() {//查询全部流程历史 succcess
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = defaultProcessEngine.getHistoryService();
        HistoricActivityInstanceQuery historicActivityInstanceQuery = historyService.createHistoricActivityInstanceQuery();
        List<HistoricActivityInstance> list =
                historicActivityInstanceQuery.orderByActivityId().orderByHistoricActivityInstanceEndTime().asc().list();
        if (list != null && list.size()>0){
            for (HistoricActivityInstance his :list){
                System.out.println("-----ActivityId:"+his.getActivityId());
                System.out.println("-----getActivityName:"+his.getActivityName());
                System.out.println("-----执行人:"+his.getAssignee());
                System.out.println("-----开始时间:"+his.getStartTime());
                System.out.println("-----结束时间:"+his.getEndTime());
                System.out.println("----------------------------------------------");
            }
        }


        System.out.println("-------------执行结束------------");
    }
    @Test
    public void queryFlow_BPMN_And_PNG2() throws IOException {//查询全部流程    1 activity的api  jdbc对clob数据的读取
        //jdbc对clob数据的读取
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> myProcess_1ist = processDefinitionQuery.processDefinitionKey("myProcess_1").list();
        InputStream bpmnAsStream = null;
        InputStream pngAsStream = null;
        FileOutputStream bpmnOutputStream = null;
        FileOutputStream pngOutputStream = null;
        SimpleDateFormat yyyyMMDDHHmmss = new SimpleDateFormat("yyyyMMDDHHmmss");
        Date date = new Date();
        String yyyyMMDDHHmmssformat = yyyyMMDDHHmmss.format(date);
        System.out.println("--------------文件数字部分为："+yyyyMMDDHHmmssformat);
        if (myProcess_1ist != null && myProcess_1ist.size() > 0) {
            System.out.println("-----------查询到流程，流程不为空");
            for (ProcessDefinition pdt : myProcess_1ist) {
                String deploymentId = pdt.getDeploymentId();
                String pngName = pdt.getDiagramResourceName();//png
                String bpmnName = pdt.getResourceName();//bpmn
                System.out.println("数据库png文件名为："+pngName+"  bpmn文件名为："+bpmnName);
                //bpmnAsStream = repositoryService.getResourceAsStream(deploymentId, bpmnName);
                //pngAsStream = repositoryService.getResourceAsStream(deploymentId, diagramResourceName);
                pngAsStream = repositoryService.getProcessDiagram(deploymentId);
                bpmnAsStream = repositoryService.getProcessModel(deploymentId);
                File file1 = new File("E:/bpmnexport/"+yyyyMMDDHHmmssformat+"/bpmnExport");

                File file2 = new File("E:/bpmnexport/"+yyyyMMDDHHmmssformat+"/bpmnExport");

                if(!file1.exists()){
                    file1.mkdirs();
                    File file3 = new File("E:/bpmnexport/"+yyyyMMDDHHmmssformat+"/bpmnExport/holidaybpmnExport.bpmn");
                    file3.createNewFile();
                }
                if(!file2.exists()){
                    file2.mkdirs();
                    File file4 = new File("E:/bpmnexport/"+yyyyMMDDHHmmssformat+"/bpmnExport/holidaypngExport.png");
                    file4.createNewFile();
                }
                bpmnOutputStream = new FileOutputStream("E:/bpmnexport/"+yyyyMMDDHHmmssformat+"/bpmnExport/holidaybpmnExport.bpmn");
                pngOutputStream = new FileOutputStream("E:/bpmnexport/"+yyyyMMDDHHmmssformat+"/bpmnExport/holidaypngExport.png");
                IOUtils.copy(bpmnAsStream, bpmnOutputStream);
                IOUtils.copy(pngAsStream, pngOutputStream);
                System.out.println("----------------执行完毕一次");
            }
        }
        if(bpmnAsStream != null){
            bpmnAsStream.close();
        }
        if(pngAsStream != null){
            pngAsStream.close();
        }
        if(bpmnOutputStream != null){
            bpmnOutputStream.close();
        }
        if(pngOutputStream != null){
            pngOutputStream.close();
        }

        System.out.println("-------------执行结束------------");
    }

}
