package net.sobe.andesitegolem;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.sobe.andesitegolem.entity.ModEntities;
import net.sobe.andesitegolem.entity.client.AndesiteGolemRenderer;

public class AndesiteGolemClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.ANDESITEGOLEM, AndesiteGolemRenderer::new);
    }
}
