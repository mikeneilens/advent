val sampleOneData =
        "         A           \n" +
        "         A           \n" +
        "  #######.#########  \n" +
        "  #######.........#  \n" +
        "  #######.#######.#  \n" +
        "  #######.#######.#  \n" +
        "  #######.#######.#  \n" +
        "  #####  B    ###.#  \n" +
        "BC...##  C    ###.#  \n" +
        "  ##.##       ###.#  \n" +
        "  ##...DE  F  ###.#  \n" +
        "  #####    G  ###.#  \n" +
        "  #########.#####.#  \n" +
        "DE..#######...###.#  \n" +
        "  #.#########.###.#  \n" +
        "FG..#########.....#  \n" +
        "  ###########.#####  \n" +
        "             Z       \n" +
        "             Z       "
val sampleTwoData =
        "                   A               \n" +
        "                   A               \n" +
        "  #################.#############  \n" +
        "  #.#...#...................#.#.#  \n" +
        "  #.#.#.###.###.###.#########.#.#  \n" +
        "  #.#.#.......#...#.....#.#.#...#  \n" +
        "  #.#########.###.#####.#.#.###.#  \n" +
        "  #.............#.#.....#.......#  \n" +
        "  ###.###########.###.#####.#.#.#  \n" +
        "  #.....#        A   C    #.#.#.#  \n" +
        "  #######        S   P    #####.#  \n" +
        "  #.#...#                 #......VT\n" +
        "  #.#.#.#                 #.#####  \n" +
        "  #...#.#               YN....#.#  \n" +
        "  #.###.#                 #####.#  \n" +
        "DI....#.#                 #.....#  \n" +
        "  #####.#                 #.###.#  \n" +
        "ZZ......#               QG....#..AS\n" +
        "  ###.###                 #######  \n" +
        "JO..#.#.#                 #.....#  \n" +
        "  #.#.#.#                 ###.#.#  \n" +
        "  #...#..DI             BU....#..LF\n" +
        "  #####.#                 #.#####  \n" +
        "YN......#               VT..#....QG\n" +
        "  #.###.#                 #.###.#  \n" +
        "  #.#...#                 #.....#  \n" +
        "  ###.###    J L     J    #.#.###  \n" +
        "  #.....#    O F     P    #.#...#  \n" +
        "  #.###.#####.#.#####.#####.###.#  \n" +
        "  #...#.#.#...#.....#.....#.#...#  \n" +
        "  #.#####.###.###.#.#.#########.#  \n" +
        "  #...#.#.....#...#.#.#.#.....#.#  \n" +
        "  #.###.#####.###.###.#.#.#######  \n" +
        "  #.#.........#...#.............#  \n" +
        "  #########.###.###.#############  \n" +
        "           B   J   C               \n" +
        "           U   P   P              "

