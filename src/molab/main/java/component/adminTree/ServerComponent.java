package molab.main.java.component.adminTree;

import java.util.ArrayList;
import java.util.List;

import molab.main.java.entity.T_Server;

public class ServerComponent {
	
	private T_Server server;
	private List<EmulatorComponent> emulators;
	
	public ServerComponent() {
		emulators = new ArrayList<EmulatorComponent>();
	}

	/**
	 * @return the server
	 */
	public T_Server getServer() {
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(T_Server server) {
		this.server = server;
	}

	/**
	 * @return the emulators
	 */
	public List<EmulatorComponent> getEmulators() {
		return emulators;
	}

	/**
	 * @param emulators the emulators to set
	 */
	public void setEmulators(List<EmulatorComponent> emulators) {
		this.emulators = emulators;
	}
	
}
