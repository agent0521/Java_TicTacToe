package utilities;

import java.util.*;
import java.io.*;
import java.net.*;

/**
 * 
 * @author Floyd Almazar
 * @version 1.0
 * 
 * Class Description: This class is responsible for getting the messages that comes through from both clients.
 */
public class InputListener extends Observable implements Runnable
{
	//Attributes
	private int 				listenerNumber;
	private Socket				socket;
	private ObjectInputStream	ois;
	
	// Constructors
	/**
	 * Method that constructs the Input Listener
	 * @param socket the socket being monitored
	 * @param observer class to be notified when something changes
	 */
	public InputListener(Socket socket,Observer observer)
	{
		listenerNumber = 0;
		this.socket = socket;
		this.addObserver(observer);
	}
	/**
	 * This adds an observer so that the message can be distributed to the players who are currently playing.
	 * @param listenerNumber number assigned to this listener
	 * @param socket the socket being monitored
	 * @param observer class to be notified when something changes
	 */
	public InputListener(int listenerNumber, Socket socket, Observer observer)
	{
		this.listenerNumber = listenerNumber;
		this.socket = socket;
		this.addObserver(observer);
	}
	//Getter and Setter Methods
	/**
	 * Method that returns the listener number.
	 * @return the listenerNumber
	 */
	public int getListenerNumber()
	{
		return listenerNumber;
	}
	/**
	 * Method that sets the listener number.
	 * @param listenerNumber the listenerNumber to set
	 */
	public void setListenerNumber(int listenerNumber)
	{
		this.listenerNumber = listenerNumber;
	}
	
	/**
	 * Method that is actively listening for any messages that is coming in.
	 * It the notifies the observers and gives them the message that was passed through.
	 */
	@Override
	public void run()
	{		
		try
		{
			// reading message of the client
			ois = new ObjectInputStream(socket.getInputStream());
			
			while(true)
			{
				Debug.output("input listener number "+listenerNumber);
				Object o = ois.readObject();
				setChanged();
				notifyObservers(o);
				
				Message m = (Message) o;
				if (m.getMessage().compareTo("has disconnected.") == 0)
				{
					Debug.output(m.getUser()+" is disconnecting...");
					ois.close();
					socket.close();
				}
					
			}
		}
		catch (SocketException e)
		{
			// not all exceptions are errors, just handle them gracefully!
			Debug.output("input listener number "+listenerNumber+": Socket has been closed.");
		}
		catch (EOFException e)
		{
			// not all exceptions are errors, just handle them gracefully!
			Debug.output("input listener number "+listenerNumber+": No stream available.");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
