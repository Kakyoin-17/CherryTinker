package com.kakyoin17.cherrytinker.datagen;

import com.kakyoin17.cherrytinker.Cherrytinker;
import com.kakyoin17.cherrytinker.content.ModBlocks;
import com.kakyoin17.cherrytinker.content.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        String locale = this.getName().replace("Languages: ", "");

        if (locale.equals("zh_cn")) {
            addChinese();
        } else {
            addEnglish();
    }
}
    //英文
    private void addEnglish() {
       //方块
       add(ModBlocks.ENCHANTEDGOLD_BLOCK.get(),"Block of Enchantedgold" ) ;
       add(ModBlocks.CHERRYGEM_BLOCK.get(),"Block of Cherrygem" );
       add(ModBlocks.CHERRY_GEM.get(),"CherryGemStone" );
       add(ModBlocks.DEEPSLATE_CHERRY_GEM.get(),"DeepslateCherryGemStone" );
       add(ModBlocks.CHERRY_BUSH.get(),"CherryPlant" );
       //物品
       add(ModItems.CHERRY.get(),"Cherry");
       add(ModItems.CHERRY_JUICE.get(),"Cherry Juice");
       add(ModItems.ENCHANTEDGOLD.get(),"EnchantedGold");
       add(ModItems.ENCHANTED_GOLDEN_CARROT.get(),"Cherry");
       add(ModItems.CHERRYGEM.get(),"CherryGem");
       add(ModItems.MONSTER_CLUSTER.get(),"Monster Cluster");
       //流体
       add("fluid_type.cherrytinker.cherry_juice","Cherry Juice");
       add("fluid_type.cherrytinker.molten_cherrygem","Molten Cherrygem" );
       add("fluid_type.cherrytinker.molten_enchantedgold","Molten Enchantedgold");
       add("fluid_type.cherrytinker.moon_cherryfuel","Blazing Blue Moon Cherry ");
       add("fluid_type.cherrytinker.sun_cherryfuel","Red Sun Sakura");
       //流体桶
       add("item.cherrytinker.cherry_juice_bucket", "Cherry Juice Bucket");
       add("item.cherrytinker.molten_cherrygem_bucket", "Molten Cherrygem Bucket");
       add("item.cherrytinker.molten_enchantedgold_bucket", "Molten Enchantedgold Bucket");
       add("item.cherrytinker.moon_cherryfuel_bucket","Blazing Blue Moon Cherry Bucket");
       add("item.cherrytinker.sun_cherryfuel_bucket","Red Sun Sakura Bucket");
       //创造模式物品栏
       add("itemGroup." + Cherrytinker.MOD_ID, "Cherry Tinker");
       //匠魂
       add("cherry_monster_creeper", "Fake Creeper");
       add("item.cherrytinker.sk_blade", "Straight blade");
       add("item.cherrytinker.sk", "Straight knives");
       add("item.cherrytinker.sk.description", "The straight knife is a melee weapon that is also effective against spider webs like a sword");
       add("item.cherrytinker.pas", "Pickaxe axe");
       add("item.cherrytinker.pas.description","The pickaxe is a multi-functional digging tool that combines the digging functions of a pickaxe, axe, and shovel, and can also right-click to debark trees,can also right-click to till the land.");
       add( "item.cherrytinker.sk_blade_cast", "Straight blade Cast");
       add("item.cherrytinker.sk_blade_sand_cast", "Straight blade Sand Cast");
       add("item.cherrytinker.sk_blade_red_sand_cast","Straight blade Red Sand Cast ");
       add("pattern.cherrytinker.sk_blade", "Straight blade");
       add("tooltip.yourmod.cherry_gem.day_transform", "Try throwing it into the blazing blood during the day");
       add("tooltip.yourmod.cherry_gem.night_transform","Try throwing it into magma at night and see");
       add("material.cherrytinker.cherrygem","Cherrygem");
       add("material.cherrytinker.enchantedgold","Enchanted Gold");
       add("material.cherrytinker.monster", "Monster");
       add("modifier.cherrytinker.cherrygemattack", "The power of cherry satiating");
       add("modifier.cherrytinker.cherrygemattack.flavor", "Cherry gems? Cherries are full!");
       add("modifier.cherrytinker.cherrygemattack.description","When hunger is greater than half, the damage of satiety is increased by half per level entry");
       add("modifier.cherrytinker.recovery","Cherry heals");
       add("modifier.cherrytinker.recovery.flavor","Learn to heal");
       add("modifier.cherrytinker.recovery.description", "Recover health based on the level of the attribute when attacked by enemies for 10 seconds, up to level 3 health recovery");
       add("modifier.cherrytinker.egold_a", "Enchanted gold attack");
       add("modifier.cherrytinker.egold_a.flavor","Enchanted gold attack power");
       add("modifier.cherrytinker.egold_a.description","Block damage from enemies with less maximum life than you, gain damage absorption for 10 seconds equal to the level of the corresponding attribute when injured, receive instant healing, and deal 10 points of Dragon Breath damage to enemies.");
       add("modifier.cherrytinker.egold_p","Enchanted gold protection");
       add("modifier.cherrytinker.egold_p.flavor", "The protective power of enchanted gold");
        add("modifier.cherrytinker.egold_p.description", "Block damage from enemies whose maximum life is lower than yours. When injured, gain damage absorption equal to the level of the corresponding attribute for 10 seconds and instant healing, and deal 10 points of dragon breath damage to enemies.");
       add("modifier.cherrytinker.monster", "Monster");
       add("modifier.cherrytinker.monster.flavor","Fleeting loyalty");
       add("modifier.cherrytinker.monster.description", "After a melee attack hits the target, a random monster is summoned to attack the target. The types of monsters that can appear increase with level (zombie, skeleton, spider, creeper, enderman).");
    }
    //中文
    private void addChinese() {
        //方块
        add(ModBlocks.ENCHANTEDGOLD_BLOCK.get(),"附魔金块" ) ;
        add(ModBlocks.CHERRYGEM_BLOCK.get(),"樱桃宝石块" );
        add(ModBlocks.CHERRY_GEM.get(),"樱桃宝石矿石" );
        add(ModBlocks.DEEPSLATE_CHERRY_GEM.get(),"深层樱桃宝石矿石" );
        add(ModBlocks.CHERRY_BUSH.get(),"樱桃植株" );
        //物品
        add(ModItems.CHERRY.get(),"樱桃");
        add(ModItems.CHERRY_JUICE.get(),"樱桃汁");
        add(ModItems.ENCHANTEDGOLD.get(),"附魔金");
        add(ModItems.ENCHANTED_GOLDEN_CARROT.get(),"附魔金胡萝卜");
        add(ModItems.CHERRYGEM.get(),"樱桃宝石");
        add(ModItems.MONSTER_CLUSTER.get(),"怪物团簇");
        //流体
        add("fluid_type.cherrytinker.cherry_juice","樱桃汁");
        add("fluid_type.cherrytinker.molten_cherrygem","熔融樱桃宝石");
        add("fluid_type.cherrytinker.molten_enchantedgold","熔融附魔金");
        add("fluid_type.cherrytinker.moon_cherryfuel","炽蓝月樱");
        add("fluid_type.cherrytinker.sun_cherryfuel","焱红日樱");
        //流体桶
        add("item.cherrytinker.cherry_juice_bucket", "樱桃汁桶");
        add("item.cherrytinker.molten_cherrygem_bucket", "熔融樱桃宝石桶");
        add("item.cherrytinker.molten_enchantedgold_bucket", "熔融附魔金桶");
        add("item.cherrytinker.moon_cherryfuel_bucket","炽蓝月樱桶");
        add("item.cherrytinker.sun_cherryfuel_bucket","焱红日樱桶");
        //创造模式物品栏
        add("itemGroup." + Cherrytinker.MOD_ID, "樱桃工匠");
        //匠魂
        add("cherry_monster_creeper", "哈亚苦");
        add("item.cherrytinker.sk_blade", "直刀刃");
        add("item.cherrytinker.sk", "直刀");
        add("item.cherrytinker.sk.description", "直刀是一种近战武器，同时像剑一样对蜘蛛网有效。");
        add("item.cherrytinker.pas", "镐斧");
        add("item.cherrytinker.pas.description","镐斧是一种多功能挖掘工具，同时结合了镐、斧、铲的挖掘功能，还可以右键给树剥皮,也可以右键耕耘土地。");
        add( "item.cherrytinker.sk_blade_cast", "直刀刃铸模");
        add("item.cherrytinker.sk_blade_sand_cast", "直刀刃沙子铸模");
        add("item.cherrytinker.sk_blade_red_sand_cast","直刀刃红沙铸模");
        add("pattern.cherrytinker.sk_blade", "直刀刃");
        add("tooltip.yourmod.cherry_gem.day_transform", "白天时丢入烈焰血中看看");
        add("tooltip.yourmod.cherry_gem.night_transform","晚上时丢入岩浆（Magma）中看看");
        add("material.cherrytinker.cherrygem","樱桃宝石");
        add("material.cherrytinker.enchantedgold","附魔金");
        add("material.cherrytinker.monster", "怪物");
        add("modifier.cherrytinker.cherrygemattack", "樱桃饱食之力");
        add("modifier.cherrytinker.cherrygemattack.flavor", "樱桃宝石？樱桃饱食!");
        add("modifier.cherrytinker.cherrygemattack.description","当饥饿值大于一半时，每级词条增加饱食度一半的伤害");
        add("modifier.cherrytinker.recovery","樱愈");
        add("modifier.cherrytinker.recovery.flavor","学樱愈");
        add("modifier.cherrytinker.recovery.description", "受到敌人攻击获得词条等级的生命恢复效果10秒,最高三级生命恢复");
        add("modifier.cherrytinker.egold_a", "附魔金进攻");
        add("modifier.cherrytinker.egold_a.flavor","附魔金攻击之力");
        add("modifier.cherrytinker.egold_a.description","每级词条增加你最大生命值一半的伤害;同时攻击后对目标造成10魔法伤害");
        add("modifier.cherrytinker.egold_p","附魔金防护");
        add("modifier.cherrytinker.egold_p.flavor", "附魔金的防护之力");
        add("modifier.cherrytinker.egold_p.description", "格挡掉最大生命小于你的敌人的伤害，受伤时获得与等同词条等级的伤害吸收10秒与瞬间治疗,并对敌人造成10点龙息伤害");
        add("modifier.cherrytinker.monster", "怪物");
        add("modifier.cherrytinker.monster.flavor","短暂的忠诚");
        add("modifier.cherrytinker.monster.description", "近战攻击命中目标后，召唤随机怪物攻击目标，随机到的怪物种类根据等级逐渐变多（僵尸，骷髅，蜘蛛，苦力怕，末影人）");





    }
}