val fullData = "                                 F       Z   M           Z       O     J       T                                   \n" +
        "                                 E       D   I           X       J     X       Y                                   \n" +
        "  ###############################.#######.###.###########.#######.#####.#######.#################################  \n" +
        "  #.#.#...#.............#.#.#.........#.#.#.#...#...#.#...#.#.....#.....#.#.......#...#.#...#.#...........#...#.#  \n" +
        "  #.#.#.###.#######.###.#.#.###.###.###.#.#.#.###.#.#.#.###.#.#######.###.#######.#.###.###.#.#.#.#########.###.#  \n" +
        "  #...#.#.#.#.#...#.#.#.......#.#.....#.....#.....#.#...#.......#.#.....#.......#.#.........#...#.#.....#.......#  \n" +
        "  #.#.#.#.###.#.#####.#.#####.#####.#.#.###########.#.#.#######.#.#.#.###.#######.#.#.###.#.#.#####.#######.#####  \n" +
        "  #.#.#.#.........#.....#.#...#...#.#.#.#...#...#...#.#.#...#.....#.#.....#.#.#.....#.#...#.#.#.#...............#  \n" +
        "  ###.#.#####.###########.###.#.#.###.#.#.#.###.###.#.###.#.#####.#.###.#.#.#.###.###########.#.#####.#######.###  \n" +
        "  #.............#...#.#.........#...#.#...#...#.....#.....#.#.#...#.#.#.#...#.#...#...#...........#...#...#.....#  \n" +
        "  #.#.#.###.#.#.###.#.###.###.#.###.#.#####.###.#.#.###.###.#.#.#.###.#####.#.###.###.###.#######.#.###.#####.###  \n" +
        "  #.#.#.#...#.#...#.#...#.#...#...#...#.......#.#.#...#.#.#.#...#.....#.#.#...#.................#.......#...#...#  \n" +
        "  ###.###.#####.#.#.#.#########.#.###.###.#####.###.###.#.#.#.#.#.#.###.#.#.#.#.#.#.#########.###.#.#.###.#######  \n" +
        "  #.#.#.#.#...#.#.........#.....#.#.....#.#.......#.#...#...#.#.#.#.#.......#.#.#.#.........#.#.#.#.#...#...#...#  \n" +
        "  #.###.###.#########.#.#.#.###.###.#####.###.#.#######.#.#.#.#######.#.#.#.#######.###########.###.#####.#####.#  \n" +
        "  #.........#...#.....#.#.....#.#.......#.#...#.#.....#.#.#.#.......#.#.#.#...#.#.........#.#.....#.#...#.......#  \n" +
        "  ###.###.#####.#####.###.###.#.#########.#.#.###.#########.###.#.#########.#.#.#.#.#.#.###.#.#######.#.###.#.###  \n" +
        "  #.....#.......#...#.#.#...#.#.#.#.#.#...#.#...#.#.........#...#.#.#.....#.#.#.#.#.#.#.........#...#.#.#...#.#.#  \n" +
        "  #.#.###.###.#.###.###.#######.#.#.#.#.#######.#.#######.#####.###.#.###.#.###.###.###########.###.#.#####.###.#  \n" +
        "  #.#.#.#.#.#.#.#.............#.......#.#.#.........#...#...#.......#.#.#.#...#...............#.........#.#...#.#  \n" +
        "  #####.#.#.#.###########.###.#######.#.#.###.#.#.#.###.#.###.#.#.#.#.#.#.#.#######.#####.#####.#.###.#.#.#.###.#  \n" +
        "  #.#.#...#.#.#.#...#.....#.......#...#.....#.#.#.#.#.#.....#.#.#.#.#...#.....#.#.......#...#.#.#...#.#.#.....#.#  \n" +
        "  #.#.###.#.###.###.#.###.#####.#.#.#.#.###.#.#.#####.#.#.#.#######.#.#.#####.#.###.#.#######.###############.#.#  \n" +
        "  #.....#...#.#.#...#.#...#.....#...#.#...#.#.#.#.......#.#.....#.#.#.#.....#.#.....#.#.#...#.......#.#.........#  \n" +
        "  #####.#.###.#.###.#######.###.###.###.#.###.#############.###.#.#.#######.###.#.#.###.#.###.#######.#######.###  \n" +
        "  #.#.......#.#.#.#.#...#.#.#.....#...#.#.#.....#.....#.......#.#...#.......#...#.#.#.#.#...#.#...#.#...#.#.....#  \n" +
        "  #.#####.###.#.#.#.###.#.###.#.###.###.#####.###.#.###.#.###.###.###.#.#####.#######.#.#.###.#.###.#.###.#####.#  \n" +
        "  #...#.#...#.....#.......#.#.#...#.#.....#.......#.#...#...#.#...#...#...#...........#.#.#...#...#.....#.#...#.#  \n" +
        "  #.#.#.#.###.#####.#######.#####.#####.#######.#####.###########.###.###########.#####.#.###.#.#####.###.#.###.#  \n" +
        "  #.#.#.#...#.#.#...#...#.#.#    L     O       P     V           N   H           W    #...#.#.#...#.....#.#.#.#.#  \n" +
        "  ###.#.#.###.#.###.#.###.#.#    S     M       Z     C           M   N           F    ###.#.#.#.#####.###.#.#.#.#  \n" +
        "  #.....#.#.#...#.....#.....#                                                         #.#.#.#.#.#.#.#...#.....#.#  \n" +
        "  #.#.###.#.###.###.#.###.###                                                         #.#.#.#.#.#.#.###.###.###.#  \n" +
        "  #.#...............#.#......ZD                                                       #.....#.....#...#...#......DA\n" +
        "  ###.#####.#.#####.#####.###                                                         #.###.#.###.#.#.#.#.#.#####  \n" +
        "  #.....#...#.#...#...#.....#                                                       ZX....#...#.....#...#.#.....#  \n" +
        "  #.###.#####.###.#.###.###.#                                                         #.#.#.#.###.#.#.###.#####.#  \n" +
        "FF....#...#.#.#.#.....#...#.#                                                         #.#.#.#.#.#.#.#...#.#.....#  \n" +
        "  #.#######.#.#.###.#.###.###                                                         #######.#.#.#######.#.#####  \n" +
        "  #.#.#...#.#.#.#.#.#.......#                                                         #.#...#.#.....#.#.......#.#  \n" +
        "  ###.#.###.###.#.###########                                                         #.#.#######.#.#.#########.#  \n" +
        "  #.................#.......#                                                         #...#...#...#.#............UK\n" +
        "  #.###.#.#.#.###.#.#.###.###                                                         #.#####.#########.###.###.#  \n" +
        "FK....#.#.#.#...#.#.#...#...#                                                       JX..#...#.#.#.........#...#.#  \n" +
        "  #.#####.#.#.###.#.#.###.#.#                                                         #.###.#.#.#######.#######.#  \n" +
        "  #.....#.#.#.#...#.#.#...#..DA                                                       #.#...#...#.....#.....#.#.#  \n" +
        "  #.###############.#.#####.#                                                         #.#.###.#.###.###.###.#.###  \n" +
        "  #.#.#.#.......#.........#.#                                                         #.......#...........#.#...#  \n" +
        "  ###.#.#####.#.#####.###.#.#                                                         #.#.###.#####.#########.###  \n" +
        "  #.#.........#.#...#...#.#.#                                                         #.#.#.....#.#.#...#........HN\n" +
        "  #.#.#.#########.###.#######                                                         #########.#.#####.#######.#  \n" +
        "  #...#.#.#.#.#.....#.#...#.#                                                         #...#...........#...#.#.#..ZZ\n" +
        "  #.###.#.#.#.###.#.#.#.###.#                                                         ###.#############.###.#.#.#  \n" +
        "  #.#...#...#.#.#.#.#.#......TY                                                     MI......#.#.#.#.#...#.......#  \n" +
        "  #.#.###.#.#.#.#.#.#####.###                                                         #.#.###.#.#.#.#.#.#.#.#####  \n" +
        "NM..#.....#.......#.......#.#                                                         #.#.............#...#.....#  \n" +
        "  #########################.#                                                         ###.#######################  \n" +
        "  #.........................#                                                         #...#.....................#  \n" +
        "  #.#.###.###.#.#.#####.###.#                                                         #.#.###.#.#.#.###########.#  \n" +
        "  #.#.#.....#.#.#.#...#...#.#                                                         #.#.#...#.#.#.#.......#...#  \n" +
        "  #.###############.#######.#                                                         #######.###########.#.#.###  \n" +
        "WF......#.....#.....#.#.....#                                                       MH..#.#.......#.#.#.#.#...#.#  \n" +
        "  #.###.#####.#.#.#.#.#####.#                                                         #.#.#.#######.#.#.#.#####.#  \n" +
        "  #.#.#.#.....#.#.#.....#....FF                                                       #.....#.#.#.....#..........OM\n" +
        "  ###.#######.#####.#########                                                         ###.###.#.###.#############  \n" +
        "PZ......................#...#                                                       FE..#.#......................JO\n" +
        "  #.#.#.#####.#######.###.###                                                         #.###.###.###.###.###.#.#.#  \n" +
        "  #.#.#.#.#.#.#.......#.....#                                                       FK....#.#...#...#.#.#.#.#.#..UM\n" +
        "  #.#.#.#.#.#.#.#.#.###.###.#                                                         ###.#.#.#####.#.###.#####.#  \n" +
        "  #.#.#.#.#...#.#.#.#...#....OJ                                                       #.#...#.#...#...#...#...#.#  \n" +
        "  #######.#########.###.###.#                                                         #.###.#####.#.#.###.#.#####  \n" +
        "  #.#.#...#.....#.......#...#                                                         #.#.#.#...#...#.#.........#  \n" +
        "  #.#.#.#####.#####.#########                                                         #.#.#####.#.#.###.#.#.#.###  \n" +
        "  #.#.....#.......#.....#.#..UM                                                     UK..........#.#.#...#.#.#....PS\n" +
        "  #.#.###.#.#.###.#######.#.#                                                         #.#######.#########.###.#.#  \n" +
        "  #.....#...#.#...#.....#...#                                                         #.#.........#.#.....#...#.#  \n" +
        "  #.#####.###.###.#####.#.###                                                         #####.#######.#####.###.#.#  \n" +
        "  #.#.#.#.#.#.#.....#...#...#                                                         #.#...................#.#.#  \n" +
        "  #.#.#.###.###.###.#.#.#.###                                                         #.#.#.###.#.###.#########.#  \n" +
        "LB........#.....#.#...#.....#                                                         #.#.#.#...#...#.......#...#  \n" +
        "  #.###.#####.###.#.#.#.###.#      M         J   S             E   P     T       L    #.###.#####.###.#.#.#.###.#  \n" +
        "  #...#...#...#.#...#.#.#.#.#      L         O   Y             V   S     F       B    #.#...#.#.#...#.#.#.#.#...#  \n" +
        "  #.###.#####.#.#.#.#.###.#########.#########.###.#############.###.#####.#######.#####.#.#.#.#.#.#.#.#######.#.#  \n" +
        "  #.#.#...#...#...#.#.#.#...................#...#.......#.......#...#.......#...........#.#.#.....#.#.......#.#.#  \n" +
        "  ###.#.#.#.#.#.#####.#.#.###.#####.#.###.###.#.#####.###.#########.###.#.###.###.###.#.###.#####.#####.#####.#.#  \n" +
        "  #.....#.#.#.#...#...#...#.#...#...#.#...#.#.#.#...#...#.#.....#.#...#.#.#.....#.#...#...#.#.#...#.#...#.#.#.#.#  \n" +
        "  #.#.#.###.###.#######.###.#.#####.#####.#.###.#.#.#.#.#.#.#.###.#.#.#.#####.#####.###.#.#.#.#.###.###.#.#.#.###  \n" +
        "  #.#.#.#.....#.#.......#.......#.....#...#.......#.#.#.#...#.#.#.#.#.#.....#.#...#.#.#.#.#.#.....#.#.......#.#.#  \n" +
        "  #.#.#####.#.#####.#######.#.#####.#.###.#.#####.#.#.#.#####.#.#.#.###.#####.#.#####.#.#######.###.###.#.#####.#  \n" +
        "  #.#.#.....#.#.........#...#.#...#.#.#...#.....#.#.#.#.#.#...#.#...#...#.#.........#.#.#...........#.#.#.......#  \n" +
        "  #.#######.#####.#########.###.###.###.#######.#######.#.#.###.###.###.#.#####.#.#.#.#####.#.#.#.###.###.###.#.#  \n" +
        "  #.#.#.#.....#.#...#.#.....#.......#.....#...........#...#.....#...#.#...#...#.#.#...#.....#.#.#.....#.....#.#.#  \n" +
        "  ###.#.#.#####.#####.#####.#############.###.###.#.#.#.#.#.###.###.#.#.#####.#.#########.#.###.###.###.#.###.###  \n" +
        "  #.#.#...#.#.................#.....#.#...#.....#.#.#.#.#.#.#.....#...#.....#.....#...#.#.#.#.....#...#.#...#...#  \n" +
        "  #.#.###.#.#.#.###.#.#.#####.###.#.#.#.#####.#.#.#####.#####.###.#.###.###.#.#.#.#.###.#######.#.#####.#.###.###  \n" +
        "  #.......#...#.#...#.#...#.#.#...#.....#...#.#.#.#.......#.....#.#.#...#...#.#.#.#.........#...#.#.....#...#...#  \n" +
        "  #.###.#####.#####.#.#.#.#.###.#######.#.#####.#####.#.#######.###.#####.###.#####.#.#########.#####.###.###.###  \n" +
        "  #.#.#.#.#.....#...#.#.#.#.#.#.#...#.........#.#...#.#.#.......#.......#.#.#.#.#...#.#...#.#.#...#.#.#...#.....#  \n" +
        "  ###.###.###.###.###.#.#.#.#.#####.#####.#####.#.###.#.#######.###.#####.#.#.#.#.#.#####.#.#.###.#.#######.###.#  \n" +
        "  #.............#.#...#.#.#.#.....#.......#.......#...#.#.#.#.#...#.#.......#...#.#.#.#.#.......#.....#.......#.#  \n" +
        "  ###.#####.#.#####.###.###.#####.#.#########.#######.###.#.#.#.###.###.###.#.###.###.#.#.#####.#.###.#####.#####  \n" +
        "  #...#.#.#.#...#.#...#.#.#.#...#.#.......#.#.#.............#.#.#...#.#.#...#.......#.#.....#.#.#...#.#.........#  \n" +
        "  ###.#.#.###.#.#.#######.#.#.###.#######.#.#.###.###########.#.#.#.#.###.###.###.###.#####.#.###########.###.###  \n" +
        "  #...#.......#...#.#...#.#...#...#.#.....#...#.#.......#...#...#.#.....#.#.#.#.#...#.#...#.......#.#...#.#.#...#  \n" +
        "  #########.#.#####.###.#.###.###.#.#.###.#.###.#######.#.#.#.#####.#####.#.#.#.#####.###.#.###.###.#.#####.#.###  \n" +
        "  #.#.#.#.#.#.#...#...................#...#.......#.#...#.#.#...#.....#.#...#.#.#...#.....#...#.......#.#...#.#.#  \n" +
        "  #.#.#.#.#.#####.###.###.###.#####.#####.#####.#.#.#.###.#.###.###.###.###.#.#.#.###.#####.#######.#.#.#.#.###.#  \n" +
        "  #.........#.........#.....#.#.........#.#.....#.#.....#.#.....#...#.......#.....................#.#.....#.....#  \n" +
        "  #################################.###.#####.#########.#.#######.###.#########.#################################  \n" +
        "                                   S   A     T         V L       E   M         M                                   \n" +
        "                                   Y   A     F         C S       V   L         H                                   "