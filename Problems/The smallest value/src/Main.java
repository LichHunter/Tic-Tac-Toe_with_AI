import java.math.BigInteger;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		BigInteger m = new BigInteger(scanner.next());
		BigInteger n = BigInteger.ONE;
		BigInteger result = BigInteger.ONE;

		while (true) {
			result = result.multiply(n);

			int compare = result.compareTo(m);
			if (compare < 0) {
				n = n.add(BigInteger.ONE);
			} else {
				break;
			}
		}

		System.out.println(n.toString());
	}
}