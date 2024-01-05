package github.thelawf.gensokyoontology.client.handler;

import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ResourceHandler implements IResourceManager {
    @Override
    public Set<String> getResourceNamespaces() {
        return null;
    }

    @Override
    public IResource getResource(ResourceLocation resourceLocationIn) throws IOException {
        return null;
    }

    @Override
    public boolean hasResource(ResourceLocation path) {
        return false;
    }

    @Override
    public List<IResource> getAllResources(ResourceLocation resourceLocationIn) throws IOException {
        return null;
    }

    @Override
    public Collection<ResourceLocation> getAllResourceLocations(String pathIn, Predicate<String> filter) {
        return null;
    }

    @Override
    public Stream<IResourcePack> getResourcePackStream() {
        return null;
    }
}
