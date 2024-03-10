package github.thelawf.gensokyoontology.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public abstract class AffiliatedEntity extends Entity {

    private UUID ownerId;
    public static final DataParameter<Optional<UUID>> DATA_OWNER = EntityDataManager.createKey(AffiliatedEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);

    public AffiliatedEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public AffiliatedEntity(EntityType<?> entityTypeIn, UUID ownerId, World worldIn) {
        super(entityTypeIn, worldIn);
        this.ownerId = ownerId;
    }

    @Override
    protected void registerData() {
        this.dataManager.register(DATA_OWNER, Optional.empty());
    }

    protected void readAdditional(@NotNull CompoundNBT compound) {
        UUID uuid = null;
        if (compound.hasUniqueId("Owner")) {
            uuid = compound.getUniqueId("Owner");
        }

        if (uuid != null) {
            this.setDataOwner();
        }
    }

    protected void writeAdditional(@NotNull CompoundNBT compound) {
        if (this.ownerId != null) {
            compound.putUniqueId("Owner", this.ownerId);
        }
    }

    public Optional<UUID> getOwnerID() {
        return this.dataManager.get(DATA_OWNER);
    }

    public void setOwnerId(UUID uuid) {
        if (this.ownerId != null) {
            this.dataManager.set(DATA_OWNER, Optional.of(uuid));
        }
    }

    public void setDataOwner() {
        if (this.world instanceof ServerWorld) {
            Optional<UUID> optionalUUID = this.getDataManager().get(DATA_OWNER);
            optionalUUID.ifPresent(this::setOwnerId);
        }
    }

    public Optional<UUID> getOwnerId() {
        if (this.world instanceof ServerWorld) {
            Optional<UUID> optionalUUID = this.getDataManager().get(DATA_OWNER);
            if (optionalUUID.isPresent()) {
                return optionalUUID;
            }
        }
        return Optional.empty();
    }

    @Override
    @NotNull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
