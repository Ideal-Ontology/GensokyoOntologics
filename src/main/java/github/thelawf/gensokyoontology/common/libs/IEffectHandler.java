package github.thelawf.gensokyoontology.common.libs;

public interface IEffectHandler {

    default IEffectHandler getInstance() {
        return this;
    }

    default String getPlayerName(String playerName) {
        return playerName;
    }

    default String getEntityId(String entityId){
        return entityId;
    }
}
