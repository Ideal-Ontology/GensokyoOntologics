package github.thelawf.gensokyoontology.common.item.touhou;

/*
public class RealismSword extends SwordItem {
    // 攻击速度冷却秒数计算方式：1 / 攻击间隔，保留一位一位小数
    public RealismSword() {
        super(GSKOItemTier.REALISM, 3, -2.4F, new Item.Properties().group(
                GSKOItemTab.GSKO_ITEM_TAB));
    }

    @Override
    public boolean hitEntity(@Nonnull ItemStack stack,@Nonnull LivingEntity entity,@Nonnull LivingEntity attacker){
        boolean flag = true;
        if (attacker instanceof PlayerEntity) {
            if (attacker.swingProgress > 0.2) {
                flag = false;
            }
        }
        if (!attacker.world.isRemote && flag) {
            ArrayList<LightningBoltEntity> boltEntityList = new ArrayList<>();
            ArrayList<Vector3d> v3dList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                LightningBoltEntity lightning = EntityType.LIGHTNING_BOLT.create(entity.world);
                Vector3d vec = entity.getPositionVec();
                Vector3d v3d = attacker.getPositionVec();
                Vector3d vectorPlus = new Vector3d(Math.abs(vec.x - v3d.x), v3d.y, Math.abs(vec.z - v3d.z));
                v3dList.add(new Vector3d(vectorPlus.x + v3d.x, v3d.y, vectorPlus.z + v3d.z));
                Objects.requireNonNull(lightning).moveForced(v3dList.get(i));
                boltEntityList.add(lightning);
            }

            if (!entity.world.isRemote) {
                for (LightningBoltEntity e : boltEntityList) {
                    entity.world.addEntity(e);
                }
            }
        }
        return super.hitEntity(stack, entity, attacker);
    }

}

 */
