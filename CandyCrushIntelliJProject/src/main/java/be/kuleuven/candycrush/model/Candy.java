package be.kuleuven.candycrush.model;

import be.kuleuven.candycrush.model.Candies.*;

public sealed interface Candy permits BlueBerrie, NormalCandy, RainbowCandy, StrawBerrie, UltimateCandy, EmptyCandy {
}
