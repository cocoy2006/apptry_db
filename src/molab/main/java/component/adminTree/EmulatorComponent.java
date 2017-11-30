package molab.main.java.component.adminTree;

import java.util.ArrayList;
import java.util.List;

import molab.main.java.entity.T_Application;
import molab.main.java.entity.T_Emulator;

public class EmulatorComponent {
	
	private T_Emulator emulator;
	private List<T_Application> applications;
	
	public EmulatorComponent() {
		applications = new ArrayList<T_Application>();
	}

	/**
	 * @return the emulator
	 */
	public T_Emulator getEmulator() {
		return emulator;
	}

	/**
	 * @param emulator the emulator to set
	 */
	public void setEmulator(T_Emulator emulator) {
		this.emulator = emulator;
	}

	/**
	 * @return the applications
	 */
	public List<T_Application> getApplications() {
		return applications;
	}

	/**
	 * @param applications the applications to set
	 */
	public void setApplications(List<T_Application> applications) {
		this.applications = applications;
	}
	
}
