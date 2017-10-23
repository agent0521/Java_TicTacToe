package serverSide;

import java.util.*;
import java.net.*;
import java.io.*;
import utilities.*;

/**
 * 
 * @author Floyd Almazar
 * @version 1.0
 * 
 * Class Description: 	Represents a ClientHandler object that (acts like a server) and handles 
 * 						communication between two clients.
 */
public class ClientHandler implements Runnable, Observer
{
	//Attributes
	private ObjectOutputStream			oos1,oos2;
	private Socket						cs1,cs2;
	private InputListener				lis1,lis2;
	
	//Constructors
	/**
	 * Method that opens up data streams for the two clients/players so they can communicate with one another.
	 * @param cs1 - socket of the 1st Player
	 * @param cs2 - socket of the 2nd Player
	 */
	public ClientHandler(Socket cs1, Socket cs2)
	{
		this.cs1 = cs1;
		this.cs2 = cs2;
		
		try
		{
			oos1 = new ObjectOutputStream(cs1.getOutputStream());
			oos2 = new ObjectOutputStream(cs2.getOutputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that creates an listeners for the two clients and gets the messages that came from them.
	 * At first, it creates its own system message that assigns its player number randomly.
	 * It closes once the connection of one client has been disconnected from the server.
	 */
	public void run()
	{
		lis1 = new InputListener(1,cs1,this);
		lis2 = new InputListener(2,cs2,this);
		
		Thread t1 = new Thread(lis1);
		t1.start();
		Thread t2 = new Thread(lis2);
		t2.start();
		
		try
		{
			
			Message m1 = new Message("Player 1 is Connected","",new Date(0));
			Message m2 = new Message("Player 2 is Connected","",new Date(0));
			
			m1.setMsg("#" + (1 + (int)Math.random() * 2));
			if(m1.getMessage().equals("#1")) 
			{
				m2.setMsg("#2");
			} 
			else 
			{
				m2.setMsg("#1");
			}
			
			oos1.writeObject(m1);
			oos2.writeObject(m2);
			
			while(cs1.isConnected() && cs2.isConnected());
			
			cs1.close();
			cs2.close();
			oos1.close();
			oos2.close();
		}
		catch (SocketException e1)
		{
			// not all exceptions are errors, just handle them gracefully!
			Debug.output("Client Handler: Socket has been closed.");
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}

	/**
	 * Method that is called whenever a message comes in.
	 * This method then distributes the message to their opponent/other listener.
	 */
	@Override
	public void update(Observable observable, Object arg)
	{
		InputListener listener = (InputListener)observable;
		Message message = (Message)arg;
		
		try
		{
			if(listener.getListenerNumber() == 1)
			{
				oos2.writeObject(message);
			}
			else
			{
				oos1.writeObject(message);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
