package game;
/**
 * 
 */


/**
 * @author leanne.miller
 * 
 */
class Door {
	private boolean open;
	private boolean locked;
	private Key key;

	// Initializes a door the default state, open
	public Door() {
		open = true;
		locked = false;
	}

	// Initializes a door to a specified state, a generic key will open it
	public Door(Player p, boolean open, boolean locked) {
		this.open = open;
		this.locked = locked;
		if (locked)
			this.open = false;
		this.key = new Key(p);
	}

	// Initializes a door to a specified state, needs a specific key to open it
	public Door(Player p, boolean open, boolean locked, String k) {
		this.open = open;
		this.locked = locked;
		if (locked)
			this.open = false;
		this.key = new Key(p, k);
	}

	// Checks to see if the door is open
	public boolean isOpen() {
		return open;
	}

	// Checks to see if the door is locked
	public boolean isLocked() {
		return locked;
	}

	// Opens a door
	public void open() {
		if (!locked)
			open = true;
	}

	// Closes a door
	public void close() {
		open = false;
	}

	// Locks the door
	public boolean lock(Key k) {
		locked = true;
		key = k;
		return locked;
	}

	// Unlocks the door if a key is present
	public boolean unlock(Key k) {
		if (key.equals(k)) {
			locked = false;
		} else {
			locked = true;
		}
		return locked;
	}

	public Key getKey() {
		return key;
	}
	
	public void setKey(Player p, String s){
		key = new Key(p, s);
	}

}
