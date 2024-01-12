package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.NettyPacketDecoder;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class TransformWaterToWinePacket {
    private BlockPos waterPos;

    public TransformWaterToWinePacket(BlockPos waterPos) {
        this.waterPos = waterPos;
    }

    public TransformWaterToWinePacket(PacketBuffer buf) {
        this.waterPos = buf.readBlockPos();
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeBlockPos(this.waterPos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            if (player != null) {
                ServerWorld world = player.getServerWorld();
                if (world.getBlockState(this.waterPos).getBlock() == Blocks.WATER) {
                    world.setBlockState(this.waterPos, BlockRegistry.SAKE_WINE_BLOCK.get().getDefaultState());
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}