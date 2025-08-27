package github.thelawf.gensokyoontology.common.tileentity;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import github.thelawf.gensokyoontology.data.recipe.DanmakuRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DanmakuTabelTileEntity extends TileEntity {

    public DanmakuTabelTileEntity() {
        super(TileEntityRegistry.DANMAKU_TABLE_TILE.get());
    }
    private float powerStored = 0F;
    private final ItemStackHandler itemHandler = createItemHandler();
    private final LazyOptional<IItemHandler> optionalHandler = LazyOptional.of(() -> itemHandler);
    public static final TranslationTextComponent CONTAINER_NAME = new TranslationTextComponent("container." +
            GensokyoOntology.MODID + ".danmaku_craft.title");


    public float getPower() {
        return this.powerStored;
    }

    public void setPower(float power){
        this.powerStored = power;
    }

    @Override
    public void read(@NotNull BlockState state, CompoundNBT nbt) {
        this.itemHandler.deserializeNBT(nbt.getCompound("inv"));
        this.setPower(nbt.getFloat("power"));
        super.read(state, nbt);
    }

    @Override
    @NotNull
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", this.itemHandler.serializeNBT());
        compound.putFloat("power", this.getPower());
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
                return stack.getItem() == ItemRegistry.DANMAKU_SHOT.get();
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

    public void tryCraft(World world, PlayerEntity player, boolean shouldCraftAll) {
        Inventory inv = new Inventory(this.itemHandler.getSlots());
        inv.setInventorySlotContents(0, this.itemHandler.getStackInSlot(0));

        if (world.isRemote) return;
        if (world.getServer() == null) return;

        ServerWorld serverWorld = (ServerWorld) world;
        Optional<DanmakuRecipe> optional = DanmakuRecipe.getInstance(serverWorld, inv, this.pos.down());
        if (!optional.isPresent()) return;

        //FIXME:
        //TODO:
        // 1. 提供网络发包来向客户端同步能力数据
        // 2. GUI图案显示错误，需要提供 server -> client 的网络发包来渲染当前拼图样式
        DanmakuRecipe recipe = optional.get();
        ItemStack stack = this.itemHandler.getStackInSlot(0);
        if (stack.isEmpty())
            return;

        if (this.getRemainingPower(recipe, shouldCraftAll) < 0) {
            GSKOUtil.showChatMsg(player, GensokyoOntology.translate("error.",".danmaku_tile.no_enough_power"), 1);
            return;
        }

        ItemStack outputs = recipe.getRecipeOutput();
        outputs.setCount(shouldCraftAll ? this.getMaxOutputCount(recipe) : 1);

        this.consume(recipe, shouldCraftAll);
        Block.spawnAsEntity(world, this.pos.up(), outputs);

        markDirty();
        world.getServer().getRecipeManager().getRecipe(RecipeRegistry.DANMAKU_RECIPE, inv, world);
    }

    public float getRemainingPower(DanmakuRecipe recipe, boolean shouldCraftAll) {
        return this.getPower() - (shouldCraftAll ?
                this.getMaxOutputCount(recipe) * recipe.getUnitCount() * 0.1F :
                recipe.getUnitCount() * 0.1F);
    }

    /**
     * @return 最多生成多少个物品
     */
    public int getMaxOutputCount(DanmakuRecipe recipe) {
        int totalInvCount = this.itemHandler.getStackInSlot(0).getCount();
        return totalInvCount / recipe.getUnitCount();
    }

    /**
     * 根据是否应该全部合成的布尔值来判断应该将方块实体槽位中的物品剩余数量设置为多少
     */
    public void consume(DanmakuRecipe recipe, boolean shouldCraftAll) {
        ItemStack prev = this.itemHandler.getStackInSlot(0);
        this.setPower(this.getRemainingPower(recipe, shouldCraftAll));
        this.itemHandler.setStackInSlot(0, this.getRemainingItem(recipe, prev.getItem(), shouldCraftAll));
    }

    /**
     * @param recipe 弹幕合成配方
     * @param shouldCraftAll 是否应该全部合成，为 true 则返回 原数量 - 最大合成数量 * 每次合成消耗的单位量。为 false 则返回 原数量 - 每次合成的消耗量
     * @return 根据是否应该全部合成的布尔值来判断方块实体中的物品应该剩余多少
     */
    public ItemStack getRemainingItem(DanmakuRecipe recipe, Item item, boolean shouldCraftAll) {
        return new ItemStack(item, this.itemHandler.getStackInSlot(0).getCount() - (shouldCraftAll ?
                (this.getMaxOutputCount(recipe) * recipe.getUnitCount()) : recipe.getUnitCount()));
    }

    public List<Block> getJigsawPattern(){
        if (this.world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) this.world;
            Optional<DanmakuRecipe> recipe = DanmakuRecipe.getInstance(serverWorld,
                    new Inventory(this.itemHandler.getSlots()), this.pos.down());
            return recipe.<List<Block>>map(DanmakuRecipe::getJigsawPattern).orElse(new ArrayList<>());
        }
        return new ArrayList<>();
    }

    private Optional<DanmakuRecipe> getRecipe() {
        if (this.world.isRemote) return Optional.empty();
        if (this.world.getServer() == null) return Optional.empty();

        ServerWorld serverWorld = (ServerWorld) world;
        Optional<DanmakuRecipe> optional = DanmakuRecipe.getInstance(serverWorld,
                new Inventory(this.itemHandler.getSlots()), this.pos.down());
        return optional;
    }

    public Pair<Float, Integer> getConsumption() {
        DanmakuRecipe recipe = this.getRecipe().orElse(null);
        if (recipe == null) return Pair.of(0F, 0);
        return Pair.of(recipe.getUnitCount() * 0.1F, recipe.getUnitCount());
    }

    @Override
    @NotNull
    public CompoundNBT getUpdateTag() {
        CompoundNBT compound = new CompoundNBT();
        this.write(compound);
        return compound;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.read(this.getBlockState(), pkt.getNbtCompound());
    }

    @Override
    public @Nullable SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 1, this.getUpdateTag());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 128.0D;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        this.read(state, tag);
    }
}
