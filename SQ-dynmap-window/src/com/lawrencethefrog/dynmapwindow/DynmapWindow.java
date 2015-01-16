package com.lawrencethefrog.dynmapwindow;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class DynmapWindow extends JFXPanel{
	JFrame topLevelFrame;
	JFXPanel thisPanel = this;
	WebView browser;
	Group group;
	Scene scene;
	
	int width = 500;
	int height = 500;
	
	public DynmapWindow(){
		
		setPreferredSize(new Dimension(width, height));
		
		setupFX(true);
		
		topLevelFrame = new JFrame("Dynmap window by lawrencethefrog");
		topLevelFrame.addComponentListener(new ResizeListener());
		topLevelFrame.add(this);
		topLevelFrame.pack();
		topLevelFrame.setAlwaysOnTop(true);
		topLevelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//topLevelFrame.setResizable(false);
		topLevelFrame.setVisible(true);
	}
	
	private void setupFX(final boolean firstTime){
		Platform.runLater(new Runnable(){
			@Override
			public void run(){
				
				if (firstTime){
					group = new Group();
					scene = new Scene(group);
					thisPanel.setScene(scene);
					browser = new WebView();
					
			        browser.getEngine().load("http://192.99.20.8:8123/index.html");
					
			        group.getChildren().add(browser);
				}
				browser.setMinSize(width, height);
				browser.setMaxSize(width, height);				
			}
		});
	}
	
	
	class ResizeListener extends ComponentAdapter {
		public void componentResized(ComponentEvent e){
			width = thisPanel.getWidth();
			height = thisPanel.getHeight();
			setupFX(false);
			topLevelFrame.repaint();
		}
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new DynmapWindow();
			}
		});
		
	}
}
