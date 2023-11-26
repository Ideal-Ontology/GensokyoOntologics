var SmallShot = Java.type("github.thelawf.gensokyoontology.common.entity.projectile.SmallShotEntity");
var Vector3d = Java.type("net.minecraft.util.math.vector.Vector3d");
var DanmakuType = Java.type("github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType");
var DanmakuColor = Java.type("github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor");

function onScriptTick (world, owner, ticksExisted) {
    var center = new Vector3d(1, 0, 0);
    var local = center.add(4, 0, 0).rotateYaw((float)(Math.PI / 60 * ticksExisted));
    var global = local.add(this.getPositionVec());

    for (var i = 0; i < 8; i++) {
        var smallShot = new SmallShot(owner, world, DanmakuType.SMALL_SHOT, DanmakuColor.RED);
        var vector3d = center.rotateYaw(Math.PI / 4 * i).rotateYaw(Math.PI / 100 * ticksExisted);
        smallShot.setLocationAndAngles(global.x, global.y, global.z, center.y, center.z);
        smallShot.setNoGravity(true);

        smallShot.shoot(vector3d.x, 0, vector3d.z, 0.5, 0);
        world.addEntity(smallShot);
    }
}