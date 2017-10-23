/**
 * 
 */
package serverSide;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import utilities.Debug;

/**
 * @author Floyd Almazar
 * @version 1.0
 * 
 * Class Description: 	This server class is used to establish connection between the players.
 */
public class Server
{
	/**
	 * This method acts as a lobby that pairs the players together to chat and play tic tac toe.
	 * @param args Unused
	 */
	public static void main(String[] args)
	{
		ArrayList<Socket> socketList = new ArrayList<Socket>(2);
		try
		{
			ServerSocket serverSocket = new ServerSocket(5555);
			//JOptionPane.showMessageDialog(null, "Server is up and Running!");
			System.out.println("Server is up and Running");
			
			while(true)
			{
				Socket clientSocket = serverSocket.accept();
				
				socketList.add(clientSocket);
				
				if(socketList.size() == 2)
				{
					ClientHandler clientHandler = new ClientHandler(socketList.get(0),socketList.get(1));
					Thread handlerThread = new Thread(clientHandler);
					handlerThread.start();
					socketList.clear();
				}
			}
		}
		catch (SocketException e)
		{
			// not all exceptions are errors, just handle them gracefully!
			Debug.output("Server: Socket has been closed.");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
