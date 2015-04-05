package com.zinncomputer.gs;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.zinncomputer.gs.listeners.Listeners;

public class GappleSickness extends JavaPlugin {

	public static Plugin instance;
	
	public static HashMap<String, Integer> timeseatenlately = new HashMap<String, Integer>();
	
	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
