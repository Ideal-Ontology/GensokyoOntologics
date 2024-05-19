package github.thelawf.gensokyoontology.common.util.math;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;

public class GeometryUtil {
    public static final double PHI = (1 + Math.sqrt(5)) / 2;
    public static final double PHI_RECIPROCAL = 1 / PHI;

    public static void latitudeSphere(IVertexBuilder builder, Matrix4f matrix4f, int meridianCount, int parallelCount,
                                      float radius, float red, float green, float blue, float alpha) {
        for (int i = 0; i < meridianCount; i++) {
            double b = radius / i;
            double f = GSKOMathUtil.pow2(radius) + GSKOMathUtil.pow2(radius - b);

            double hypotenuse = Math.sqrt(f + GSKOMathUtil.pow2(b));
            double latitude = Math.acos(radius / hypotenuse);
            // double a = Math.sqrt(f);
            // double beta = Math.acos(f / Math.sqrt(f * (f + GSKOMathUtil.pow2(b))));
        }
    }

    /**
     * 创建一个二十面体的三角面顶点组
     * @param builder 顶点构建器
     * @param matrix4f 位置渲染矩阵
     * @param a 二十面体的周长
     */
    public static void regularIcosahedron(IVertexBuilder builder, Matrix4f matrix4f, float a,
                                      float red, float green, float blue, float alpha) {
        // final double scale = (a * Math.sqrt(2)) / (Math.sqrt(5 + Math.sqrt(5)));
        final double scale = 1;
        final double phiS = scale * PHI;
        final double phiSR = scale * PHI_RECIPROCAL;
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(scale, scale, scale), of(scale, scale, -scale), of(0, phiS, phiSR));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(scale, scale, scale), of(0, phiS, phiSR), of(-scale, scale, scale));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(scale, scale, scale), of(-scale, scale, scale), of(phiS, phiSR, 0));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(scale, scale, scale), of(phiS, phiSR, 0), of(phiSR, 0, phiS));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(scale, scale, scale), of(phiSR, 0, phiS), of(scale, scale, -scale));

        triangularFace(builder, matrix4f, red, green, blue, alpha, of(scale, scale, -scale), of(phiSR, 0, -phiS), of(0, phiS, -phiSR));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(scale, scale, -scale), of(0, phiS, -phiSR), of(0, phiS, phiSR));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(scale, -scale, scale), of(0, -phiS, phiSR), of(-scale, -scale, scale));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(scale, -scale, scale), of(-scale, -scale, scale), of(-phiSR, 0, phiS));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(scale, -scale, scale), of(-phiSR, 0, phiS), of(phiS, -phiSR, 0));

        triangularFace(builder, matrix4f, red, green, blue, alpha, of(scale, -scale, scale), of(phiS, -phiSR, 0), of(phiSR, 0, phiS));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(scale, -scale, scale), of(phiSR, 0, phiS), of(0, -phiS, phiSR));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(scale, -scale, -scale), of(-scale, scale, -scale), of(0, -phiS, -phiSR));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(scale, -scale, -scale), of(0, -phiS, -phiSR), of(-phiSR, 0, -phiS));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(scale, -scale, -scale), of(-phiSR, 0, -phiS), of(-scale, -scale, -scale));

        triangularFace(builder, matrix4f, red, green, blue, alpha, of(scale, -scale, -scale), of(-scale, -scale, -scale), of(phiSR, 0, -phiS));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(scale, -scale, -scale), of(phiSR, 0, -phiS), of(-scale, scale, -scale));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(-scale, scale, scale), of(-phiS, phiSR, 0), of(-phiSR, 0, phiS));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(-scale, scale, scale), of(-phiSR, 0, phiS), of(-scale, -scale, scale));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(-scale, scale, -scale), of(0, -phiS, -phiSR), of(-phiSR, 0, -phiS));
    }

    /**
     * 创建一个二十面体的三角面顶点组
     * @param builder 顶点构建器
     * @param matrix4f 位置渲染矩阵
     */
    public static void regularIcosahedron(IVertexBuilder builder, Matrix4f matrix4f,
                                          float red, float green, float blue, float alpha) {
        // final double scale = (a * Math.sqrt(2)) / (Math.sqrt(5 + Math.sqrt(5)));

        triangularFace(builder, matrix4f, red, green, blue, alpha, of(0, 1, PHI), of(0, 1, -PHI), of(1, PHI, 0));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(0, 1, PHI), of(0, 1, -PHI), of(-1, PHI, 0));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(0, 1, PHI), of(1, PHI, 0), of(PHI, 0, 1));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(0, 1, PHI), of(-1, PHI, 0), of(-PHI, 0, 1));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(0, 1, -PHI), of(PHI, 0, -1), of(1, PHI, 0));

        triangularFace(builder, matrix4f, red, green, blue, alpha, of(0, 1, -PHI), of(-1, PHI, 0), of(-PHI, 0, -1));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(0, -1, PHI), of(0, -1, -PHI), of(1, -PHI, 0));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(0, -1, PHI), of(0, -1, -PHI), of(-1, -PHI, 0));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(0, -1, PHI), of(1, -PHI, 0), of(PHI, 0, 1));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(0, -1, PHI), of(-1, -PHI, 0), of(-PHI, 0, 1));

        triangularFace(builder, matrix4f, red, green, blue, alpha, of(0, -1, -PHI), of(PHI, 0, -1), of(1, -PHI, 0));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(0, -1, -PHI), of(-1, -PHI, 0), of(-PHI, 0, -1));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(1, PHI, 0), of(1, -PHI, 0), of(PHI, 0, 1));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(1, PHI, 0), of(PHI, 0, -1), of(1, -PHI, 0));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(-1, PHI, 0), of(-1, -PHI, 0), of(-PHI, 0, 1));

        triangularFace(builder, matrix4f, red, green, blue, alpha, of(-1, PHI, 0), of(-1, -PHI, 0), of(-PHI, 0, -1));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(PHI, 0, 1), of(PHI, 0, -1), of(-PHI, 0, 1));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(PHI, 0, 1), of(-PHI, 0, 1), of(0, 1, PHI));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(PHI, 0, -1), of(-PHI, 0, -1), of(0, 1, -PHI));
        triangularFace(builder, matrix4f, red, green, blue, alpha, of(-PHI, 0, 1), of(-PHI, 0, -1), of(0, -1, PHI));
    }

    public static void renderIcosahedron(IVertexBuilder builder, Matrix4f matrix4f, float size,
                                          float red, float green, float blue, float alpha) {
        // final double scale = (a * Math.sqrt(2)) / (Math.sqrt(5 + Math.sqrt(5)));
        float X = 0.525731112119133606f * size;
        float Z = 0.850650808352039932f * size;

        float[][] vertices = {
                {-X, 0.0f, Z}, {X, 0.0f, Z}, {-X, 0.0f, -Z}, {X, 0.0f, -Z},
                {0.0f, Z, X}, {0.0f, Z, -X}, {0.0f, -Z, X}, {0.0f, -Z, -X},
                {Z, X, 0.0f}, {-Z, X, 0.0f}, {Z, -X, 0.0f}, {-Z, -X, 0.0f}
        };

        int[][] faces = {
                {0, 4, 1}, {0, 9, 4}, {9, 5, 4}, {4, 5, 8}, {4, 8, 1},
                {8, 10, 1}, {8, 3, 10}, {5, 3, 8}, {5, 2, 3}, {2, 7, 3},
                {7, 10, 3}, {7, 6, 10}, {7, 11, 6}, {11, 0, 6}, {0, 1, 6},
                {6, 1, 10}, {9, 0, 11}, {9, 11, 2}, {9, 2, 5}, {7, 2, 11}
        };

        for (int[] face : faces) {
            renderTriangle(matrix4f, builder, vertices[face[0]], vertices[face[1]], vertices[face[2]],
                    red, green, blue, alpha);
        }
    }

    private static void renderTriangle(Matrix4f matrix, IVertexBuilder vertexBuilder, float[] v1, float[] v2, float[] v3,
                                       float red, float green, float blue, float alpha) {
        addVertex(matrix, vertexBuilder, v1, red, green, blue, alpha);
        addVertex(matrix, vertexBuilder, v2, red, green, blue, alpha);
        addVertex(matrix, vertexBuilder, v3, red, green, blue, alpha);

        addVertex(matrix, vertexBuilder, v1, red, green, blue, alpha);
        addVertex(matrix, vertexBuilder, v3, red, green, blue, alpha);
        addVertex(matrix, vertexBuilder, v2, red, green, blue, alpha);

        addVertex(matrix, vertexBuilder, v3, red, green, blue, alpha);
        addVertex(matrix, vertexBuilder, v2, red, green, blue, alpha);
        addVertex(matrix, vertexBuilder, v1, red, green, blue, alpha);

        addVertex(matrix, vertexBuilder, v2, red, green, blue, alpha);
        addVertex(matrix, vertexBuilder, v1, red, green, blue, alpha);
        addVertex(matrix, vertexBuilder, v3, red, green, blue, alpha);
    }

    private static void addVertex(Matrix4f matrix, IVertexBuilder vertexBuilder, float[] pos, float red, float green, float blue, float alpha) {
        vertexBuilder.pos(matrix, pos[0], pos[1], pos[2])
                .color(red, green, blue, alpha)
                .endVertex();
    }

    public static void triangularFace(IVertexBuilder builder, Matrix4f matrix4f, Vector3f a, Vector3f b, Vector3f c) {
        builder.pos(matrix4f, a.getX(), a.getY(), a.getZ()).endVertex();
        builder.pos(matrix4f, b.getX(), b.getY(), b.getZ()).endVertex();
        builder.pos(matrix4f, c.getX(), c.getY(), c.getZ()).endVertex();

        builder.pos(matrix4f, c.getX(), c.getY(), c.getZ()).endVertex();
        builder.pos(matrix4f, b.getX(), b.getY(), b.getZ()).endVertex();
        builder.pos(matrix4f, a.getX(), a.getY(), a.getZ()).endVertex();
    }

    public static Vector3f of(double x, double y, double z) {
        return new Vector3f((float) x, (float) y, (float) z);
    }

    public static void triangularFace(IVertexBuilder builder, Matrix4f matrix4f, float red, float green, float blue, float alpha,
                                      Vector3f a, Vector3f b, Vector3f c) {
        builder.pos(matrix4f, a.getX(), a.getY(), a.getZ()).color(red, green, blue, alpha).endVertex();
        builder.pos(matrix4f, b.getX(), b.getY(), b.getZ()).color(red, green, blue, alpha).endVertex();
        builder.pos(matrix4f, c.getX(), c.getY(), c.getZ()).color(red, green, blue, alpha).endVertex();

        builder.pos(matrix4f, c.getX(), c.getY(), c.getZ()).color(red, green, blue, alpha).endVertex();
        builder.pos(matrix4f, b.getX(), b.getY(), b.getZ()).color(red, green, blue, alpha).endVertex();
        builder.pos(matrix4f, a.getX(), a.getY(), a.getZ()).color(red, green, blue, alpha).endVertex();
    }
}
