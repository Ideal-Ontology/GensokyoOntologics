package github.thelawf.gensokyoontology.data;

import github.thelawf.gensokyoontology.common.capability.entity.VillagerOrder;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraft.util.math.vector.Quaternion;
import org.jetbrains.annotations.NotNull;

public class GSKOSerializers {

    public static final IDataSerializer<VillagerOrder> VILLAGER_ORDER = new  IDataSerializer<VillagerOrder>() {
        @Override
        public void write(PacketBuffer buf, VillagerOrder value) {
            buf.writeCompoundTag(value.serializeNBT());
        }

        @Override
        public VillagerOrder read(PacketBuffer buf) {
            return VillagerOrder.deserialize(buf.readCompoundTag());
        }

        @Override
        public VillagerOrder copyValue(VillagerOrder value) {
            VillagerOrder order = new VillagerOrder();
            order.setAppetizer(value.getAppetizer());
            order.setDessert(value.getDessert());
            order.setDrinks(value.getDrinks());
            order.setEntrees(value.getEntrees());
            order.setFavouriteTags(value.getFavouriteTags());
            return order;
        }
    };

    public static final IDataSerializer<Quaternion> QUATERNION = new IDataSerializer<Quaternion>() {
        public void write(PacketBuffer buf, Quaternion value) {
            buf.writeFloat(value.getX());
            buf.writeFloat(value.getY());
            buf.writeFloat(value.getZ());
            buf.writeFloat(value.getW());
        }

        public @NotNull Quaternion read(PacketBuffer buf) {
            return new Quaternion(buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat());
        }

        public @NotNull Quaternion copyValue(@NotNull Quaternion value) {
            return value.copy();
        }
    };
}
