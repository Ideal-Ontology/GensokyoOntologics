package github.thelawf.gensokyoontology.core;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.libs.danmakulib.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.*;

import java.util.ArrayList;
import java.util.HashMap;

public class SpellCardRegistry {

    public static final IForgeRegistry<SpellData> SPELL_CARD_REGISTRY;



    static {
        SPELL_CARD_REGISTRY = new RegistryBuilder<SpellData>()
                .setDefaultKey(new ResourceLocation("spell_data"))
                .setType(SpellData.class)
                .create();
    }

    public static final DeferredRegister<SpellData> SPELL_DATA = DeferredRegister.create(
            SPELL_CARD_REGISTRY,GensokyoOntology.MODID);

    public static final RegistryObject<SpellData> IDO_NO_KAIHO_DATA = SPELL_DATA.register(
            "ido_no_kaiho_data", SpellCardRegistry::initSpell);

    private static SpellData initSpell() {
        HashMap<Integer, TransformFunction> functions = new HashMap<>();
        ArrayList<VectorOperations> orders = new ArrayList<>();

        orders.add(VectorOperations.ROTATE_YAW);
        orders.add(VectorOperations.VECTOR_SCALE);
        orders.add(VectorOperations.VECTOR_ADD);

        TransformFunction function = TransformFunction.Builder.create()
                .setYaw((float) Math.PI / 2)
                .setScaling(0.8F)
                .setTransformOrders(orders);

        functions.put(1, function);
        return new SpellData(functions, DanmakuType.HEART_SHOT, DanmakuColor.PINK, false, false);
    }
}
