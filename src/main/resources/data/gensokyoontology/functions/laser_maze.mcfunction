summon gensokyoontology:spell_card ~ ~6 ~-26
data merge entity @e[type=gensokyoontology:laser_source,limit=1] {Owner:[I;0,0,0,0]}
data modify entity @e[type=gensokyoontology:laser_source,limit=1] Owner set from entity @r UUID
gsko set-spell @e[type=gensokyoontology:spell_card,limit=1] laser_maze