package list6_1.user.domain;

/**
 * @author wglee21g@gmail.com
 */
public enum Level {
	GOLD(3, null, "골드"), SILVER(2, GOLD, "실버"), BASIC(1, SILVER, "기본");

	private final int value;
	private final Level next;
	private final String name;

	Level(int value, Level next, String name) {
		this.value = value;
		this.next = next;
		this.name = name;
	}

	public int intValue() {
		return value;
	}

	public Level nextLevel() {
		return next;
	}

	public String getName() {
		return name;
	}

	public static Level valueOf(int value) {
		switch (value) {
			case 1:
				return BASIC;
			case 2:
				return SILVER;
			case 3:
				return GOLD;
			default:
				throw new AssertionError("Unknown value: " + value);
		}
	}
}
