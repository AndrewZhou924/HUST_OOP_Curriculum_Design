package plants;

public class Plant {
	int x, y; // 植物位置
	int hp; // 即HP，生命值
	int cooldown_time; // 即CD，冷却时间
	int cost; // 价格

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCooldown_time() {
		return cooldown_time;
	}

	public void setCooldown_time(int cooldown_time) {
		this.cooldown_time = cooldown_time;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}
