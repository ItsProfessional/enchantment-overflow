package me.proferk.enchantmentoverflow.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @ModifyArgs(method = "addEnchantment", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;createNbt(Lnet/minecraft/util/Identifier;I)Lnet/minecraft/nbt/NbtCompound;"))
    private void removeByteCast(Args args, Enchantment enchantment, int level)
    {
        // The byte cast needs to be removed, to remove byte's limit of 127

        // replaces: EnchantmentHelper.createNbt(EnchantmentHelper.getEnchantmentId(enchantment), (byte)level)
        // with    : EnchantmentHelper.createNbt(EnchantmentHelper.getEnchantmentId(enchantment), level)

        args.set(1, level);
    }

}
