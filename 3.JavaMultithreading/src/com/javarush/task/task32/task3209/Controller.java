package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

/**
 * Created by n_alex on 05.05.2017.
 */
public class Controller
{
	private View view;
	private HTMLDocument document;
	private File currentFile;
	
	public Controller(View view)
	{
		this.view = view;
	}
	
	public static void main(String[] args)
	{
		View newView = new View();
		Controller controller = new Controller(newView);
		
		newView.setController(controller);
		newView.init();
		
		controller.init();
	}
	
	public void init()
	{
		createNewDocument();
	}
	
	public void exit()
	{
		System.exit(0);
	}
	
	public HTMLDocument getDocument()
	{
		return document;
	}
	
	public void resetDocument()
	{
		if (document != null)
			document.removeUndoableEditListener(view.getUndoListener());
			
		document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
		document.addUndoableEditListener(view.getUndoListener());
	
		view.update();
	}
	
	public void setPlainText(String text)
	{
		resetDocument();
		
		StringReader reader = new StringReader(text);
		
		try
		{
			new HTMLEditorKit().read(reader, document, 0);
		}
		catch (Exception e)
		{
			ExceptionHandler.log(e);
		}
		
		reader.close();
	}
	
	public String getPlainText()
	{
		StringWriter writer = new StringWriter();
		String result = null;
		
		try
		{
			new HTMLEditorKit().write(writer, document, 0, document.getLength());
			result = writer.toString();
			
			writer.close();
		}
		catch (Exception e)
		{
			ExceptionHandler.log(e);
		}
		
		return result;
	}
	
	public void createNewDocument()
	{
		view.selectHtmlTab();
		
		resetDocument();
		
		view.setTitle("HTML редактор");
		view.resetUndo();
		
		currentFile = null;
	}
	
	public void openDocument()
	{
		view.selectHtmlTab();
		
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new HTMLFileFilter());
		
		if (chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION)
		{
			currentFile = chooser.getSelectedFile();
			
			resetDocument();
			
			view.setTitle(currentFile.getName());
			
			try
			{
				FileReader reader = new FileReader(currentFile);
				new HTMLEditorKit().read(reader, document, 0);
				reader.close();
				
				view.resetUndo();
			}
			catch (Exception e)
			{
				ExceptionHandler.log(e);
			}
		}
	}
	
	public void saveDocument()
	{
		if (currentFile == null)
			saveDocumentAs();
		else
		{
			view.selectHtmlTab();
			
			try
			{
				FileWriter writer = new FileWriter(currentFile);
				
				new HTMLEditorKit().write(writer, document, 0, document.getLength());
				
				writer.close();
			}
			catch (Exception e)
			{
				ExceptionHandler.log(e);
			}
		}
	}
	
	public void saveDocumentAs()
	{
		view.selectHtmlTab();
		
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new HTMLFileFilter());
		
		if (chooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION)
		{
			currentFile = chooser.getSelectedFile();
			view.setTitle(currentFile.getName());
			
			try
			{
				FileWriter writer = new FileWriter(currentFile);
				
				new HTMLEditorKit().write(writer, document, 0, document.getLength());
				
				writer.close();
			}
			catch (Exception e)
			{
				ExceptionHandler.log(e);
			}
		}
	}
}
