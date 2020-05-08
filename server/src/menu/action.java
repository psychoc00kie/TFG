package menu;

public class action {

	public action() {
		super();
		// TODO Auto-generated constructor stub
	}

	int action = 0;
	String description ="";



	public action(int action, String description) {
		super();
		this.action = action;
		this.description = description;
	}


	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "action [action=" + action + ", description=" + description + "]";
	}


}
