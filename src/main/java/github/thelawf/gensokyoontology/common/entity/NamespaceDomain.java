package github.thelawf.gensokyoontology.common.entity;

import github.thelawf.gensokyoontology.common.screen.container.DanmakuCraftingContainer;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class NamespaceDomain extends DomainFieldEntity {

    public static final EntityType<NamespaceDomain> NAMESPACE_DOMAIN = EntityType.Builder.<NamespaceDomain>create(
                    NamespaceDomain::new, EntityClassification.CREATURE).updateInterval(2)
            .size(64f, 64f).trackingRange(10).build("citizen");


    private final ItemStackHandler itemHandler = createItemHandler();
    private final LazyOptional<IItemHandler> optionalHandler = LazyOptional.of(() -> itemHandler);

    public NamespaceDomain(EntityType<NamespaceDomain> type, World world) {
        super(type, world);
    }


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

    @Override
    @NotNull
    public ActionResultType applyPlayerInteraction(PlayerEntity player, Vector3d vec, Hand hand) {
        if (player.isSneaking()) {
            return ActionResultType.PASS;
        }
        Logger LOGGER = LogManager.getLogger();
        LOGGER.info("Player interacted");
        if (!world.isRemote()) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            NetworkHooks.openGui(serverPlayer, createContainer(world, this.entityBlockPosition));

        }
        return super.applyPlayerInteraction(player, vec, hand);
    }

    public static INamedContainerProvider createContainer(World worldIn, BlockPos posIn) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("container.gensokyoontology.danmaku_craft.title");
            }

            @Nullable
            @Override
            public Container createMenu(int winwdowId, PlayerInventory playerInventory, PlayerEntity player) {
                return new DanmakuCraftingContainer(winwdowId, playerInventory, player);
            }
        };
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
