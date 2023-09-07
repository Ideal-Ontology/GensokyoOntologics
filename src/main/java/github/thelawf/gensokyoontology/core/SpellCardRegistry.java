package github.thelawf.gensokyoontology.core;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.SpellData;
import github.thelawf.gensokyoontology.common.util.danmaku.TransformFunction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

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

    public static final RegistryObject<SpellData> IDO_NO_KAIHO_MONSTER = SPELL_DATA.register(
            "ido_no_kaiho_monster", SpellCardRegistry::initMonsterSpell);

    private static SpellData initMonsterSpell() {
        HashMap<Integer, TransformFunction> functions = new HashMap<>();
        TransformFunction function = TransformFunction.Builder.create()
                .setInitLocation(Vector3d.ZERO)
                .setShootVector(Vector3d.ZERO);

        return new SpellData(functions, DanmakuType.HEART_SHOT, DanmakuColor.PINK, true, true);
    }

    private static SpellData initSpell() {
        HashMap<Integer, TransformFunction> functions = new HashMap<>();
        // ArrayList<VectorOperations> orders = new ArrayList<>();
//
        // orders.add(VectorOperations.ROTATE_YAW);
        // orders.add(VectorOperations.VECTOR_SCALE);
        // orders.add(VectorOperations.VECTOR_ADD);

        TransformFunction function = TransformFunction.Builder.create()
                .setYaw((float) Math.PI / 2)
                .setScaling(0.8F);

        functions.put(1, function);
        return new SpellData(functions, DanmakuType.HEART_SHOT, DanmakuColor.PINK, false, false);
    }
}
