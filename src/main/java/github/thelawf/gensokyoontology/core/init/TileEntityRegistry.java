package github.thelawf.gensokyoontology.core.init;

import com.mojang.datafixers.types.Type;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.tileentity.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class TileEntityRegistry {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(
            ForgeRegistries.TILE_ENTITIES, GensokyoOntology.MODID);

    public static final RegistryObject<TileEntityType<SpaceFissureTileEntity>> SPACE_FISSURE_TILE_ENTITY =
            TILE_ENTITIES.register("space_fissure_tileentity", () -> TileEntityType.Builder.create(
                    SpaceFissureTileEntity::new, BlockRegistry.SPACE_FISSURE_BLOCK.get()).build(null));
    public static final RegistryObject<TileEntityType<DanmakuTabelTileEntity>> DANMAKU_TABLE_TILE =
            TILE_ENTITIES.register("danmaku_table_tileentity", () -> TileEntityType.Builder.create(
                    DanmakuTabelTileEntity::new, BlockRegistry.DANMAKU_TABLE.get()).build(null));
    public static final RegistryObject<TileEntityType<GapTileEntity>> GAP_TILE_ENTITY =
            TILE_ENTITIES.register("sukima_tileentity", () -> TileEntityType.Builder.create(
                    GapTileEntity::new, BlockRegistry.GAP_BLOCK.get()).build(null));
    public static final RegistryObject<TileEntityType<SorceryExtractorTileEntity>> SORCERY_EXTRACTOR_TILE_ENTITY =
            TILE_ENTITIES.register("sorcery_tileentity", () -> TileEntityType.Builder.create(
                    SorceryExtractorTileEntity::new, BlockRegistry.SORCERY_EXTRACTOR.get()).build(null));
    public static final RegistryObject<TileEntityType<SaisenBoxTileEntity>> SAISEN_BOX_TILE_ENTITY =
            TILE_ENTITIES.register("saisen_box_tileentity", () -> TileEntityType.Builder.create(
                    SaisenBoxTileEntity::new, BlockRegistry.SAISEN_BOX.get()).build(null));
    public static final RegistryObject<TileEntityType<DisposableSpawnerTile>> DISPOSABLE_SPAWNER_TILE_ENTITY =
            TILE_ENTITIES.register("disposable_spawner_tileentity", () -> TileEntityType.Builder.create(
                    DisposableSpawnerTile::new, BlockRegistry.DISPOSABLE_SPAWNER.get()).build(null));
    public static final RegistryObject<TileEntityType<SpellConsoleTileEntity>> SPELL_CONSOLE_TILE_ENTITY =
            TILE_ENTITIES.register("spell_console_tileentity", () -> TileEntityType.Builder.create(
                    SpellConsoleTileEntity::new, BlockRegistry.SPELL_CARD_CONSOLE.get()).build(null));
    public static final RegistryObject<TileEntityType<AdobeTileEntity>> ADOBE_TILE_ENTITY =
            TILE_ENTITIES.register("adobe_tileentity", () -> TileEntityType.Builder.create(
                    AdobeTileEntity::new, BlockRegistry.CLAY_ADOBE_BLOCK.get()).build(null));
    public static final RegistryObject<TileEntityType<HaniwaTileEntity>> HANIWA_TILE_ENTITY =
            TILE_ENTITIES.register("haniwa_tileentity", () -> TileEntityType.Builder.create(
                    HaniwaTileEntity::new, BlockRegistry.HANIWA_BLOCK.get()).build(null));
}
