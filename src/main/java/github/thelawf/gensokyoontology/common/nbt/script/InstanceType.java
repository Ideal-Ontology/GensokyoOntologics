package github.thelawf.gensokyoontology.common.nbt.script;

public enum InstanceType {
    VECTOR3D("vector3d"),
    DANMAKU("danmaku"),
    NULLABLE("nullable");
    public final String name;

    InstanceType(String name) {
        this.name = name;
    }

}
