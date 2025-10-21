package github.thelawf.gensokyoontology.client.settings;


import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.settings.IKeyConflictContext;

/**
 * @see org.lwjgl.glfw.GLFW GLFW 中的输入映射值
 * @see InputMappings InputMappings: Minecraft 中的输入映射表
 */
@OnlyIn(Dist.CLIENT)
public class GSKOKeyBinding extends KeyBinding {

    public GSKOKeyBinding(String description, IKeyConflictContext keyConflictContext, InputMappings.Type inputType, int keyCode, String category) {
        super(description, keyConflictContext, inputType, keyCode, category);
    }
}
