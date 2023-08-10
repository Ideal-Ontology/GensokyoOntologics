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
    public static final RegistryObject<TileEntityType<SukimaTileEntity>> SUKIMA_TILE_ENTITY =
            TILE_ENTITIES.register("sukima_tileentity", () -> TileEntityType.Builder.create(
                    SukimaTileEntity::new, BlockRegistry.SUKIMA_BLOCK.get()).build(null));

    public static final RegistryObject<TileEntityType<ComputerTileEntity>> COMPUTER_TILE_ENTITY =
            TILE_ENTITIES.register("computer_tileentity", () -> TileEntityType.Builder.create(
                    ComputerTileEntity::new, BlockRegistry.COMPUTER_BLOCK.get()).build(null));

    public static final RegistryObject<TileEntityType<RailTrackTileEntity>> RAIL_TRACK_TILE =
            TILE_ENTITIES.register("rail_track_tileentity", () -> TileEntityType.Builder.create(
                    RailTrackTileEntity::new, BlockRegistry.RAIL_TRACK_BLOCK.get()).build(null));
}
