package github.thelawf.gensokyoontology.client.gui.screen.skill;

import github.thelawf.gensokyoontology.common.item.touhou.HakureiGohei;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.InputMappings;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HakureiModeSelectScreen extends MultiSelectScreen{
    private HakureiGohei.Mode mode;
    public HakureiModeSelectScreen(ITextComponent titleIn, HakureiGohei.Mode mode) {
        super(titleIn);
        this.mode = mode;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public void switchMode(int index) {
        this.mode = EnumUtil.moveTo(HakureiGohei.Mode.class, this.mode, index);
    }

    public void setMode(HakureiGohei.Mode mode) {
        this.mode = mode;
    }

    public HakureiGohei.Mode getMode() {
        return this.mode;
    }
}
