package pers.fengzili.fstart;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;

import pers.fengzili.fstart.config.Config;


@SpringBootApplication
public class App implements CommandLineRunner {
	
	@Autowired
	private Config config;
	
	@Autowired
	private FJobEngine jobEngine;
	
	public static void main(String[] args) {
//		SpringApplication.run(App.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
		builder.headless(false).run(args);//设置headless为false，以便支持弹出框
	}
	
	public MenuItem[] getMenuItems() {
		MenuItem item = new MenuItem("ConfigJobs");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec(App.this.config.getOpenJobPath());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		return new MenuItem[] {item};
	}
	
	@Override
	public void run(String... args) throws Exception {
		if(SystemTray.isSupported()){
			TrayIcon icon = 
			new TrayIcon(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/images/learn.png")));
			PopupMenu menu = new PopupMenu();
			MenuItem item = new MenuItem("Exit");
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			menu.add(item);
			for (MenuItem additionItem : this.getMenuItems()) {
				menu.add(additionItem);
			}
			icon.setPopupMenu(menu);
			SystemTray tray = SystemTray.getSystemTray();
			try {
				tray.add(icon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}
		this.jobEngine.start(args);
	}
}