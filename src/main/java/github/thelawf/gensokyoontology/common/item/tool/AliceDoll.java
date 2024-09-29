package github.thelawf.gensokyoontology.common.item.tool;

import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

// 用长枪的是上海人偶
public abstract class AliceDoll extends Item {
    private final ResourceLocation dollName;
    public final CompoundNBT dollData = new CompoundNBT();
    public AliceDoll(ResourceLocation dollName) {
        super(new Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB).maxStackSize(1));
        this.dollName = dollName;
    }

    public ResourceLocation getDollName() {
        return this.dollName;
    }
    public abstract void dollBehaviour(World worldIn, LivingEntity living, ItemStack itemStack);
}
