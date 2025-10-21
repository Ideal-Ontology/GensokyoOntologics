package github.thelawf.gensokyoontology.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraft.util.math.vector.Vector3d;
import org.jetbrains.annotations.NotNull;

public class CatmullRomSpline {
    public Vector3d ctrl1;
    public Vector3d start;
    public Vector3d end;
    public Vector3d ctrl2;

    public static final CatmullRomSpline EMPTY = new CatmullRomSpline(Vector3d.ZERO, Vector3d.ZERO, Vector3d.ZERO, Vector3d.ZERO);

    public CatmullRomSpline(Vector3d ctrl1, Vector3d start, Vector3d end, Vector3d ctrl2) {
        this.ctrl1 = ctrl1;
        this.start = start;
        this.end = end;
        this.ctrl2 = ctrl2;
    }

    public static CatmullRomSpline create(Vector3d ctrl1, Vector3d start, Vector3d end, Vector3d ctrl2) {
        return new CatmullRomSpline(ctrl1, start, end, ctrl2);
    }

    public Vector3d getCtrl1() {
        return ctrl1;
    }

    public Vector3d getStart() {
        return start;
    }

    public Vector3d getEnd() {
        return end;
    }

    public Vector3d getCtrl2() {
        return ctrl2;
    }

    public static CompoundNBT serializeNBT(CatmullRomSpline spline) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putDouble("ctrl1X", spline.ctrl1.x);
        nbt.putDouble("ctrl1Y", spline.ctrl1.y);
        nbt.putDouble("ctrl1Z", spline.ctrl1.z);

        nbt.putDouble("startX", spline.start.x);
        nbt.putDouble("startY", spline.start.y);
        nbt.putDouble("startZ", spline.start.z);

        nbt.putDouble("endX",spline.end.x);
        nbt.putDouble("endY",spline.end.y);
        nbt.putDouble("endZ",spline.end.z);

        nbt.putDouble("ctrl2X",spline.ctrl2.x);
        nbt.putDouble("ctrl2Y",spline.ctrl2.y);
        nbt.putDouble("ctrl2Z",spline.ctrl2.z);

        return nbt;
    }

    public static CatmullRomSpline deserializeNBT(CompoundNBT nbt) {
        Vector3d ctrl1 = new Vector3d(nbt.getDouble("ctrl1X"), nbt.getDouble("ctrl1Y"), nbt.getDouble("ctrl1Z"));
        Vector3d start = new Vector3d(nbt.getDouble("startX"), nbt.getDouble("startY"), nbt.getDouble("startZ"));
        Vector3d end = new Vector3d(nbt.getDouble("endX"), nbt.getDouble("endY"), nbt.getDouble("endZ"));
        Vector3d ctrl2 = new Vector3d(nbt.getDouble("ctrl2X"), nbt.getDouble("ctrl2Y"), nbt.getDouble("ctrl2Z"));

        return new CatmullRomSpline(ctrl1, start, end, ctrl2);
    }

    public static class Deserializer implements IDataSerializer<CatmullRomSpline> {
        @Override
        public void write(PacketBuffer buf, CatmullRomSpline value) {
            buf.writeDouble(value.ctrl1.x);
            buf.writeDouble(value.ctrl1.y);
            buf.writeDouble(value.ctrl1.z);

            buf.writeDouble(value.start.x);
            buf.writeDouble(value.start.y);
            buf.writeDouble(value.start.z);

            buf.writeDouble(value.end.x);
            buf.writeDouble(value.end.y);
            buf.writeDouble(value.end.z);

            buf.writeDouble(value.ctrl2.x);
            buf.writeDouble(value.ctrl2.y);
            buf.writeDouble(value.ctrl2.z);
        }

        @Override
        public @NotNull CatmullRomSpline read(PacketBuffer buf) {
            Vector3d ctrl1 = new Vector3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
            Vector3d start = new Vector3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
            Vector3d end = new Vector3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
            Vector3d ctrl2 = new Vector3d(buf.readDouble(), buf.readDouble(), buf.readDouble());

            float time = buf.readFloat();
            return new CatmullRomSpline(ctrl1, start, end, ctrl2);
        }

        @Override
        public @NotNull CatmullRomSpline copyValue(CatmullRomSpline value) {
            return new CatmullRomSpline(value.ctrl1, value.start, value.end, value.ctrl2);
        }
    }
}
