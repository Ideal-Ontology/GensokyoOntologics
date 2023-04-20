package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.item.InyoJade;
import github.thelawf.gensokyoontology.common.item.OccultBall;
import github.thelawf.gensokyoontology.common.item.food.Butter;
import github.thelawf.gensokyoontology.common.item.ByteCoin;
import github.thelawf.gensokyoontology.common.item.GameCoin;
import github.thelawf.gensokyoontology.common.item.spellcard.SC_WaveAndParticle;
import github.thelawf.gensokyoontology.common.item.food.*;
import github.thelawf.gensokyoontology.common.item.tools.*;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraft.item.Items.BUCKET;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GensokyoOntology.MODID);
    public static final RegistryObject<Item> HOTSPRING_BUCKET =
            ITEMS.register("hotspring_bucket",() -> new BucketItem(
                    FluidRegistry.HOT_SPRING_SOURCE, new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)
                    .maxStackSize(16).containerItem(BUCKET)));
    public static final RegistryObject<Item> SPIRIT_DIALECTICS =
            ITEMS.register("dialectic_spirit", () -> new Item(
                    new Item.Properties()
                            .group(GSKOItemTab.GSKO_ITEM_TAB)
                            .maxStackSize(16)));
    public static final RegistryObject<Item> SPIRIT_UTOPIAN =
            ITEMS.register("utopian_spirit", () -> new Item(
                    new Item.Properties()
                            .group(GSKOItemTab.GSKO_ITEM_TAB)
                            .maxStackSize(16)));
    public static final RegistryObject<Item> SPIRIT_THEOLOGY =
            ITEMS.register("theology_spirit", () -> new Item(
                    new Item.Properties()
                            .group(GSKOItemTab.GSKO_ITEM_TAB)
                            .maxStackSize(16)));

    public static final RegistryObject<Item> SPIRIT_CREATIVE =
            ITEMS.register("creative_spirit",() -> new Item(
                    new Item.Properties()
                            .group(GSKOItemTab.GSKO_ITEM_TAB)
                            .maxStackSize(16)));

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
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<OccultBall> MYSTERY_SPHERE =
            ITEMS.register("occult_ball", () -> new OccultBall(
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<YattsumeUna> YATTSUME_UNA =
            ITEMS.register("yattsume_una",() -> new YattsumeUna(
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

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

    public static final RegistryObject<Item> BYTE_COIN = ITEMS.register("bytecoin",
            () -> new ByteCoin(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> GAME_COIN = ITEMS.register("game_coin",
            () -> new GameCoin(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<DanmakuTestItem> DANMAKU_TEST_ITEM = ITEMS.register(
            "danmaku_test", () -> new DanmakuTestItem(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAb)));

    public static final RegistryObject<SC_WaveAndParticle> SC_WAVE_AND_PARTICLE = ITEMS.register(
            "sc_wave_and_particle", () -> new SC_WaveAndParticle(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAb),
                    "波与粒的境界","",500));

    public static final RegistryObject<BlockItem> WASABI_ITEM = ITEMS.register(
            "wasabi", () -> new BlockItem(BlockRegistry.WASABI_BLOCK.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> MAGIC_LOG_ITEM = ITEMS.register(
            "magic_leaves", () -> new BlockItem(BlockRegistry.MAGIC_LOG.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> SAKURA_LEAVES_ITEM = ITEMS.register(
            "sakura_leaves", () -> new BlockItem(BlockRegistry.SAKURA_LEAVES.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> SAKURA_LOG_ITEM = ITEMS.register(
            "sakura_log", () -> new BlockItem(BlockRegistry.SAKURA_LOG.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> RAIL_NODE = ITEMS.register(
            "rail_node", () -> new BlockItem(BlockRegistry.RAIL_NODE_BLOCK.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> DAKIMAKURA_ITEM = ITEMS.register(
            "dakimakura", () -> new BlockItem(BlockRegistry.DAKIMAKURA.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> ROTATE_FRAME_ITEM = ITEMS.register(
            "rotate_frame", () -> new BlockItem(BlockRegistry.ROTATE_FRAME.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> INYO_JADE_BLACK = ITEMS.register(
            "inyo_jade_black", () -> new InyoJade(DyeColor.BLACK,
                    new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAb)));

    public static final RegistryObject<Item> INYO_JADE_RED = ITEMS.register(
            "inyo_jade_red", () -> new InyoJade(DyeColor.RED,
                    new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAb)));

    public static final RegistryObject<Item> INYO_JADE_YELLOW = ITEMS.register(
            "inyo_jade_yellow", () -> new InyoJade(DyeColor.YELLOW,
                    new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAb)));

    public static final RegistryObject<Item> INYO_JADE_GREEN = ITEMS.register(
            "inyo_jade_green", () -> new InyoJade(DyeColor.GREEN,
                    new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAb)));

    public static final RegistryObject<Item> INYO_JADE_AQUA = ITEMS.register(
            "inyo_jade_aqua", () -> new InyoJade(DyeColor.LIGHT_BLUE,
                    new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAb)));

    public static final RegistryObject<Item> INYO_JADE_BLUE = ITEMS.register(
            "inyo_jade_blue", () -> new InyoJade(DyeColor.BLUE,
                    new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAb)));

    public static final RegistryObject<Item> INYO_JADE_PURPLE = ITEMS.register(
            "inyo_jade_purple", () -> new InyoJade(DyeColor.PURPLE,
                    new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAb)));


    //======================= ↓ The Mod Cyber Statistics ↓ ==================//
    // public static final RegistryObject<Item> MAGIC_LEAVES_ITEM = ITEMS.register(
    //         "magic_leaves",() -> new BlockItem(BlockRegistry.MAGIC_LEAVES.get(),
    //                 new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> HASH_LEAVES_ITEM = ITEMS.register(
            "hash_leaves", () -> new BlockItem(BlockRegistry.HASH_LEAVES.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> HASH_LOG_ITEM = ITEMS.register(
            "hash_log",() -> new BlockItem(BlockRegistry.HASH_LOG.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> FRACTAL_LOG_ITEM = ITEMS.register(
            "fractal_log",() -> new BlockItem(BlockRegistry.FRACTAL_LOG.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    //======================= ↑ The Mod Cyber Statistics ↑ ====================//

}
