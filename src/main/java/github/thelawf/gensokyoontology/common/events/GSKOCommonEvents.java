package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.capability.KoishiEmpathicDomainProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class GSKOCommonEvents {

    // @SubscribeEvent
    // public static void onCapabilityAttach(AttachCapabilitiesEvent<Entity> event) {
    //     Entity entity = event.getObject();
    //     if (entity instanceof PlayerEntity) {
    //         event.addCapability(new ResourceLocation(GensokyoOntology.MODID,
    //                 "koishi_domain"), new KoishiEmpathicDomainProvider());
    //     }
    // }
}
