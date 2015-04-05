package com.zinncomputer.gs.listeners;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import com.zinncomputer.gs.GappleSickness;

public class Listeners implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerEatGapple(PlayerItemConsumeEvent e) {
		if (e.getItem().getType().equals(Material.GOLDEN_APPLE)) {
			final Player p = e.getPlayer();
			if (p.hasPermission("gapplesickness.sick")) {
				if (GappleSickness.timeseatenlately.get(p.getUniqueId()
						.toString()) != null) {
					if (GappleSickness.timeseatenlately.get(p.getUniqueId()
							.toString()) >= GappleSickness.instance.getConfig()
							.getInt("gapples-maxatatime")) {
						p.sendMessage(ChatColor.RED + "Hey dude! Chill on the gapples!");
						PotionEffect blind = new PotionEffect(
								PotionEffectType.BLINDNESS, 200, 10);
						p.addPotionEffect(blind);
						PotionEffect confused = new PotionEffect(
								PotionEffectType.CONFUSION, 200, 10);
						p.addPotionEffect(confused);
						PotionEffect poison = new PotionEffect(
								PotionEffectType.POISON, 200, 5);
						p.addPotionEffect(poison);	
					}
					GappleSickness.timeseatenlately.put(p.getUniqueId()
							.toString(), GappleSickness.timeseatenlately.get(p
							.getUniqueId().toString()) + 1);
					BukkitScheduler scheduler = Bukkit.getServer()
							.getScheduler();
					scheduler.scheduleSyncDelayedTask(
							GappleSickness.instance,
							new Runnable() {
								@Override
								public void run() {
									GappleSickness.timeseatenlately.put(p
											.getUniqueId().toString(),
											GappleSickness.timeseatenlately
													.get(p.getUniqueId()
															.toString()) - 1);
								}
							},
							GappleSickness.instance.getConfig().getLong(
									"per-time-seconds") * 20);
				} else {
					GappleSickness.timeseatenlately.put(p.getUniqueId()
							.toString(), 1);
					BukkitScheduler scheduler = Bukkit.getServer()
							.getScheduler();
					scheduler.scheduleSyncDelayedTask(
							GappleSickness.instance,
							new Runnable() {
								@Override
								public void run() {
									GappleSickness.timeseatenlately.put(p
											.getUniqueId().toString(),
											GappleSickness.timeseatenlately
													.get(p.getUniqueId()
															.toString()) - 1);
								}
							},
							GappleSickness.instance.getConfig().getLong(
									"per-time-seconds") * 20);
				}
			}
		}

	}

}
