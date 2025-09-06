package github.thelawf.gensokyoontology.common.tileentity;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import github.thelawf.gensokyoontology.data.recipe.AltarRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AltarTileEntity extends TileEntity implements ITickableTileEntity {
    private final ItemStackHandler itemHandler = createItemHandler();
    private final LazyOptional<IItemHandler> optionalHandler = LazyOptional.of(() -> itemHandler);
    public static final TranslationTextComponent CONTAINER_NAME = new TranslationTextComponent("container." +
            GensokyoOntology.MODID + ".altar_table.title");

    public AltarTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
    public void checkCraft() {
        Inventory inv = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots() - 1; i++)
            inv.setInventorySlotContents(i, itemHandler.getStackInSlot(i));
    }

    //TODO: 添加合成配方和战利品表
    public void tryCraft(){
        if (this.world == null) return;
        if (this.world.isRemote) return;

        ServerWorld serverWorld = (ServerWorld)this.world;
        AltarRecipe.getInstance(serverWorld, new Inventory(this.itemHandler.getSlots()), this.pos.down());
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

    @Override
    public void tick() {

    }

    private ItemStackHandler createItemHandler() {
        int slotCount = 9;
        return new ItemStackHandler(slotCount) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return true;
            }

        };
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return optionalHandler.cast();
        return super.getCapability(cap, side);
    }


    public static List<Pair<Pair<Integer, Integer>, ItemStack>> getMaterialsOfPos(World world, BlockPos center) {
        List<Pair<Pair<Integer, Integer>, ItemStack>> items = new ArrayList<>();
        for (int z = 1; z <= 3; z++) {
            for (int i = 0; i < 8; i++) {
                int x = ONBASHIRA_POS.get(i).getFirst();
                int y = ONBASHIRA_POS.get(i).getSecond();
                if (z == 3){
                    int index = i;
                    GSKOUtil.getTileByType(world, center.add(x, y, z), TileEntityRegistry.ONBASHIRA_TILE_ENTITY.get())
                            .ifPresent(onbashira -> items.add(Pair.of(ONBASHIRA_POS.get(index),
                                    onbashira.getMaterial())));
                }
            }
        }
        return items;
    }

    public static final List<Pair<Integer, Integer>> ONBASHIRA_POS = ImmutableList.of(
            Pair.of(-1, -2), Pair.of(1, -2),
            Pair.of(-2, -1),     Pair.of(2, -1),
            Pair.of(-2,  1),     Pair.of(2,  1),
            Pair.of(-1 , 2), Pair.of(1,  2)
    );
}
