package github.thelawf.gensokyoontology.common.world.dimension.feature.structure;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.IglooPieces;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

@Deprecated
public final class LootChestGenerator implements IStructurePieceType {
    @Override
    public StructurePiece load(TemplateManager p_load_1_, CompoundNBT p_load_2_) {
        return null;
    }
    public static class GSKOPiece extends AbstractVillagePiece {

        public GSKOPiece(TemplateManager p_i242036_1_, JigsawPiece p_i242036_2_, BlockPos p_i242036_3_, int p_i242036_4_, Rotation p_i242036_5_, MutableBoundingBox p_i242036_6_) {
            super(p_i242036_1_, p_i242036_2_, p_i242036_3_, p_i242036_4_, p_i242036_5_, p_i242036_6_);
        }

        @Override
        protected void readAdditional(CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
        }

        @Override
        protected boolean generateChest(ISeedReader worldIn, MutableBoundingBox structurebb, Random randomIn, int x, int y, int z, ResourceLocation loot) {
            return super.generateChest(worldIn, structurebb, randomIn, x, y, z, loot);
        }

        @Override
        protected boolean generateChest(IServerWorld worldIn, MutableBoundingBox boundsIn, Random rand, BlockPos posIn, ResourceLocation resourceLocationIn, @Nullable BlockState p_191080_6_) {
            return super.generateChest(worldIn, boundsIn, rand, posIn, resourceLocationIn, p_191080_6_);
        }
    }
}
