package github.thelawf.gensokyoontology.common.item.script;

import com.mojang.serialization.Dynamic;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.world.World;

public abstract class ScriptReadOnlyItem extends Item {
    public ScriptReadOnlyItem() {
        super(new Properties());
    }

    public abstract void addReadOnlyData(World world, PlayerEntity player, ItemStack stack, CompoundNBT nbt);
}
