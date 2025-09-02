package github.thelawf.gensokyoontology.common.util.client;

import github.thelawf.gensokyoontology.core.GSKOSoundEvents;
import net.minecraft.client.audio.BackgroundMusicSelector;

public class GSKOMusicSelector {
    public static final BackgroundMusicSelector MUSIC_CICADA_AMBIENT = new BackgroundMusicSelector(
            GSKOSoundEvents.CICADA_AMBIENT.get(), 8000, 12000, true);
    public static final BackgroundMusicSelector MUSIC_BAMBOO_PARTRIDGE = new BackgroundMusicSelector(
            GSKOSoundEvents.BAMBOO_PARTRIDGE.get(), 13000, 20000, true);
}
