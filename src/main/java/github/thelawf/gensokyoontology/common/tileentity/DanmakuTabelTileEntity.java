package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.container.DanmakuCraftingContainer;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DanmakuTabelTileEntity extends TileEntity {
    public DanmakuTabelTileEntity() {
        super(TileEntityRegistry.DANMAKU_TABLE_TILE.get());
    }

    private final ItemStackHandler itemHandler = createItemHandler();
    private final LazyOptional<IItemHandler> optionalHandler = LazyOptional.of(() -> itemHandler);
    public static final TranslationTextComponent CONTAINER_NAME = new TranslationTextComponent("container." +
            GensokyoOntology.MODID + ".danmaku_craft.title");

    public static INamedContainerProvider createContainer(World worldIn, BlockPos posIn) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return CONTAINER_NAME;
            }

            @Nullable
            @Override
            public Container createMenu(int winwdowId, PlayerInventory playerInventory, PlayerEntity player) {
                return new DanmakuCraftingContainer(winwdowId, playerInventory);
            }
        };
    }

    @Override
    public void read(@NotNull BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.read(state, nbt);
    }

    @Override
    @NotNull
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        return super.write(compound);
    }

    private ItemStackHandler createItemHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {

                if (slot >= 0 && slot < 2) {
                    return stack.getItem() == ItemRegistry.DANMAKU_SHOT.get();
                }
                return super.isItemValid(slot, stack);
            }

            @Override
            public int getSlotLimit(int slot) {
                if (slot >= 0 && slot < 2) {
                    return 1;
                } else {
                    return super.getSlotLimit(slot);
                }
            }

            @NotNull
            @Override
            public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {

        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return optionalHandler.cast();
        }
        return super.getCapability(cap);
    }


    public void tryCraft(World world) {
        Inventory inv = new Inventory(this.itemHandler.getSlots());
        for (int i = 0; i < this.itemHandler.getSlots() - 1; i++)
            inv.setInventorySlotContents(i, this.itemHandler.getStackInSlot(i));
        if (world.isRemote) return;

        world.getServer().getRecipeManager().getRecipe(RecipeRegistry.DANMAKU_RECIPE, inv, world)
                .ifPresent(recipe -> {
                    ItemStack outputs = recipe.getRecipeOutput();
                    outputs.grow(recipe.getMinOutputCount(inv));
                    Block.spawnAsEntity(world, this.pos, outputs);
                });
        markDirty();

    }
}
