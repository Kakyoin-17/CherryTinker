package com.kakyoin17.cherrytinker.content;

import com.kakyoin17.cherrytinker.Cherrytinker;
import com.kakyoin17.cherrytinker.modifier.*;
import net.minecraftforge.eventbus.api.IEventBus;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class ModModifiers {
    //
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(Cherrytinker.MOD_ID);
    //
    public static StaticModifier<Modifier> recovery =MODIFIERS.register("recovery", RecoveryModifier::new);
    public static StaticModifier<Modifier> cherrygemattack =MODIFIERS.register("cherrygemattack", CherrygemAttack::new);
    public static StaticModifier<Modifier> egold_p =MODIFIERS.register("egold_p", Egold_P::new);
    public static StaticModifier<Modifier> egold_a =MODIFIERS.register("egold_a", Egold_A::new);
    public static StaticModifier<Modifier> monster =MODIFIERS.register("monster", Monster::new);




























    //
    public static void register(IEventBus eventBus) {MODIFIERS.register(eventBus);}
}
