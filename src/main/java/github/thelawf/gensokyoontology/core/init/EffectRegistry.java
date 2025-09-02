package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.potion.*;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class EffectRegistry {
    public static final DeferredRegister<Effect> POTION_EFFECTS = DeferredRegister.create(
            ForgeRegistries.POTIONS, GensokyoOntology.MODID);
    public static final RegistryObject<Effect> LOVE_EFFECT = POTION_EFFECTS.register("love_potion",
            LovePotionEffect::new);
    public static final RegistryObject<Effect> HYPNOSIS_EFFECT = POTION_EFFECTS.register("hypnosis",
            HypnosisEffect::new);
    public static final RegistryObject<Effect> UNSELECTABLE = POTION_EFFECTS.register("unselectable",
            UnselectableEffect::new);
    public static final RegistryObject<Effect> IMPRISONED = POTION_EFFECTS.register("imprisoned",
            ImprisonedEffect::new);

    public static final RegistryObject<Effect> HAKUREI_BLESS_EFFECT = POTION_EFFECTS.register("hakurei_bless", HakureiBlessEffect::new);
}
