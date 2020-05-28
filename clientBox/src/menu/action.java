package menu;

import java.io.Serializable;

public class action implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6656534029716592310L;
	/**
	 * 
	 */
	
	public int actionId;
	public String actionData;
	public boolean successfull;
	public int errorDataCode;
	
	public action(int actionId, String actionData, boolean successfull, int errorDataCode) {
		super();
		this.actionId = actionId;
		this.actionData = actionData;
		this.successfull = successfull;
		this.errorDataCode = errorDataCode;
	}

	public action() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public String getActionData() {
		return actionData;
	}

	public void setActionData(String actionData) {
		this.actionData = actionData;
	}

	public boolean isSuccessfull() {
		return successfull;
	}

	public void setSuccessfull(boolean successfull) {
		this.successfull = successfull;
	}

	public int getErrorDataCode() {
		return errorDataCode;
	}

	public void setErrorDataCode(int errorDataCode) {
		this.errorDataCode = errorDataCode;
	}
	
	public void emptyPacket() {
		setActionId(0);
		setActionData(null);
		setSuccessfull(false);
		setErrorDataCode(0);
	}
	

	@Override
	public String toString() {
		return "PacketTransfer [actionId=" + actionId + ", actionData=" + actionData + ", successfull=" + successfull
				+ ", errorDataCode=" + errorDataCode + "]";
	}
	
	
	
}
