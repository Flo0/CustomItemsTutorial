package com.gestankbratwurst.customitemstutorial;

import co.aikar.commands.BukkitCommandManager;
import com.gestankbratwurst.customitemstutorial.commands.CustomItemCommand;
import com.gestankbratwurst.customitemstutorial.typelinked.items.CustomItemListener;
import com.gestankbratwurst.customitemstutorial.typelinked.items.CustomItemManager;
import com.gestankbratwurst.customitemstutorial.typelinked.projectiles.CustomProjectileListener;
import com.gestankbratwurst.customitemstutorial.typelinked.projectiles.CustomProjectileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomItemsPlugin extends JavaPlugin {

  @Override
  public void onEnable() {
    CustomItemManager customItemManager = new CustomItemManager(this);
    CustomProjectileManager customProjectileManager = new CustomProjectileManager(this);
    Bukkit.getPluginManager().registerEvents(new CustomItemListener(customItemManager, customProjectileManager), this);
    Bukkit.getPluginManager().registerEvents(new CustomProjectileListener(customProjectileManager), this);

    // For debug
    this.registerCommands(customItemManager);
  }

  private void registerCommands(CustomItemManager customItemManager) {
    BukkitCommandManager commandManager = new BukkitCommandManager(this);
    commandManager.registerCommand(new CustomItemCommand(customItemManager));
  }

}
