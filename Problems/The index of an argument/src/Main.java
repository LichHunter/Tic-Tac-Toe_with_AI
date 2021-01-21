class Problem {
	public static void main(String[] args) {
		boolean flag = true;
		for (int i = 0; i < args.length; i++) {
			if ("test".equals(args[i])) {
				flag = false;
				System.out.println(i);
				break;
			}
		}
		if (flag)
			System.out.println(-1);
	}
}