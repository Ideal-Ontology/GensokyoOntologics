package github.thelawf.gensokyoontology.common.screen.container;

import github.thelawf.gensokyoontology.common.util.TriMap;
import github.thelawf.gensokyoontology.core.ContainerRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.*;

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

    private final CraftingInventory craftingMatrix = new CraftingInventory(this, 5, 5);
    private final Inventory resultsMatrix = new Inventory(4);

    public DanmakuCraftingContainer(int windowId,
                                       PlayerInventory playerInventory,
                                       PlayerEntity player) {
        super(ContainerRegistry.DANMAKU_CRAFTING_CONTAINER.get(), windowId);
        // this.fieldEntity = (DomainFieldEntity) world.getEntityByID(entityId);
        this.player = player;
        this.playerInventory = new InvWrapper(playerInventory);

        layoutPlayerInventorySlots(28, 124);

        addSlotBox(this.craftingMatrix, 0, 16, 20,5,5,18,18);
        addSlotBox(this.resultsMatrix, 0, 149, 41, 2,2,18,18);

    }

    @Override
    public boolean canInteractWith(@NotNull PlayerEntity playerIn) {
        return true;
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        //playerIn.inventory.addItemStackToInventory(craftingMatrix.getStackInSlot())
    }

    @Override
    public void onCraftMatrixChanged(@NotNull IInventory inventoryIn) {
        super.onCraftMatrixChanged(inventoryIn);
        // 大型星弹的槽位
        List<Integer> starShotSlots = createRecipeIndexes(2,7,10,11,12,13,14,16,18,19,23);
        // 心弹的槽位
        List<Integer> heartShotSlots = createRecipeIndexes(1,3,5,7,9,10,14,16,18,22);
        // 大弹的槽位
        List<Integer> largeShotSlots = createRecipeIndexes(0,1,2,3,4,5,9,10,14,15,19,20,24);

        LOGGER.info("Matches Heart = " + matches(inventoryIn, heartShotSlots));
        LOGGER.info("Matches Large = " + matches(inventoryIn, largeShotSlots));
        if (matches(inventoryIn, heartShotSlots)) {
            this.resultsMatrix.setInventorySlotContents(0, new ItemStack(ItemRegistry.HEART_SHOT_ITEM.get()));
        }
        else if (matches(inventoryIn, largeShotSlots)) {
            this.resultsMatrix.setInventorySlotContents(0, new ItemStack(ItemRegistry.LARGE_SHOT_ITEM.get()));
        }
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
     * @param slots 槽位的索引
     * @return 能够合成出物品的槽位的索引集合
     */
    private List<Integer> createRecipeIndexes(Integer... slots) {
        List<Integer> list = new ArrayList<>();
        return new ArrayList<>(Arrays.asList(slots));
    }

    private List<TriMap<Integer, String, ItemStack>> createRecipeMap(int index, String pattern, ItemStack stack) {
        List<TriMap<Integer, String, ItemStack>> list = new ArrayList<>();
        TriMap<Integer, String, ItemStack> triMap = new TriMap<>();
        triMap.put(index, pattern, stack);
        list.add(triMap);
        return list;
    }

    private boolean matches (IInventory inventoryIn, List<Integer> list) {
        int matchCount = 0;
        for (int i : list) {
            if (inventoryIn.getStackInSlot(i).getItem() == ItemRegistry.DANMAKU_SHOT.get()) {
                matchCount++;
            }
        }
        return matchCount == list.size();
    }


    private void addIngredientSlots (PlayerEntity entity) {
        entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(ih ->
                addSlotBox(ih, 61, 16,21, 5, 5, 18,18));
    }

    private void addResultSlots(PlayerEntity entity) {
        entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(ih ->
                addSlotBox(ih, 36, 149, 41, 2,2, 18, 18));
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }

        return index;
    }

    private void addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int verAmount,int dx,  int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
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

    private void addSlotBox(IInventory inventory, int index, int x, int y, int horAmount, int verAmount,int dx,  int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }

    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 3, 18, 18);

        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
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
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 0) {

                if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

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

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
            if (index == 0) {
                playerIn.dropItem(itemstack2, false);
            }
        }

        return itemstack;
    }
}
