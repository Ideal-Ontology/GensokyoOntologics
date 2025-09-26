package github.thelawf.gensokyoontology.core;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.data.GSKOSerializers;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DataSerializerEntry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DataRegistry {
    public static final DeferredRegister<DataSerializerEntry> SERIALIZERS = DeferredRegister.create(
            ForgeRegistries.DATA_SERIALIZERS, GensokyoOntology.MODID);

    public static final RegistryObject<DataSerializerEntry> QUAT_SERIALIZER = SERIALIZERS.register(
            "quaternion", () -> new DataSerializerEntry(GSKOSerializers.QUATERNION)
    );
}
