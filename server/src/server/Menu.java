package server;

public class Menu {
	
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Menu(String output, int option) {
		super();
		this.output = output;
		this.option = option;
	}


 
	public String output;
	public int option;
	

	
	public String execute(int opt )        
	{
		switch(opt) {
		
		case 1:
			output =  "Notify drone on its way \n waiting for id of drone tobe confirmed ";
			break;
		case 2:
			output = "Paso 2 del Buzon en la que se confirma la identidad del dron que entrega";
			break;
		default: 
			output = "Unknown comando, please resend again. "; 
			break; 
		
	}
		return output;
	
	}
}
