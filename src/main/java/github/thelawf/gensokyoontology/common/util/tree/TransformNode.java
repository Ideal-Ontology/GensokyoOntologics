package github.thelawf.gensokyoontology.common.util.tree;

import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TransformNode<O> extends Vector3d implements ITreeNode<O> {

    public O object;
    public String name;
    public int id;
    public boolean isRoot;

    public TransformNode<O> parent;

    public List<TransformNode<O>> ancestors = new ArrayList<>();
    public List<TransformNode<O>> children = new ArrayList<>();

    public TransformNode(double x, double y, double z, String name) {
        super(x, y, z);
        this.name = name;
    }

    public TransformNode(Vector3f vector3f, String name) {
        super(vector3f);
        this.name = name;
    }

    @Override
    public O get() {
        return this.object;
    }

    @Override
    public void addChild(TransformNode<O> child) {
        if (contains(child.name)) {
            throw new IllegalArgumentException("The Name of the Child Node already exists.");
        }
        child.parent = this;
        this.children.add(child);
    }

    @Override
    public boolean contains(String nameIn) {

        boolean b1 = Objects.equals(this.name, nameIn);
        boolean b2 = false;
        boolean b3 = false;

        if (!b1 && this.ancestors.size() == 0 && this.children.size() == 0)
            return false;

        if (this.ancestors.size() != 0) {
            for (TransformNode<O> node : this.ancestors) {
                if (node.contains(nameIn)) {
                    b2 = true;
                    break;
                }
            }
        }

        for (TransformNode<O> node : this.children) {
            if (node.contains(nameIn)) {
                b3 = true;
                break;
            }
        }
        return b1 || b2 || b3;
    }

    @Override
    public boolean hasName(String nameIn) {
        return this.name.equals(nameIn);
    }

    @Override
    public TransformNode<O> getNode(String nameIn) {
        if (this.ancestors.size() != 0) {
            for (TransformNode<O> node : this.ancestors) {
                if (node.contains(nameIn)) {
                    return node;
                }
            }
        }

        for (TransformNode<O> node : this.children) {
            if (node.contains(nameIn)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public boolean hasChild() {
        return this.children.size() > 0;
    }

    @Override
    public boolean hasParent() {
        return this.parent != null;
    }

    @Override
    public boolean hasAncestors() {
        return this.ancestors.size() > 0;
    }
}
