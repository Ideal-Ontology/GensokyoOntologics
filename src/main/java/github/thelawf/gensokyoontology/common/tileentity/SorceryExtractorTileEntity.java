package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.container.SorceryExtractorContainer;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import github.thelawf.gensokyoontology.data.recipe.SorceryExtractorRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
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

import javax.annotation.Nullable;
import java.util.Optional;

public class SorceryExtractorTileEntity extends TileEntity implements ITickableTileEntity {
    private final int slotCount = 5;
    private final ItemStackHandler itemHandler = createItemHandler();
    private final LazyOptional<IItemHandler> optionalHandler = LazyOptional.of(() -> itemHandler);
    public static final TranslationTextComponent CONTAINER_NAME = new TranslationTextComponent("container." +
            GensokyoOntology.MODID + ".sorcery_extractor.title");

    public SorceryExtractorTileEntity() {
        super(TileEntityRegistry.SORCERY_EXTRACTOR_TILE_ENTITY.get());
    }

    public static INamedContainerProvider createContainer(World worldIn, BlockPos posIn) {
        return new INamedContainerProvider() {
            @Override
            @NotNull
            public ITextComponent getDisplayName() {
                return CONTAINER_NAME;
            }

            @Override
            public Container createMenu(int windowId, @NotNull PlayerInventory playerInventory, @NotNull PlayerEntity player) {
                return new SorceryExtractorContainer(windowId, worldIn, posIn, playerInventory);
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
        return new ItemStackHandler(this.slotCount) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return true;
            }

            @Override
            public int getSlotLimit(int slot) {
                if (slot >= 0 && slot < 25) {
                    return 1;
                } else {
                    return super.getSlotLimit(slot);
                }
            }
        };
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return optionalHandler.cast();
        return super.getCapability(cap, side);
    }

    public void checkCraft() {
        Inventory inv = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots() - 1; i++)
            inv.setInventorySlotContents(i, itemHandler.getStackInSlot(i));

        Optional<SorceryExtractorRecipe> recipe = world.getRecipeManager().getRecipe(RecipeRegistry.SORCERY_RECIPE, inv, world);
        recipe.ifPresent(this::doCraft);
    }

    public void doCraft(SorceryExtractorRecipe recipe) {
        itemHandler.insertItem(4, recipe.getRecipeOutput(), false);
        markDirty();
    }

    @Override
    public void tick() {
        if (world != null && world.isRemote) return;
        checkCraft();
    }
}
