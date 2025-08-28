package github.thelawf.gensokyoontology.data;

import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapDecoration;

import java.util.Locale;

public class GSKOMapManager {

    public static ItemStack createExplorationMap(ServerWorld world, BlockPos structurePos,
                                                 Structure<?> destination, MapDecoration.Type decoration) {
        // 创建新地图
        ItemStack mapStack = FilledMapItem.setupNewMap(world,
                structurePos.getX(),
                structurePos.getZ(),
                (byte) 2, true, true);

        // 锁定地图
        FilledMapItem.func_226642_a_(world, mapStack); // lockMap
        // 添加目标标记
        MapData.addTargetDecoration(mapStack, structurePos, "+", decoration);
        // 设置显示名称
        mapStack.setDisplayName(new TranslationTextComponent(
                "filled_map." + destination.getStructureName().toLowerCase(Locale.ROOT)
        ));

        return mapStack;
    }
}
