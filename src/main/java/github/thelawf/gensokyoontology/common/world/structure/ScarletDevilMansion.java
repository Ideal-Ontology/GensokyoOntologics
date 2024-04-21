package github.thelawf.gensokyoontology.common.world.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.ReflectHelper;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.LegacySingleJigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

// locate gensokyoontology:scarlet_devil_mansion
// gensokyoontology:scarlet_devil_mansion/mansion_
public class ScarletDevilMansion extends Structure<NoFeatureConfig> {
    public ScarletDevilMansion(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    private static final List<MobSpawnInfo.Spawners> MONSTERS = ImmutableList.of(
            new MobSpawnInfo.Spawners(EntityRegistry.FAIRY_ENTITY.get(), 10, 4, 9)
    );

    @Override
    @NotNull
    public GenerationStage.Decoration getDecorationStage() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }


    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return MONSTERS;
    }


    @Override
    public boolean getDefaultRestrictsSpawnsToInside() {
        return true;
    }


    @Override
    @NotNull
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return ScarletDevilMansion.Start::new;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> p_i225876_1_, int p_i225876_2_, int p_i225876_3_, MutableBoundingBox p_i225876_4_, int p_i225876_5_, long p_i225876_6_) {
            super(p_i225876_1_, p_i225876_2_, p_i225876_3_, p_i225876_4_, p_i225876_5_, p_i225876_6_);
        }

        @Override
        protected int getMaxRefCount() {
            return 5;
        }

        @Override
        protected void func_214628_a(int p_214628_1_, Random p_214628_2_, int p_214628_3_) {
            super.func_214628_a(p_214628_1_, p_214628_2_, p_214628_3_);
        }

        @Override
        public void func_230364_a_(@NotNull DynamicRegistries dynamicRegistry, @NotNull ChunkGenerator chunkGenerator, @NotNull TemplateManager templateManagerIn,
                                   int chunkX, int chunkZ, @NotNull Biome p_230364_6_, @NotNull NoFeatureConfig p_230364_7_) {
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            BlockPos pos = new BlockPos(x, 0, z);

            // addPieces() Method
            // 不知道为什么这里只能递归地添加6个建筑模块(StructurePiece)
            JigsawManager.func_242837_a(dynamicRegistry,
                    new VillageConfig(() -> dynamicRegistry.getRegistry(Registry.JIGSAW_POOL_KEY)
                            .getOrDefault(new ResourceLocation(GensokyoOntology.MODID, "scarlet_devil_mansion/start_pool")),
                            10), AbstractVillagePiece::new, chunkGenerator, templateManagerIn,
                    pos, this.components, this.rand, false, true);


            // 所以需要在这里继续递归添加建筑模块
            JigsawManager.func_242837_a(dynamicRegistry,
                    new VillageConfig(() -> dynamicRegistry.getRegistry(Registry.JIGSAW_POOL_KEY)
                            .getOrDefault(new ResourceLocation(GensokyoOntology.MODID, "scarlet_devil_mansion/house/p_2_0_0")),
                            10), AbstractVillagePiece::new, chunkGenerator, templateManagerIn,
                    pos, this.components, this.rand, false, true);
            this.recalculateStructureSize();

            this.components.forEach(piece -> {
                ReflectHelper.setFieldValue(StructurePiece.class, "mirror", piece, Mirror.NONE);
                ReflectHelper.setFieldValue(StructurePiece.class, "rotation", piece, Rotation.CLOCKWISE_90);
                if (this.components.indexOf(piece) == this.components.size() -1 ||
                        this.components.indexOf(piece) == this.components.size() -2 ) {
                    ReflectHelper.setFieldValue(StructurePiece.class, "mirror", piece, Mirror.LEFT_RIGHT);
                    ReflectHelper.setFieldValue(StructurePiece.class, "rotation", piece, Rotation.COUNTERCLOCKWISE_90);
                    piece.offset(0,0,143);
                }
                GSKOUtil.log(this.getClass(), ReflectHelper.getFieldValue(StructurePiece.class, "mirror", piece));
            });
            this.components.forEach(piece -> GSKOUtil.log(this.getClass(), piece.getRotation().name()));

        }
    }
}
