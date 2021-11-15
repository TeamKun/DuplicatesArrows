package net.kunmc.lab.duplicatesarrows;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Supplier;

public final class DuplicatesArrows extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("DuplicatesArrowsプラグインが有効になりました。");
        getServer().getPluginManager().registerEvents(this, this);

        Objects.requireNonNull(getCommand(Config.MAIN_COMMAND)).setExecutor(new CommandHandler());
        Objects.requireNonNull(getCommand(Config.MAIN_COMMAND)).setTabCompleter(new TabComplete());
    }

    @EventHandler
    public void onShoot(EntityShootBowEvent event){
        Arrow originalArrow = (Arrow) event.getProjectile();
        if (originalArrow.getShooter() instanceof Player){
            if (Config.playerList.contains(originalArrow.getShooter())){
                Location originalLocation = originalArrow.getLocation();
                @NotNull Vector originalVelocity = originalArrow.getVelocity();
                originalArrow.remove();
                for (int i = 0; i < Config.MULTIPLE; i++) {
                    Objects.requireNonNull(originalLocation.getWorld().spawnArrow(originalLocation, originalVelocity, (float) originalVelocity.length() * Config.SPEED,Config.SPREAD));
                }
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("DuplicatesArrowsプラグインが無効になりました。");
    }
}
