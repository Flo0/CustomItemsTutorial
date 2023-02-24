package com.gestankbratwurst.customitemstutorial.typelinked.projectiles;

import com.gestankbratwurst.customitemstutorial.CustomItemsPlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Projectile;
import org.bukkit.persistence.PersistentDataType;

public class CustomProjectileManager {

  private final NamespacedKey customProjectileKey;

  public CustomProjectileManager(CustomItemsPlugin customItemsPlugin) {
    this.customProjectileKey = new NamespacedKey(customItemsPlugin, "custom-projectile-type");
  }

  public void setTypeOfProjectile(Projectile projectile, CustomProjectileType customProjectileType) {
    projectile.getPersistentDataContainer().set(customProjectileKey, PersistentDataType.STRING, customProjectileType.toString());
  }

  public CustomProjectileType getTypeOfProjectile(Projectile projectile) {
    String typeValue = projectile.getPersistentDataContainer().get(customProjectileKey, PersistentDataType.STRING);
    if (typeValue == null) {
      return null;
    }
    return CustomProjectileType.valueOf(typeValue);
  }

}
