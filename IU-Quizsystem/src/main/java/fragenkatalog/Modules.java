package fragenkatalog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Modules {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrement
	private int module_id;
	private String module_short;
	private String module_long;
	
	public Modules() {
	}
	
	public Modules(int module_id, String module_short, String module_long) {
	   this.module_id = module_id;
	   this.module_short = module_short;
	   this.module_long = module_long;
	}

	public int getModule_id() {
		return module_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public String getModule_short() {
		return module_short;
	}

	public void setModule_short(String module_short) {
		this.module_short = module_short;
	}

	public String getModule_long() {
		return module_long;
	}

	public void setModule_long(String module_long) {
		this.module_long = module_long;
	}	
}
