package com.training.utility;

import java.util.Random;

public class RandomInteger {
	public static int random()
	{
	 Random ran = new Random();
	 int x = ran.nextInt(6151) + 11;
	 return x;
	 
}
}
