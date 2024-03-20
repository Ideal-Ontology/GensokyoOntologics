package github.thelawf.gensokyoontology.core.init;

import com.mojang.serialization.Dynamic;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.screen.script.DanmakuBuilderScreen;
import github.thelawf.gensokyoontology.common.container.script.BinaryOperationContainer;
import github.thelawf.gensokyoontology.client.gui.screen.script.ConstBuilderScreen;
import github.thelawf.gensokyoontology.client.gui.screen.script.Vector3dBuilderScreen;
import github.thelawf.gensokyoontology.common.block.ore.JadeOreBlock;
import github.thelawf.gensokyoontology.common.item.*;
import github.thelawf.gensokyoontology.common.item.danmaku.*;
import github.thelawf.gensokyoontology.common.item.food.*;
import github.thelawf.gensokyoontology.common.item.ore.*;
import github.thelawf.gensokyoontology.common.item.script.DynamicScriptItem;
import github.thelawf.gensokyoontology.common.item.script.ScriptBuilderItem;
import github.thelawf.gensokyoontology.common.item.script.ScriptReadOnlyItem;
import github.thelawf.gensokyoontology.common.item.spellcard.*;
import github.thelawf.gensokyoontology.common.item.tool.*;
import github.thelawf.gensokyoontology.common.item.touhou.*;
import github.thelawf.gensokyoontology.common.nbt.GSKONBTUtil;
import github.thelawf.gensokyoontology.common.nbt.script.BinaryOperation;
import github.thelawf.gensokyoontology.common.nbt.script.GSKOScriptUtil;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

import static net.minecraft.item.Items.BUCKET;

