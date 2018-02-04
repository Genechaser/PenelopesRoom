/**
 * 
 */
package starDriver;

/**
 * @author Colin.Barker Class for control of a 16-channel PCA9685 board over I2C
 * 
 *         Address - Control Register - Command
 */
public class pca9685 {

	/**
	 * 
	 */
	private byte deviceAddress;

	// Control variables
	private byte ALL_CALL_WRITE = (byte) 0b11100001;
	private byte SOFTWARE_RESET = (byte) 0b00000110;

	// Look up table for brightness - allows smooth dim
	// table generated using code from: http://jared.geek.nz/2013/feb/linear-led-pwm
	public int[] cie = { 0, 2, 4, 5, 7, 9, 11, 12, 14, 16, 18, 20, 21, 23, 25, 27, 28, 30, 32, 34, 36, 37, 39, 41, 43,
			45, 47, 49, 52, 54, 56, 59, 61, 64, 66, 69, 72, 75, 77, 80, 83, 87, 90, 93, 96, 100, 103, 107, 111, 115,
			118, 122, 126, 131, 135, 139, 144, 148, 153, 157, 162, 167, 172, 177, 182, 187, 193, 198, 204, 209, 215,
			221, 227, 233, 239, 246, 252, 259, 265, 272, 279, 286, 293, 300, 308, 315, 323, 330, 338, 346, 354, 362,
			371, 379, 388, 396, 405, 414, 423, 432, 442, 451, 461, 470, 480, 490, 501, 511, 521, 532, 543, 553, 564,
			576, 587, 598, 610, 622, 634, 646, 658, 670, 683, 695, 708, 721, 734, 748, 761, 775, 788, 802, 816, 831,
			845, 860, 874, 889, 904, 920, 935, 951, 966, 982, 999, 1015, 1031, 1048, 1065, 1082, 1099, 1116, 1134, 1152,
			1170, 1188, 1206, 1224, 1243, 1262, 1281, 1300, 1320, 1339, 1359, 1379, 1399, 1420, 1440, 1461, 1482, 1503,
			1525, 1546, 1568, 1590, 1612, 1635, 1657, 1680, 1703, 1726, 1750, 1774, 1797, 1822, 1846, 1870, 1895, 1920,
			1945, 1971, 1996, 2022, 2048, 2074, 2101, 2128, 2155, 2182, 2209, 2237, 2265, 2293, 2321, 2350, 2378, 2407,
			2437, 2466, 2496, 2526, 2556, 2587, 2617, 2648, 2679, 2711, 2743, 2774, 2807, 2839, 2872, 2905, 2938, 2971,
			3005, 3039, 3073, 3107, 3142, 3177, 3212, 3248, 3283, 3319, 3356, 3392, 3429, 3466, 3503, 3541, 3578, 3617,
			3655, 3694, 3732, 3772, 3811, 3851, 3891, 3931, 3972, 4012, 4054, 4095 };

	public pca9685(byte device) {
		// TODO Auto-generated constructor stub
		this.deviceAddress = device;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void fadeUp(int whichLED, int Rate) throws InterruptedException {
		// loops through the cie array and writes to the PCA9685. Rate sets delay
		// between steps (255 steps; rate is delay in ms).
		int[] ledRegisters = getRegister(whichLED);
		int fadeRate = Rate;
		for (int i = 0; i > cie.length; i++) {
			// Write to the PWM

			// delay the next write
			Thread.sleep(Rate);
		}
		//Final latch on full

	}

	private int[] getRegister(int LED) {
		int[] result = { 0, 0, 0, 0 };

		if (LED < 16 | LED >= 0) {
			int intitalReg = (LED*4)+6;
			
			for (int i = 0; i < result.length; i++) {
				result[i] = intitalReg+i;
			}

		} else {
			System.out.println("Invalid LED Request");
			for (int i = 0; i < result.length; i++) {
				result[i] = -1;
			}

		}
		return result;
	}
}
