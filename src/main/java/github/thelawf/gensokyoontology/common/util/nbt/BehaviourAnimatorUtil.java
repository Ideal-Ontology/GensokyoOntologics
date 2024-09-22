package github.thelawf.gensokyoontology.common.util.nbt;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.ListNBT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BehaviourAnimatorUtil {

    public static class Builder{
        private String type;
        private final List<Behaviour> behaviours = new ArrayList<>();
        private final CompoundNBT scriptNBT = new CompoundNBT();

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder addBehaviour(int keyTick, Map<String, List<Double>> params) {
            this.behaviours.add(Behaviour.of(keyTick, params));
            return this;
        }

        public CompoundNBT build() {
            ListNBT listNBT = new ListNBT();
            this.scriptNBT.putString("type", this.type);
            this.behaviours.forEach(behaviour -> listNBT.add(behaviour.asCompound()));
            this.scriptNBT.put("behaviours", listNBT);
            return this.scriptNBT;
        }
    }

    public static class Behaviour {
        private int keyTick;
        private Map<String, List<Double>> functions;
        private final CompoundNBT behaviourNBT = new CompoundNBT();

        public Behaviour(int keyTick, Map<String, List<Double>> functions) {
            this.keyTick = keyTick;
            this.functions = functions;
        }

        public static Behaviour of(int keyTick, Map<String, List<Double>> functions) {
            return new Behaviour(keyTick, functions);
        }


        public CompoundNBT asCompound() {
            this.behaviourNBT.putInt("keyTick", this.keyTick);
            functions.forEach((key, doubles) -> {
                ListNBT listNBT = new ListNBT();
                doubles.forEach(d -> listNBT.add(DoubleNBT.valueOf(d)));
                this.behaviourNBT.put(key, listNBT);
            });
            return this.behaviourNBT;
        }
    }
}
