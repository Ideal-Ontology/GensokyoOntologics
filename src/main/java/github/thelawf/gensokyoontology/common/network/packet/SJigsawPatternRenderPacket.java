package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.client.gui.screen.DanmakuCraftingScreen;
import github.thelawf.gensokyoontology.common.network.PacketHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SJigsawPatternRenderPacket {
    private final List<Block> blocks;

    public SJigsawPatternRenderPacket(List<Block> blocks) {
        this.blocks = blocks;
    }

    public static SJigsawPatternRenderPacket fromBytes(PacketBuffer buf) {
        List<Block> blocks = PacketHelper.readAsResourcesByNBT(buf, "jigsaw_pattern").stream().map(
                ForgeRegistries.BLOCKS::getValue).collect(Collectors.toList());

        // PacketHelper.readAsStringList(buf, ",").stream().map(ResourceLocation::new)
        //         .map(ForgeRegistries.BLOCKS::getValue).forEach(blocks::add);
        return new SJigsawPatternRenderPacket(blocks);
    }

    public void toBytes(PacketBuffer buf) {
        PacketHelper.writeAsResourcesByNBT(buf, this.blocks.stream().map(Block::getRegistryName).collect(Collectors.toList()),
                "jigsaw_pattern");
//        PacketHelper.writeAsString(buf, this.blocks.stream().map(ResourceLocation::toString)
//                .collect(Collectors.toList()), ",");
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            if(minecraft.currentScreen instanceof DanmakuCraftingScreen) {
                DanmakuCraftingScreen screen = (DanmakuCraftingScreen)minecraft.currentScreen;
                screen.setRenderedBlocks(this.blocks);
            }
        });
        ctx.get().setPacketHandled(true);
    }

}