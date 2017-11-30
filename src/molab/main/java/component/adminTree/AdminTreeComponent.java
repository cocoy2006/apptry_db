package molab.main.java.component.adminTree;

import java.util.ArrayList;
import java.util.List;

public class AdminTreeComponent {
	
	private List<ServerComponent> servers;
	
	public AdminTreeComponent() {
		servers = new ArrayList<ServerComponent>();
	}

	/**
	 * @return the servers
	 */
	public List<ServerComponent> getServers() {
		return servers;
	}

	/**
	 * @param servers the servers to set
	 */
	public void setServers(List<ServerComponent> servers) {
		this.servers = servers;
	}
	
}