public class TacoStand
{
    /* CONSTANT VARIABLES */
	public static final String BAR = "----------------------------------------";

	/* STATIC VARIABLES */
	private static int numAsada = 0, numPollo = 0, numLengua = 0, numUltimate = 0;
	private static double totalFunds = 0;

	/**
	 * Sets the store to zero for use in automated testing.
	 */
	public static void initialize()
	{
		numAsada = numPollo = numLengua = numUltimate = 0;
		totalFunds = 0.0D;
	}

	/**
	 * Outputs menu options (kinds of tacos) customer can use to order
	 */
	public static void printMenu()
	{
		System.out.println("Menu options:\n" + TacoStand.BAR);
		System.out.printf("%2d. %-21s [$%5.2f]%n", 1, "Carne Asada (Steak)", 2.5);
		System.out.printf("%2d. %-21s [$%5.2f]%n", 2, "Pollo Asado (Chicken)", 1.75);
		System.out.printf("%2d. %-21s [$%5.2f]%n", 3, "Lengua (Beef Tongue)", 3.0);
		System.out.printf("%2d. %-21s [$%5.2f]%n", 4, "Ultimate Taco", 18.0);
		System.out.println(TacoStand.BAR);
	}
	
	/**
	* Returns a summary (all static variables) of the CURRENT status of the taco stand
	*
	* @return String containing current values related to taco stand, no new line at end
	*/
	public static String getStatus()
	{
		return String.format("%s%nMCC Taco Stand Status%n%s%n" +
			"%-23s$%-7.2f%n%s%n" +
			"%-23s%2d tacos%n" +
			"%-23s%2d tacos%n" +
			"%-23s%2d tacos%n" +
			"%-23s%2d tacos%n%s",
			TacoStand.BAR, TacoStand.BAR, 
      "Funds Available:", TacoStand.totalFunds, TacoStand.BAR,
      "# of Asada Left:", TacoStand.numAsada,
			"# of Pollo Left:", TacoStand.numPollo,
      "# of Lengua Left:", TacoStand.numLengua,
      "# of Ultimate Left:",TacoStand.numUltimate, TacoStand.BAR);
	}

	/**
	 * Increases totalFunds static variable
	 * 
	 * @param funds assumes >0 value added to total funds available for cart
	 */
	public static void addTotalFunds(int funds)
	{
		TacoStand.totalFunds += funds; //should be static variable
	}
	
	/**
	 * Checks if proposed budget to order supplies can be used to buy more supplies. If within total funds,
	 * will update total funds and increment number of each option of tacos based on budget. Otherwise,
	 * no variables are changed. (should be else if statement)
	 * 
	 * @param budget funds to use to order supplies
	 * 
	 * @return boolean representing if supplies could be ordered (within total funds)
	 */
	public static boolean orderSupplies(double budget) //lines 78-90 done in class by Nadia
	{
		if (budget <= 0) {
			System.out.println("Insufficient budget. Must be greater than $0.");
			return false;
		}
		if (budget <= totalFunds) {
				//tacos cost 75 cents each in supplies, keeping it simple
	    		int tacosEach = (int)(Math.round(budget / 0.75 / 4));

	    		TacoStand.totalFunds -= budget;

	    		TacoStand.numAsada += tacosEach;
	    		TacoStand.numPollo += tacosEach;
	    		TacoStand.numLengua += tacosEach;
	    		TacoStand.numUltimate += tacosEach;

				return true;

		} else {
			return false;
		}
	}

	/**
	 * Adds funds to total (static variable) based on kind of taco (different prices) and number of tacos
	 * in order. Will also update appropriate number of tacos left (static variables).
	 * 
	 * @param tacoOption menu option (kind of taco)
	 * @param numTacos number of tacos as part of order, assume > 0
	 */
	public static void updateTotalFunds(int tacoOption, int numTacos)
	{
		double tacoPrice = 0.0;
	
		switch (tacoOption) {
			case 1:
				tacoPrice = 2.50;
				numAsada -= numTacos;
				break;
			case 2:
				tacoPrice = 1.75;
				numPollo -= numTacos;
				break;
			case 3:
				tacoPrice = 3.00;
				numLengua -= numTacos;
				break;
			case 4:
				tacoPrice = 18.00;
				numUltimate -= numTacos;
				break;
			default:
				System.out.println("Invalid taco option.");
				return; //exit early for invalid options
		}
	
		//update funds based on order
		totalFunds += numTacos * tacoPrice;
	}
	
	/**
	 * Determines if taco order can be fullfilled (number of tacos for specific kinda are available)
	 * 
	 * @param tacoOption menu option (kind of taco)
	 * @param numTacos number of tacos as part of order
	 * 
	 * @return boolean representing if specific kind of tacos, for the number in order, are available
	 */
	public static boolean areTacosAvailable(int tacoOption, int numTacos)
	{
		switch (tacoOption) {
			case 1: return numTacos <= numAsada;
			case 2: return numTacos <= numPollo;
			case 3: return numTacos <= numLengua;
			case 4: return numTacos <= numUltimate;
			default: return false;
		}
	}

}


