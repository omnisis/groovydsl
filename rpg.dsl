races {
    wood_elf {
        disposition GOOD
        description "Sharp-witted, pointy-eared lovers of nature, art and song" 
        baseAttrs [STR: 25,  AGILITY: 45, DEX: 15, WILLPWR: 25, INT: 60, CHA: 35]
        bonuses [MAGICKA: +10, MUSIC: +5, ARCHERY: +20]
        specialAbilities ['TREE_WHISPERER']
    }
    human {
        disposition NEUTRAL
        description "Diverse race with broad-ranging abilities and strong willpower"
        baseAttrs [STR: 25,  AGILITY: 30, DEX: 25, WILLPWR: 50, INT: 40, CHA: 45]
        bonuses [ARMOR: +10]
        specialAbilities ['DUEL_WIELD']

    }
    troll {
        disposition EVIL
        description "Despicable creatures that care only about their hunger for food and lust for battle."
        baseAttrs [STR: 40, AGILITY: 25, DEX: 10, WILLPWR: 25, INT: 10, CHA: 0]
        bonuses ['BERSERKERdoc ']
        specialAbilities ['EAT_CHILDREN']

    }

}
