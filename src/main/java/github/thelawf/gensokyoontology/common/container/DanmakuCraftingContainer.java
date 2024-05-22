package github.thelawf.gensokyoontology.common.container;

import com.mojang.datafixers.DataFixUtils;
import github.thelawf.gensokyoontology.common.util.client.GSKOGUIUtil;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import github.thelawf.gensokyoontology.data.recipe.DanmakuRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Stream;

/**
 * 弹幕合成界面UV数据：<br><br>
 * - 玩家背包槽位左上角坐标: (x=27, y=123)<br>
 * - 弹幕之点槽位组合左上角坐标: (x=15, y=20)<br>
 * - 合成结果槽位组合左上角坐标: (x=148, y=40)<br><br>
 * - Minecraft 原版一个槽位的长宽: (w=18, h=18)<br><br>
 * - 弹幕之点槽位数量：5 × 5<br>
 * - 合成结果槽位数量：4 × 4
 */
public class DanmakuCraftingContainer extends Container {
    // private final DomainFieldEntity fieldEntity;
    private final PlayerEntity player;
    private final IItemHandler playerInventory;

    public static final Logger LOGGER = LogManager.getLogger();
    public static final List<List<Integer>> RECIPES = GSKOGUIUtil.makeDanmakuRecipes();

    private final IWorldPosCallable POS_CALLABLE;

    private final CraftingInventory craftingMatrix = new CraftingInventory(this, 5, 5);
    private final CraftResultInventory resultInv = new CraftResultInventory();
    private final IInventory result = new Inventory(1);
    public DanmakuCraftingContainer(int windowId, PlayerInventory playerInventory) {
        this(windowId, playerInventory, IWorldPosCallable.DUMMY);
    }

