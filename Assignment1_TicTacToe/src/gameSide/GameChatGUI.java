/**
 * 
 */
package gameSide;

import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import utilities.InputListener;
import utilities.Message;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Panel;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.UIManager;


/**
 * @author Floyd Almazar
 * @version 1.0
 * 
 * Class Description: This class will go towards the client that will play the game.
 * 					It will contain Tic Tac Toe Buttons and the Chat System that allows the players to 
 * 					communicate.
 */
public class GameChatGUI extends JFrame implements Observer
{
	//Attributes
	private JTextField 			txtSend;
	private JTextArea			displayConvo;	
	private JButton				btnConnect, btnDisconnect, btnSend;
	private JButton				btnI, btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH;
	private ArrayList<JButton>	btnArray;
	private JPanel				pnlTicTac;

	private Socket				socket;
	private String				ip;
	private String 				userName;
	private int					playerNumber;
	private ObjectOutputStream 	oos;
	private InputListener		inputListener;
	private boolean				newSession;
	
	//Constructors
	/**
	 * Method that constructs the User Interface for both game and chat.
	 */
	public GameChatGUI()
	{
		createClientGUI();	
	}
	
	/**
	 * Method will firstly create the chat system that is composed of a Text area 
	 * that will contain the chat history of the players, as well as a textbox for the 
	 * user to type in and send it to the other player.
	 * 
	 * This method also displays the title of the Chat Messenger as well as the game.
	 * Tic Tac Toe Buttons: There are 9 buttons that will be constructed.
	 * 						All of which has a listener that is when pressed, it delivers a message 
	 * 						to the other player indicating what button was pressed as well as 
	 * 						make necessary changes to the UI.
	 * Connect and Submit Buttons: There are 2 buttons that were used to connect and disconnect to the server.
	 */
	private void createClientGUI()
	{
		JLabel lblTicTacToe = new JLabel("Tic Tac Toe (^_^)");
		lblTicTacToe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTicTacToe.setFont(new Font("Lithos Pro Regular", Font.BOLD, 36));
		lblTicTacToe.setBounds(374, 29, 566, 56);
		getContentPane().add(lblTicTacToe);
		
		this.setTitle("Tic Tac Toe: Chat and Play");
		this.setBounds(400, 150, 977, 720);
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblTictacMessenger = new JLabel("TicTac Messenger");
		lblTictacMessenger.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTictacMessenger.setFont(new Font("Chaparral Pro", Font.PLAIN, 30));
		lblTictacMessenger.setForeground(new Color(0, 0, 255));
		lblTictacMessenger.setBackground(Color.WHITE);
		lblTictacMessenger.setHorizontalAlignment(SwingConstants.CENTER);
		lblTictacMessenger.setBounds(91, 0, 216, 38);
		getContentPane().add(lblTictacMessenger);
		
		//*******************************************************************************
		pnlTicTac = new JPanel();
		pnlTicTac.setBounds(374, 86, 566, 563);
		getContentPane().add(pnlTicTac);
		pnlTicTac.setLayout(null);
		
		btnI = new JButton("");
		btnI.setActionCommand("1");
		btnI.addActionListener(new MyActionListener());
		btnI.setBackground(UIManager.getColor("CheckBox.highlight"));
		btnI.setFont(new Font("ARIAL", Font.BOLD, 180));
		btnI.setBounds(0, 0, 191, 189);
		pnlTicTac.add(btnI);
		
		btnA = new JButton("");
		btnA.setActionCommand("2");
		btnA.addActionListener(new MyActionListener());
		btnA.setBackground(UIManager.getColor("CheckBox.highlight"));
		btnA.setFont(new Font("ARIAL", Font.BOLD, 180));
		btnA.setBounds(189, 0, 191, 189);
		pnlTicTac.add(btnA);
		
		btnB = new JButton("");
		btnB.setActionCommand("3");
		btnB.addActionListener(new MyActionListener());
		btnB.setBackground(UIManager.getColor("CheckBox.highlight"));
		btnB.setFont(new Font("ARIAL", Font.BOLD, 180));
		btnB.setBounds(377, 0, 191, 189);
		pnlTicTac.add(btnB);
		
		btnC = new JButton("");
		btnC.setActionCommand("4");
		btnC.addActionListener(new MyActionListener());
		btnC.setBackground(UIManager.getColor("CheckBox.highlight"));
		btnC.setFont(new Font("ARIAL", Font.BOLD, 180));
		btnC.setBounds(0, 185, 191, 189);
		pnlTicTac.add(btnC);
		
		btnD = new JButton("");
		btnD.setActionCommand("5");
		btnD.addActionListener(new MyActionListener());
		btnD.setBackground(UIManager.getColor("CheckBox.highlight"));
		btnD.setFont(new Font("ARIAL", Font.BOLD, 180));
		btnD.setBounds(189, 185, 191, 189);
		pnlTicTac.add(btnD);
		
		btnE = new JButton("");
		btnE.setActionCommand("6");
		btnE.addActionListener(new MyActionListener());
		btnE.setBackground(UIManager.getColor("CheckBox.highlight"));
		btnE.setFont(new Font("ARIAL", Font.BOLD, 180));
		btnE.setBounds(377, 185, 191, 189);
		pnlTicTac.add(btnE);
		
		btnF = new JButton("");
		btnF.setActionCommand("7");
		btnF.addActionListener(new MyActionListener());
		btnF.setBackground(UIManager.getColor("CheckBox.highlight"));
		btnF.setFont(new Font("ARIAL", Font.BOLD, 180));
		btnF.setBounds(0, 368, 191, 189);
		pnlTicTac.add(btnF);
		
		btnG = new JButton("");
		btnG.setActionCommand("8");
		btnG.addActionListener(new MyActionListener());
		btnG.setBackground(UIManager.getColor("CheckBox.highlight"));
		btnG.setFont(new Font("ARIAL", Font.BOLD, 180));
		btnG.setBounds(189, 368, 191, 189);
		pnlTicTac.add(btnG);
		
		btnH = new JButton("");
		btnH.setActionCommand("9");
		btnH.addActionListener(new MyActionListener());
		btnH.setBackground(UIManager.getColor("CheckBox.highlight"));
		btnH.setFont(new Font("ARIAL", Font.BOLD, 180));
		btnH.setBounds(377, 368, 191, 189);
		pnlTicTac.add(btnH);

		Panel pnlShowChat = new Panel();
		pnlShowChat.setBounds(15, 38, 351, 611);
		getContentPane().add(pnlShowChat);
		pnlShowChat.setLayout(null);
		
		displayConvo = new JTextArea(5,20);
		displayConvo.setEditable(false);
		displayConvo.setFont(new Font("Calibri", Font.PLAIN, 13));
		displayConvo.setLineWrap(true);
		
		JScrollPane scrollChat = new JScrollPane();
		scrollChat.setViewportView(displayConvo);
		scrollChat.setBounds(0, 0, 358, 480);
		scrollChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pnlShowChat.add(scrollChat);
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(new MyActionListener());
		btnSend.setEnabled(false);
		btnSend.setBounds(267, 484, 84, 69);
		pnlShowChat.add(btnSend);
		
		txtSend = new JTextField();
		txtSend.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSend.setHorizontalAlignment(SwingConstants.LEFT);
		txtSend.setBounds(0, 485, 264, 68);
		pnlShowChat.add(txtSend);
		txtSend.addActionListener(new MyActionListener());
		
		btnConnect = new JButton("CONNECT");
		btnConnect.addActionListener(new MyActionListener());
		btnConnect.setEnabled(true);
		btnConnect.setBounds(10, 566, 145, 29);
		pnlShowChat.add(btnConnect);
		
		btnDisconnect = new JButton("QUIT");
		btnDisconnect.addActionListener(new MyActionListener());
		btnDisconnect.setEnabled(false);
		btnDisconnect.setBounds(191, 566, 145, 29);
		pnlShowChat.add(btnDisconnect);
		

		btnArray = new ArrayList<JButton>();
		btnArray.add(btnI);
		btnArray.add(btnA);
		btnArray.add(btnB);
		btnArray.add(btnC);
		btnArray.add(btnD);
		btnArray.add(btnE);
		btnArray.add(btnF);
		btnArray.add(btnG);
		btnArray.add(btnH);
		for(JButton startPlay : btnArray) {
			startPlay.setEnabled(false);
		}
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Method that is notified whenever a message is received.
	 * That Message is then analyzed to determine what function has to be done. 
	 * 
	 * If the message has a date of December 31, 1969 the program assumes that it is a system message.
	 * It will contain a specific type of message body.
	 * Specifically:
	 * - #1 or #2				this assigns the player's number to determine who will go first.
	 * 
	 * - 0 to 9				this is a Tic Tac Toe Button ranging from 1 to 9 that determines 
	 * 							which button was pressed by the opponent.
	 * 	
	 * - Player 1 or 2 wins  	this indicates that the game has finished and assign player 1 to the winner and then 
	 * 							creates a Dialog Box that prompts the user if he/she want to continue playing.
	 * 							It also sends a system message to their opponent whether they want to continue playing or not.
	 *  
	 * - Draw					This indicates that the game has drawn and the players are prompted whether they want to
	 *  						continue playing or not.
	 * 	
	 * - Quit					This indicates that the other player has quit the game.
	 *  
	 * - Yes					This indicates that the other player wants to continue playing.
	 * 
	 * If the message contains the current date, the program prints the message body as well as the author of the message
	 * in the chat history.
	 */
	@Override
	public void update(Observable observable, Object arg)
	{
		Message message = (Message)arg;
		if(message.getTimeStamp().toString().equals(new Date(0).toString()))
		{
			if(message.getMessage().matches("([#][1-2])")) 
			{
				playerNumber = Integer.parseInt(message.getMessage().substring(1));
				btnSend.setEnabled(true);
				btnDisconnect.setEnabled(true);

				
				if(playerNumber == 1)
				{
					enableAllButtons();
				}	
			}
			if(message.getMessage().matches("[0-9]"))
			{
				if (message.getUser().equals("1")) {
						btnArray.get(Integer.parseInt(message.getMessage())-1).setText("X");
					} else {
						btnArray.get(Integer.parseInt(message.getMessage())-1).setText("O");
					}
					for(JButton btnEachArray: btnArray)
					{
						if(btnEachArray.getText().equals(""))
						{
							btnEachArray.setEnabled(true);
						}
					}
				}
			if(message.getMessage().matches("(Player )[1-2]( wins!)")) {
				playerNumber = 2;
				disableAllButtons();
				JOptionPane alertBox = new JOptionPane();
				if(alertBox.showConfirmDialog(null, message.getMessage() + "\nContinue?", "You Lose!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					resetButtons();
					try
					{
						Message sendDraw = new Message("0", "Yes", new Date(0));
						oos.writeObject(sendDraw);
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try
					{
						Message quitMessage = new Message(playerNumber + "", "Quit", new Date(0));
						oos.writeObject(quitMessage);

						disconnectMe();
						System.exit(0);
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
			if(message.getMessage().equals("Draw")) {
				disableAllButtons();
				JOptionPane alertBox = new JOptionPane();
				if(alertBox.showConfirmDialog(null, "Continue?", "Game is a Draw!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					resetButtons();
					
					try
					{
						oos.writeObject(new Message("", "Yes", new Date(0)));
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					disconnectMe();
					System.exit(0);
				}
			}
			if(message.getMessage().equals("Quit")) {
				disconnectMe();
				connectMe();
			}
			if(message.getMessage().compareTo("Yes") == 0) {
				if(playerNumber == 1)
					enableAllButtons();
			}
		} else {
			String msg = message.getUser()+": "+message.getMessage()+" ("+message.getTimeStamp()+")";
			displayConvo.append(msg+"\n");
		}
	}

	/**
	 * Method that checks every possible winning combinations of the buttons in tic tac toe.
	 * If one of the combinations has been found, it indicates that the player has won.
	 * It then creates a system message his/her opponent that the game has ended and then it prompts
	 * the user if he/she wants to continue playing.
	 * 
	 * This method also checks for a draw where it determines if there is other possible moves that can be done
	 * Otherwise, it sends a message for both players indicating the game has finished and prompts them if they want to 
	 * continue playing.
	 * 
	 * If the player decides not to continue playing, the server will disconnect and the program will terminate.
	 * 
	 */
	private void checkWinner()
	{
		String playerSign = playerNumber == 1 ? "X" : "O";
		if((btnArray.get(0).getText() + 
			btnArray.get(1).getText() + 
			btnArray.get(2).getText()).equals(playerSign+playerSign+playerSign) ||
			(btnArray.get(3).getText() + 
			btnArray.get(4).getText() + 
			btnArray.get(5).getText()).equals(playerSign+playerSign+playerSign) ||
			(btnArray.get(6).getText() + 
			btnArray.get(7).getText() + 
			btnArray.get(8).getText()).equals(playerSign+playerSign+playerSign) ||
			(btnArray.get(0).getText() + 
			btnArray.get(3).getText() + 
			btnArray.get(6).getText()).equals(playerSign+playerSign+playerSign) ||
			(btnArray.get(1).getText() + 
			btnArray.get(4).getText() + 
			btnArray.get(7).getText()).equals(playerSign+playerSign+playerSign) ||
			(btnArray.get(2).getText() + 
			btnArray.get(5).getText() + 
			btnArray.get(8).getText()).equals(playerSign+playerSign+playerSign) ||		
			(btnArray.get(0).getText() + 
			btnArray.get(4).getText() + 
			btnArray.get(8).getText()).equals(playerSign+playerSign+playerSign) ||
			(btnArray.get(2).getText() + 
			btnArray.get(4).getText() + 
			btnArray.get(6).getText()).equals(playerSign+playerSign+playerSign))	
		{
			try
			{
				Message sendWinner = new Message(playerNumber + "", "Player " + playerNumber + " wins!", new Date(0));
				oos.writeObject(sendWinner);
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			disableAllButtons();
			playerNumber = 1;
			JOptionPane alertBox = new JOptionPane();
			if(alertBox.showConfirmDialog(null, "Winner!\nContinue?", "You're the Winner!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
			{
				resetButtons();
				try
				{
					Message sendDraw = new Message("0", "Yes", new Date(0));
					oos.writeObject(sendDraw);
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			else 
			{
				try
				{
					Message quitMessage = new Message(playerNumber + "", "Quit", new Date(0));
					oos.writeObject(quitMessage);
	
					disconnectMe();
					System.exit(0);
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		if((btnArray.get(0).getText() + btnArray.get(1).getText() + btnArray.get(2).getText() +
			btnArray.get(3).getText() + btnArray.get(4).getText() + btnArray.get(5).getText() +
			btnArray.get(6).getText() + btnArray.get(7).getText() + btnArray.get(8).getText()).length() == 9) 
		{
			try
			{
				Message sendDraw = new Message("0", "Draw", new Date(0));
				oos.writeObject(sendDraw);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			disableAllButtons();
			JOptionPane alertBox = new JOptionPane();
			if(alertBox.showConfirmDialog(null, "Continue?", "Game is a Draw!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
			{
				resetButtons();
				
				try
				{
					oos.writeObject(new Message("", "Yes", new Date(0)));
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			else 
			{
				disconnectMe();
				System.exit(0);
			}
		}
	}
	/**
	 * Method that connects the player to the server.
	 * It firstly, prevents any user input until the player has found an opponent.
	 * It connects to the server by asking the user to enter the IP Address followed by his/her username.
	 * Once the player has connected, it will wait for an opponent to connect to the server.
	 * Once they found an opponent, a player number will be assigned to them. 
	 */
	private void connectMe()
	{
		resetButtons();
		disableAllButtons();
		try
		{
			if(ip == null)
			{
				do
				{
					ip = JOptionPane.showInputDialog("Please enter the IP Address");
				} while(ip.isEmpty());
			}
			
			int port=5555;
			socket = new Socket(ip, port);
			
			if(newSession)
			{
				if(userName == null)
				{
					userName = JOptionPane.showInputDialog("Enter user name");
					while(userName.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Username has to be entered.");
						userName = JOptionPane.showInputDialog(null, "Enter user name");
					}
				}
			}
			btnConnect.setEnabled(false);
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			displayConvo.append("Connected! Waiting for a chat partner...\n");
			//start an input listener thread
			inputListener = new InputListener(socket,this);
			Thread t1 = new Thread(inputListener);
			t1.start();
		}
		catch (HeadlessException e1)
		{
			e1.printStackTrace();
		}
		catch (UnknownHostException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}
	/**
	 * Method that disconnects the player to the server.
	 * It firstly, sends a system message to their opponent that they have left the game.
	 * They will then be waiting for another opponent to connect to the server.
	 */
	private void disconnectMe()
	{
		try
		{
			Message quitMessage = new Message(playerNumber + "", "Quit", new Date(0));
			oos.writeObject(quitMessage);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		resetButtons();
		disableAllButtons();
		
		displayConvo.append("Disconnected.\n");
		btnDisconnect.setEnabled(false);
		btnSend.setEnabled(false);
		btnConnect.setEnabled(true);
		
		try
		{
			oos.close();
			socket.close();
			inputListener = null;
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}
	
	/**
	 * This class will have all the button listeners for all of the buttons in the interface.
	 * 
	 * All the buttons will have different functions.
	 *  - btnConnect				This will connect the user to the server.
	 *  - btnSend					This will send the message from the message box to the chat history that can be 
	 *  							visible to both players.
	 *  - btnDisconnect				This will disconnect the user to the server.
	 *  							It will send a system message to their opponent indicating that he/she has quit the game.
	 *  - btnArray					If a tic tac toe button was clicked, depending on which button was pressed,
	 *  							it will have the symbol of the player assigned to it.
	 *  							It also sends a system message to their opponent indicating which button is pressed.
	 *              				Afterwards, the program determines if there is a winner.
	 */
	private class MyActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == btnConnect)
			{
				newSession = true;
				connectMe();
			}
			if(e.getSource() == btnSend)
			{
				Message message = new Message(userName,txtSend.getText(),new Date());
				
				try
				{
					oos.writeObject(message);
					displayConvo.append("Me: "+message.getMessage()+" \n("+message.getTimeStamp()+")\n");
					txtSend.setText("");
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
			
			if(e.getSource() == btnDisconnect)
			{
				newSession = true;
				disconnectMe();
			}
			
			if(btnArray.contains(e.getSource()))
			{
				Message message = new Message(playerNumber + "","",new Date(0));
				
				try
				{
					JButton btnClick = (JButton) e.getSource();
					message.setMsg(btnClick.getActionCommand());
					oos.writeObject(message);
					
					btnClick = btnArray.get(Integer.parseInt(message.getMessage())-1);
					btnClick.setText(playerNumber == 1 ? "X" : "O");
					for(JButton btnEachArray : btnArray)
					{
						btnEachArray.setEnabled(false);
					}
					btnClick.setEnabled(false);
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
				checkWinner();
			}
		}
	}
	/**
	 * Method that is called whenever the program has to reset the values of the tic tac toe buttons.
	 */
	private void resetButtons()
	{
		for(JButton btnEachArray : btnArray)
		{
			btnEachArray.setText("");
		}
	}
	/**
	 * Method that is called whenever the program has to disable all the tic tac toe buttons.
	 */
	private void disableAllButtons()
	{
		for(JButton btnEachArray : btnArray)
		{
			btnEachArray.setEnabled(false);
		}
	}
	/**
	 * Method that is called whenever the program has to enable all the tic tac toe buttons.
	 */
	private void enableAllButtons()
	{
		for(JButton btnEachArray : btnArray)
		{
			btnEachArray.setEnabled(true);
		}
	}
	
	/**
	 * This method runs the program.
	 * @param args Unused
	 */
	public static void main(String[] args)
	{
		new GameChatGUI();
	}
}
