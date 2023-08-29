package github.thelawf.gensokyoontology.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.ISkyRenderHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class MixinWorldRenderer {
    @Shadow
    private ClientWorld world;

    @Shadow @Final
    private static ResourceLocation MOON_PHASES_TEXTURES;

    // @SuppressWarnings("deprecation")
    // @Inject(method = "renderSky(Lcom/mojang/blaze3d/matrix/MatrixStack;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;getMoonPhase()I"))
    // private void changeMoonColor(MatrixStack matrixStackIn, float partialTicks, CallbackInfo ci) {
    //     Vector3f glColor = new Vector3f(200, 0, 0);
    //     RenderSystem.color4f(glColor.getX(), glColor.getY(), glColor.getZ(), 1.0F - this.world.getRainStrength(partialTicks));
    // }
}
