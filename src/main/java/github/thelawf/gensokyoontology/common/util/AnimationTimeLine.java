package github.thelawf.gensokyoontology.common.util;

import github.thelawf.gensokyoontology.common.util.danmaku.TransformFunction;

import java.util.HashMap;

/** 模仿动画窗格添加的关键帧映射，表示弹幕在这一个游戏刻时所需要进行的操作 */
public class AnimationTimeLine {
    int tickExisted = 0;
    private TransformFunction behavior;

    public TransformFunction getBehavior() {
        HashMap map = new HashMap<>();
        return behavior;
    }

    public void setBehavior(TransformFunction behavior) {
        this.behavior = behavior;
    }
}
