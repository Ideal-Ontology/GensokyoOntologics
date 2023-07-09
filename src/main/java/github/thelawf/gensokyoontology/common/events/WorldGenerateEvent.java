package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

public class WorldGenerateEvent extends ChunkEvent {
    private final IChunk chunk;
    private final IWorld world;
    private final PlayerEntity player;
    private final ResourceLocation name;
    private Biome.Climate climate;
    private Biome.Category category;
    private float depth;
    private float scale;
    private BiomeAmbience effects;
    private final BiomeGenerationSettingsBuilder gen;
    private final MobSpawnInfoBuilder spawns;

    public WorldGenerateEvent(@Nullable final ResourceLocation name, final Biome.Climate climate, final Biome.Category category,
                              final float depth, final float scale, final BiomeAmbience effects,
                              final BiomeGenerationSettingsBuilder gen, final MobSpawnInfoBuilder spawns,
                              IChunk chunk, IWorld world, PlayerEntity player)
    {
        super(chunk, world);
        this.name = name;
        this.climate = climate;
        this.category = category;
        this.depth = depth;
        this.scale = scale;
        this.effects = effects;
        this.gen = gen;
        this.spawns = spawns;
        this.chunk = chunk;
        this.world = world;
        this.player = player;
    }

    /**
     * This will get the registry name of the biome.
     * It generally SHOULD NOT be null, but due to vanilla's biome handling and codec weirdness, there may be cases where it is.
     * Do check for this possibility!
     */
    @Nullable
    public ResourceLocation getName()
    {
        return name;
    }

    public Biome.Climate getClimate()
    {
        return climate;
    }

    public void setClimate(final Biome.Climate value)
    {
        this.climate = value;
    }

    public Biome.Category getCategory()
    {
        return category;
    }

    public void setCategory(final Biome.Category value)
    {
        this.category = value;
    }

    public float getDepth()
    {
        return depth;
    }

    public void setDepth(final float value)
    {
        this.depth = value;
    }

    public float getScale()
    {
        return scale;
    }

    public void setScale(final float value)
    {
        this.scale = value;
    }

    public BiomeAmbience getEffects()
    {
        return effects;
    }

    public void setEffects(final BiomeAmbience value)
    {
        this.effects = value;
    }

    public BiomeGenerationSettingsBuilder getGeneration()
    {
        return gen;
    }

    public MobSpawnInfoBuilder getSpawns()
    {
        return spawns;
    }

    public IChunk getChunk() {
        return chunk;
    }

    public IWorld getWorld() {
        return world;
    }

    public PlayerEntity getPlayer() {
        return player;
    }
}
