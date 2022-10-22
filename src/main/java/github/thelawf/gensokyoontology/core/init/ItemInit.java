package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.item.Butter;
import github.thelawf.gensokyoontology.common.item.Onion;
import github.thelawf.gensokyoontology.common.item.food.MilkBottle;
import github.thelawf.gensokyoontology.common.item.YattsumeUna;
import github.thelawf.gensokyoontology.common.item.food.*;
import github.thelawf.gensokyoontology.common.item.tools.*;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.stats.IStatFormatter;
import net.minecraft.world.Dimension;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraft.item.Items.BUCKET;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GensokyoOntology.MODID);
    public static final RegistryObject<Item> HOTSPRING_BUCKET =
            ITEMS.register("hotspring_bucket",() -> new BucketItem(
                    FluidRegistry.HOT_SPRING_SOURCE, new Item.Properties().group(
                            GSKOItemTab.GSKO_ITEM_TAB).maxStackSize(16).containerItem(BUCKET)));
    public static final RegistryObject<Item> SPIRIT_DIALECTICS =
            ITEMS.register("dialectic_spirit", () -> new Item(
                    new Item.Properties()
                            .group(GSKOItemTab.GSKO_ITEM_TAB)
                            .maxStackSize(16)
            ));
    public static final RegistryObject<Item> SPIRIT_UTOPIAN =
            ITEMS.register("utopian_spirit", () -> new Item(
                    new Item.Properties()
                            .group(GSKOItemTab.GSKO_ITEM_TAB)
                            .maxStackSize(16)
            ));
    public static final RegistryObject<Item> SPIRIT_THEOLOGY =
            ITEMS.register("theology_spirit", () -> new Item(
                    new Item.Properties()
                            .group(GSKOItemTab.GSKO_ITEM_TAB)
                            .maxStackSize(16)
            ));

    public static final RegistryObject<Item> SPIRIT_CREATIVE =
            ITEMS.register("creative_spirit",() -> new Item(
                    new Item.Properties()
                            .group(GSKOItemTab.GSKO_ITEM_TAB)
                            .maxStackSize(16)
            ));

    public static final RegistryObject<Item> REALISM_SWORD =
            ITEMS.register("realism_sword", RealismSword::new);

    public static final RegistryObject<Item> METAPHYSICS_SWORD = ITEMS.register(
            "metaphysics_sword", MetaphysicsSword::new);

    public static final RegistryObject<Item> IDEALISM_SWORD = ITEMS.register(
            "idealism_sword", IdealismSword::new);

    public static final RegistryObject<Item> PRAXIS_SWORD =
            ITEMS.register("praxis_sword", PraxisSword::new);

    public static final RegistryObject<Item> KITCHEN_KNIFE =
            ITEMS.register("kitchen_knife", KitchenKnife::new);

    public static final RegistryObject<Item> LYCORIS_RADIATA =
            ITEMS.register("lycoris_radiata",() -> new BlockItem(
                    BlockRegistry.LYCORIS_RADIATA.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)
            ));
    public static final RegistryObject<YattsumeUna> YATTSUME_UNA =
            ITEMS.register("yattsume_una",() -> new YattsumeUna(
                    new Item.Properties()
                            .group(GSKOItemTab.GSKO_ITEM_TAB)
            ));

    public static final RegistryObject<YattsumeUnaYaki> YATTSUME_UNA_YAKI =
            ITEMS.register("yattsume_una_yaki", YattsumeUnaYaki::new);

    public static final RegistryObject<KoishiHatMousse> KOISHI_HAT_MOUSSE =
            ITEMS.register("koishi_hat_mousse", KoishiHatMousse::new);

    public static final RegistryObject<CakeScarletDemon> CAKE_SCARLET_DEMON =
            ITEMS.register("cake_scarlet_demon", CakeScarletDemon::new);

    public static final RegistryObject<Lingoame> LINGOAME = ITEMS.register(
            "lingoame",Lingoame::new);

    public static final RegistryObject<TakoYaki> TAKO_YAKI = ITEMS.register(
            "tako_yaki", TakoYaki::new);

    public static final RegistryObject<WhiteSnow> WHITE_SNOW = ITEMS.register(
            "white_snow", WhiteSnow::new);

    public static final RegistryObject<BurgerMeatRaw> BURGER_MEAT_RAW =
            ITEMS.register("burger_meat_raw", BurgerMeatRaw::new);

    public static final RegistryObject<BugerMeat> BURGER_MEAT = ITEMS.register(
            "burger_meat", BugerMeat::new);

    public static final RegistryObject<Butter> BUTTER = ITEMS.register("butter",
            () -> new Butter(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB) ));

    public static final RegistryObject<MilkBottle> MILK_BOTTLE = ITEMS.register(
            "milk_bottle", MilkBottle::new);

    public static final RegistryObject<SquidTentacle> SQUID_TENTACLE = ITEMS.register(
            "squid_tentacle", SquidTentacle::new);

    public static final RegistryObject<Item> ONION = ITEMS.register("onion", () ->
            new BlockItem(BlockRegistry.ONION_CROP_BLOCK.get(),new Item.Properties().group(
                    GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> FRIED_PANE_ITEM = ITEMS.register(
            "fried_pane", () -> new BlockItem(BlockRegistry.FRIED_PANE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));


}
