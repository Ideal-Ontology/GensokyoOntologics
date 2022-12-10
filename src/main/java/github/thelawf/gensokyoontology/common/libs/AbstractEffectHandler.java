package github.thelawf.gensokyoontology.common.libs;

public abstract class AbstractEffectHandler implements IEffectHandler{
    @Override
    public String getEntityId(String entityId) {
        return IEffectHandler.super.getEntityId(entityId);
    }

    @Override
    public String getPlayerName(String playerName) {
        return IEffectHandler.super.getPlayerName(playerName);
    }
}
