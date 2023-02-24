package com.gestankbratwurst.customitemstutorial.typelinked.items;

import com.gestankbratwurst.customitemstutorial.typelinked.projectiles.CustomProjectileManager;
import com.gestankbratwurst.customitemstutorial.typelinked.projectiles.CustomProjectileType;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

public class CustomItemListener implements Listener {

  private final CustomItemManager customItemManager;
  private final CustomProjectileManager customProjectileManager;

  public CustomItemListener(CustomItemManager customItemManager, CustomProjectileManager customProjectileManager) {
    this.customItemManager = customItemManager;
    this.customProjectileManager = customProjectileManager;
  }

  @EventHandler
  public void onHit(EntityDamageByEntityEvent event) {
    Entity attacker = event.getDamager();
    if (!(attacker instanceof LivingEntity livingAttacker)) {
      return;
    }

    EntityEquipment attackerEquipment = livingAttacker.getEquipment();
    if (attackerEquipment == null) {
      return;
    }

    ItemStack attackerItem = attackerEquipment.getItem(EquipmentSlot.HAND);
    CustomItemType itemType = customItemManager.getTypeOfItem(attackerItem);

    if(itemType == null) {
      return;
    }

    World world = event.getEntity().getWorld();
    Location location = event.getEntity().getLocation();

    switch (itemType) {
      case EXPLOSION_SWORD -> world.createExplosion(location, 3f);
      case FLAME_SWORD -> event.getEntity().setFireTicks(200);
      case ELECTRIC_SWORD -> world.strikeLightning(location);
    }
  }

  @EventHandler
  public void onLaunch(ProjectileLaunchEvent event) {
    Projectile projectile = event.getEntity();
    ProjectileSource source = projectile.getShooter();
    if(source instanceof LivingEntity entity) {
      EntityEquipment entityEquipment = entity.getEquipment();
      if(entityEquipment == null) {
        return;
      }

      ItemStack bowItem = entityEquipment.getItem(EquipmentSlot.HAND);

      CustomItemType itemType = customItemManager.getTypeOfItem(bowItem);
      if(itemType == null) {
        return;
      }

      CustomProjectileType projectileType = switch (itemType) {
        case EXPLOSION_LAUNCHER -> CustomProjectileType.EXPLOSION;
        case LIGHTNING_LAUNCHER -> CustomProjectileType.LIGHTNING;
        default -> null;
      };

      if(projectileType == null) {
        return;
      }

      customProjectileManager.setTypeOfProjectile(projectile, projectileType);
    }
  }

}
