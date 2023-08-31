package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.tileentity.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class TileEntityTypeRegistry {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(
            ForgeRegistries.TILE_ENTITIES, GensokyoOntology.MODID);

    public static final RegistryObject<TileEntityType<SpaceFissureTileEntity>> SPACE_FISSURE_TILE_ENTITY =
            TILE_ENTITIES.register("space_fissure_tileentity",() -> TileEntityType.Builder.create(
                    SpaceFissureTileEntity::new, BlockRegistry.SPACE_FISSURE_BLOCK.get()).build(null));
    public static final RegistryObject<TileEntityType<DanmakuTabelTileEntity>> DANMAKU_TABLE_TILE =
            TILE_ENTITIES.register("danmaku_table_tileentity", () -> TileEntityType.Builder.create(
                    DanmakuTabelTileEntity::new, BlockRegistry.DANMAKU_TABLE.get()).build(null));
    public static final RegistryObject<TileEntityType<GapTileEntity>> SUKIMA_TILE_ENTITY =
            TILE_ENTITIES.register("sukima_tileentity", () -> TileEntityType.Builder.create(
                    GapTileEntity::new, BlockRegistry.GAP_BLOCK.get()).build(null));
    public static final RegistryObject<TileEntityType<SorceryExtractorTileEntity>> SORCERY_EXTRACTOR_TILE_ENTITY =
            TILE_ENTITIES.register("sorcery_tileentity", () -> TileEntityType.Builder.create(
                    SorceryExtractorTileEntity::new, BlockRegistry.SORCERY_EXTRACTOR.get()).build(null));
}
