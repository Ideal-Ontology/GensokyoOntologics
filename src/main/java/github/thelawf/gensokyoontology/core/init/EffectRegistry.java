package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.potion.HypnosisEffect;
import github.thelawf.gensokyoontology.common.potion.LovePotionEffect;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectRegistry {
    public static final DeferredRegister<Effect> POTION_EFFECTS = DeferredRegister.create(
            ForgeRegistries.POTIONS, GensokyoOntology.MODID);
    public static final RegistryObject<Effect> LOVE_EFFECT = POTION_EFFECTS.register("love_potion",
            LovePotionEffect::new);
    public static final RegistryObject<Effect> HYPNOSIS_EFFECT = POTION_EFFECTS.register("hypnosis_potion",
            HypnosisEffect::new);
}
