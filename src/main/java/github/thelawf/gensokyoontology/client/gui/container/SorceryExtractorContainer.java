package github.thelawf.gensokyoontology.client.gui.container;

import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 各个槽位的位置：<br>
 * 上方槽位：(46, 3)<br>
 * 左边槽位：(1, 47)<br>
 * 【合成结果槽位】中间槽位：(46, 47)<br>
 * 右边槽位：(91, 47)<br>
 * 下方槽位：(46, 92)<br>
 */
public class SorceryExtractorContainer extends Container {
    private final PlayerEntity player;
    private final IItemHandler playerInventory;

    //public static final Logger LOGGER = LogManager.getLogger();
    public List<List<ItemStack>> recipes = new ArrayList<>();

    private final IWorldPosCallable POS_CALLABLE = IWorldPosCallable.DUMMY;

    private List<ItemStack> prevStacks = new ArrayList<>();

    private final Inventory ingredientInventory = new Inventory(4);
    private final Inventory resultInventory = new Inventory(1);
    public SorceryExtractorContainer(int id, PlayerInventory playerInventory, PlayerEntity player) {
        super(ContainerRegistry.SORCERY_EXTRACTOR_CONTAINER.get(), id);
        this.player = player;
        this.playerInventory = new InvWrapper(playerInventory);

        addSlot(new Slot(this.ingredientInventory, 0, 46, 3));
        addSlot(new Slot(this.ingredientInventory, 1, 1, 47));
        addSlot(new Slot(this.ingredientInventory, 2, 91, 47));
        addSlot(new Slot(this.ingredientInventory, 3, 46, 92));
        addResultSlot(this.resultInventory, 4, 46, 47);

        this.recipes.add(createRecipe(
                new ItemStack(ItemRegistry.ORB_JADE.get()),
                new ItemStack(ItemRegistry.TALES_SCARLET_MIST.get()),
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                new ItemStack(ItemRegistry.SORCERY_SCARLET_MIST.get())));

        this.recipes.add(createRecipe(
                new ItemStack(Items.ENDER_EYE),
                new ItemStack(ItemRegistry.GITSUNE_TUBE_FULL.get()),
                new ItemStack(ItemRegistry.TALES_OCCULT_BALL.get()),
                ItemStack.EMPTY,
                new ItemStack(ItemRegistry.SORCERY_SCARLET_MIST.get())));
    }

    @Override
    public void onContainerClosed(@NotNull PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.clearContainer(playerIn, playerIn.world, this.ingredientInventory);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        super.onCraftMatrixChanged(inventoryIn);
        for (List<ItemStack> recipe : this.recipes) {
            if (matches(this.ingredientInventory, recipe)) {
                this.resultInventory.setInventorySlotContents(0, recipe.get(4));
            }
        }
    }


    private void addResultSlot(IInventory inventory, int index, int xPos, int yPos) {
        addSlot(new Slot(inventory, index, xPos, yPos){
            @Override
            public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
                for (int i = 0; i < 5; i++) {
                    SorceryExtractorContainer.this.ingredientInventory.removeStackFromSlot(i);
                }
                detectAndSendChanges();
                SorceryExtractorContainer.this.onCraftMatrixChanged(
                        SorceryExtractorContainer.this.ingredientInventory);
                return super.onTake(thePlayer, stack);
            }
        });
    }

    public static List<ItemStack> createRecipe(ItemStack upSlot, ItemStack leftSlot,
                                        ItemStack rightSlot, ItemStack downSlot, ItemStack result) {
        List<ItemStack> stacks = new ArrayList<>();
        stacks.add(upSlot);
        stacks.add(leftSlot);
        stacks.add(rightSlot);
        stacks.add(downSlot);
        stacks.add(result);
        return stacks;
    }

    private boolean matches(IInventory inventory, List<ItemStack> recipes) {
        int totalCount = 0;
        int matchCount = 0;
        //for (int i = 0; i < recipes.size()-1; i++) {
        //    if (recipes.get(i).hasTag()) {
        //
        //    }
        //}
        for (int i = 0; i < 4; i++) {
            if (!recipes.get(i).equals(ItemStack.EMPTY)) {
                totalCount++;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (inventory.getStackInSlot(i).equals(recipes.get(i))) {
                matchCount++;
            }
        }
        return totalCount == matchCount;
    }
}
