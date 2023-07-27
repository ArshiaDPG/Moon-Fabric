package net.digitalpear.moon;


import net.digitalpear.moon.common.worldgen.MoonFeature;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class Moon implements ModInitializer {
    public static final String MOD_ID = "moon";
    public static final RegistryKey<World> MOON = RegistryKey.of(RegistryKeys.WORLD, new Identifier(MOD_ID, "the_moon"));
    private final List<ServerPlayerEntity> playersToTeleport = new ArrayList<>();
    private final List<ServerPlayerEntity> playersOnMoon = new ArrayList<>();

    @Override
    public void onInitialize() {
        MoonFeature.init();

//        Map<SpawnGroup, Map<EntityType<?>, SpawnRestriction.Location>> spawnRestrictions = new HashMap<>();
//        spawnRestrictions.put(SpawnGroup.CREATURE, (Map<EntityType<?>, SpawnRestriction.Location>) new HashMap<>().put(EntityType.COW, SpawnRestriction.getLocation(EntityType.COW)));
//        spawnRestrictions.get(SpawnGroup.CREATURE).remove(EntityType.COW);
//        SpawnRestriction.register(EntityType.COW, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,  (type, world, spawnGroup, pos, random) -> world.getBlockState(pos.down()).getBlock() == Blocks.END_STONE);


        // Listen for the server tick event to detect player's height
        ServerTickEvents.START_WORLD_TICK.register((world) -> {
            // Check if the world is a server world (dimension)
            if (world != null) {
                ServerWorld serverWorld = world;
                for (ServerPlayerEntity player : serverWorld.getPlayers()) {
                    // Check if the player's position is at Y=700 or above
                    if (player.getServerWorld().getRegistryKey() == MOON){
                        playersOnMoon.add(player);
                    }
                    if (player.getY() >= 700) {
                        playersToTeleport.add(player); // Add players who need teleportation to the list
                    }
                }
            }
        });

        // Perform teleportation outside of the iteration loop to avoid concurrent modification
        ServerTickEvents.END_WORLD_TICK.register((world) -> {
            if (world != null && world.getRegistryKey() != MOON) {
                for (ServerPlayerEntity player : playersToTeleport) {
                    if (player.getServerWorld().getRegistryKey() == World.OVERWORLD) {
                        teleportPlayer(player, MOON);
                    } else if (player.getServerWorld().getRegistryKey() == MOON) {
                        teleportPlayer(player, World.OVERWORLD);
                    }
                }

                for (ServerPlayerEntity player : playersOnMoon){
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 20, 0, true, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20, 1, true, false));
                }
                playersToTeleport.clear(); // Clear the list after teleportation
                playersOnMoon.clear();
            }
        });
    }
    public void teleportPlayer(ServerPlayerEntity player, RegistryKey<World> key) {
        ServerWorld world = player.getServer().getWorld(key);
        if (world != null) {
            player.teleport(world, player.getX(), 600, player.getZ(), player.getYaw(), player.getPitch());
        }
    }
}
