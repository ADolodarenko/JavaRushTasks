package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

/**
 * Created by n_alex on 06.05.2017.
 */
public class TextEditMenuListener implements MenuListener
{
	private View view;
	
	public TextEditMenuListener(View view)
	{
		this.view = view;
	}
	
	@Override
	public void menuSelected(MenuEvent e)
	{
		Object source = e.getSource();
		if (source != null && source instanceof JMenu)
		{
			JMenu menu = (JMenu) source;
			boolean isHtmlTabEnabled = view.isHtmlTabSelected();
			
			for (Component item : menu.getMenuComponents())
				item.setEnabled(isHtmlTabEnabled);
		}
	}
	
	@Override
	public void menuDeselected(MenuEvent e)
	{
	
	}
	
	@Override
	public void menuCanceled(MenuEvent e)
	{
	
	}
}
