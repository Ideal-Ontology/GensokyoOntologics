package github.thelawf.gensokyoontology.core;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DataSerializerEntry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SerializerRegistry {

    public static final DeferredRegister<DataSerializerEntry> SPELL_DATA_SERIALIZER = DeferredRegister.create(
            ForgeRegistries.DATA_SERIALIZERS, GensokyoOntology.MODID);

}
