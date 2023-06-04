package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.item.*;
import github.thelawf.gensokyoontology.common.item.danmaku.DanmakuShotItem;
import github.thelawf.gensokyoontology.common.item.danmaku.HeartShot;
import github.thelawf.gensokyoontology.common.item.danmaku.LargeShot;
import github.thelawf.gensokyoontology.common.item.food.Butter;
import github.thelawf.gensokyoontology.common.item.spellcard.SC_WaveAndParticle;
import github.thelawf.gensokyoontology.common.item.food.*;
import github.thelawf.gensokyoontology.common.item.spellcard.SpellCardBlank;
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

    // --------------------------- 草本植物类方块：----------------------------//
    public static final RegistryObject<Item> LYCORIS_RADIATA =
            ITEMS.register("lycoris_radiata",() -> new BlockItem(
                    BlockRegistry.LYCORIS_RADIATA.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> WASABI = ITEMS.register(
            "wasabi", () -> new BlockItem(BlockRegistry.WASABI_BLOCK.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    // ======================= GSKO杂项：功能性方块 =========================//

    // ======================= GSKO杂项：道具类物品 =========================//
    // ----------------------- 东方project特殊功能道具 ----------------------//

    public static final RegistryObject<HakureiGohei> HAKUREI_GOHEI = ITEMS.register(
            "hakurei_gohei", () -> new HakureiGohei(
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<MarisaHakkeiro> MARISA_HAKKEIRO = ITEMS.register(
            "marisa_hakkeiro", () -> new MarisaHakkeiro(
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<SakuyaStopWatch> SAKUYA_WATCH = ITEMS.register(
            "sakuya_stop_watch", () -> new SakuyaStopWatch(
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<KoishiEye> KOISHI_EYE = ITEMS.register(
            "koishi_eye", () -> new KoishiEye(
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<ArmorItem> KOISHI_HAT = ITEMS.register(
            "koishi_hat", () -> new ArmorItem(GSKOArmorMaterial.EMPATHY,
                    EquipmentSlotType.HEAD, (new Item.Properties().group(
                    GSKOItemTab.GSKO_ITEM_TAB))));
    public static final RegistryObject<SpiritTube> SPIRIT_TUBE = ITEMS.register(
            "spirit_tube", () -> new SpiritTube(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<KudaGitsuneTube> KUDA_GITSUNE_TUBE =ITEMS.register(
            "kuda_gitsune_tube", () -> new KudaGitsuneTube(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<Item> GITSUNE_TUBE_FULL = ITEMS.register(
            "gitsune_tube_full", () -> new KudaGitsuneTube(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB).containerItem(ItemRegistry.SPIRIT_TUBE.get())));

    public static final RegistryObject<OccultBall> OCCULT_BALL =
            ITEMS.register("occult_ball", () -> new OccultBall(
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    // ---------------------------- 一般物品 -------------------------------//
    public static final RegistryObject<Item> HOTSPRING_BUCKET =
            ITEMS.register("hotspring_bucket",() -> new BucketItem(
                    FluidRegistry.HOT_SPRING_SOURCE, new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)
                    .maxStackSize(16).containerItem(BUCKET)));


    // ======================= GSKO杂项：合成消耗品 =========================//

    // public static final RegistryObject<Item> ISHI_ZAKURA = ITEMS.register("ishi_zakura",
    //         () -> new IshiZakuraBlock(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> WANDERING_SOUL = ITEMS.register(
            "wandering_soul", () -> new WanderingSoul(new Item.Properties()
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
    public static final RegistryObject<SpiritStar> SPIRIT_STAR = ITEMS.register(
            "spirit_star", () -> new SpiritStar(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)
                    .maxStackSize(16)));

    public static final RegistryObject<SpiritFragment> SPIRIT_FRAGMENT = ITEMS.register(
            "spirit_fragment", () -> new SpiritFragment(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)
                    .maxStackSize(16)));
    public static final RegistryObject<Item> SPIRIT_DIALECTICS = ITEMS.register(
            "dialectic_spirit", () -> new Item(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)
                    .maxStackSize(16)));
    public static final RegistryObject<Item> SPIRIT_UTOPIAN = ITEMS.register(
            "utopian_spirit", () -> new Item(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)
                    .maxStackSize(16)));
    public static final RegistryObject<Item> SPIRIT_THEOLOGY = ITEMS.register(
            "theology_spirit", () -> new Item(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)
                    .maxStackSize(16)));

    public static final RegistryObject<Item> SPIRIT_CREATIVE = ITEMS.register(
            "creative_spirit",() -> new Item(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)
                    .maxStackSize(16)));

    // --------------------------- 故事书页 ------------------------------//

    public static final RegistryObject<Item> MEMORY_CARD = ITEMS.register(
            "story_card", () -> new StoryCard(new CompoundNBT()));


    // Technical Items that will break the game balance: //
    public static final RegistryObject<Item> REALISM_SWORD =
            ITEMS.register("realism_sword", RealismSword::new);

    public static final RegistryObject<Item> METAPHYSICS_SWORD = ITEMS.register(
            "metaphysics_sword", MetaphysicsSword::new);

    public static final RegistryObject<Item> IDEALISM_SWORD = ITEMS.register(
            "idealism_sword", IdealismSword::new);

    public static final RegistryObject<Item> PRAXIS_SWORD = ITEMS.register(
            "praxis_sword", PraxisSword::new);


    public static final RegistryObject<Item> BYTE_COIN = ITEMS.register("bytecoin",
            () -> new ByteCoin(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> GAME_COIN = ITEMS.register("game_coin",
            () -> new GameCoin(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    // ======================== GSKO 实用类方块 ===========================//
    public static final RegistryObject<BlockItem> DRAGON_SPHERE_ORE_ITEM = ITEMS.register(
            "dragon_sphere_ore", () -> new BlockItem(BlockRegistry.DRAGON_SPHERE_ORE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    // ======================== GSKO战斗类物品 ============================//
    // ----------------------------- 符卡 --------------------------------//

    public static final RegistryObject<SpellCardBlank> SPELL_CARD_BLANK = ITEMS.register(
            "spell_card_blank", () -> new SpellCardBlank(new Item.Properties()
                    .group(GSKOCombatTab.GSKO_COMBAT_TAB)));

    public static final RegistryObject<SC_WaveAndParticle> SC_WAVE_AND_PARTICLE = ITEMS.register(
            "sc_wave_and_particle", () -> new SC_WaveAndParticle(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB),
                    "波与粒的境界","",500));

    // --------------------- 投掷物：弹幕 阴阳玉 灵符 -----------------------//

    public static final RegistryObject<DanmakuShotItem> DANMAKU_SHOT = ITEMS.register("danmaku_shot",
            () -> new DanmakuShotItem(DanmakuType.DANMAKU_SHOT));
    public static final RegistryObject<Item> LARGE_SHOT_ITEM = ITEMS.register(
            "large_shot", () -> new LargeShot(DanmakuType.LARGE_SHOT));
    public static final RegistryObject<Item> HEART_SHOT_ITEM = ITEMS.register(
            "heart_shot", () -> new HeartShot(DanmakuType.HEART_SHOT));

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
    public static final RegistryObject<Item> BOMB_ITEM = ITEMS.register(
            "bomb_item", () -> new BombItem(
                    new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));

    // +++++++++++++++++++++++++++ Deprecated Items +++++++++++++++++++++++//
    public static final RegistryObject<BlockItem> MAGIC_LOG_ITEM = ITEMS.register(
            "magic_leaves", () -> new BlockItem(BlockRegistry.MAGIC_LOG.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> RAIL_NODE = ITEMS.register(
            "rail_node", () -> new BlockItem(BlockRegistry.RAIL_NODE_BLOCK.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> DAKIMAKURA_ITEM = ITEMS.register(
            "dakimakura", () -> new BlockItem(BlockRegistry.DAKIMAKURA.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> ROTATE_FRAME_ITEM = ITEMS.register(
            "rotate_frame", () -> new BlockItem(BlockRegistry.ROTATE_FRAME.get(),
                    new Item.Properties()));


    //======================= ↓ The Mod Cyber Statistics ↓ ====================//

    public static final RegistryObject<Item> HASH_LEAVES_ITEM = ITEMS.register(
            "hash_leaves", () -> new BlockItem(BlockRegistry.HASH_LEAVES.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> HASH_LOG_ITEM = ITEMS.register(
            "hash_log",() -> new BlockItem(BlockRegistry.HASH_LOG.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> FRACTAL_LOG_ITEM = ITEMS.register(
            "fractal_log",() -> new BlockItem(BlockRegistry.FRACTAL_LOG.get(),
                    new Item.Properties()));

    //======================= ↑ The Mod Cyber Statistics ↑ ====================//

}
