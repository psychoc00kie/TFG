package menu;

import comunication.*;

import java.io.IOException;
import java.util.LinkedList;

public class Menu {
	
	public String output;
	public int option;
	public action _action = new action();
	
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getOut() {
		return output;
	}
	
	public void setOut(String out)
	{
		output = out;
	}
	
	public int getOpt() {
		return option;
	}
	
	public void setOpt(int opt) {
		
		option = opt;		
	}
	
	public Menu(String output, int option) {
		super();
		this.output = output;
		this.option = option;
	}


	public static boolean isSuccessfull()
	{
		if(0== (int)(Math.random()*2))
			return true;
		else
			return false;
	}
	
	public void checkSuccessfull() {
		if ( isSuccessfull())
		{
			setOut(Integer.toString( getOpt()) +") successfull ");
			setOpt(getOpt()+1);
			
			//_actions.getFirst().setAction(option++);
			System.out.println(getOut());
			
		}
		else
			setOut(Integer.toString( getOpt()) +") not successfull try again");
			System.out.println(getOut());
			
	}
	
	public action execute(action a)        
	{
		
		switch(a.getActionId())
		{
		case 1:
			System.out.println("Notificacion de que lega un paquete");
			if(isSuccessfull())
				_action = new action(2,"1",false,0);
			else
				_action = new action(1,"2000",false,0);
			return _action;
			
			
		case 2:

			System.out.println("Lectura de QR Code");
			
			if(isSuccessfull())
				_action = new action(3,"",false,0);
			else
				_action = new action(2,"1",false,0);
			return _action;

			
		
		case 3:
			System.out.println("Apertura de puertas y reccepcio de paquete");
			
			if(isSuccessfull())
				_action = new action(4,"all ok ",false,0);
			else
				_action = new action(3,"",false,0);
			return _action;
			
		case 4:
			System.out.println("Notificacion a Servidor de entrega succesfull");
			if(isSuccessfull())
				_action = new action(5,"1",false,0);
			else
				_action = new action(4,"2000",false,0);
			return _action;
		case 5:
			
			System.out.println("Succesfull delivery returning to Stand By");
			
			if(isSuccessfull())
				_action = new action(5,"1",false,0);
			else
				_action = new action(6,"2000",false,0);
			return _action;
			
			
			
			default:

				System.out.println("La opcion no existe.");
				break;
		}
		return _action;
	
	}
}
