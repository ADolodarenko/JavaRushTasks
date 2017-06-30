package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by n_alex on 23.04.2017.
 */
public class Server
{
	private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();
	
	public static void main(String[] args)
	{
		try
		{
			int port = ConsoleHelper.readInt();
			ServerSocket serverSocket = null;
			
			try
			{
				serverSocket = new ServerSocket(port);
				
				ConsoleHelper.writeMessage("Сервер запущен.");
				
				while (true)
				{
					Socket clientSocket = serverSocket.accept();
					
					new Handler(clientSocket).start();
				}
			}
			catch (Exception e)
			{
				if (serverSocket != null)
					serverSocket.close();
			}
		}
		catch (Exception e)
		{
			ConsoleHelper.writeMessage(e.getMessage());
		}
	}
	
	public static void sendBroadcastMessage(Message message)
	{
		try
		{
			for (Map.Entry<String, Connection> entry : connectionMap.entrySet())
				entry.getValue().send(message);
		}
		catch (IOException e)
		{
			ConsoleHelper.writeMessage("Не удалось отправить сообщение.");
		}
	}
	
	private static class Handler extends Thread
	{
		private Socket socket;
		
		public Handler(Socket socket)
		{
			this.socket = socket;
		}
		
		@Override
		public void run()
		{
			try
			{
				String userName = null;
				Connection connection = null;
				
				try
				{
					ConsoleHelper.writeMessage("Установлено новое соединение с удаленным адресом "
							+ socket.getRemoteSocketAddress());
					
					connection = new Connection(socket);
					
					userName = serverHandshake(connection);
					sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
					
					sendListOfUsers(connection, userName);
					
					serverMainLoop(connection, userName);
				}
				catch (IOException e)
				{
					ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом.");
				}
				catch (ClassNotFoundException e)
				{
					ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом.");
				}
				finally
				{
					if (userName != null)
					{
						connectionMap.remove(userName);
						
						sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
					}
					
					if (connection != null)
						connection.close();
					
					ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто.");
				}
			}
			catch (IOException e)
			{}
		}
		
		private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException
		{
			String name = null;
			
			while (true)
			{
				connection.send(new Message(MessageType.NAME_REQUEST));
				Message message = connection.receive();
				
				if (message.getType() == MessageType.USER_NAME)
				{
					name = message.getData();
					
					if (!name.isEmpty() && !connectionMap.containsKey(name))
					{
						connectionMap.put(name, connection);
						connection.send(new Message(MessageType.NAME_ACCEPTED));
						
						break;
					}
				}
			}
			
			return name;
		}
		
		private void sendListOfUsers(Connection connection, String userName) throws IOException
		{
			for (String name : connectionMap.keySet())
				if (!name.equals(userName))
					connection.send(new Message(MessageType.USER_ADDED, name));
		}
		
		private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException
		{
			while (true)
			{
				Message message = connection.receive();
				
				if (message.getType() == MessageType.TEXT)
				{
					Message newMessage = new Message(MessageType.TEXT, userName + ": " + message.getData());
					
					sendBroadcastMessage(newMessage);
				}
				else
					ConsoleHelper.writeMessage("Ошибка");
			}
		}
	}
}
