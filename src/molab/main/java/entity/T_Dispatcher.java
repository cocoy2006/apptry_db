package molab.main.java.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "t_dispatcher")
public class T_Dispatcher extends BaseEntity {
	private int id;
	private int developer_id;
	private int application_id;
	private long time;
	private int state;

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the developer_id
	 */
	public int getDeveloper_id() {
		return developer_id;
	}

	/**
	 * @param developerId the developer_id to set
	 */
	public void setDeveloper_id(int developerId) {
		developer_id = developerId;
	}

	/**
	 * @return the application_id
	 */
	public int getApplication_id() {
		return application_id;
	}

	/**
	 * @param applicationId the application_id to set
	 */
	public void setApplication_id(int applicationId) {
		application_id = applicationId;
	}

	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
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