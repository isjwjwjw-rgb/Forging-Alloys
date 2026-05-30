package net.ashleboby.forgingalloys.item;


import net.ashleboby.forgingalloys.ForgingAlloys;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
		public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ForgingAlloys.MOD_ID);
		
		public static class Tools {
		}
		
		public static class Weapons {
		}
		
		public static class Fuels {
				public static final DeferredItem<Item> STARLIGHT_ASHES = ITEMS.register("starlight_ashes",
						() -> new Item(new Item.Properties()));
		}
		
		public static class Foods {
				public static final DeferredItem<Item> RADISH = ITEMS.register("radish",
						() -> new Item(new Item.Properties().food(ModFoodProperties.RADISH)){
								@Override
								public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
										tooltipComponents.add(Component.translatable("tooltip.thingsfordays.radish.tooltip"));
										super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
								}
						});
		}
		
		public static class Resource {
				
		}
		
		public static void register(IEventBus eventBus) {
				initNestedClasses(ModItems.class);
				ITEMS.register(eventBus);
		}
		
		
		// I used CLAUDE for initNestedClasses
		private static void initNestedClasses(Class<?> clazz) {
				for (Class<?> inner : clazz.getDeclaredClasses()) {
						try {
								Class.forName(inner.getName(), true, inner.getClassLoader());
						} catch (ClassNotFoundException e) {
								throw new RuntimeException("Failed to initialize class: " + inner.getName(), e);
						}
						initNestedClasses(inner); // recurse into deeper nesting
				}
		}
}
