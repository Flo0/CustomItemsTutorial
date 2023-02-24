package com.gestankbratwurst.customitemstutorial.typelinked.projectiles;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.entity.ProjectileHitEvent;

public enum CustomProjectileType {

  EXPLOSION() {
    @Override
    void handleImpact(ProjectileHitEvent event) {
      World world = event.getEntity().getWorld();
      Location location = event.getEntity().getLocation();
      world.createExplosion(location, 3f);
    }
  },
  LIGHTNING() {
    @Override
    void handleImpact(ProjectileHitEvent event) {
      World world = event.getEntity().getWorld();
      Location location = event.getEntity().getLocation();
      world.strikeLightning(location);
    }
  };

  abstract void handleImpact(ProjectileHitEvent event);

}
