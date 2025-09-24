package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SRenderRailPacket {
    public final BlockPos pos;

    public final boolean shouldRender;

    public SRenderRailPacket (BlockPos pos, boolean shouldRender) {
        this.pos = pos;
        this.shouldRender = shouldRender;
    }

    public static SRenderRailPacket fromBytes(PacketBuffer buf) {
        return new SRenderRailPacket(buf.readBlockPos(), buf.readBoolean());
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeBlockPos(this.pos);
        buf.writeBoolean(this.shouldRender);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> setRenderInfo(this));
        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    public static void setRenderInfo(SRenderRailPacket packet) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.world != null) {
            minecraft.world.notifyBlockUpdate(packet.pos, minecraft.world.getBlockState(packet.pos), minecraft.world.getBlockState(packet.pos), 3);
            GSKOUtil.getTileByType(minecraft.world, packet.pos, TileEntityRegistry.RAIL_TILE_ENTITY.get())
                    .ifPresent(tileEntity -> {
                        tileEntity.setShouldRender(packet.shouldRender);
                        GSKOUtil.log(tileEntity.getClass(), "render: " + tileEntity.shouldRender());
                    });
        }
    }
}