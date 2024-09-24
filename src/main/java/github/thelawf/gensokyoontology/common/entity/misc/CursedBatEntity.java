package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.core.init.StructureRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

public class CursedBatEntity extends BatEntity implements IFlyingAnimal {
    private static final DataParameter<BlockPos> MANSION_POS = EntityDataManager.createKey(CursedBatEntity.class,
            DataSerializers.BLOCK_POS);
    private static final DataParameter<Boolean> HAS_DESTINATION = EntityDataManager.createKey(CursedBatEntity.class,
            DataSerializers.BOOLEAN);

    private boolean hasDestination;
    private BlockPos mansionPos;
    static {
        new BlockPos(0, 0, 0);
    }

    public CursedBatEntity(EntityType<? extends BatEntity> type, World worldIn, BlockPos mansionPos) {
        super(type, worldIn);
        this.mansionPos = mansionPos;
    }

    public CursedBatEntity(EntityType<CursedBatEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new BatFlyToTargetGoal(this));
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(MANSION_POS, this.mansionPos);
        this.dataManager.register(HAS_DESTINATION, this.hasDestination);
    }

    public BlockPos getMansionPos() {
        return this.dataManager.get(MANSION_POS);
    }

    public void setMansionPos(BlockPos mansionPos) {
        this.mansionPos = mansionPos;
        this.dataManager.set(MANSION_POS, this.mansionPos);
    }

    public boolean hasDestination() {
        return this.hasDestination;
    }

    public void setHasDestination(boolean hasDestination) {
        this.hasDestination = hasDestination;
        this.dataManager.set(HAS_DESTINATION, hasDestination);
    }

    @Override
    public void writeAdditional(@NotNull CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putLong("mansion_pos", this.getMansionPos().toLong());
        compound.putBoolean("has_destination", this.hasDestination);
    }

    @Override
    public void readAdditional(@NotNull CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("mansion_pos")) {
            this.setMansionPos(BlockPos.fromLong(compound.getLong("mansion_pos")));
        }
        if (compound.contains("has_destination")) {
            this.hasDestination = compound.getBoolean("mansion_pos");
            this.dataManager.set(HAS_DESTINATION, this.hasDestination);
        }
    }


    static class BatFlyToTargetGoal extends Goal {
        private Path path;
        private final CursedBatEntity bat;
        public BatFlyToTargetGoal(CursedBatEntity bat) {
            this.bat = bat;
        }

        @Override
        public void tick() {
            super.tick();
            Vector3d vector3d = new Vector3d(this.bat.getMansionPos().getX(), this.bat.getMansionPos().getY(), this.bat.getMansionPos().getZ());
            final float speed = 0.6f;
            this.bat.getLookController().setLookPosition(vector3d);
            this.bat.getNavigator().tryMoveToXYZ(vector3d.x, vector3d.y, vector3d.z, speed);
            this.bat.setNoGravity(true);
        }

        @Override
        public void startExecuting() {
            super.startExecuting();
            if (!this.bat.world.isRemote && !this.bat.hasDestination) {
                ServerWorld serverWorld = (ServerWorld) this.bat.world;
                this.bat.setMansionPos(serverWorld.getStructureLocation(StructureRegistry.SCARLET_DEVIL_MANSION.get(), this.bat.getPosition(), 100, false));
                this.bat.setHasDestination(true);
            }
        }

        @Override
        public boolean shouldExecute() {
            return this.bat.hasDestination();
        }
    }
}
