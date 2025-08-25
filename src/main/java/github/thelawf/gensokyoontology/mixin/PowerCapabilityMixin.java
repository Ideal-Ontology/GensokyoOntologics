package github.thelawf.gensokyoontology.mixin;

import com.github.tartaricacid.touhoulittlemaid.capability.PowerCapability;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.events.ValueChangedEvent;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.PowerChangedPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PowerCapability.class)
public class PowerCapabilityMixin {

    @Shadow
    private float power;

    @Inject(method = "add(F)", at = @At(value = "HEAD", remap = false))
    public void onAdd(float amount, CallbackInfo ci){
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        if(player == null) return;

        MinecraftForge.EVENT_BUS.post(new ValueChangedEvent<>(this.power, amount));
        player.getCapability(GSKOCapabilities.POWER).ifPresent(cap -> {
            cap.add(amount);
            GSKONetworking.CHANNEL.sendToServer(new PowerChangedPacket(amount));
        });
    }

    @Inject(method = "set", at = @At("HEAD"), remap = false)
    public void onSet(float currentPower, CallbackInfo ci){
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        if(player == null) return;

        MinecraftForge.EVENT_BUS.post(new ValueChangedEvent<>(this.power, currentPower));
        player.getCapability(GSKOCapabilities.POWER).ifPresent(cap -> {
            cap.setCount(currentPower);
            GSKONetworking.CHANNEL.sendToServer(new PowerChangedPacket(currentPower));
        });
    }
}
