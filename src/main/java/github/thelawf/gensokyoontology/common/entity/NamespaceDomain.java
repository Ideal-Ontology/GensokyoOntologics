package github.thelawf.gensokyoontology.common.entity;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;


public class NamespaceDomain extends DomainFieldEntity{

    private final ItemStackHandler itemHandler = createItemHandler();
    private final LazyOptional<IItemHandler> optionalHandler = LazyOptional.of(() -> itemHandler);

    @Override
    public void read(CompoundNBT compound) {
        itemHandler.deserializeNBT(compound);
        super.read(compound);
    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        super.writeAdditional(compound);
    }

    public NamespaceDomain(EntityType<?> entityTypeIn, World worldIn, LivingEntity master) {
        super(entityTypeIn, worldIn, master);
    }

    private ItemStackHandler createItemHandler() {
        return new ItemStackHandler(29) {
            @Override
            protected void onContentsChanged(int slot) {
                markPositionDirty();
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {

                if (slot >= 0 && slot < 25) {
                    return stack.getItem() == ItemRegistry.DANMAKU_SHOT.get();
                }
                return super.isItemValid(slot, stack);
            }

            @Override
            public int getSlotLimit(int slot) {
                if (slot >= 0 && slot < 25) {
                    return 1;
                }
                else {
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
}
