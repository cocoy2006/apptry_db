package molab.main.java.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Table(name = "t_emulator")
public class T_Emulator extends BaseEntity {
	private int id;
	private int server_id;
	private String serialNumber;
	private String manufacturer;
	private String model;
	private String os;
	private int width;
	private int height;
	private String dpi;
	private int totalClicks;
	private int monthClicks;
	private int state;

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the server_id
	 */
	public int getServer_id() {
		return server_id;
	}

	/**
	 * @param serverId the server_id to set
	 */
	public void setServer_id(int serverId) {
		server_id = serverId;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	public String getDpi() {
		return dpi;
	}

	public void setDpi(String dpi) {
		this.dpi = dpi;
	}

	/**
	 * @return the totalClicks
	 */
	public int getTotalClicks() {
		return totalClicks;
	}

	/**
	 * @param totalClicks the totalClicks to set
	 */
	public void setTotalClicks(int totalClicks) {
		this.totalClicks = totalClicks;
	}

	/**
	 * @return the monthClicks
	 */
	public int getMonthClicks() {
		return monthClicks;
	}

	/**
	 * @param monthClicks the monthClicks to set
	 */
	public void setMonthClicks(int monthClicks) {
		this.monthClicks = monthClicks;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
	
}