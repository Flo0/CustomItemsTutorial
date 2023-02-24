package com.gestankbratwurst.customitemstutorial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Syntax;
import com.gestankbratwurst.customitemstutorial.typelinked.items.CustomItemManager;
import com.gestankbratwurst.customitemstutorial.typelinked.items.CustomItemType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("customitem|ci")
public class CustomItemCommand extends BaseCommand {

  private final CustomItemManager customItemManager;

  public CustomItemCommand(CustomItemManager customItemManager) {
    this.customItemManager = customItemManager;
  }

  @Subcommand("give")
  @Syntax("<Material> <CustomItemType>")
  public void onGive(Player player, Material material, CustomItemType itemType) {
    ItemStack itemStack = new ItemStack(material);
    customItemManager.setTypeOfItem(itemStack, itemType);
    player.getInventory().addItem(itemStack);
    player.sendMessage("> You have created a custom item.");
  }

}
