package com.Kakyoin17.cherrytinker.registry;

import com.Kakyoin17.cherrytinker.Modifiers.armor.Egold_P;
import com.Kakyoin17.cherrytinker.Modifiers.armor.RecoveryModifier;
import com.Kakyoin17.cherrytinker.Modifiers.battle.common.CherrygemAttack;
import com.Kakyoin17.cherrytinker.Modifiers.battle.common.Egold_A;
import com.Kakyoin17.cherrytinker.Modifiers.battle.common.Knife;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.Kakyoin17.cherrytinker.cherrytinker.MODID;

public class ModModifiers {
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(MODID);

    public static StaticModifier<Modifier> recovery =MODIFIERS.register("recovery", RecoveryModifier::new);
    public static StaticModifier<Modifier> cherrygemattack =MODIFIERS.register("cherrygemattack", CherrygemAttack::new);

    public static StaticModifier<Modifier> egold_p =MODIFIERS.register("egold_p", Egold_P::new);
    public static StaticModifier<Modifier> egold_a =MODIFIERS.register("egold_a", Egold_A::new);

    public static StaticModifier<Modifier> knife =MODIFIERS.register("knife", Knife::new);
}
