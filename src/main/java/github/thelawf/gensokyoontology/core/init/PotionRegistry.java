package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionRegistry {
    public static final DeferredRegister<Potion> POTION_TYPES = DeferredRegister.create(
            ForgeRegistries.POTION_TYPES, GensokyoOntology.MODID);

    public static final RegistryObject<Potion> LOVE_POTION = POTION_TYPES.register("love_potion",
            () -> new Potion(new EffectInstance(EffectInit.LOVE_EFFECT.get())));

    public static final RegistryObject<Potion> HYPNOSIS_POTION = POTION_TYPES.register("hypnosis_potion",
            () -> new Potion(new EffectInstance(EffectInit.HYPNOSIS_EFFECT.get())));
}
