package github.thelawf.gensokyoontology.core;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

// 可以设置最短到最长的播放音效的时间间隔
public class GSKOSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(
            ForgeRegistries.SOUND_EVENTS, GensokyoOntology.MODID);

    public static final RegistryObject<SoundEvent> MUSIC_GENSOKYO = register("music_gensokyo");
    public static final RegistryObject<SoundEvent> CICADA_AMBIENT = register("cicada_ambient");
    public static final RegistryObject<SoundEvent> BAMBOO_PARTRIDGE = register("bamboo_partridge");
    public static final RegistryObject<SoundEvent> MASTER_SPARK = register("master_spark");
    public static final RegistryObject<SoundEvent> SHOOT_DANMAKU = register("shoot_danmaku");

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(
                GensokyoOntology.MODID, name)));
    }

    public static void registerSound(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
