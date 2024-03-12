package github.thelawf.gensokyoontology.client.gui.screen.script;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class RefWorldScreen extends ScriptBuilderScreen{
    private final CompoundNBT refName = new CompoundNBT();
    private TextFieldWidget nameInput;
    private World world;
    private final ITextComponent worldTip = GensokyoOntology.withTranslation("gui.",".ref_world.tip");
    protected RefWorldScreen(ITextComponent titleIn, ItemStack stack, World world) {
        super(titleIn, stack);
        this.world = world;
    }

    @Override
    protected void init() {
        super.init();
    }
}
