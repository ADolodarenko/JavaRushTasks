package com.javarush.task.task30.task3008.client;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by n_alex on 24.04.2017.
 */
public class BotClient extends Client
{
	public static void main(String[] args)
	{
		BotClient client = new BotClient();
		client.run();
	}
	
	
	@Override
	protected String getUserName()
	{
		int number = (int)(Math.random() * 100);
		return "date_bot_" + number;
	}
	
	@Override
	protected boolean shouldSendTextFromConsole()
	{
		return false;
	}
	
	@Override
	protected SocketThread getSocketThread()
	{
		return new BotSocketThread();
	}
	
	public class BotSocketThread extends SocketThread
	{
		@Override
		protected void clientMainLoop() throws IOException, ClassNotFoundException
		{
			sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
			
			super.clientMainLoop();
		}
		
		@Override
		protected void processIncomingMessage(String message)
		{
			super.processIncomingMessage(message);
			
			int pos = message.indexOf(": ");
			
			if (pos > 0)
			{
				String userName = message.substring(0, pos);
				String text = message.substring(pos + 2);
				
				String formatPattern = null;
				
				if ("дата".equals(text))
					formatPattern = "d.MM.YYYY";
				else if ("день".equals(text))
					formatPattern = "d";
				else if ("месяц".equals(text))
					formatPattern = "MMMM";
				else if ("год".equals(text))
					formatPattern = "YYYY";
				else if ("время".equals(text))
					formatPattern = "H:mm:ss";
				else if ("час".equals(text))
					formatPattern = "H";
				else if ("минуты".equals(text))
					formatPattern = "m";
				else if ("секунды".equals(text))
					formatPattern = "s";
				
				if (formatPattern != null)
				{
					DateFormat format = new SimpleDateFormat(formatPattern);
					
					String answer = String.format("Информация для %s: %s",
							userName, format.format(Calendar.getInstance().getTime()));
					
					sendTextMessage(answer);
				}
			}
		}
	}
}
