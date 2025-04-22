package github.thelawf.gensokyoontology.mixin;

import github.thelawf.gensokyoontology.common.world.layer.AddRiverLayer;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.LayerUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.LongFunction;

@Mixin(LayerUtil.class)
public class LayerUtilMixin {

    @Inject(
            method = "setupOverworldLayer",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/function/LongFunction;apply(J)Ljava/lang/Object;",
                    ordinal = 8))
    private static <T extends IArea, C extends IExtendedNoiseRandom<T>> void addFjordLayerAndSetupBiome(
            boolean p_237216_0_, int p_237216_1_, int p_237216_2_, LongFunction<C> p_237216_3_, CallbackInfoReturnable<IAreaFactory<T>> cir){

    }
}