    public DanmakuCraftingContainer(int windowId, PlayerInventory playerInventory, IWorldPosCallable callable) {
        super(ContainerRegistry.DANMAKU_CRAFTING_CONTAINER.get(), windowId);
        this.player = playerInventory.player;
        this.playerInventory = new InvWrapper(playerInventory);
        this.POS_CALLABLE = callable;

        this.addPlayerInventorySlots(playerInventory, 28, 124, 124+58);
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                this.addSlot(new Slot(this.craftingMatrix, j + i * 5, 16 + j * 18, 21 + i * 18));
            }
        }
        this.addSlot(new CraftingResultSlot(playerInventory.player, this.craftingMatrix, this.resultInv, 0, 170, 58));
        // this.addSlot(new Slot(this.resultInv, 0, 165, 58));
    }


    @Override
    public boolean canInteractWith(@NotNull PlayerEntity playerIn) {
        return true;
    }

    public CraftingInventory getCraftingMatrix() {
        return this.craftingMatrix;
    }

    @Override
    public void onContainerClosed(@NotNull PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.clearContainer(playerIn, playerIn.world, this.craftingMatrix);
        this.clearContainer(playerIn, playerIn.world, this.resultInv);
    }


    protected static void updateCraftingResult(int id, World world, PlayerEntity player, CraftingInventory inventory, CraftResultInventory inventoryResult) {
        if (!world.isRemote) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)player;
            ItemStack itemstack = ItemStack.EMPTY;

            Optional<DanmakuRecipe> optional = world.getServer().getRecipeManager().getRecipes().stream().flatMap(iRecipe ->  {
                if (iRecipe instanceof DanmakuRecipe) {
                    DanmakuRecipe recipe = (DanmakuRecipe) iRecipe;
                    return DataFixUtils.orElseGet(RecipeRegistry.DANMAKU_RECIPE.matches(recipe, world, inventory).map(Stream::of),
                            Stream::empty);
                }
                return null;
            }).findFirst();
            // Optional<DanmakuRecipe> optional = world.getServer().getRecipeManager().getRecipe(RecipeRegistry.DANMAKU_RECIPE, inventory, world);

            if (optional.isPresent()) {
                DanmakuRecipe recipe = optional.get();
                if (recipe.matches(inventory, world)) {
                    itemstack = recipe.getCraftingResult(inventory);
                }
            }

            inventoryResult.setInventorySlotContents(0, itemstack);
            serverplayerentity.connection.sendPacket(new SSetSlotPacket(id, 0, itemstack));
        }
    }

    @Override
    public void onCraftMatrixChanged(@NotNull IInventory inventoryIn) {
        this.POS_CALLABLE.consume((world, pos) -> {
            updateCraftingResult(this.windowId, world, this.player, this.craftingMatrix, this.resultInv);
        });
        // if (!player.world.isRemote() && inventoryIn == this.craftingMatrix) {
        //     ServerPlayerEntity serverPlayer = (ServerPlayerEntity) this.player;
        //     // 大型星弹的槽位
        //     List<Integer> largeStarShotSlots = createRecipeIndexes(2, 7, 10, 11, 12, 13, 14, 16, 18, 20, 24);
        //     // 心弹的槽位
        //     List<Integer> heartShotSlots = createRecipeIndexes(1, 3, 5, 7, 9, 10, 14, 16, 18, 22);
        //     // 大弹的槽位
        //     List<Integer> largeShotSlots = createRecipeIndexes(0, 1, 2, 3, 4, 5, 9, 10, 14, 15, 19, 20, 24);
//
//
        //     if (matches(craftingMatrix, heartShotSlots)) {
        //         ItemStack stack = new ItemStack(ItemRegistry.HEART_SHOT.get());
        //         stack.setCount(getMinStackCount(heartShotSlots));
        //         this.resultsMatrix.setInventorySlotContents(0, stack);
        //         this.prevStacks.set(0, stack);
        //     } else if (matches(craftingMatrix, largeShotSlots)) {
        //         ItemStack stack = new ItemStack(ItemRegistry.LARGE_SHOT.get());
        //         stack.setCount(getMinStackCount(largeShotSlots));
        //         this.resultsMatrix.setInventorySlotContents(0, stack);
        //         this.prevStacks.set(0, stack);
        //     } else if (matches(craftingMatrix, largeStarShotSlots)) {
        //         ItemStack stack = new ItemStack(ItemRegistry.LARGE_STAR_SHOT.get());
        //         stack.setCount(getMinStackCount(largeStarShotSlots));
        //         this.resultsMatrix.setInventorySlotContents(0, stack);
        //         this.prevStacks.set(0, stack);
        //     } else {
        //         for (int i = 0; i < this.resultsMatrix.getSizeInventory(); i++) {
        //             this.resultsMatrix.setInventorySlotContents(i, ItemStack.EMPTY);
        //             this.prevStacks.set(i, ItemStack.EMPTY);
        //             detectAndSendChanges();
        //         }
        //     }
//
        //     boolean flag = false;
//
        //     for (int i = 0; i < this.resultsMatrix.getSizeInventory(); i++) {
        //         if (this.resultsMatrix.getStackInSlot(i).isEmpty() && !this.prevStacks.get(i).isEmpty()) {
        //             flag = true;
        //             detectAndSendChanges();
        //             break;
        //         }
        //     }
//
        //     if (flag) {
        //         for (int j = 0; j < this.craftingMatrix.getSizeInventory(); j++) {
        //             this.craftingMatrix.setInventorySlotContents(j, ItemStack.EMPTY);
        //             detectAndSendChanges();
        //         }
        //     }
        // }
    }

    private int getMinStackCount(List<Integer> stackIndexes) {
        int count = this.craftingMatrix.getStackInSlot(stackIndexes.get(0)).getCount();
        LOGGER.info(count);
        for (Integer stackIndex : stackIndexes) {
            int count1 = this.craftingMatrix.getStackInSlot(stackIndex).getCount();
            if (count1 < count) {
                count = count1;
            }
        }
        //LOGGER.info("Min count of each slot: " + count);
        return count;
    }

    private void addCraftingSlots() {
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                this.addSlot(new Slot(this.craftingMatrix, j + i * 5, 16 + j * 18, 21 + i * 18) {
                    @Override
                    @NotNull
                    public ItemStack onTake(@NotNull PlayerEntity thePlayer, @NotNull ItemStack stack) {

                        if (DanmakuCraftingContainer.this.resultInv.getStackInSlot(0) != ItemStack.EMPTY) {
                            DanmakuCraftingContainer.this.resultInv.removeStackFromSlot(0);
                        }

                        detectAndSendChanges();
                        return super.onTake(thePlayer, stack);
                    }

                    @Override
                    public void onSlotChanged() {
                        if (!(this.inventory instanceof CraftingInventory)) return;
                        CraftingInventory inv = (CraftingInventory) this.inventory;
                        World world = DanmakuCraftingContainer.this.player.world;
                        // for (int k = 0; k < inv.getSizeInventory() - 1; k++) {
                        //     GSKOUtil.showChatMsg(player, "第" + k + "个槽位是：" + inv.getStackInSlot(k), 1);
                        // }
                        updateCraftingResult(DanmakuCraftingContainer.this.windowId, world,
                                DanmakuCraftingContainer.this.player, inv,
                                DanmakuCraftingContainer.this.resultInv);
                    }
                });
            }
        }
    }

    public void fillStackedContents(RecipeItemHelper itemHelperIn) {
        this.craftingMatrix.fillStackedContents(itemHelperIn);
    }

    public void clear() {
        this.craftingMatrix.clear();
        this.resultInv.clear();
    }

    private void addResultSlots() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                addSlot(new Slot(this.resultInv, j + i * 2, 149 + j * 18, 41 + i * 18) {
                    @Override
                    @NotNull
                    public ItemStack onTake(@NotNull PlayerEntity thePlayer, @NotNull ItemStack stack) {
                        for (int k = 0; k < 25; k++) {
                            DanmakuCraftingContainer.this.resultInv.decrStackSize(k, stack.getCount());
                        }
                        detectAndSendChanges();
                        DanmakuCraftingContainer.this.onCraftMatrixChanged(DanmakuCraftingContainer.this.craftingMatrix);
                        return super.onTake(thePlayer, stack);
                    }

                });
            }

        }
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private void addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int verAmount, int dx, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
    }

    private void addSlotBox(IInventory inventory, int index, int x, int y, int horAmount, int verAmount, int dx, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
    }

    private int addSlotRange(IInventory inventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += dx;
            index++;
        }

        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 3, 18, 18);

        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    private void layoutPlayerInventorySlots(PlayerInventory playerInventory, int leftCol, int topRow) {
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 3, 18, 18);
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    private void addPlayerInventorySlots(PlayerInventory playerInventory, int left, int top, int hotBarTop) {
        for(int k = 0; k < 3; ++k) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, left + i1 * 18, top + k * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(playerInventory, l, left + l * 18, hotBarTop));
        }
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int DANMAKU_SLOT_COUNT = 29;  // must match TileEntityInventoryBasic.NUMBER_OF_SLOTS

    @Override
    @NotNull
    public ItemStack transferStackInSlot(@NotNull PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
//
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 0) {
//
                if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }
