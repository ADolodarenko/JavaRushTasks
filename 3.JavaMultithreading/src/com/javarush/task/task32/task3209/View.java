package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by n_alex on 05.05.2017.
 */
public class View extends JFrame implements ActionListener
{
	private Controller controller;
	
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JTextPane htmlTextPane = new JTextPane();
	private JEditorPane plainTextPane = new JEditorPane();
	
	private UndoManager undoManager = new UndoManager();
	private UndoListener undoListener = new UndoListener(undoManager);
	
	public View()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			ExceptionHandler.log(e);
		}
	}
	
	public Controller getController()
	{
		return controller;
	}
	
	public void setController(Controller controller)
	{
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		
		if ("Новый".equals(command))
			controller.createNewDocument();
		else if ("Открыть".equals(command))
			controller.openDocument();
		else if ("Сохранить".equals(command))
			controller.saveDocument();
		else if ("Сохранить как...".equals(command))
			controller.saveDocumentAs();
		else if ("Выход".equals(command))
			controller.exit();
		else if ("О программе".equals(command))
			showAbout();
	}
	
	public void init()
	{
		initGui();
		
		FrameListener listener = new FrameListener(this);
		addWindowListener(listener);
		
		setVisible(true);
	}
	
	public void initGui()
	{
		initMenuBar();
		initEditor();
		pack();
	}
	
	public void initMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		
		MenuHelper.initFileMenu(this, menuBar);
		MenuHelper.initEditMenu(this, menuBar);
		MenuHelper.initStyleMenu(this, menuBar);
		MenuHelper.initAlignMenu(this, menuBar);
		MenuHelper.initColorMenu(this, menuBar);
		MenuHelper.initFontMenu(this, menuBar);
		MenuHelper.initHelpMenu(this, menuBar);
		
		getContentPane().add(menuBar, BorderLayout.NORTH);
	}
	
	public void initEditor()
	{
		htmlTextPane.setContentType("text/html");
		JScrollPane htmlScrollPane = new JScrollPane(htmlTextPane);
		tabbedPane.addTab("HTML", htmlScrollPane);
		
		JScrollPane textScrollPane = new JScrollPane(plainTextPane);
		tabbedPane.addTab("Текст", textScrollPane);
		
		tabbedPane.setPreferredSize(null);
		
		TabbedPaneChangeListener listener = new TabbedPaneChangeListener(this);
		tabbedPane.addChangeListener(listener);
		
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}
	
	public boolean canUndo()
	{
		return undoManager.canUndo();
	}
	
	public boolean canRedo()
	{
		return undoManager.canRedo();
	}
	
	public void undo()
	{
		try
		{
			undoManager.undo();
		}
		catch (Exception e)
		{
			ExceptionHandler.log(e);
		}
	}
	
	public void redo()
	{
		try
		{
			undoManager.redo();
		}
		catch (Exception e)
		{
			ExceptionHandler.log(e);
		}
	}
	
	public void selectHtmlTab()
	{
		tabbedPane.setSelectedIndex(0);
		resetUndo();
	}
	
	public void update()
	{
		htmlTextPane.setDocument(controller.getDocument());
	}
	
	public void showAbout()
	{
		JOptionPane.showMessageDialog(this,
				"HTML редактор",
				"О программе",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void resetUndo()
	{
		undoManager.discardAllEdits();
	}
	
	public boolean isHtmlTabSelected()
	{
		return tabbedPane.getSelectedIndex() == 0;
	}
	
	public void selectedTabChanged()
	{
		if (tabbedPane.getSelectedIndex() == 0)
			controller.setPlainText(plainTextPane.getText());
		else
			plainTextPane.setText(controller.getPlainText());
		
		resetUndo();
	}
	
	public UndoListener getUndoListener()
	{
		return undoListener;
	}
	
	public void exit()
	{
		controller.exit();
	}
}
