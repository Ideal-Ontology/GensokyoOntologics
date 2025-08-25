package github.thelawf.gensokyoontology.mixin;

import com.github.tartaricacid.touhoulittlemaid.capability.PowerCapability;
import github.thelawf.gensokyoontology.common.events.ValueChangedEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PowerCapability.class)
public class PowerCapabilityMixin {

    @Shadow
    private float power;

    @Inject(method = "set", at = @At("HEAD"), remap = false)
    public void onSet(float currentPower, CallbackInfo ci){
        MinecraftForge.EVENT_BUS.post(new ValueChangedEvent<>(this.power, currentPower));
    }
}
