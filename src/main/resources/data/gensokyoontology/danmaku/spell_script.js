const SmallShot = Java.type("github.thelawf.gensokyoontology.common.entity.projectile.SmallShotEntity");
const Vector3d = Java.type("net.minecraft.util.math.vector.Vector3d");
const DanmakuType = Java.type("github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType");
const DanmakuColor = Java.type("github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor");

const onScriptTick = function (world, owner, ticksExisted) {
    let center = new Vector3d(1, 0, 0);
    const local = center.add(4, 0, 0).rotateYaw((float)(Math.PI / 60 * ticksExisted));
    const global = local.add(this.getPositionVec());

    for (let i = 0; i < 8; i++) {
        const small_shot = new SmallShot(owner, world, DanmakuType.SMALL_SHOT, DanmakuColor.RED);
        let vector3d = center.rotateYaw(Math.PI / 4 * i).rotateYaw(Math.PI / 100 * ticksExisted);
        smallShot.setLocationAndAngles(global.x, global.y, global.z, center.y, center.z);
        smallShot.setNoGravity(true);

        smallShot.shoot(vector3d.x, 0, vector3d.z, 0.5, 0);
        world.addEntity(smallShot);
    }
};