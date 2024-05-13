package github.thelawf.gensokyoontology.common.world.structure;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.tileentity.DisposableSpawnerTile;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ScarletMansionPieces {
    public static IStructurePieceType TYPE = IStructurePieceType.register(Piece::new, "scarlet_devil_mansion");
    public static final String MODID = GensokyoOntology.MODID;
    public static final String STRUCTURE_PATH = "scarlet_devil_mansion/";
    public static final String LOOT_PATH = "chests/";

    // 000: l=47, w=33, h=47
    // 100: l=46
    // 200: l=28
    // 300: l=23
    //
    // Garden:
    // South West: l = 34<<, 0, 48vv
    // back: l=>>48,
    //
    // Dungeon:
    // 1: l=>>3, -22vv, 0
    // 7: l=>>25, -22vv, 43
    public static final Map<ResourceLocation, BlockPos> OFFSET = new ImmutableMap.Builder<ResourceLocation, BlockPos>()
            .put(withStructureRL("mansion_0_0_0"), new BlockPos(-47, 0, 0))
            .put(withStructureRL("mansion_1_0_0"), new BlockPos(0, 0, 0))
            .put(withStructureRL("mansion_2_0_0"), new BlockPos(46, 0, 0))
            .put(withStructureRL("mansion_3_0_0"), new BlockPos(46+28, 0, 0))

            .put(withStructureRL("mansion_0_0_1"), new BlockPos(-47, 0, -47))
            .put(withStructureRL("mansion_1_0_1"), new BlockPos(0, 0, -47))
            .put(withStructureRL("mansion_2_0_1"), new BlockPos(46, 0, -47))
            .put(withStructureRL("mansion_3_0_1"), new BlockPos(46+28, 0, -47))

            .put(withStructureRL("mansion_0_0_2"), new BlockPos(-47, 0, -47-18))
            .put(withStructureRL("mansion_1_0_2"), new BlockPos(0, 0, -47-18))
            .put(withStructureRL("mansion_2_0_2"), new BlockPos(46, 0, -47-18))
            .put(withStructureRL("mansion_3_0_2"), new BlockPos(46+28, 0, -47-18))

            .put(withStructureRL("mansion_0_1_0"), new BlockPos(-47, 47, 0))
            .put(withStructureRL("mansion_0_1_1"), new BlockPos(-47, 47, -32))
            .put(withStructureRL("mansion_1_1_1"), new BlockPos(0, 47, -32))
            .put(withStructureRL("mansion_2_1_1"), new BlockPos(46, 47, -32))

            .put(withStructureRL("mansion_0_1_2"), new BlockPos(-47, 47, -32-33))
            .put(withStructureRL("mansion_1_1_2"), new BlockPos(0, 47, -32-33))
            .put(withStructureRL("mansion_2_1_2"), new BlockPos(46, 47, -32-33))
            .put(withStructureRL("mansion_0_2_1"), new BlockPos(-47, 47+27, -47))

            .put(withStructureRL("garden_sw"), new BlockPos(-34-47, -1, 33))
            .put(withStructureRL("garden_front_left"), new BlockPos(14-47, -1, 33))
            .put(withStructureRL("garden_front"), new BlockPos(15, -1, 33))
            .put(withStructureRL("garden_front_right"), new BlockPos(84-47, -1, 33))
            .put(withStructureRL("garden_se"), new BlockPos(85, -1, 33))

            .put(withStructureRL("garden_maze_1"), new BlockPos(-34-47, -1, -13))
            .put(withStructureRL("garden_maze_2"), new BlockPos(-34-47, -1, -48-13))
            .put(withStructureRL("garden_maze_3"), new BlockPos(84+27, -1, -13))
            .put(withStructureRL("garden_maze_4"), new BlockPos(84+27, -1, -48-13))

            .put(withStructureRL("garden_nw"), new BlockPos(-34-47, -1, -48*2-13))
            .put(withStructureRL("garden_back_left"), new BlockPos(-47, -1, -48*2-13))
            .put(withStructureRL("garden_back"), new BlockPos(0, -1, -48*2-13))
            .put(withStructureRL("garden_back_right"), new BlockPos(48, -1, -48*2-13))
            .put(withStructureRL("garden_ne"), new BlockPos(48*2, -1, -48*2-11))

            .put(withStructureRL("dungeon_01"), new BlockPos(-44, -22, -13))
            .put(withStructureRL("dungeon_02"), new BlockPos(-44, -22, -48-13))
            .put(withStructureRL("dungeon_03"), new BlockPos(4, -22, -13))
            .put(withStructureRL("dungeon_04"), new BlockPos(4, -22, -48-13))
            .put(withStructureRL("dungeon_05"), new BlockPos(4+48, -22, -13))
            .put(withStructureRL("dungeon_06"), new BlockPos(4+48, -22, -48-13))
            .put(withStructureRL("dungeon_07"), new BlockPos(29-47, -22, 31))
            .build();
    public static final List<ResourceLocation> LOOT_TABLES = ImmutableList.of(
            withLootRL("mansion_danmaku_loot.json"),
            withLootRL("mansion_vanilla_loot.json"),
            withLootRL("mansion_shot_loot.json"),
            withLootRL("mansion_small_shot_loot.json"),
            withLootRL("mansion_color_shot_loot.json"));
    public static ResourceLocation withStructureRL(String name) {
        return GensokyoOntology.withRL(STRUCTURE_PATH + name);
    }
    public static ResourceLocation withLootRL(String name) {
        return GensokyoOntology.withRL(LOOT_PATH + name);
    }
    public static void start(TemplateManager templateManager, BlockPos pos, Rotation rotation,
                             List<StructurePiece> pieces) {
        int x = pos.getX();
        int z = pos.getZ();

        for (Map.Entry<ResourceLocation, BlockPos> entry: OFFSET.entrySet()) {
            String name = entry.getKey().toString().replace("gensokyoontology:scarlet_devil_mansion/","");
            BlockPos rotationOffSet = new BlockPos(0, 0, 0).rotate(setRotFrom(warp(name)));
            BlockPos blockpos = rotationOffSet.add(x, pos.getY(), z);
            pieces.add(new Piece(templateManager, name, blockpos, rotation, Mirror.NONE));
        }

    }

    /**
     * 根据不同的建筑模板设置其旋转
     */
    private static Rotation setRotFrom(Vector3i relative) {
       return Rotation.NONE;
    }

    /**
     * 根据模板的名字返回其相对坐标
     */
    private static Vector3i warp(String templateName) {
        String regex = "[^0-9]+";
        Pattern pattern = Pattern.compile(regex);
        List<String> ls = Arrays.asList(pattern.split(templateName));
        List<String> list = ls.stream().filter(s -> !Objects.equals(s, "")).collect(Collectors.toList());
        if (list.size() <= 1) return new Vector3i(0,0,0);
        return new Vector3i(Integer.parseInt(list.get(0)), Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2)));
    }

    public static class Piece extends TemplateStructurePiece {
        private ResourceLocation templateName;
        private final Rotation rotation;
        private final Mirror mirror;

        public Piece(TemplateManager templateManager, String templateName, BlockPos pos, Rotation rotation, Mirror mirror) {
            super(TYPE, 0);
            this.templateName = GensokyoOntology.withRL("scarlet_devil_mansion/" + templateName);
            BlockPos blockPos = OFFSET.get(this.templateName);
            this.templatePosition = pos.add(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            this.rotation = rotation;
            this.mirror = mirror;
            this.loadTemplate(templateManager);
        }

        public Piece(TemplateManager templateManager, CompoundNBT nbt) {
            super(TYPE, nbt);
            this.templateName = new ResourceLocation(nbt.getString("Template"));
            this.rotation = Rotation.NONE; // Rotation.valueOf(nbt.getString("Rot"));
            this.mirror = Mirror.NONE; // Mirror.valueOf(nbt.getString("Mi"));
            this.loadTemplate(templateManager);
        }

        private void loadTemplate(TemplateManager templateManager) {
            Template template = templateManager.getTemplateDefaulted(this.templateName);
            PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(false).setRotation(this.rotation).setMirror(this.mirror).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            //this.template = template;
            //this.placeSettings = placementsettings;
            //this.setCoordBaseMode(Direction.NORTH);
            //this.templatePosition = this.template.getSize();
            //this.boundingBox = template.getMutableBoundingBox(placementsettings, this.templatePosition);

            this.setup(template, this.templatePosition, placementsettings);
        }


        @Override
        protected void readAdditional(@NotNull CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
            tagCompound.putString("Template", this.templateName.toString());
            tagCompound.putString("Rot", this.rotation.name());
        }

        @Override
        protected void handleDataMarker(@NotNull String function, @NotNull BlockPos pos, @NotNull IServerWorld worldIn, @NotNull Random rand, @NotNull MutableBoundingBox sbb) {
            if ("chest".equals(function)) {
                worldIn.setBlockState(pos, Blocks.CHEST.getDefaultState(), 2);
                TileEntity tileentity = worldIn.getTileEntity(pos);
                // Just another check to make sure everything is going well before we try to set the chest.
                if (tileentity instanceof ChestTileEntity) {
                    ((ChestTileEntity) tileentity).setLootTable(LOOT_TABLES.get(rand.nextInt(LOOT_TABLES.size())), rand.nextLong());
                }
            }
            if("gensokyoontology:disposable_spawner_tileentity".equals(function)) {
                worldIn.setBlockState(pos, BlockRegistry.DISPOSABLE_SPAWNER.get().getDefaultState(), 2);
                TileEntity tileentity = worldIn.getTileEntity(pos);

                if (tileentity instanceof DisposableSpawnerTile && this.templateName.getPath().equals("mansion_1_0_2")) {
                    ((DisposableSpawnerTile) tileentity).setEntityType(EntityRegistry.REMILIA_SCARLET.get());
                }
            }
        }

    }

}
