package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by n_alex on 24.04.2017.
 */
public class Client
{
	protected Connection connection;
	private volatile boolean clientConnected = false;
	
	public static void main(String[] args)
	{
		Client client = new Client();
		client.run();
	}
	
	public void run()
	{
		SocketThread socketThread = getSocketThread();
		socketThread.setDaemon(true);
		socketThread.start();
		
		boolean interrupted = false;
		
		synchronized (this)
		{
			try
			{
				this.wait();
			}
			catch (InterruptedException e)
			{
				interrupted = true;
			}
		}
		
		if (interrupted)
		{
			ConsoleHelper.writeMessage("Выполнение программы прервано.");
			return;
		}
		
		if (clientConnected)
		{
			ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду ‘exit’.");
			
			String input = null;
			while (clientConnected && !"exit".equals(input))
			{
				input = ConsoleHelper.readString();
				if (!"exit".equals(input))
					if (shouldSendTextFromConsole())
						sendTextMessage(input);
			}
		}
		else
			ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
		
	}
	
	protected String getServerAddress()
	{
		System.out.print("Адрес сервера: ");
		
		return ConsoleHelper.readString();
	}
	
	protected int getServerPort()
	{
		System.out.print("Порт сервера: ");
		
		return ConsoleHelper.readInt();
	}
	
	protected String getUserName()
	{
		System.out.print("Имя пользователя: ");
		
		return ConsoleHelper.readString();
	}
	
	protected boolean shouldSendTextFromConsole()
	{
		return true;
	}
	
	protected SocketThread getSocketThread()
	{
		return new SocketThread();
	}
	
	protected void sendTextMessage(String text)
	{
		try
		{
			connection.send(new Message(MessageType.TEXT, text));
		}
		catch (IOException e)
		{
			ConsoleHelper.writeMessage("Не удалось отправить сообщение.");
			
			clientConnected = false;
		}
	}
	
	public class SocketThread extends Thread
	{
		@Override
		public void run()
		{
			String address = getServerAddress();
			int port = getServerPort();
			
			Socket socket = null;
			
			try
			{
				socket = new Socket(address, port);
				connection = new Connection(socket);
				
				clientHandshake();
				
				clientMainLoop();
			}
			catch (IOException e)
			{
				notifyConnectionStatusChanged(false);
			}
			catch (ClassNotFoundException e)
			{
				notifyConnectionStatusChanged(false);
			}
		}
		
		protected void clientHandshake() throws IOException, ClassNotFoundException
		{
			while (true)
			{
				Message message = connection.receive();
				
				if (message.getType() == MessageType.NAME_REQUEST)
					connection.send(new Message(MessageType.USER_NAME, getUserName()));
				else if (message.getType() == MessageType.NAME_ACCEPTED)
				{
					notifyConnectionStatusChanged(true);
					return;
				}
				else
					throw new IOException("Unexpected MessageType");
			}
		}
		
		protected void clientMainLoop() throws IOException, ClassNotFoundException
		{
			while (true)
			{
				Message message = connection.receive();
				
				if (message.getType() == MessageType.TEXT)
				{
					processIncomingMessage(message.getData());
					
					continue;
				}
				
				if (message.getType() == MessageType.USER_ADDED)
				{
					informAboutAddingNewUser(message.getData());
					
					continue;
				}
				
				if (message.getType() == MessageType.USER_REMOVED)
				{
					informAboutDeletingNewUser(message.getData());
					
					continue;
				}
				
				throw new IOException("Unexpected MessageType");
			}
		}
		
		protected void processIncomingMessage(String message)
		{
			ConsoleHelper.writeMessage(message);
		}
		
		protected void informAboutAddingNewUser(String userName)
		{
			ConsoleHelper.writeMessage(userName + " присоединился к чату.");
		}
		
		protected void informAboutDeletingNewUser(String userName)
		{
			ConsoleHelper.writeMessage(userName + " покинул чат.");
		}
		
		protected void notifyConnectionStatusChanged(boolean clientConnected)
		{
			synchronized (Client.this)
			{
				Client.this.clientConnected = clientConnected;
				Client.this.notify();
			}
		}
	
	}
}
