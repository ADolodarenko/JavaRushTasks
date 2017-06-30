package com.javarush.task.task30.task3008;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by n_alex on 23.04.2017.
 */
public class Connection implements Closeable
{
	private final Socket socket;
	private final ObjectOutputStream out;
	private final ObjectInputStream in;
	
	public Connection(Socket socket) throws IOException
	{
		this.socket = socket;
		this.out = new ObjectOutputStream(this.socket.getOutputStream());
		this.in = new ObjectInputStream(this.socket.getInputStream());
	}
	
	public void send(Message message) throws IOException
	{
		synchronized (out)
		{
			out.writeObject(message);
		}
	}
	
	public Message receive() throws IOException, ClassNotFoundException
	{
		Message result = null;
		
		synchronized (in)
		{
			result = (Message) in.readObject();
		}
		
		return result;
	}
	
	public SocketAddress getRemoteSocketAddress()
	{
		return socket.getRemoteSocketAddress();
	}
	
	public void close() throws IOException
	{
		in.close();
		out.close();
		socket.close();
	}
}
