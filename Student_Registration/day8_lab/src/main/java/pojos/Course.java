package pojos;

public enum Course {
DAC(90000),DITISS(95000),DBDA(100000);

 public int getI() {
	return i;
}

private int i;

Course(int i) {
	this.i=i;
}

}
