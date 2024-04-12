package github.thelawf.gensokyoontology.client.event;

import com.google.common.collect.Lists;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.PerspectiveItemModel;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.renderer.entity.model.HorseArmorChestsModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID, value = Dist.CLIENT)
public class GSKOModelEvent {
    private static final List<ModelResourceLocation> MODELS = Lists.newArrayList();

    @SubscribeEvent
    public static void registerItemModel(RegistryEvent.Register<Item> event) {
        addItemModel(ItemRegistry.HAKUREI_GOHEI.get());
    }

    @SubscribeEvent
    public static void onModelBaked(ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> registryMap = event.getModelRegistry();
        for (ModelResourceLocation mrl : MODELS) {
            PerspectiveItemModel model = new PerspectiveItemModel(registryMap.get(mrl));
            registryMap.put(mrl, model);
        }
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        MODELS.forEach(ModelLoader::addSpecialModel);
    }

    public static void addItemModel(Item item) {
        ResourceLocation location = item.getRegistryName();
        if (location != null) {
            ModelResourceLocation modelName = ModelLoader.getInventoryVariant(location.toString());
            MODELS.add(modelName);
        }
    }

}
