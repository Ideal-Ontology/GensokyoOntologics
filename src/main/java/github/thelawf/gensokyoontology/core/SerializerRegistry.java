package github.thelawf.gensokyoontology.core;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuUtil;
import github.thelawf.gensokyoontology.common.libs.danmakulib.SpellData;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DataSerializerEntry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SerializerRegistry {

    public static final DeferredRegister<DataSerializerEntry> SPELL_DATA_SERIALIZER = DeferredRegister.create(
            ForgeRegistries.DATA_SERIALIZERS, GensokyoOntology.MODID);

    public static final RegistryObject<DataSerializerEntry> SPELL_DATA = SPELL_DATA_SERIALIZER.register(
            "spell_data_serializer", () -> new DataSerializerEntry(DanmakuUtil.SPELL_DATA));
}
