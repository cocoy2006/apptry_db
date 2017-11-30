package molab.main.java.entity;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "t_application")
public class T_Application extends BaseEntity {
	private int id;
	private String md5;
	private String name;
	private String aliasName;
	private long size;
	private String packageName;
	private String version;
	private String os;
	private String startActivity;
	private Blob icon;

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the aliasName
	 */
	public String getAliasName() {
		return aliasName;
	}

	/**
	 * @param aliasName the aliasName to set
	 */
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getStartActivity() {
		return startActivity;
	}

	public void setStartActivity(String startActivity) {
		this.startActivity = startActivity;
	}
	
	/**
	 * @return the image
	 */
	public Blob getIcon() {
		return icon;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setIcon(Blob icon) {
		this.icon = icon;
	}

}