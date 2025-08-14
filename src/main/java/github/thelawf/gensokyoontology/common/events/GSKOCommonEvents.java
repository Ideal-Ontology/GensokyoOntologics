package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.data.expression.IExpressionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryBuilder;

import static github.thelawf.gensokyoontology.core.init.Expressions.EXP_KEY;


@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GSKOCommonEvents {
    // @SubscribeEvent
    public static void onRegisterNewEntry(RegistryEvent.NewRegistry event){
        new RegistryBuilder<IExpressionType>().setName(EXP_KEY.getRegistryName()).setType(IExpressionType.class).setMaxID(256).create();
    }

}
