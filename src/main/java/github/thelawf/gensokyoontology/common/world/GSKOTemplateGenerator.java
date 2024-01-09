package github.thelawf.gensokyoontology.common.world;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.util.block.BlockPositions;
import github.thelawf.gensokyoontology.common.util.block.BlockStateData;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;

public class GSKOTemplateGenerator {

    /**
     * 将模板池内的建筑生成至世界的对应位置，先创建两个序列化器，然后遍历加载的结构模板，将其位置和方块状态信息放入Pair列表，最后遍历Pair列表
     * 在其forEach方法中用pair作为lambda参数，使用pairs.indexOf()方法获得pair实例在列表中的索引将这个索引应用至positions和states的get
     * 方法，确保了positions的索引值和states的索引值一致，然后将获取到的位置和方块状态信息传入setBlockState方法。让程序遍历生成建筑模板在
     * 对应位置上包含的方块。
     *
     */
    public void generate(World worldIn, BlockPos posStart, Template template) throws NoSuchFieldException, IllegalAccessException {
        BlockPositions positions = new BlockPositions();
        BlockStateData states = new BlockStateData();
        List<Pair<BlockPositions, BlockStateData>> pairs = new ArrayList<>();
        Template.Palette palette = template.blocks.get(0);

        palette.func_237157_a_().forEach(blockInfo -> {
            positions.add(posStart.toMutable().setPos(blockInfo.pos));
            states.add(blockInfo.state);
            pairs.add(Pair.of(positions, states));
        });
        pairs.forEach(pair -> worldIn.setBlockState(pair.getFirst().get(pairs.indexOf(pair)), pair.getSecond().get(pairs.indexOf(pair))));
    }

    private Template getTemplate(ServerWorld serverWorld, ResourceLocation location) {
        return serverWorld.getStructureTemplateManager().getTemplate(location);
    }
}