public final class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, GensokyoOntology.MODID);

    // ======================= GSKO杂项：装饰类方块 ==========================//
    // --------------------------- 泥土石头类方块：----------------------------//
    public static final RegistryObject<BlockItem> DEFOLIATION_DIRT_ITEM = ITEMS.register(
            "defoliation_dirt", () -> new BlockItem(BlockRegistry.DEFOLIATION_DIRT.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> KAOLIN_BLOCK_ITEM = ITEMS.register(
            "kaolin", () -> new BlockItem(BlockRegistry.KAOLIN.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> KAOLINITE_BLOCK_ITEM = ITEMS.register(
            "kaolinite", () -> new BlockItem(BlockRegistry.KAOLINITE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

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
    // public static final RegistryObject<BlockItem> ZELKOVA_SAPLING_ITEM = ITEMS.register(
    //         "zelkova_sapling.json", () -> new BlockItem(BlockRegistry.ZELKOVA_SAPLING.get(),
    //                 new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> ZELKOVA_LEAVES_ITEM = ITEMS.register(
            "zelkova_leaves", () -> new BlockItem(BlockRegistry.ZELKOVA_LEAVES.get(),
                    new Item.Properties()));
    public static final RegistryObject<BlockItem> ZELKOVA_LOG_ITEM = ITEMS.register(
            "zelkova_log", () -> new BlockItem(BlockRegistry.ZELKOVA_LOG.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> ZELKOVA_PLANKS_ITEM = ITEMS.register(
            "zelkova_planks", () -> new BlockItem(BlockRegistry.ZELKOVA_PLANKS.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> ZELKOVA_BUTTON_ITEM = ITEMS.register(
            "zelkova_button", () -> new BlockItem(BlockRegistry.ZELKOVA_BUTTON.get(),
                    new Item.Properties().group(ItemGroup.REDSTONE)));
    public static final RegistryObject<BlockItem> ZELKOVA_SLAB_ITEM = ITEMS.register(
            "zelkova_slab", () -> new BlockItem(BlockRegistry.ZELKOVA_SLAB.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> ZELKOVA_STAIRS_ITEM = ITEMS.register(
            "zelkova_stairs", () -> new BlockItem(BlockRegistry.ZELKOVA_STAIRS.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> ZELKOVA_DOOR_ITEM = ITEMS.register(
            "zelkova_door", () -> new BlockItem(BlockRegistry.ZELKOVA_DOOR.get(),
                    new Item.Properties()));
    public static final RegistryObject<BlockItem> ZELKOVA_FENCE_ITEM = ITEMS.register(
            "zelkova_fence", () -> new BlockItem(BlockRegistry.ZELKOVA_FENCE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> ZELKOVA_FENCE_GATE_ITEM = ITEMS.register(
            "zelkova_fence_gate", () -> new BlockItem(BlockRegistry.ZELKOVA_FENCE_GATE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> ZELKOVA_TRAPDOOR_ITEM = ITEMS.register(
            "zelkova_trapdoor", () -> new BlockItem(BlockRegistry.ZELKOVA_TRAPDOOR.get(),
                    new Item.Properties()));
    public static final RegistryObject<BlockItem> ZELKOVA_PRESSRUE_PLATE_ITEM = ITEMS.register(
            "zelkova_pressure_plate", () -> new BlockItem(BlockRegistry.ZELKOVA_PRESSRUE_PLATE.get(),
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
    public static final RegistryObject<BlockItem> MAPLE_PRESSURE_PLATE_ITEM = ITEMS.register(
            "maple_pressure_plate", () -> new BlockItem(BlockRegistry.MAPLE_PRESSURE_PLATE.get(),
                    new Item.Properties().group(ItemGroup.REDSTONE)));

    public static final RegistryObject<BlockItem> MAGIC_SAPLING_ITEM = ITEMS.register(
            "magic_sapling", () -> new BlockItem(BlockRegistry.MAGIC_SAPLING.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> MAGIC_LEAVES_ITEM = ITEMS.register(
            "magic_leaves", () -> new BlockItem(BlockRegistry.MAGIC_LEAVES.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> MAGIC_LOG_ITEM = ITEMS.register(
            "magic_log", () -> new BlockItem(BlockRegistry.MAGIC_LOG.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    //////////////////////////////// 银杏木 ////////////////////////////////
    public static final RegistryObject<BlockItem> GINKGO_LEAVES_ITEM = ITEMS.register(
            "ginkgo_leaves", () -> new BlockItem(BlockRegistry.GINKGO_LEAVES.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> GINKGO_LOG_ITEM = ITEMS.register(
            "ginkgo_log", () -> new BlockItem(BlockRegistry.GINKGO_LOG.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    //////////////////////////////// 红杉木 ////////////////////////////////
    public static final RegistryObject<BlockItem> REDWOOD_LEAVES_ITEM = ITEMS.register(
            "redwood_leaves", () -> new BlockItem(BlockRegistry.REDWOOD_LEAVES.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    //////////////////////////////// 落叶堆 ////////////////////////////////
    public static final RegistryObject<BlockItem> GINKGO_LEAVES_PILE_ITEM = ITEMS.register(
            "ginkgo_leaves_pile", () -> new BlockItem(BlockRegistry.GINKGO_LEAVES_PILE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> MAPLE_LEAVES_PILE_ITEM = ITEMS.register(
            "maple_leaves_pile", () -> new BlockItem(BlockRegistry.MAPLE_LEAVES_PILE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> SAKURA_LEAVES_PILE_ITEM = ITEMS.register(
            "sakura_leaves_pile", () -> new BlockItem(BlockRegistry.SAKURA_LEAVES_PILE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> ZELKOVA_LEAVES_PILE_ITEM = ITEMS.register(
            "zelkova_leaves_pile", () -> new BlockItem(BlockRegistry.ZELKOVA_LEAVES_PILE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    // --------------------------- 草本植物类方块：----------------------------//
    public static final RegistryObject<BlockItem> BLUE_ROSE_ITEM = ITEMS.register("blue_rose_bush",
            () -> new BlockItem(BlockRegistry.BLUE_ROSE_BUSH.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> LYCORIS_RADIATA = ITEMS.register("lycoris_radiata",
            () -> new BlockItem(BlockRegistry.LYCORIS_RADIATA.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> WASABI = ITEMS.register(
            "wasabi", () -> new BlockItem(BlockRegistry.WASABI_BLOCK.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    // ------------------------------ 蘑菇方块 --------------------------------//
    public static final RegistryObject<BlockItem> BLUE_MUSHROOM_ITEM = ITEMS.register(
            "blue_mushroom_block", () -> new BlockItem(BlockRegistry.BLUE_MUSHROOM_BLOCK.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<BlockItem> PURPLE_MUSHROOM_ITEM = ITEMS.register(
            "purple_mushroom_block", () -> new BlockItem(BlockRegistry.PURPLE_MUSHROOM_BLOCK.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    ///////////////////////////////    工艺装饰类方块    //////////////////////////////////
    // public static final RegistryObject<BlockItem> CHIREIDEN_COLORED_GLASS = ITEMS.register(
    //         "chireiden_colored_glass", () -> new BlockItem(BlockRegistry.CHIREIDEN_COLORED_GLASS.get(),
    //                 new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> CLAY_ADOBE_ITEM = ITEMS.register(
            "clay_adobe", () -> new BlockItem(BlockRegistry.CLAY_ADOBE_BLOCK.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> HANIWA_ITEM = ITEMS.register(
            "haniwa", () -> new BlockItem(BlockRegistry.HANIWA_BLOCK.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    // ======================= GSKO杂项：功能性方块 =========================//
    //----------------------------- 合成台 --------------------------//
    public static final RegistryObject<BlockItem> DANMAKU_TABLE_ITEM = ITEMS.register(
            "danmaku_table", () -> new BlockItem(BlockRegistry.DANMAKU_TABLE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> SORCERY_EXTRACTOR_ITEM = ITEMS.register(
            "sorcery_extractor", () -> new BlockItem(BlockRegistry.SORCERY_EXTRACTOR.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> SAISEN_BOX_ITEM = ITEMS.register(
            "saisen_box", () -> new BlockItem(BlockRegistry.SAISEN_BOX.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> SPELL_CONSOLE_ITEM = ITEMS.register(
            "spell_card_console", () -> new BlockItem(BlockRegistry.SPELL_CARD_CONSOLE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    // -------------------------------- 矿石 ---------------------------------//
    public static final RegistryObject<BlockItem> IZANO_OBJECT_ORE_ITEM = ITEMS.register(
            "izano_object_ore", () -> new BlockItem(BlockRegistry.IZANO_OBJECT_ORE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> DRAGON_SPHERE_ORE_ITEM = ITEMS.register(
            "dragon_sphere_ore", () -> new BlockItem(BlockRegistry.DRAGON_SPHERE_ORE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    /**
     * 在玉石方块的物品类中重写其与方块的交互逻辑，实现用玉石原矿赌石的功能
     */
    public static final RegistryObject<BlockItem> JADE_ORE_ITEM = ITEMS.register(
            "jade_ore", () -> new BlockItem(BlockRegistry.JADE_ORE.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)) {
                @Override
                @NotNull
                public ActionResultType onItemUse(@NotNull ItemUseContext context) {
                    World world = context.getWorld();
                    Block block = world.getBlockState(context.getPos()).getBlock();
                    Random random = new Random();

                    if (context.getPlayer() != null && block.matchesBlock(Blocks.STONECUTTER)) {
                        context.getPlayer().playSound(SoundEvents.UI_STONECUTTER_TAKE_RESULT, 1.0f, 1.0f);
                    }

                    if (!world.isRemote && Screen.hasShiftDown() && block.matchesBlock(Blocks.STONECUTTER) &&
                            random.nextInt(6) == 1 &&
                            !JadeOreBlock.getItemToDrop(world, 150, 440, 2000, 6000).equals(ItemStack.EMPTY)) {

                        ServerWorld serverWorld = (ServerWorld) world;
                        if (context.getItem().getCount() >= 10) {
                            context.getItem().shrink(10);
                            for (int i = 0; i < 10; i++) {
                                Block.spawnAsEntity(world, context.getPos(), JadeOreBlock.getItemToDrop(world,
                                        150, 440, 2000, 6000));
                            }
                            return ActionResultType.FAIL;
                        }
                        context.getItem().shrink(1);
                        Block.spawnAsEntity(world, context.getPos(), JadeOreBlock.getItemToDrop(world, context.getPlayer(),
                                serverWorld.getDimensionKey()));

                    }
                    return super.onItemUse(context);
                }
            });
    public static final RegistryObject<BlockItem> IMMEMORIAL_ALLOY_BLOCK_ITEM = ITEMS.register(
            "immemorial_alloy_block", () -> new BlockItem(BlockRegistry.IMMEMORIAL_ALLOY_BLOCK.get(),
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    // ------------------------------- 技术性道具 ----------------------------------//
    public static final RegistryObject<BlockItem> DISPOSABLE_SPAWNER_ITEM = ITEMS.register(
            "disposable_spawner", () -> new BlockItem(BlockRegistry.DISPOSABLE_SPAWNER.get(), new Item.Properties()));
    public static final RegistryObject<Item> DESTRUCTIVE_EYE = ITEMS.register("destructive_eye",
            () -> new Item(new Item.Properties()));

    // ======================= GSKO杂项：道具类物品 =========================//
    // ----------------------- 东方project特殊功能道具 ----------------------//
    public static final RegistryObject<HakureiGohei> HAKUREI_GOHEI = ITEMS.register(
            "hakurei_gohei", () -> new HakureiGohei(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB).maxStackSize(1)));
    public static final RegistryObject<MarisaHakkeiro> MARISA_HAKKEIRO = ITEMS.register(
            "marisa_hakkeiro", () -> new MarisaHakkeiro(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB).maxStackSize(1)));
    public static final RegistryObject<SakuyaStopWatch> SAKUYA_WATCH = ITEMS.register(
            "sakuya_stop_watch", () -> new SakuyaStopWatch(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB).maxStackSize(1)));
    public static final RegistryObject<CrookedClockNeedle> CROOKED_CLOCK_NEEDLE = ITEMS.register(
            "crooked_clock_needle", () -> new CrookedClockNeedle(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB).maxStackSize(1)));
    public static final RegistryObject<BlockItem> GAP_BLOCK = ITEMS.register(
            "gap_block", () -> new BlockItem(BlockRegistry.GAP_BLOCK.get(), new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB).maxStackSize(1)));
    // public static final RegistryObject<EirinYagokoroArrow> EIRIN_YAGOKORO_ARROW = ITEMS.register(
    //         "eirin_yagokoro_arrow", () -> new EirinYagokoroArrow(new Item.Properties()
    //                 .group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<AyaFans> AYA_FANS = ITEMS.register(
            "aya_fans", () -> new AyaFans(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB).maxStackSize(1)));
    public static final RegistryObject<KoishiEyeOpen> KOISHI_EYE_OPEN = ITEMS.register(
            "koishi_eye_open", () -> new KoishiEyeOpen(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<KoishiEyeClosed> KOISHI_EYE_CLOSED = ITEMS.register(
            "koishi_eye_closed", () -> new KoishiEyeClosed(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<ArmorItem> KOISHI_HAT = ITEMS.register(
            "koishi_hat", () -> new ArmorItem(GSKOArmorMaterial.JADE,
                    EquipmentSlotType.HEAD, (new Item.Properties())));
    public static final RegistryObject<SatoriEye> SATORI_EYE = ITEMS.register(
            "satori_eye", () -> new SatoriEye(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<SeigaHairpin> SEIGA_HAIRPIN = ITEMS.register(
            "seiga_hairpin", () -> new SeigaHairpin(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<SpiritTube> SPIRIT_TUBE = ITEMS.register(
            "spirit_tube", () -> new SpiritTube(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<KudaGitsuneTube> KUDA_GITSUNE_TUBE = ITEMS.register(
            "kuda_gitsune_tube", () -> new KudaGitsuneTube(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<Item> GITSUNE_TUBE_FULL = ITEMS.register(
            "gitsune_tube_full", () -> new GitsuneTubeFull(new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB).containerItem(ItemRegistry.SPIRIT_TUBE.get())));
    public static final RegistryObject<OccultBall> OCCULT_BALL = ITEMS.register(
            "occult_ball", () -> new OccultBall(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<ChimataMarketLicense> CHIMATA_MARKET_LICENSE = ITEMS.register(
            "chimata_market_license", () -> new ChimataMarketLicense(new Item.Properties()));
    // 魔法道具
    public static final RegistryObject<SorceryScarletMist> SORCERY_SCARLET_MIST = ITEMS.register(
            "sorcery_scarlet_mmist", () -> new SorceryScarletMist(new Item.Properties()));

    // ----------------------------------- 杂项物品 --------------------------------------//
    public static final RegistryObject<Item> SILVER_COIN = ITEMS.register("silver_coin", () -> new CoinItem(1F));
    public static final RegistryObject<Item> SAKE_WORM = ITEMS.register("sake_worm", () -> new SakeWormItem(
            new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB).maxStackSize(1)));
    public static final RegistryObject<Item> HOTSPRING_BUCKET = ITEMS.register("hotspring_bucket",
            () -> new BucketItem(FluidRegistry.HOT_SPRING_SOURCE, new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)
                    .maxStackSize(1).containerItem(BUCKET)));
    public static final RegistryObject<Item> SAKE_BUCKET = ITEMS.register("sake_bucket",
            () -> new BucketItem(FluidRegistry.SAKE_WINE_SOURCE, new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)
                    .maxStackSize(1).containerItem(BUCKET)));

    public static final RegistryObject<Item> PAPER_PULP_BUCKET = ITEMS.register("paper_pulp_bucket",
            () -> new BucketItem(FluidRegistry.PAPER_PULP_SOURCE, new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)
                    .maxStackSize(1).containerItem(BUCKET)));

    // ========================== GSKO杂项：合成消耗品 =========================//

    // public static final RegistryObject<Item> ISHI_ZAKURA_FRAGMENT = ITEMS.register("ishi_zakura_fragment",
    //         () -> new IshiZakuraFragment(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<BlockItem> ISHI_ZAKURA = ITEMS.register("ishi_zakura",
            () -> new BlockItem(BlockRegistry.ISHI_ZAKURA.get(), new Item.Properties()
                    .group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> CHERRY_BLOSSOM = ITEMS.register("cherry_blossom",
            () -> new CherryBlossom(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> WANDERING_SOUL = ITEMS.register("wandering_soul",
            () -> new WanderingSoul(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> WASHI_PAPER = ITEMS.register("washi_paper",
            () -> new WashiPaper(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    // public static final RegistryObject<Item> PAPER_SHIDE = ITEMS.register("paper_shide",
    //         () -> new PaperShide(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> IZANO_OBJECT = ITEMS.register("izano_object",
            () -> new IzanoObject(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<Item> DRAGON_SPHERE_FRAGMENT = ITEMS.register("dragon_sphere_fragment",
            () -> new DragonSphere(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<Item> DRAGON_SPHERE = ITEMS.register("dragon_sphere",
            () -> new DragonSphere(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> CRIMSON_ALLOY_INGOT = ITEMS.register("crimson_alloy_ingot",
            () -> new CrimsonAlloyIngot(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    public static final RegistryObject<Item> CRIMSON_ALLOY_FRAGMENT = ITEMS.register("crimson_alloy_fragment",
            () -> new CrimsonMetalFragment(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    ////////////////////////////////////  各个等级的玉石  ///////////////////////////////////////
    public static final RegistryObject<Item> JADE_LEVEL_B = ITEMS.register("jade_level_b",
            () -> new JadeItem(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<Item> JADE_LEVEL_A = ITEMS.register("jade_level_a",
            () -> new JadeItem(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<Item> JADE_LEVEL_S = ITEMS.register("jade_level_s",
            () -> new JadeItem(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<Item> JADE_LEVEL_SS = ITEMS.register("jade_level_ss",
            () -> new JadeItem(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<Item> JADE_LEVEL_SSS = ITEMS.register("jade_level_sss",
            () -> new JadeItem(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<Item> ORB_JADE = ITEMS.register("orb_jade",
            () -> new OrbJade(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<Item> DARK_SPIRIT = ITEMS.register("dark_spirit",
            () -> new DarkSpirit(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<Item> LIGHT_SPIRIT = ITEMS.register("light_spirit",
            () -> new LightSpirit(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    // ---------------------------- 食物原材料 -----------------------------//
    public static final RegistryObject<Item> KITCHEN_KNIFE = ITEMS.register("kitchen_knife", KitchenKnife::new);
    public static final RegistryObject<Butter> BUTTER = ITEMS.register("butter",
            () -> new Butter(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<MilkBottle> MILK_BOTTLE = ITEMS.register("milk_bottle", MilkBottle::new);

    public static final RegistryObject<SquidTentacle> SQUID_TENTACLE = ITEMS.register("squid_tentacle", SquidTentacle::new);
    public static final RegistryObject<Item> ONION = ITEMS.register("onion", () ->
            new BlockItem(BlockRegistry.ONION_CROP_BLOCK.get(), new Item.Properties().group(
                    GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<YattsumeUna> YATTSUME_UNA =
            ITEMS.register("yattsume_una", () -> new YattsumeUna(
                    new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    // ------------------------------- 食物 -------------------------------//
    public static final RegistryObject<YattsumeUnaYaki> YATTSUME_UNA_YAKI = ITEMS.register("yattsume_una_yaki", YattsumeUnaYaki::new);
    public static final RegistryObject<KoishiHatMousse> KOISHI_HAT_MOUSSE = ITEMS.register("koishi_hat_mousse", KoishiHatMousse::new);
    public static final RegistryObject<CakeScarletDemon> CAKE_SCARLET_DEMON = ITEMS.register("cake_scarlet_demon", CakeScarletDemon::new);
    public static final RegistryObject<Lingoame> LINGOAME = ITEMS.register("lingoame", Lingoame::new);
    public static final RegistryObject<TakoYaki> TAKO_YAKI = ITEMS.register("tako_yaki", TakoYaki::new);
    public static final RegistryObject<WhiteSnow> WHITE_SNOW = ITEMS.register("white_snow", WhiteSnow::new);
    public static final RegistryObject<BurgerMeatRaw> BURGER_MEAT_RAW = ITEMS.register("burger_meat_raw", BurgerMeatRaw::new);
    public static final RegistryObject<BurgerMeat> BURGER_MEAT = ITEMS.register("burger_meat", BurgerMeat::new);


    //////////////////////////////////// 被遗忘的传说 /////////////////////////////////
    public static final RegistryObject<Item> TALES_SCARLET_MIST = ITEMS.register(
            "oblivious_tales_scarlet_mist", () -> new ObliviousTales(new CompoundNBT()));
    public static final RegistryObject<Item> TALES_SPRING_SNOWS = ITEMS.register(
            "oblivious_tales_spring_snows", () -> new ObliviousTales(new CompoundNBT()));
    public static final RegistryObject<Item> TALES_IMPERISHABLE_NIGHT = ITEMS.register(
            "oblivious_tales_imperishable_night", () -> new ObliviousTales(new CompoundNBT()));
    public static final RegistryObject<Item> TALES_OCCULT_BALL = ITEMS.register(
            "oblivious_tales_occult_ball", () -> new ObliviousTales(new CompoundNBT()));

    // Technical Items that will break the game balance: //
    // public static final RegistryObject<Item> REALISM_SWORD = ITEMS.register(
    //         "realism_sword", RealismSword::new);
    // public static final RegistryObject<Item> METAPHYSICS_SWORD = ITEMS.register(
    //         "metaphysics_sword", MetaphysicsSword::new);
    // public static final RegistryObject<Item> IDEALISM_SWORD = ITEMS.register(
    //         "idealism_sword", IdealismSword::new);
    // public static final RegistryObject<Item> PRAXIS_SWORD = ITEMS.register(
    //         "praxis_sword", PraxisSword::new);


    // ============================ GSKO生物刷怪蛋 ================================//
    public static final RegistryObject<ForgeSpawnEggItem> HAKURE_REIMU_SPAWN_EGG = ITEMS.register(
            "hakurei_reimu_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.HAKUREI_REIMU,
                    0xFF200A, 0xFFFFFC, new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<ForgeSpawnEggItem> FAIRY_SPAWN_EGG = ITEMS.register(
            "fairy_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.FAIRY_ENTITY,
                    0x0E51D5, 0xFAEB1C, new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<ForgeSpawnEggItem> LILY_WHITE_SPAWN_EGG = ITEMS.register(
            "lily_white_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.LILY_WHITE_ENTITY,
                    0xFFF8E8, 0xF52C2C, new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<ForgeSpawnEggItem> REMILIA_SCARLET_SAWN_EGG = ITEMS.register(
            "remilia_scarlet_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.REMILIA_SCARLET,
                    0x99CAFF, 0xFF0F3F, new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<ForgeSpawnEggItem> FLANDRE_SCARLET_SPAWN_EGG = ITEMS.register(
            "flandre_scarlet_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.FLANDRE_SCARLET,
                    0xDC143C, 0xFDFD78, new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<ForgeSpawnEggItem> KOMEIJI_KOISHI_SAPWN_EGG = ITEMS.register(
            "komeiji_koishi_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.KOMEIJI_KOISHI,
                    0x42B983, 0xFCFA58, new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));

    // ======================== GSKO战斗类物品 ============================//
    // ----------------------------- 符卡 --------------------------------//
    public static final RegistryObject<SpellCardBlank> SPELL_CARD_BLANK = ITEMS.register(
            "spell_card_blank", () -> new SpellCardBlank(new Item.Properties()
                    .group(GSKOCombatTab.GSKO_COMBAT_TAB).maxStackSize(1)));
    public static final RegistryObject<SC_HyperboloidLaser> SC_HYPERBOLOID_LASER = ITEMS.register(
            "sc_hyperboloid_laser", () -> new SC_HyperboloidLaser(new Item.Properties()
                    .group(GSKOCombatTab.GSKO_COMBAT_TAB).maxStackSize(1)));
    public static final RegistryObject<SC_WaveAndParticle> SC_WAVE_AND_PARTICLE = ITEMS.register(
            "sc_wave_and_particle", () -> new SC_WaveAndParticle(new Item.Properties()
                    .group(GSKOCombatTab.GSKO_COMBAT_TAB).maxStackSize(1), 500));
    public static final RegistryObject<SC_IdoNoKaiho> SC_IDO_NO_KAIHO = ITEMS.register(
            "sc_ido_no_kaiho", () -> new SC_IdoNoKaiho(new Item.Properties()
                    .group(GSKOCombatTab.GSKO_COMBAT_TAB).maxStackSize(1), 600));
    public static final RegistryObject<SC_SpiralWheel> SC_SPIRAL_WHEEL = ITEMS.register(
            "sc_spiral_wheel", () -> new SC_SpiralWheel(new Item.Properties()
                    .group(GSKOCombatTab.GSKO_COMBAT_TAB).maxStackSize(1), 500));
    public static final RegistryObject<SC_HellEclipse> SC_HELL_ECLIPSE = ITEMS.register(
            "sc_hell_eclipse", () -> new SC_HellEclipse(new Item.Properties()
                    .group(GSKOCombatTab.GSKO_COMBAT_TAB).maxStackSize(1), 500));
    public static final RegistryObject<SC_MountainOfFaith> SC_MOUNTAIN_OF_FAITH = ITEMS.register(
            "sc_mountain_of_faith", () -> new SC_MountainOfFaith(new Item.Properties()
                    .group(GSKOCombatTab.GSKO_COMBAT_TAB).maxStackSize(1), 500));
    public static final RegistryObject<SC_MobiusRingWorld> SC_MOBIUS_RING_WORLD = ITEMS.register(
            "sc_mobius_ring_world", () -> new SC_MobiusRingWorld(new Item.Properties()
                    .group(GSKOCombatTab.GSKO_COMBAT_TAB).maxStackSize(1)));
    public static final RegistryObject<SC_FullCherryBlossom> SC_FULL_CHERRY_BLOSSOM = ITEMS.register(
            "sc_full_cherry_blossom", () -> new SC_FullCherryBlossom(new Item.Properties()
                    .group(GSKOCombatTab.GSKO_COMBAT_TAB).maxStackSize(1)));
    public static final RegistryObject<SC_HanaShigure> SC_HANA_SHIGURE = ITEMS.register(
            "sc_hana_shigure", () -> new SC_HanaShigure(new Item.Properties()
                    .group(GSKOCombatTab.GSKO_COMBAT_TAB).maxStackSize(1)));
    public static final RegistryObject<SC_ManiaDepress> SC_MANIA_DEPRESS = ITEMS.register(
            "sc_mania_depress", () -> new SC_ManiaDepress(new Item.Properties()
                    .group(GSKOCombatTab.GSKO_COMBAT_TAB).maxStackSize(1)));
    public static final RegistryObject<SC_GalacticSpiralArms> SC_GALACTIC_SPIRAL_ARMS = ITEMS.register(
            "sc_galactic_spiral_arms", () -> new SC_GalacticSpiralArms(new Item.Properties()
                    .group(GSKOCombatTab.GSKO_COMBAT_TAB).maxStackSize(1)));
    public static final RegistryObject<ScriptedSpellCard> SCRIPTED_SPELL_CARD = ITEMS.register(
            "scripted_spell_card", () -> new ScriptedSpellCard(new Item.Properties()
                    .group(GSKOCombatTab.GSKO_COMBAT_TAB).maxStackSize(1)));

    // --------------------- 投掷物：弹幕 阴阳玉 灵符 -----------------------//
    public static final RegistryObject<DanmakuShotItem> DANMAKU_SHOT = ITEMS.register("danmaku_shot",
            () -> new DanmakuShotItem(DanmakuType.DANMAKU_SHOT));

    /////////////////////////// 所有颜色的大弹 ////////////////////////////////

    public static final RegistryObject<Item> LARGE_SHOT = ITEMS.register(
            "large_shot", () -> new LargeShot(DanmakuType.LARGE_SHOT));
    public static final RegistryObject<Item> LARGE_SHOT_RED = ITEMS.register(
            "large_shot_red", () -> new LargeShot(DanmakuType.LARGE_SHOT));
    public static final RegistryObject<Item> LARGE_SHOT_ORANGE = ITEMS.register(
            "large_shot_orange", () -> new LargeShot(DanmakuType.LARGE_SHOT));
    public static final RegistryObject<Item> LARGE_SHOT_YELLOW = ITEMS.register(
            "large_shot_yellow", () -> new LargeShot(DanmakuType.LARGE_SHOT));
    public static final RegistryObject<Item> LARGE_SHOT_GREEN = ITEMS.register(
            "large_shot_green", () -> new LargeShot(DanmakuType.LARGE_SHOT));
    public static final RegistryObject<Item> LARGE_SHOT_AQUA = ITEMS.register(
            "large_shot_aqua", () -> new LargeShot(DanmakuType.LARGE_SHOT));
    public static final RegistryObject<Item> LARGE_SHOT_BLUE = ITEMS.register(
            "large_shot_blue", () -> new LargeShot(DanmakuType.LARGE_SHOT));
    public static final RegistryObject<Item> LARGE_SHOT_PURPLE = ITEMS.register(
            "large_shot_purple", () -> new LargeShot(DanmakuType.LARGE_SHOT));
    public static final RegistryObject<Item> LARGE_SHOT_MAGENTA = ITEMS.register(
            "large_shot_magenta", () -> new LargeShot(DanmakuType.LARGE_SHOT));

    /////////////////////////// 所有颜色的小弹 ////////////////////////////////
    public static final RegistryObject<Item> SMALL_SHOT = ITEMS.register(
            "small_shot", () -> new SmallShot(DanmakuType.SMALL_SHOT));
    public static final RegistryObject<Item> SMALL_SHOT_RED = ITEMS.register(
            "small_shot_red", () -> new SmallShot(DanmakuType.SMALL_SHOT));
    public static final RegistryObject<Item> SMALL_SHOT_ORANGE = ITEMS.register(
            "small_shot_orange", () -> new SmallShot(DanmakuType.SMALL_SHOT));
    public static final RegistryObject<Item> SMALL_SHOT_YELLOW = ITEMS.register(
            "small_shot_yellow", () -> new SmallShot(DanmakuType.SMALL_SHOT));
    public static final RegistryObject<Item> SMALL_SHOT_GREEN = ITEMS.register(
            "small_shot_green", () -> new SmallShot(DanmakuType.SMALL_SHOT));
    public static final RegistryObject<Item> SMALL_SHOT_AQUA = ITEMS.register(
            "small_shot_aqua", () -> new SmallShot(DanmakuType.SMALL_SHOT));
    public static final RegistryObject<Item> SMALL_SHOT_BLUE = ITEMS.register(
            "small_shot_blue", () -> new SmallShot(DanmakuType.SMALL_SHOT));
    public static final RegistryObject<Item> SMALL_SHOT_PURPLE = ITEMS.register(
            "small_shot_purple", () -> new SmallShot(DanmakuType.SMALL_SHOT));
    public static final RegistryObject<Item> SMALL_SHOT_MAGENTA = ITEMS.register(
            "small_shot_magenta", () -> new SmallShot(DanmakuType.SMALL_SHOT));

    ////////////////////////////// 所有颜色的米弹  /////////////////////////////////
    public static final RegistryObject<Item> RICE_SHOT = ITEMS.register("rice_shot",
            () -> new RiceShot(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> RICE_SHOT_RED = ITEMS.register("rice_shot_red",
            () -> new RiceShot(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> RICE_SHOT_BLUE = ITEMS.register("rice_shot_blue",
            () -> new RiceShot(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> RICE_SHOT_PURPLE = ITEMS.register("rice_shot_purple",
            () -> new RiceShot(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));

    ////////////////////////////// 所有颜色的鳞弹  /////////////////////////////////
    public static final RegistryObject<Item> SCALE_SHOT = ITEMS.register("scale_shot",
            () -> new ScaleShot(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> SCALE_SHOT_RED = ITEMS.register("scale_shot_red",
            () -> new ScaleShot(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> SCALE_SHOT_YELLOW = ITEMS.register("scale_shot_yellow",
            () -> new ScaleShot(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> SCALE_SHOT_GREEN = ITEMS.register("scale_shot_green",
            () -> new ScaleShot(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> SCALE_SHOT_BLUE = ITEMS.register("scale_shot_blue",
            () -> new ScaleShot(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> SCALE_SHOT_PURPLE = ITEMS.register("scale_shot_purple",
            () -> new ScaleShot(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));

    ////////////////////////////// 所有颜色的心弹  /////////////////////////////////
    public static final RegistryObject<Item> HEART_SHOT = ITEMS.register(
            "heart_shot", () -> new HeartShot(DanmakuType.HEART_SHOT));
    public static final RegistryObject<Item> HEART_SHOT_PINK = ITEMS.register(
            "heart_shot_pink", () -> new HeartShot(DanmakuType.HEART_SHOT));
    public static final RegistryObject<Item> HEART_SHOT_RED = ITEMS.register(
            "heart_shot_red", () -> new HeartShot(DanmakuType.HEART_SHOT));
    public static final RegistryObject<Item> HEART_SHOT_AQUA = ITEMS.register(
            "heart_shot_aqua", () -> new HeartShot(DanmakuType.HEART_SHOT));
    public static final RegistryObject<Item> HEART_SHOT_BLUE = ITEMS.register(
            "heart_shot_blue", () -> new HeartShot(DanmakuType.HEART_SHOT));

    ////////////////////////////// 所有颜色的小型星弹  /////////////////////////////////
    public static final RegistryObject<Item> SMALL_STAR_SHOT = ITEMS.register(
            "small_star_shot", () -> new StarShot(DanmakuType.STAR_SHOT_SMALL));
    public static final RegistryObject<Item> SMALL_STAR_SHOT_RED = ITEMS.register(
            "small_star_shot_red", () -> new StarShot(DanmakuType.STAR_SHOT_SMALL));
    public static final RegistryObject<Item> SMALL_STAR_SHOT_YELLOW = ITEMS.register(
            "small_star_shot_yellow", () -> new StarShot(DanmakuType.STAR_SHOT_SMALL));
    public static final RegistryObject<Item> SMALL_STAR_SHOT_GREEN = ITEMS.register(
            "small_star_shot_green", () -> new StarShot(DanmakuType.STAR_SHOT_SMALL));
    public static final RegistryObject<Item> SMALL_STAR_SHOT_AQUA = ITEMS.register(
            "small_star_shot_aqua", () -> new StarShot(DanmakuType.STAR_SHOT_SMALL));
    public static final RegistryObject<Item> SMALL_STAR_SHOT_BLUE = ITEMS.register(
            "small_star_shot_blue", () -> new StarShot(DanmakuType.STAR_SHOT_SMALL));
    public static final RegistryObject<Item> SMALL_STAR_SHOT_PURPLE = ITEMS.register(
            "small_star_shot_purple", () -> new StarShot(DanmakuType.STAR_SHOT_SMALL));

    ////////////////////////////// 所有颜色的大型星弹  /////////////////////////////////
    public static final RegistryObject<Item> LARGE_STAR_SHOT = ITEMS.register(
            "large_star_shot", () -> new StarShot(DanmakuType.STAR_SHOT_LARGE));
    public static final RegistryObject<Item> LARGE_STAR_SHOT_RED = ITEMS.register(
            "large_star_shot_red", () -> new StarShot(DanmakuType.STAR_SHOT_LARGE));
    public static final RegistryObject<Item> LARGE_STAR_SHOT_YELLOW = ITEMS.register(
            "large_star_shot_yellow", () -> new StarShot(DanmakuType.STAR_SHOT_LARGE));
    public static final RegistryObject<Item> LARGE_STAR_SHOT_GREEN = ITEMS.register(
            "large_star_shot_green", () -> new StarShot(DanmakuType.STAR_SHOT_LARGE));
    public static final RegistryObject<Item> LARGE_STAR_SHOT_AQUA = ITEMS.register(
            "large_star_shot_aqua", () -> new StarShot(DanmakuType.STAR_SHOT_LARGE));
    public static final RegistryObject<Item> LARGE_STAR_SHOT_BLUE = ITEMS.register(
            "large_star_shot_blue", () -> new StarShot(DanmakuType.STAR_SHOT_LARGE));
    public static final RegistryObject<Item> LARGE_STAR_SHOT_PURPLE = ITEMS.register(
            "large_star_shot_purple", () -> new StarShot(DanmakuType.STAR_SHOT_LARGE));

    ////////////////////////////// 所有颜色的札弹  /////////////////////////////////
    public static final RegistryObject<Item> TALISMAN_SHOT = ITEMS.register(
            "talisman_shot", () -> new TalismanShot(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> TALISMAN_SHOT_RED = ITEMS.register("talisman_shot_red",
            () -> new TalismanShot(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> TALISMAN_SHOT_GREEN = ITEMS.register("talisman_shot_green",
            () -> new TalismanShot(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> TALISMAN_SHOT_AQUA = ITEMS.register("talisman_shot_aqua",
            () -> new TalismanShot(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> TALISMAN_SHOT_BLUE = ITEMS.register("talisman_shot_blue",
            () -> new TalismanShot(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> TALISMAN_SHOT_PURPLE = ITEMS.register("talisman_shot_purple",
            () -> new TalismanShot(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));

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

    //////////////////////////// 道具：B点、残机 ////////////////////////////////
    public static final RegistryObject<Item> FAKE_LUNAR_ITEM = ITEMS.register("fake_lunar",
            () -> new FakeLunarItem(DanmakuType.FAKE_LUNAR));

    public static final RegistryObject<Item> POWER_ITEM = ITEMS.register("power_item",
            () -> new PowerItem(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB)));
    public static final RegistryObject<Item> BOMB_FRAGMENT = ITEMS.register("bomb_fragment",
            () -> new BombFragment(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));

    public static final RegistryObject<Item> LIFE_FRAGMENT = ITEMS.register("life_fragment",
            () -> new LifeFragment(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));

    public static final RegistryObject<Item> BOMB_ITEM = ITEMS.register("bomb_item",
            () -> new BombItem(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));

    public static final RegistryObject<Item> EXTEND_ITEM = ITEMS.register("extend_item",
            () -> new ExtendItem(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));

    // ------------------------------- 装备 -------------------------------//
    public static final RegistryObject<Item> JADE_AXE = ITEMS.register("jade_axe", () -> new JadeAxe(
            new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> JADE_HOE = ITEMS.register("jade_hoe", () -> new JadeHoe(
            new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> JADE_PICKAXE = ITEMS.register("jade_pickaxe", () -> new JadePickaxe(
            new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> JADE_SHOVEL = ITEMS.register("jade_shovel", () -> new JadeShovel(
            new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> JADE_SWORD = ITEMS.register("jade_sword", () -> new JadeSword(
            new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB)));

    public static final RegistryObject<Item> JADE_HELMET = ITEMS.register("jade_helmet", () -> new ArmorItem(
            GSKOArmorMaterial.JADE, EquipmentSlotType.HEAD, (new Item.Properties()).group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> JADE_CHESTPLATE = ITEMS.register("jade_chestplate", () -> new ArmorItem(
            GSKOArmorMaterial.JADE, EquipmentSlotType.CHEST, (new Item.Properties()).group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> JADE_LEGGINGS = ITEMS.register("jade_leggings", () -> new ArmorItem(
            GSKOArmorMaterial.JADE, EquipmentSlotType.LEGS, (new Item.Properties()).group(GSKOCombatTab.GSKO_COMBAT_TAB)));
    public static final RegistryObject<Item> JADE_BOOTS = ITEMS.register("jade_boots", () -> new ArmorItem(
            GSKOArmorMaterial.JADE, EquipmentSlotType.FEET, (new Item.Properties()).group(GSKOCombatTab.GSKO_COMBAT_TAB)));


    // ====================================== 技术性物品 ====================================== //
    public static final RegistryObject<Item> DREAM_SEAL_ITEM = ITEMS.register("dream_seal", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPHERE_EFFECT_ITEM = ITEMS.register("sphere_effect", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CONST_BUILDER = ITEMS.register("const_builder", () -> new ScriptBuilderItem() {
        @Override
        public void openScriptEditGUI(World world, PlayerEntity player, ItemStack stack) {
            Minecraft minecraft = Minecraft.getInstance();
            ITextComponent title = GensokyoOntology.withTranslation("gui.",".const_builder.title");
            minecraft.displayGuiScreen(new ConstBuilderScreen(title, stack));
        }
    });

    public static final RegistryObject<Item> V3D_BUILDER = ITEMS.register("vector3d_builder", () -> new ScriptBuilderItem() {
        @Override
        public void openScriptEditGUI(World world, PlayerEntity player, ItemStack stack) {
            Minecraft minecraft = Minecraft.getInstance();
            ITextComponent title = GensokyoOntology.withTranslation("gui.",".vector3d_builder.title");
            minecraft.displayGuiScreen(new Vector3dBuilderScreen(title, stack));
        }
    });

    public static final RegistryObject<Item> DANMAKU_BUILDER = ITEMS.register("danmaku_builder", () -> new ScriptBuilderItem() {
        @Override
        public void openScriptEditGUI(World world, PlayerEntity player, ItemStack stack) {
            Minecraft minecraft = Minecraft.getInstance();
            ITextComponent title = GensokyoOntology.withTranslation("gui.",".danmaku_builder.title");
            if (Screen.hasShiftDown()) minecraft.displayGuiScreen(new DanmakuBuilderScreen(title, stack));
        }

        @Override
        public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
            TranslationTextComponent entityText = GensokyoOntology.withTranslation("tooltip.",".danmaku_entity");
            TranslationTextComponent colorText = GensokyoOntology.withTranslation("tooltip.",".danmaku_color");
            TranslationTextComponent typeText = GensokyoOntology.withTranslation("tooltip.",".danmaku_type");
            if (stack.getTag() != null) {
                CompoundNBT nbt = stack.getTag();
                tooltip.add(entityText);
                tooltip.add(new TranslationTextComponent(nbt.getString("type")));
                if (nbt.contains("danmakuType")) {
                    tooltip.add(typeText);
                    DanmakuType type = DanmakuType.valueOf(nbt.getString("danmakuType").toUpperCase());
                    tooltip.add(type.toTextComponent());
                }
                if (nbt.contains("danmakuColor")) {
                    tooltip.add(colorText);
                    DanmakuColor color = DanmakuColor.valueOf(nbt.getString("danmakuColor").toUpperCase());
                    tooltip.add(color.toTextComponent());
                }
                else if (GSKONBTUtil.containsAllowedType(nbt)) {
                    GSKONBTUtil.getMemberValues(nbt).forEach(s -> tooltip.add(new StringTextComponent(s)));
                }
            }
        }
    });

    public static final RegistryObject<Item> BINARY_OPERATION_BUILDER = ITEMS.register("binary_operation_builder", () -> new DynamicScriptItem() {
        @Override
        public void addDynamicData(World world, PlayerEntity player, ItemStack stack, Dynamic<INBT> dynamic) {

        }
        @Override
        public void openScriptEditGUI(World world, PlayerEntity player, ItemStack stack) {
            // minecraft.displayGuiScreen(new DanmakuBuilderScreen(title, stack, world, player));
            if (!world.isRemote) player.openContainer(BinaryOperationContainer.create());
        }

        @Override
        public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
            if (stack.getTag() == null) return;
            CompoundNBT nbt = stack.getTag();
            tooltip.add(OPERATION_TYPE_TIP);
            tooltip.add(BinaryOperation.valueOf(GSKOScriptUtil.getScriptValue(nbt).getString("operation")
                    .toUpperCase()).toTextComponent());
            tooltip.add(LEFT_TYPE_TIP);
            tooltip.add(new StringTextComponent(TYPE_HIGHLIGHT + GSKOScriptUtil.getOptLeft(nbt).getString("type")));
            tooltip.add(RIGHT_TYPE_TIP);
            tooltip.add(new StringTextComponent(TYPE_HIGHLIGHT + GSKOScriptUtil.getOptRight(nbt).getString("type")));
        }
    });

    public static final RegistryObject<Item> TIME_STAMP = ITEMS.register("time_stamp", () -> new ScriptReadOnlyItem() {
        @Override
        public void addReadOnlyData(World world, PlayerEntity player, ItemStack stack) {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString("type", "time_stamp");
            nbt.putString("name", "ticksExisted");
            nbt.putString("value", "increasedByTick");
            stack.setTag(nbt);
        }
    });

    // public static final RegistryObject<Item> FUNC_INVOCATION = ITEMS.register("func_invocation", () -> new ScriptReadOnlyItem() {
    //     @Override
    //     public void addReadOnlyData(World world, PlayerEntity player, ItemStack stack) {
    //     }
    // });
}
