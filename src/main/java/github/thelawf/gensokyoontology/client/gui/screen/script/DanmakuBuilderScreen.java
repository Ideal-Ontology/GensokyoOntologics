package github.thelawf.gensokyoontology.client.gui.screen.script;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class DanmakuBuilderScreen extends ScriptBuilderScreen {
    private CompoundNBT danmakuData = new CompoundNBT();

    public DanmakuBuilderScreen(ITextComponent titleIn, ItemStack stack, LivingEntity thrower, DanmakuType danmakuType, DanmakuColor danmakuColor) {
        super(titleIn, stack);
    }
}
