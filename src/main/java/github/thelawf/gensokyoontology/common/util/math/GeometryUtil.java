package github.thelawf.gensokyoontology.common.util.math;

import com.ibm.icu.impl.coll.Collation;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GeometryUtil {
    public static final double PHI = (1 + Math.sqrt(5)) / 2;
    public static final double PHI_RECIPROCAL = 1 / PHI;

    /**
     * 创建一个二十面体的三角面顶点组
     * @param builder 顶点构建器
     * @param matrix4f 位置渲染矩阵
     * @param a 二十面体的周长
     */
    private static void regularIcosahedron(IVertexBuilder builder, Matrix4f matrix4f, float a,
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

    public static void renderIcosahedron(IVertexBuilder builder, Matrix4f matrix4f, float size, int subdivisionLevel,
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

        // subdivide(vertices, faces, subdivisionLevel);

        for (int[] face : faces) {
            renderTriangle(matrix4f, builder, vertices[face[0]], vertices[face[1]], vertices[face[2]],
                    red, green, blue, alpha);
        }
    }


    public static void longitudeSphere(IVertexBuilder builder, Matrix4f matrix4f, int latitudeBands, int longitudeBands, float radius,
                                             float r, float g, float b, float a) {
        float[][] vertices = longitudeSphereVertices(latitudeBands, longitudeBands, radius);
        int[][] faces = longitudeSphereFaces(latitudeBands, longitudeBands);

        for (int[] face : faces) {
            renderTriangle(matrix4f, builder, vertices[face[0]], vertices[face[1]], vertices[face[2]], r, g, b, a);
        }
    }

    private static float[][] longitudeSphereVertices(int latitudeBands, int longitudeBands, float radius) {
        int vertexCount = (latitudeBands + 1) * (longitudeBands + 1);
        float[][] vertices = new float[vertexCount][3];

        int index = 0;
        for (int latNumber = 0; latNumber <= latitudeBands; latNumber++) {
            float theta = (float) (latNumber * Math.PI / latitudeBands);
            float sinTheta = (float) Math.sin(theta);
            float cosTheta = (float) Math.cos(theta);

            for (int longNumber = 0; longNumber <= longitudeBands; longNumber++) {
                float phi = (float) (longNumber * 2 * Math.PI / longitudeBands);
                float sinPhi = (float) Math.sin(phi);
                float cosPhi = (float) Math.cos(phi);

                float x = cosPhi * sinTheta;
                float y = cosTheta;
                float z = sinPhi * sinTheta;
                vertices[index][0] = radius * x;
                vertices[index][1] = radius * y;
                vertices[index][2] = radius * z;
                index++;
            }
        }
        return vertices;
    }

    private static int[][] longitudeSphereFaces(int latitudeBands, int longitudeBands) {
        int indexCount = latitudeBands * longitudeBands * 6;
        int[][] indices = new int[indexCount / 3][3];

        int index = 0;
        for (int latNumber = 0; latNumber < latitudeBands; latNumber++) {
            for (int longNumber = 0; longNumber < longitudeBands; longNumber++) {
                int first = (latNumber * (longitudeBands + 1)) + longNumber;
                int second = first + longitudeBands + 1;

                indices[index][0] = first;
                indices[index][1] = second;
                indices[index][2] = first + 1;
                index++;

                indices[index][0] = second;
                indices[index][1] = second + 1;
                indices[index][2] = first + 1;
                index++;
            }
        }
        return indices;
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
