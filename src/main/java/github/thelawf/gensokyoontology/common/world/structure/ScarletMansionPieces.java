package github.thelawf.gensokyoontology.common.world.structure;

import com.google.common.collect.ImmutableMap;
import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
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
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ScarletMansionPieces {
    public static IStructurePieceType TYPE = IStructurePieceType.register(Piece::new, "scarlet_devil_mansion");
    private static final Vector3i VEC_001 = new Vector3i(0,0,1);
    private static final Vector3i VEC_101 = new Vector3i(1,0,1);
    public static final String MODID = GensokyoOntology.MODID;
    public static final String PREFIX = "scarlet_devil_mansion/";

    // 000: l=47, w=33, h=47
    // 100: l=46
    // 200: l=28
    // 300: l=23
    //
    // -18
    // -47 (-41) ^+47 ^+27
    // 0 -> +47 -> +46 -> +28
    public static final Map<ResourceLocation, BlockPos> OFFSET = new ImmutableMap.Builder<ResourceLocation, BlockPos>()
            .put(GensokyoOntology.withRL(PREFIX + "mansion_0_0_0"), BlockPos.ZERO)
            .put(GensokyoOntology.withRL(PREFIX + "mansion_1_0_0"), new BlockPos(47, 0, 0))
            .put(GensokyoOntology.withRL(PREFIX + "mansion_2_0_0"), new BlockPos(47+46, 0, 0))
            .put(GensokyoOntology.withRL(PREFIX + "mansion_3_0_0"), new BlockPos(47+46+28, 0, 0))

            .put(GensokyoOntology.withRL(PREFIX + "mansion_0_0_1"), new BlockPos(0, 0, -47))
            .put(GensokyoOntology.withRL(PREFIX + "mansion_1_0_1"), new BlockPos(47, 0, -47))
            .put(GensokyoOntology.withRL(PREFIX + "mansion_2_0_1"), new BlockPos(47+46, 0, -47))
            .put(GensokyoOntology.withRL(PREFIX + "mansion_3_0_1"), new BlockPos(47+46+28, 0, -47))

            .put(GensokyoOntology.withRL(PREFIX + "mansion_0_0_2"), new BlockPos(0, 0, -47-18))
            .put(GensokyoOntology.withRL(PREFIX + "mansion_1_0_2"), new BlockPos(47, 0, -47-18))
            .put(GensokyoOntology.withRL(PREFIX + "mansion_2_0_2"), new BlockPos(47+46, 0, -47-18))
            .put(GensokyoOntology.withRL(PREFIX + "mansion_3_0_2"), new BlockPos(47+46+28, 0, -47-18))

            .put(GensokyoOntology.withRL(PREFIX + "mansion_0_1_0"), new BlockPos(0, 47, 0))
            .put(GensokyoOntology.withRL(PREFIX + "mansion_0_1_1"), new BlockPos(0, 47, -32))
            .put(GensokyoOntology.withRL(PREFIX + "mansion_1_1_1"), new BlockPos(47, 47, -32))
            .put(GensokyoOntology.withRL(PREFIX + "mansion_2_1_1"), new BlockPos(47+46, 47, -32))
//
            .put(GensokyoOntology.withRL(PREFIX + "mansion_0_1_2"), new BlockPos(0, 47, -32-33))
            .put(GensokyoOntology.withRL(PREFIX + "mansion_1_1_2"), new BlockPos(47, 47, -32-33))
            .put(GensokyoOntology.withRL(PREFIX + "mansion_2_1_2"), new BlockPos(47+46, 47, -32-33))

            .put(GensokyoOntology.withRL(PREFIX + "mansion_0_2_1"), new BlockPos(0, 47+27, -47)).build();
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
            LogManager.getLogger().info(this.templateName.toString());
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
                // if (tileentity instanceof ChestTileEntity) {
                //     ((ChestTileEntity) tileentity).setLootTable(<resource_location_to_loottable>, rand.nextLong());
                // }
            }
        }
    }

    public interface IGenerator{
        void init();
        boolean generate(TemplateManager manager, int recursiveDepth, Piece template,
                         BlockPos pos, List<StructurePiece> pieces);
    }
}
