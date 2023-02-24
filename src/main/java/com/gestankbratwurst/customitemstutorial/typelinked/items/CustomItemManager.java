package com.gestankbratwurst.customitemstutorial.typelinked.items;

import com.gestankbratwurst.customitemstutorial.CustomItemsPlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class CustomItemManager {

  private final NamespacedKey customItemTypeKey;

  public CustomItemManager(CustomItemsPlugin customItemsPlugin) {
    this.customItemTypeKey = new NamespacedKey(customItemsPlugin, "custom-item-type");
  }

  public void setTypeOfItem(ItemStack itemStack, CustomItemType customItemType) {
    ItemMeta meta = itemStack.getItemMeta();
    if(meta == null) {
      return;
    }
    PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
    dataContainer.set(this.customItemTypeKey, PersistentDataType.STRING, customItemType.toString());
    itemStack.setItemMeta(meta);
  }

  public CustomItemType getTypeOfItem(ItemStack itemStack) {
    ItemMeta meta = itemStack.getItemMeta();
    if(meta == null) {
      return null;
    }
    PersistentDataContainer container = meta.getPersistentDataContainer();
    String typeValue = container.get(this.customItemTypeKey, PersistentDataType.STRING);
    if(typeValue == null) {
      return null;
    }
    return CustomItemType.valueOf(typeValue);
  }

}
