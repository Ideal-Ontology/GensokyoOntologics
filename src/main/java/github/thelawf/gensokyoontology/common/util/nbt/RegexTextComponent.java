package github.thelawf.gensokyoontology.common.util.nbt;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.NBTTextComponent;
import net.minecraft.util.text.TextComponent;

import java.util.stream.Stream;

public abstract class RegexTextComponent extends TextComponent implements IFormattableTextComponent {

    public abstract Object parse(String regex);
}
