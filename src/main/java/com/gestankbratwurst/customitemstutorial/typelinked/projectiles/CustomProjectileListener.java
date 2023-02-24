package com.gestankbratwurst.customitemstutorial.typelinked.projectiles;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class CustomProjectileListener implements Listener {

  private final CustomProjectileManager projectileManager;

  public CustomProjectileListener(CustomProjectileManager projectileManager) {
    this.projectileManager = projectileManager;
  }

  @EventHandler
  public void onHit(ProjectileHitEvent event) {
    CustomProjectileType projectileType = projectileManager.getTypeOfProjectile(event.getEntity());
    if (projectileType != null) {
      projectileType.handleImpact(event);
    }
  }

}
