package com.kakyoin17.cherrytinker.content;

import com.kakyoin17.cherrytinker.Cherrytinker;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.definition.IMaterial;
import slimeknights.tconstruct.library.tools.helper.ToolBuildHandler;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.part.IMaterialItem;

import java.util.List;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Cherrytinker.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CHERRY_TAB = TABS.register("cherry_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.cherrytinker"))
            .icon(() -> new ItemStack(ModItems.CHERRY.get()))
            .displayItems((parameters, output) -> {
                for(RegistryObject<Item> entry : ModItems.ITEMS.getEntries()){
                    Item item = entry.get();
                    if(entry.getId().getPath().equals("cherry_bush")){
                        continue;
                    }
                    if (item instanceof IModifiable modifiableItem) {
                        ToolBuildHandler.addVariants(output::accept, (IModifiable) item, "");
                    }else if (item instanceof IMaterialItem part){
                        for (IMaterial material : MaterialRegistry.getMaterials()) {
                            if (!material.isHidden() && part.canUseMaterial(material)) {
                                output.accept(part.withMaterial(material.getIdentifier()));
                            }
                        }
                    }
                    else {
                        output.accept(item);
                    }
                }
                List<Item> casts = ModItems.SK_BLADE_CAST.values();
                for (Item item : casts) {
                    output.accept(item);
                }
            }).build());

    public static void register(IEventBus eventBus) {TABS.register(eventBus);}
}