//
                slot.onSlotChange(itemstack1, itemstack);
            } else if (index >= 10 && index < 46) {
                if (!this.mergeItemStack(itemstack1, 1, 10, false)) {
                    if (index < 37) {
                        if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
                return ItemStack.EMPTY;
            }
//
            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
//
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
//
            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
            if (index == 0) {
                playerIn.dropItem(itemstack2, false);
            }
        }
//
        return itemstack;
    }

    /**
     * "__A__"<br>
     * "__A__"<br>
     * "AAAAA"    -> 合成 星弹物品<br>
     * "_A_A_"<br>
     * "A___A"<br>
     * <br><br>
     * "_A_A_"<br>
     * "A_A_A"<br>
     * "A___A"    -> 合成 心弹物品<br>
     * "_A_A_"<br>
     * "__A__"<br>
     * <br><br>
     * "AAAAA"<br>
     * "A___A"<br>
     * "A___A"    -> 合成 大弹物品<br>
     * "A___A"<br>
     * "AAAAA"<br>
     * <br><br>
     * "_A_A_"<br>
     * "A_A_A"<br>
     * "A___A"    -> 合成 心弹物品<br>
     * "_A_A_"<br>
     * "__A__"<br>
     *
     * @param slots 槽位的索引
     * @return 能够合成出物品的槽位的索引集合
     */
    private List<Integer> createRecipeIndexes(Integer... slots) {
        List<Integer> list = new ArrayList<>();
        return new ArrayList<>(Arrays.asList(slots));
    }

    private boolean matches(IInventory inventoryIn, List<Integer> list) {
        int matchCount = 0;
        for (int i : list) {
            if (inventoryIn.getStackInSlot(i).getItem() == ItemRegistry.DANMAKU_SHOT.get()) {
                matchCount++;
            }
        }
        return matchCount == list.size();
    }

    private boolean matches(IInventory inventoryIn, Map<Integer, ItemStack> stackMap) {
        int matchCount = 0;
        int emptyCount = 0;
        List<Object> list = Arrays.asList(stackMap.keySet().toArray());
        for (int i = 0; i < inventoryIn.getSizeInventory(); i++) {
            if (list.get(i).equals(i)) {
                matchCount++;
            } else {
                emptyCount++;
            }
        }
        return matchCount == stackMap.size() && matchCount + emptyCount == inventoryIn.getSizeInventory();
    }

    private boolean isResultMatrixChange(IInventory inventory) {
        for (Slot inventorySlot : this.inventorySlots) {
            return inventorySlot.getHasStack();
        }
        return false;
    }
}
