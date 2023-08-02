package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.item.*;
import github.thelawf.gensokyoontology.common.item.danmaku.*;
import github.thelawf.gensokyoontology.common.item.food.Butter;
import github.thelawf.gensokyoontology.common.item.spellcard.*;
import github.thelawf.gensokyoontology.common.item.food.*;
import github.thelawf.gensokyoontology.common.item.tools.*;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraft.item.Items.BUCKET;

public final class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GensokyoOntology.MODID);

    // ======================= GSKO杂项：装饰类方块 ==========================//
    // ---------------------------- 树木类方块：------------------------------//
    //////////////////////////////// 樱花木 ////////////////////////////////
    public static final RegistryObject<BlockItem> SAKURA_SAPLING_ITEM = ITEMS.register(
            "sakura_sapling", () -> new BlockItem(BlockRegistry.SAKURA_SAPLING.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> SAKURA_LEAVES_ITEM = ITEMS.register(
            "sakura_leaves", () -> new BlockItem(BlockRegistry.SAKURA_LEAVES.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> SAKURA_LOG_ITEM = ITEMS.register(
            "sakura_log", () -> new BlockItem(BlockRegistry.SAKURA_LOG.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> SAKURA_PLANKS_ITEM = ITEMS.register(
            "sakura_planks", () -> new BlockItem(BlockRegistry.SAKURA_PLANKS.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> SAKURA_BUTTON_ITEM = ITEMS.register(
            "sakura_button", () -> new BlockItem(BlockRegistry.SAKURA_BUTTON.get(),
                    new Item.Properties().group(ItemGroup.REDSTONE)));

    public static final RegistryObject<BlockItem> SAKURA_SLAB_ITEM = ITEMS.register(
            "sakura_slab", () -> new BlockItem(BlockRegistry.SAKURA_SLAB.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> SAKURA_STAIRS_ITEM = ITEMS.register(
            "sakura_stairs", () -> new BlockItem(BlockRegistry.SAKURA_STAIRS.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> SAKURA_DOOR_ITEM = ITEMS.register(
            "sakura_door", () -> new BlockItem(BlockRegistry.SAKURA_DOOR.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> SAKURA_FENCE_ITEM = ITEMS.register(
            "sakura_fence", () -> new BlockItem(BlockRegistry.SAKURA_FENCE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> SAKURA_FENCE_GATE_ITEM = ITEMS.register(
            "sakura_fence_gate", () -> new BlockItem(BlockRegistry.SAKURA_FENCE_GATE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> SAKURA_TRAPDOOR_ITEM = ITEMS.register(
            "sakura_trapdoor", () -> new BlockItem(BlockRegistry.SAKURA_TRAPDOOR.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> SAKURA_PRESSRUE_PLATE_ITEM = ITEMS.register(
            "sakura_pressure_plate", () -> new BlockItem(BlockRegistry.SAKURA_PRESSRUE_PLATE.get(),
                    new Item.Properties().group(ItemGroup.REDSTONE)));

    //////////////////////////////// 榉树木 ////////////////////////////////
    // public static final RegistryObject<BlockItem> FAGUS_SAPLING_ITEM = ITEMS.register(
    //         "fagus_sapling.json", () -> new BlockItem(BlockRegistry.FAGUS_SAPLING.get(),
    //                 new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> FAGUS_LEAVES_ITEM = ITEMS.register(
            "fagus_leaves", () -> new BlockItem(BlockRegistry.FAGUS_LEAVES.get(),
                    new Item.Properties()));

    public static final RegistryObject<BlockItem> FAGUS_LOG_ITEM = ITEMS.register(
            "fagus_log", () -> new BlockItem(BlockRegistry.FAGUS_LOG.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> FAGUS_PLANKS_ITEM = ITEMS.register(
            "fagus_planks", () -> new BlockItem(BlockRegistry.FAGUS_PLANKS.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> FAGUS_BUTTON_ITEM = ITEMS.register(
            "fagus_button", () -> new BlockItem(BlockRegistry.FAGUS_BUTTON.get(),
                    new Item.Properties().group(ItemGroup.REDSTONE)));

    public static final RegistryObject<BlockItem> FAGUS_SLAB_ITEM = ITEMS.register(
            "fagus_slab", () -> new BlockItem(BlockRegistry.FAGUS_SLAB.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> FAGUS_STAIRS_ITEM = ITEMS.register(
            "fagus_stairs", () -> new BlockItem(BlockRegistry.FAGUS_STAIRS.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> FAGUS_DOOR_ITEM = ITEMS.register(
            "fagus_door", () -> new BlockItem(BlockRegistry.FAGUS_DOOR.get(),
                    new Item.Properties()));

    public static final RegistryObject<BlockItem> FAGUS_FENCE_ITEM = ITEMS.register(
            "fagus_fence", () -> new BlockItem(BlockRegistry.FAGUS_FENCE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> FAGUS_FENCE_GATE_ITEM = ITEMS.register(
            "fagus_fence_gate", () -> new BlockItem(BlockRegistry.FAGUS_FENCE_GATE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> FAGUS_TRAPDOOR_ITEM = ITEMS.register(
            "fagus_trapdoor", () -> new BlockItem(BlockRegistry.FAGUS_TRAPDOOR.get(),
                    new Item.Properties()));

    public static final RegistryObject<BlockItem> FAGUS_PRESSRUE_PLATE_ITEM = ITEMS.register(
            "fagus_pressure_plate", () -> new BlockItem(BlockRegistry.FAGUS_PRESSRUE_PLATE.get(),
                    new Item.Properties().group(ItemGroup.REDSTONE)));

    //////////////////////////////// 枫木 ////////////////////////////////
    public static final RegistryObject<BlockItem> MAPLE_SAPLING_ITEM = ITEMS.register(
            "maple_sapling", () -> new BlockItem(BlockRegistry.MAPLE_SAPLING.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> MAPLE_LEAVES_ITEM = ITEMS.register(
            "maple_leaves", () -> new BlockItem(BlockRegistry.MAPLE_LEAVES.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> MAPLE_LOG_ITEM = ITEMS.register(
            "maple_log", () -> new BlockItem(BlockRegistry.MAPLE_LOG.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> MAPLE_PLANKS_ITEM = ITEMS.register(
            "maple_planks", () -> new BlockItem(BlockRegistry.MAPLE_PLANKS.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> MAPLE_BUTTON_ITEM = ITEMS.register(
            "maple_button", () -> new BlockItem(BlockRegistry.MAPLE_BUTTON.get(),
                    new Item.Properties().group(ItemGroup.REDSTONE)));

    public static final RegistryObject<BlockItem> MAPLE_SLAB_ITEM = ITEMS.register(
            "maple_slab", () -> new BlockItem(BlockRegistry.MAPLE_SLAB.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> MAPLE_STAIRS_ITEM = ITEMS.register(
            "maple_stairs", () -> new BlockItem(BlockRegistry.MAPLE_STAIRS.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> MAPLE_DOOR_ITEM = ITEMS.register(
            "maple_door", () -> new BlockItem(BlockRegistry.MAPLE_DOOR.get(),
                    new Item.Properties()));

    public static final RegistryObject<BlockItem> MAPLE_FENCE_ITEM = ITEMS.register(
            "maple_fence", () -> new BlockItem(BlockRegistry.MAPLE_FENCE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> MAPLE_FENCE_GATE_ITEM = ITEMS.register(
            "maple_fence_gate", () -> new BlockItem(BlockRegistry.MAPLE_FENCE_GATE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> MAPLE_TRAPDOOR_ITEM = ITEMS.register(
            "maple_trapdoor", () -> new BlockItem(BlockRegistry.MAPLE_TRAPDOOR.get(),
                    new Item.Properties()));

    public static final RegistryObject<BlockItem> MAPLE_PRESSRUE_PLATE_ITEM = ITEMS.register(
            "maple_pressure_plate", () -> new BlockItem(BlockRegistry.MAPLE_PRESSURE_PLATE.get(),
                    new Item.Properties().group(ItemGroup.REDSTONE)));

    public static final RegistryObject<BlockItem> MAGIC_LEAVES_ITEM = ITEMS.register(
            "magic_leaves", () -> new BlockItem(BlockRegistry.MAGIC_LEAVES.get(),
                    new Item.Properties()));
    public static final RegistryObject<BlockItem> MAGIC_LOG_ITEM = ITEMS.register(
            "magic_log", () -> new BlockItem(BlockRegistry.MAGIC_LOG.get(),
                    new Item.Properties()));

    // --------------------------- 草本植物类方块：----------------------------//
    public static final RegistryObject<Item> BLUE_ROSE_ITEM = ITEMS.register(
            "blue_rose_bush", () -> new BlockItem(BlockRegistry.BLUE_ROSE_BUSH.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<Item> LYCORIS_RADIATA =
            ITEMS.register("lycoris_radiata",() -> new BlockItem(
                    BlockRegistry.LYCORIS_RADIATA.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> WASABI = ITEMS.register(
            "wasabi", () -> new BlockItem(BlockRegistry.WASABI_BLOCK.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    // ------------------------------ 蘑菇方块 --------------------------------//
    public static final RegistryObject<Item> BLUE_MUSHROOM_ITEM = ITEMS.register(
            "blue_mushroom_block", () -> new BlockItem(BlockRegistry.BLUE_MUSHROOM_BLOCK.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<Item> PURPLE_MUSHROOM_ITEM = ITEMS.register(
            "purple_mushroom_block", () -> new BlockItem(BlockRegistry.PURPLE_MUSHROOM_BLOCK.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    // ======================= GSKO杂项：功能性方块 =========================//

    // ======================= GSKO杂项：道具类物品 =========================//
    // ----------------------- 东方project特殊功能道具 ----------------------//
    public static final RegistryObject<HakureiGohei> HAKUREI_GOHEI = ITEMS.register(
            "hakurei_gohei", () -> new HakureiGohei(
                    new Item.Properties()));
    public static final RegistryObject<MarisaHakkeiro> MARISA_HAKKEIRO = ITEMS.register(
            "marisa_hakkeiro", () -> new MarisaHakkeiro(
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB).maxStackSize(1)));
    public static final RegistryObject<SakuyaStopWatch> SAKUYA_WATCH = ITEMS.register(
            "sakuya_stop_watch", () -> new SakuyaStopWatch(
                    new Item.Properties()));
    public static final RegistryObject<AyaFans> AYA_FANS = ITEMS.register("aya_fans",
            () -> new AyaFans(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<KoishiEye> KOISHI_EYE = ITEMS.register(
            "koishi_eye", () -> new KoishiEye(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<ArmorItem> KOISHI_HAT = ITEMS.register(
            "koishi_hat", () -> new ArmorItem(GSKOArmorMaterial.EMPATHY,
                    EquipmentSlotType.HEAD, (new Item.Properties())));
    public static final RegistryObject<SpiritTube> SPIRIT_TUBE = ITEMS.register(
            "spirit_tube", () -> new SpiritTube(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<KudaGitsuneTube> KUDA_GITSUNE_TUBE =ITEMS.register(
            "kuda_gitsune_tube", () -> new KudaGitsuneTube(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<Item> GITSUNE_TUBE_FULL = ITEMS.register(
            "gitsune_tube_full", () -> new KudaGitsuneTube(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB).containerItem(ItemRegistry.SPIRIT_TUBE.get())));

    public static final RegistryObject<OccultBall> OCCULT_BALL = ITEMS.register(
            "occult_ball", () -> new OccultBall(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<ChimataMarketLicense> CHIMATA_MARKET_LICENSE = ITEMS.register(
            "chimata_market_license", () -> new ChimataMarketLicense(new Item.Properties()));

    // ---------------------------- 杂项物品 -------------------------------//
    public static final RegistryObject<Item> HOTSPRING_BUCKET = ITEMS.register("hotspring_bucket",
            () -> new BucketItem(FluidRegistry.HOT_SPRING_SOURCE, new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)
                    .maxStackSize(1).containerItem(BUCKET)));

    public static final RegistryObject<Item> SAKE_BUCKET = ITEMS.register("sake_bucket",
            () -> new BucketItem(FluidRegistry.SAKE_WINE_SOURCE, new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)
                    .maxStackSize(1).containerItem(BUCKET)));


    // ======================= GSKO杂项：合成消耗品 =========================//

    // public static final RegistryObject<Item> ISHI_ZAKURA = ITEMS.register("ishi_zakura",
    //         () -> new IshiZakuraBlock(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> WANDERING_SOUL = ITEMS.register(
            "wandering_soul", () -> new WanderingSoul(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> BOMB_FRAGMENT = ITEMS.register(
            "bomb_fragment", () -> new BombFragment(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> LIFE_FRAGMENT = ITEMS.register(
            "life_fragment", () -> new LifeFragment(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)));

    // ---------------------------- 食物原材料 -----------------------------//
    public static final RegistryObject<Item> KITCHEN_KNIFE = ITEMS.register(
            "kitchen_knife", KitchenKnife::new);
    public static final RegistryObject<Butter> BUTTER = ITEMS.register("butter",
            () -> new Butter(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB) ));
    public static final RegistryObject<MilkBottle> MILK_BOTTLE = ITEMS.register(
            "milk_bottle", MilkBottle::new);
    public static final RegistryObject<SquidTentacle> SQUID_TENTACLE = ITEMS.register(
            "squid_tentacle", SquidTentacle::new);
    public static final RegistryObject<Item> ONION = ITEMS.register("onion", () ->
            new BlockItem(BlockRegistry.ONION_CROP_BLOCK.get(),new Item.Properties().group(
                    GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<YattsumeUna> YATTSUME_UNA =
            ITEMS.register("yattsume_una",() -> new YattsumeUna(
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    // ------------------------------- 食物 -------------------------------//
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

    // ---------------------------- 意识形态 ------------------------------//
    // public static final RegistryObject<SpiritStar> SPIRIT_STAR = ITEMS.register(
    //         "spirit_star", () -> new SpiritStar(new Item.Properties()
    //                 .group(GSKOItemTab.GSKO_ITEM_TAB)
    //                 .maxStackSize(16)));
//
    // public static final RegistryObject<SpiritFragment> SPIRIT_FRAGMENT = ITEMS.register(
    //         "spirit_fragment", () -> new SpiritFragment(new Item.Properties()
    //                 .group(GSKOItemTab.GSKO_ITEM_TAB)
    //                 .maxStackSize(16)));
    // public static final RegistryObject<Item> SPIRIT_DIALECTICS = ITEMS.register(
    //         "dialectic_spirit", () -> new Item(new Item.Properties()
    //                 .group(GSKOItemTab.GSKO_ITEM_TAB)
    //                 .maxStackSize(16)));
    // public static final RegistryObject<Item> SPIRIT_UTOPIAN = ITEMS.register(
    //         "utopian_spirit", () -> new Item(new Item.Properties()
    //                 .group(GSKOItemTab.GSKO_ITEM_TAB)
    //                 .maxStackSize(16)));
    // public static final RegistryObject<Item> SPIRIT_THEOLOGY = ITEMS.register(
    //         "theology_spirit", () -> new Item(new Item.Properties()
    //                 .group(GSKOItemTab.GSKO_ITEM_TAB)
    //                 .maxStackSize(16)));
//
    // public static final RegistryObject<Item> SPIRIT_CREATIVE = ITEMS.register(
    //         "creative_spirit",() -> new Item(new Item.Properties()
    //                 .group(GSKOItemTab.GSKO_ITEM_TAB)
    //                 .maxStackSize(16)));
//
    // --------------------------- 故事书页 ------------------------------//
    public static final RegistryObject<Item> MEMORY_CARD = ITEMS.register(
            "story_card", () -> new StoryCard(new CompoundNBT()));


    // Technical Items that will break the game balance: //
    // public static final RegistryObject<Item> REALISM_SWORD = ITEMS.register(
    //         "realism_sword", RealismSword::new);
    // public static final RegistryObject<Item> METAPHYSICS_SWORD = ITEMS.register(
    //         "metaphysics_sword", MetaphysicsSword::new);
    // public static final RegistryObject<Item> IDEALISM_SWORD = ITEMS.register(
    //         "idealism_sword", IdealismSword::new);
    // public static final RegistryObject<Item> PRAXIS_SWORD = ITEMS.register(
    //         "praxis_sword", PraxisSword::new);

    // public static final RegistryObject<Item> BYTE_COIN = ITEMS.register("bytecoin",
    //         () -> new ByteCoin(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
//
    // public static final RegistryObject<Item> GAME_COIN = ITEMS.register("game_coin",
    //         () -> new GameCoin(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    // ======================== GSKO 实用类方块 ===========================//
    public static final RegistryObject<BlockItem> DRAGON_SPHERE_ORE_ITEM = ITEMS.register(
            "dragon_sphere_ore", () -> new BlockItem(BlockRegistry.DRAGON_SPHERE_ORE.get(),
                    new Item.Properties()));

    // ======================== GSKO战斗类物品 ============================//
    // ----------------------------- 符卡 --------------------------------//
    public static final RegistryObject<SpellCardBlank> SPELL_CARD_BLANK = ITEMS.register(
            "spell_card_blank", () -> new SpellCardBlank(new Item.Properties()
                    .group(GSKOCombatTab.GSKO_COMBAT_TAB)));

    public static final RegistryObject<SC_WaveAndParticle> SC_WAVE_AND_PARTICLE = ITEMS.register(
            "sc_wave_and_particle", () -> new SC_WaveAndParticle(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB),
                    "境符「波与粒的境界」","",500));

    public static final RegistryObject<SC_IdoNoKaiho> SC_IDO_NO_KAIHO = ITEMS.register(
            "sc_ido_no_kaiho", () -> new SC_IdoNoKaiho(new Item.Properties(),
                    "本能「本我的解放」","", 600));

    public static final RegistryObject<SC_CircleCross> SC_CIRCLE_CROSS = ITEMS.register(
            "sc_circle_cross", () -> new SC_CircleCross(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB),
                    "圆形交叉弹", "", 500));

    public static final RegistryObject<SC_HellEclipse> SC_HELL_ECLIPSE = ITEMS.register(
            "sc_hell_eclipse", () -> new SC_HellEclipse(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB),
                    "狱符「地狱月食」", "", 500));

    public static final RegistryObject<SC_MountainOfFaith> SC_MOUNTAIN_OF_FAITH = ITEMS.register(
            "sc_mountain_of_faith", () -> new SC_MountainOfFaith(new Item.Properties(),
                    "信仰之山", "", 500));


    // --------------------- 投掷物：弹幕 阴阳玉 灵符 -----------------------//

    public static final RegistryObject<DanmakuShotItem> DANMAKU_SHOT = ITEMS.register("danmaku_shot",
            () -> new DanmakuShotItem(DanmakuType.DANMAKU_SHOT));

    /////////////////////////// 所有颜色的大弹 ////////////////////////////////
    public static final RegistryObject<Item> LARGE_SHOT_RED = ITEMS.register(
            "large_shot_red", () -> new LargeShot(DanmakuType.LARGE_SHOT));
    public static final RegistryObject<Item> LARGE_SHOT_YELLOW = ITEMS.register(
            "large_shot_yellow", () -> new LargeShot(DanmakuType.LARGE_SHOT));
    public static final RegistryObject<Item> LARGE_SHOT_GREEN = ITEMS.register(
            "large_shot_green", () -> new LargeShot(DanmakuType.LARGE_SHOT));
    public static final RegistryObject<Item> LARGE_SHOT_BLUE = ITEMS.register(
            "large_shot_blue", () -> new LargeShot(DanmakuType.LARGE_SHOT));
    public static final RegistryObject<Item> LARGE_SHOT_PURPLE = ITEMS.register(
            "large_shot_purple", () -> new LargeShot(DanmakuType.LARGE_SHOT));

    /////////////////////////// 所有颜色的小弹 ////////////////////////////////
    public static final RegistryObject<Item> SMALL_SHOT_RED = ITEMS.register(
            "small_shot_red", () -> new SmallShot(DanmakuType.SMALL_SHOT));
    public static final RegistryObject<Item> SMALL_SHOT_YELLOW = ITEMS.register(
            "small_shot_yellow", () -> new SmallShot(DanmakuType.SMALL_SHOT));
    public static final RegistryObject<Item> SMALL_SHOT_GREEN = ITEMS.register(
            "small_shot_green", () -> new SmallShot(DanmakuType.SMALL_SHOT));
    public static final RegistryObject<Item> SMALL_SHOT_BLUE = ITEMS.register(
            "small_shot_blue", () -> new SmallShot(DanmakuType.SMALL_SHOT));
    public static final RegistryObject<Item> SMALL_SHOT_PURPLE = ITEMS.register(
            "small_shot_purple", () -> new SmallShot(DanmakuType.SMALL_SHOT));

    ////////////////////////////// 所有颜色的心弹  /////////////////////////////////
    public static final RegistryObject<Item> HEART_SHOT_PINK = ITEMS.register(
            "heart_shot_pink", () -> new HeartShot(DanmakuType.HEART_SHOT));
    public static final RegistryObject<Item> HEART_SHOT_RED = ITEMS.register(
            "heart_shot_red", () -> new HeartShot(DanmakuType.HEART_SHOT));
    public static final RegistryObject<Item> HEART_SHOT_AQUA = ITEMS.register(
            "heart_shot_aqua", () -> new HeartShot(DanmakuType.HEART_SHOT));
    public static final RegistryObject<Item> HEART_SHOT_BLUE = ITEMS.register(
            "heart_shot_blue", () -> new HeartShot(DanmakuType.HEART_SHOT));

    ////////////////////////////// 所有颜色的星弹  /////////////////////////////////
    public static final RegistryObject<Item> STAR_SHOT_RED = ITEMS.register(
            "star_shot_red", () -> new StarShot(DanmakuType.STAR_SHOT_LARGE));
    public static final RegistryObject<Item> STAR_SHOT_YELLOW = ITEMS.register(
            "star_shot_yellow", () -> new StarShot(DanmakuType.STAR_SHOT_LARGE));
    public static final RegistryObject<Item> STAR_SHOT_GREEN = ITEMS.register(
            "star_shot_green", () -> new StarShot(DanmakuType.STAR_SHOT_LARGE));
    public static final RegistryObject<Item> STAR_SHOT_AQUA = ITEMS.register(
            "star_shot_aqua", () -> new StarShot(DanmakuType.STAR_SHOT_LARGE));
    public static final RegistryObject<Item> STAR_SHOT_BLUE = ITEMS.register(
            "star_shot_blue", () -> new StarShot(DanmakuType.STAR_SHOT_SMALL));
    public static final RegistryObject<Item> STAR_SHOT_PURPLE = ITEMS.register(
            "star_shot_purple", () -> new StarShot(DanmakuType.STAR_SHOT_LARGE));

    ////////////////////////////// 所有颜色的阴阳玉 ///////////////////////////////////
    public static final RegistryObject<Item> INYO_JADE_BLACK = ITEMS.register(
            "inyo_jade_black", () -> new InyoJade(DyeColor.BLACK,
                    new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> INYO_JADE_RED = ITEMS.register(
            "inyo_jade_red", () -> new InyoJade(DyeColor.RED,
                    new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> INYO_JADE_YELLOW = ITEMS.register(
            "inyo_jade_yellow", () -> new InyoJade(DyeColor.YELLOW,
                    new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> INYO_JADE_GREEN = ITEMS.register(
            "inyo_jade_green", () -> new InyoJade(DyeColor.GREEN,
                    new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> INYO_JADE_AQUA = ITEMS.register(
            "inyo_jade_aqua", () -> new InyoJade(DyeColor.LIGHT_BLUE,
                    new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> INYO_JADE_BLUE = ITEMS.register(
            "inyo_jade_blue", () -> new InyoJade(DyeColor.BLUE,
                    new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> INYO_JADE_PURPLE = ITEMS.register(
            "inyo_jade_purple", () -> new InyoJade(DyeColor.PURPLE,
                    new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));

    public static final RegistryObject<Item> FAKE_LUNAR_ITEM = ITEMS.register(
            "fake_lunar", () -> new FakeLunarItem(DanmakuType.FAKE_LUNAR));

    public static final RegistryObject<Item> BOMB_ITEM = ITEMS.register(
            "bomb_item", () -> new BombItem(
                    new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));

    public static final RegistryObject<Item> EXTRA_LIFE_ITEM = ITEMS.register(
            "extra_life", () -> new ExtraLifeItem(
                    new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));

    // +++++++++++++++++++++++++++ Deprecated Items +++++++++++++++++++++++//
    // public static final RegistryObject<Item> RAIL_NODE = ITEMS.register(
    //         "rail_node", () -> new BlockItem(BlockRegistry.RAIL_NODE_BLOCK.get(),
    //                 new Item.Properties()));
//
    // public static final RegistryObject<Item> DAKIMAKURA_ITEM = ITEMS.register(
    //         "dakimakura", () -> new BlockItem(BlockRegistry.DAKIMAKURA.get(),
    //                 new Item.Properties()));
//
    // public static final RegistryObject<Item> ROTATE_FRAME_ITEM = ITEMS.register(
    //         "rotate_frame", () -> new BlockItem(BlockRegistry.ROTATE_FRAME.get(),
    //                 new Item.Properties()));
//
//
    //======================= ↓ The Mod Cyber Statistics ↓ ====================//

    // public static final RegistryObject<Item> HASH_LEAVES_ITEM = ITEMS.register(
    //         "hash_leaves", () -> new BlockItem(BlockRegistry.HASH_LEAVES.get(),
    //                 new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
//
    // public static final RegistryObject<Item> HASH_LOG_ITEM = ITEMS.register(
    //         "hash_log",() -> new BlockItem(BlockRegistry.HASH_LOG.get(),
    //                 new Item.Properties()));
//
    // public static final RegistryObject<Item> FRACTAL_LOG_ITEM = ITEMS.register(
    //         "fractal_log",() -> new BlockItem(BlockRegistry.FRACTAL_LOG.get(),
    //                 new Item.Properties()));
//
    //======================= ↑ The Mod Cyber Statistics ↑ ====================//

}
