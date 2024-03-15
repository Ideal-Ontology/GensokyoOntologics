/*package github.thelawf.gensokyoontology.client.renderer.world;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.util.math.logos.LineSegment3D;
import github.thelawf.gensokyoontology.common.util.math.logos.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.world.ConnectionUtil;
import github.thelawf.gensokyoontology.common.util.nbt.GSKONBTUtil;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.model.data.EmptyModelData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class InWorldRenderer {
    public static final Logger LOGGER = LogManager.getLogger();

    // IWorldRenderer <- ConnectionUtil <- BezierUtil, RailWrench.onItemUseFinish()

    /** 在Forge事件总线上添加了这个方法之后会持续监听玩家是否持有存在着"start_pos"和"end_pos"标签的物品
     * 而这两个标签只有在玩家按合法顺序点击轨道方块的时候才能被添加至RailWrench.<br>
     * <br>
     * Possible Error:<br>
     * 1. GSKOMathUtil.bezier2()<br>
     * 2. Rotation is not considered yet<br>
     * 3. new Quaternion()<br>
     * <br>
     * · 渲染的思路大概是：<br>
     * 1. 获取NBT数据标签里面储存的方块位置数组，将其转为Vector3d。<br>
     * 2. 大致计算需要多少个单独的轨道节点才能连接，然后将个数作为迭代次数，开始计算离散贝塞尔曲线<br>
     * 3. 每次迭代返回一对数据，分别为方块的坐标和欧拉角，以三维向量的形式存储，使用matrix矩阵变换至这些位置并渲染。<br>
     * @param event 渲染世界事件

    @OnlyIn(value = Dist.CLIENT)
    public static void renderBezierRail(RenderWorldLastEvent event) {

        ClientPlayerEntity player = Minecraft.getInstance().player;
        ItemStack stack = Objects.requireNonNull(player).getHeldItemMainhand();
        IRenderTypeBuffer.Impl buffer = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();

        BlockPos startPos = GSKONBTUtil.readPosFromStack(stack, "start_pos");
        BlockPos endPos = GSKONBTUtil.readPosFromStack(stack, "end_pos");

        Vector3d startVec = GSKONBTUtil.blockPosToVec(startPos);
        Vector3d endVec = GSKONBTUtil.blockPosToVec(endPos);

        // 如果不满足ConnectionUtil里面的条件则不予渲染轨道
        // 机械动力之中判断segment个数的方式是用segment之间的距离除以总长度
        if (!ConnectionUtil.tryConnect(startPos, endPos))
            return;

        MatrixStack matrix = event.getMatrixStack();
        float count = ConnectionUtil.getSegCount(startPos, endPos);

        LineSegment3D lineA = new LineSegment3D(startPos.getX(), startPos.getY(), startPos.getZ(),
                startPos.getX() + 1, startPos.getY() + 1, startPos.getZ() + 1);
        LineSegment3D lineB = new LineSegment3D(endPos.getX(), endPos.getY(), endPos.getZ(),
                endPos.getX() + 1, endPos.getY() + 1, endPos.getZ() + 1);
        Vector3d intersection = GSKOMathUtil.intersection3D(lineA, lineB);

        for (int i = 0; i < 1 / count; i ++) {
            Pair<Vector3d, Vector3d> pose = ConnectionUtil.getPosAndRot(startVec, intersection, endVec, i);
            matrix.push();
            matrix.translate(pose.getFirst().getX(), pose.getFirst().getY(), pose.getFirst().getZ());
            matrix.rotate(new Quaternion(
                    (float) pose.getSecond().x, (float) pose.getSecond().y,
                    (float) pose.getSecond().z, true));
            BlockRendererDispatcher blockRen = Minecraft.getInstance().getBlockRendererDispatcher();
            BlockState state = BlockRegistry.RAIL_NODE_BLOCK.get().getDefaultState();
            blockRen.renderBlock(state, matrix, buffer, 1, 1, EmptyModelData.INSTANCE);
            matrix.pop();
        }

    }

}
 */