package menu;

import java.io.IOException;
import java.util.LinkedList;

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


	public static void main(String[] args) throws IOException {
		
		LinkedList<action> _actions = new LinkedList<action>();
		action aa = new action(1,"start");
		_actions.add(aa);
		boolean run = true;
		int option = 1;
		while(run)
		{
			
			switch(option)
			{
			case 1:
				System.out.println("Notificacion de que lega un paquete"+"\n");
				if ( isSuccessfull())
				{
					option ++;
					//_actions.getFirst().setAction(option++);
					System.out.println("successfull \n");
					break;
				}
				else
					System.out.println("not successfull try again \n");
					break;
			case 2:

				System.out.println("Lectura de QR Code"+"\n");
				if ( isSuccessfull())
				{
					option ++;
					//_actions.getFirst().setAction(option++);
					System.out.println("successfull \n");
					break;
				}
				else
					System.out.println("not successfull try again \n");
					break;
			
			case 3:
				System.out.println("Apertura de puertas y reccepcio de paquete"+"\n");
				
				if ( isSuccessfull())
				{
					option ++;
					//_actions.getFirst().setAction(option++);
					System.out.println("successfull \n");
					break;
				}
				else
					System.out.println("not successfull try again \n");
					break;
			case 4:
				System.out.println("Notificacion a Servidor de entrega succesfull"+"\n");
				
				if ( isSuccessfull())
				{
					option ++;
					//_actions.getFirst().setAction(option++);
					System.out.println("successfull \n");
					break;
				}
				else
					System.out.println("not successfull try again \n");
					break;
			case 5:
				run = false;
				System.out.println("Succesfull delivery returning to Stand By"+"\n");
				_actions.removeFirst();
				
				
				break;
				default:

					System.out.println("La opcion no existe."+"\n");
					break;
			}
			//execution++;
		}
		
		
		
		
		
	}
 
	public String output;
	public int option;
	

	public static boolean isSuccessfull()
	{
		if(0== (int)(Math.random()*2))
			return true;
		else
			return false;
	}
	
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
