package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.HumanrResidentModel;
import github.thelawf.gensokyoontology.common.entity.passive.HumanResidentEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class HumanVillagerRenderer extends LivingRenderer<HumanResidentEntity, EntityModel<HumanResidentEntity>> {
    public static final ResourceLocation MALE_TEX = GensokyoOntology.withRL("textures/entity/human_villager_male.png");
    public static final ResourceLocation FEMALE_TEX = GensokyoOntology.withRL("textures/entity/human_villager_female.png");
    public HumanVillagerRenderer(EntityRendererManager renderManager, HumanrResidentModel model) {
        super(renderManager, model, 0.8f);
    }

    @Override
    public @NotNull ResourceLocation getEntityTexture(HumanResidentEntity entity) {
        return entity.getGender() == HumanResidentEntity.Gender.MALE ? MALE_TEX : FEMALE_TEX;
    }

}
