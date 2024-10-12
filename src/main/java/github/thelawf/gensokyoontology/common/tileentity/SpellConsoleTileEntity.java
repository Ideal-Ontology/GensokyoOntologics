package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.container.SpellCardConsoleContainer;
import github.thelawf.gensokyoontology.common.item.script.DynamicScriptItem;
import github.thelawf.gensokyoontology.common.item.script.ScriptBuilderItem;
import github.thelawf.gensokyoontology.common.item.script.ScriptReadOnlyItem;
import github.thelawf.gensokyoontology.common.item.spellcard.SpellCardItem;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
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
import org.jetbrains.annotations.Nullable;

// TODO: 符卡控制台可以处理插入的物品，为其附加NBT数据
public class SpellConsoleTileEntity extends TileEntity implements ITickableTileEntity {
    public final int slotCount = 31;
    private final ItemStackHandler itemHandler = createItemHandler();
    private final LazyOptional<IItemHandler> optionalHandler = LazyOptional.of(() -> itemHandler);
    public static final TranslationTextComponent CONTAINER_NAME = GensokyoOntology.fromLocaleKey("container.", ".spell_card_console.title");
    public SpellConsoleTileEntity() {
        super(TileEntityRegistry.SPELL_CONSOLE_TILE_ENTITY.get());
    }

    @Override
    public void read(@NotNull BlockState state, CompoundNBT nbt) {
        this.itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.read(state, nbt);
    }

    @Override
    @NotNull
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", this.itemHandler.serializeNBT());
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
                if (slot == this.getSlots() - 1) {
                    return stack.getItem() == ItemRegistry.SCRIPTED_SPELL_CARD.get();
                }
                return stack.getItem() instanceof ScriptReadOnlyItem ||
                        stack.getItem() instanceof DynamicScriptItem ||
                        stack.getItem() instanceof ScriptBuilderItem ||
                        stack.getItem() instanceof SpellCardItem;
                // return stack.getItem() == ItemRegistry.TIME_STAMP.get() &&
                //         stack.getItem() == ItemRegistry.V3D_BUILDER.get() &&
                //         stack.getItem() == ItemRegistry.CONST_BUILDER.get() &&
                //         stack.getItem() == ItemRegistry.BINARY_OPERATION_BUILDER.get();
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
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

    public static INamedContainerProvider create(World worldIn, BlockPos posIn) {
        return new INamedContainerProvider() {
            @Override
            @NotNull
            public ITextComponent getDisplayName() {
                return CONTAINER_NAME;
            }

            @NotNull
            @Override
            public Container createMenu(int windowsId, @NotNull PlayerInventory playerInventory, @NotNull PlayerEntity player) {
                return new SpellCardConsoleContainer(windowsId, player, worldIn, posIn);
            }
        };
    }
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return optionalHandler.cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {

    }

    public boolean isAllowedItem(int index, IItemHandler itemHandler) {
        return itemHandler.isItemValid(index, itemHandler.getStackInSlot(index));
    }

    public boolean hasAllowedTag(int index, IItemHandler itemHandler) {
        CompoundNBT nbt = getTag(index, itemHandler);
        return nbt.keySet().contains("type") && nbt.keySet().contains("value") || nbt.keySet().contains("name");
    }

    public CompoundNBT getTag(int index, IItemHandler itemHandler) {
        return itemHandler.getStackInSlot(index).getTag();
    }

    public ItemStack getOutputStack(IItemHandler itemHandler) {
        return itemHandler.getStackInSlot(itemHandler.getSlots() - 1);
    }

    public IItemHandler getItemHandler() {
        return this.itemHandler;
    }
}
