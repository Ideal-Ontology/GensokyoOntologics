package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.data.expression.ByteInfo;
import github.thelawf.gensokyoontology.data.expression.ConstExpression;
import github.thelawf.gensokyoontology.data.expression.ExpressionType;
import github.thelawf.gensokyoontology.data.expression.IExpressionType;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.*;

public class DataRegistry {
    public static final RegistryKey<Registry<IExpressionType>> EXP_KEY = RegistryKey.getOrCreateRootKey(
            GensokyoOntology.withRL("expression"));
    public static final IForgeRegistry<IExpressionType> EXPRESSION_TYPES = RegistryManager.ACTIVE.getRegistry(IExpressionType.class);
    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS,
            GensokyoOntology.MODID);
    public static final DeferredRegister<IExpressionType> EXPRESSIONS = DeferredRegister.create(EXPRESSION_TYPES,
            GensokyoOntology.MODID);

    public static final RegistryObject<? extends IExpressionType> CONST = EXPRESSIONS.register("const", () -> new ConstExpression(ByteInfo.of(0)));

}
