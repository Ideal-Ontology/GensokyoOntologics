package github.thelawf.gensokyoontology.data.expression;

import com.mojang.serialization.MapCodec;
import net.minecraftforge.registries.IForgeRegistryEntry;

public interface IExpressionType extends IForgeRegistryEntry<IExpressionType> {

    MapCodec<? extends IExpressionType> type();

    ExpressionType<?> getExp();
}
