package molab.main.java.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import molab.main.java.component.ApptryComponent;
import molab.main.java.component.adminTree.EmulatorComponent;
import molab.main.java.component.adminTree.ServerComponent;
import molab.main.java.entity.T_Application;
import molab.main.java.entity.T_Deployment;
import molab.main.java.entity.T_Developer;
import molab.main.java.entity.T_Dispatcher;
import molab.main.java.entity.T_Emulator;
import molab.main.java.entity.T_Server;
import molab.main.java.service.ApplicationService;
import molab.main.java.service.ApptryService;
import molab.main.java.service.DeploymentService;
import molab.main.java.service.DeveloperService;
import molab.main.java.service.DispatcherService;
import molab.main.java.service.EmulatorService;
import molab.main.java.service.ServerService;
import molab.main.java.util.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class ApptryWeb {
	
	private static final Logger LOG = Logger.getLogger(ApptryWeb.class.getName());
	private static byte[] lock = new byte[0];
	
	@Autowired
	private ApptryService apptryService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private DeploymentService deploymentService;
	
	@Autowired
	private DeveloperService developerService;
	
	@Autowired
	private DispatcherService dispatcherService;
	
	@Autowired
	private EmulatorService emulatorService;
	
	@Autowired
	private ServerService serverService;
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/try/{applicationId}")
	public String loadAll(@PathVariable int applicationId) {
		try {
			synchronized(lock) {
				List deployments = deploymentService.loadAll(applicationId);
				if(deployments != null) {
					// only one deployment, so load it directly
					T_Deployment deployment = (T_Deployment) deployments.get(0);
					// load emulator
					int emulatorId = deployment.getEmulator_id();
					T_Emulator emulator = emulatorService.load(emulatorId);
					T_Application application = applicationService.load(applicationId);
					// load server
					int serverId = emulator.getServer_id();
					T_Server server = serverService.load(serverId);
					// load dispatcher and developer
					int dispatcherId = deployment.getDispatcher_id();
					T_Dispatcher dispatcher = dispatcherService.load(dispatcherId);
					int developerId = dispatcher.getDeveloper_id();
					T_Developer developer = developerService.load(developerId);
					// minus 1 from leftClicks of developer if he's leftClicks > 0
					int leftClicks = developer.getLeftClicks();
					if(leftClicks > 0) {
						developer.setLeftClicks(--leftClicks);
						developerService.update(developer);
						// plus 1 from monthClicks of emulator
						int monthClicks = emulator.getMonthClicks();
						emulator.setMonthClicks(++monthClicks);
						// shift emulator's state from idle to busy
						LOG.log(Level.INFO, "Apptry: Shift emulator @" + emulator.getSerialNumber() + "'s state to BUSY.");
						emulator.setState(Status.BUSY);
						emulatorService.update(emulator);
						// load apptryComponent
						ApptryComponent ac = new ApptryComponent();
						ac.setApplication(application);
						ac.setDeployment(deployment);
						ac.setDeveloper(developer);
						ac.setEmulator(emulator);
						ac.setServer(server);
						return new Gson().toJson(ac);
					}
					
					
//					for(int i = 0; i < deployments.size(); i++) {
//						// load deployment
//						T_Deployment deployment = (T_Deployment) deployments.get(i);
//						// load emulator
//						int emulatorId = deployment.getEmulator_id();
//						T_Emulator emulator = emulatorService.load(emulatorId);
//						if(emulator.getState() == Status.IDLE) {
//							// load application
//							T_Application application = applicationService.load(applicationId);
//							// load server
//							int serverId = emulator.getServer_id();
//							T_Server server = serverService.load(serverId);
//							// load dispatcher and developer
//							int dispatcherId = deployment.getDispatcher_id();
//							T_Dispatcher dispatcher = dispatcherService.load(dispatcherId);
//							int developerId = dispatcher.getDeveloper_id();
//							T_Developer developer = developerService.load(developerId);
//							// minus 1 from leftClicks of developer if he's leftClicks > 0
//							int leftClicks = developer.getLeftClicks();
//							if(leftClicks > 0) {
//								developer.setLeftClicks(--leftClicks);
//								developerService.update(developer);
//								// plus 1 from monthClicks of emulator
//								int monthClicks = emulator.getMonthClicks();
//								emulator.setMonthClicks(++monthClicks);
//								// shift emulator's state from idle to busy
//								LOG.log(Level.INFO, "Apptry: Shift emulator @" + emulator.getSerialNumber() + "'s state to BUSY.");
//								emulator.setState(Status.BUSY);
//								emulatorService.update(emulator);
//								// load apptryComponent
//								ApptryComponent ac = new ApptryComponent();
//								ac.setApplication(application);
//								ac.setDeployment(deployment);
//								ac.setDeveloper(developer);
//								ac.setEmulator(emulator);
//								ac.setServer(server);
//								return new Gson().toJson(ac);
//							}
//						}
//					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadAdminTree")
	public String loadAdminTree() {
		try {
//			AdminTreeComponent servers = new AdminTreeComponent();
			List<ServerComponent> servers = new ArrayList<ServerComponent>();
			for(T_Server server : serverService.loadAll()) {
				ServerComponent sc = new ServerComponent();
				sc.setServer(server);
				for(T_Emulator emulator : emulatorService.loadAll(server.getId())) {
					EmulatorComponent ec = new EmulatorComponent();
					ec.setEmulator(emulator);
					for(T_Deployment deployment : deploymentService.loadAllEmulators(emulator.getId())) {
						T_Application application = applicationService.load(deployment.getApplication_id());
						ec.getApplications().add(application);
					}
					sc.getEmulators().add(ec);
				}
//				servers.getServers().add(sc);
				servers.add(sc);
			}
			return new Gson().toJson(servers);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new Gson().toJson("");
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/loadDynatree")
	public String loadDynatree() {
		try {
			List servers = new ArrayList();
			for(T_Server server : serverService.loadAll()) {
				Map serverMap = new HashMap();
				serverMap.put("isFolder", true);
				serverMap.put("title", server.getIpAddress().concat(":").concat(String.valueOf(server.getPort())));
				serverMap.put("key", "server");
				serverMap.put("id", server.getId());
				List emulators = new ArrayList();
				serverMap.put("children", emulators);
				for(T_Emulator emulator : emulatorService.loadAll(server.getId())) {
					Map emulatorMap = new HashMap();
					emulatorMap.put("isFolder", true);
					emulatorMap.put("title", emulator.getSerialNumber());
					emulatorMap.put("key", "emulator");
					emulatorMap.put("id", emulator.getId());
					List applications = new ArrayList();
					emulatorMap.put("children", applications);
					for(T_Deployment deployment : deploymentService.loadAllEmulators(emulator.getId())) {
						T_Application application = applicationService.load(deployment.getApplication_id());
						Map applicationMap = new HashMap();
						applicationMap.put("title", application.getPackageName());
						applicationMap.put("key", "application");
						applicationMap.put("id", application.getId());
						applications.add(applicationMap);
					}
					emulators.add(emulatorMap);
				}
				servers.add(serverMap);
			}
			return new Gson().toJson(servers);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new Gson().toJson("");
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadApplications/{developerId}/")
	public String loadApplications(@PathVariable int developerId) {
		try {
			List<T_Application> list =  apptryService.loadApplications(developerId);
			return new Gson().toJson(list);
		} catch(Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return "";
		}
	}

}
