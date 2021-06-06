

public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Body(double xP, double yP, double xV, double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}
	public Body(Body b) {
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}
	public double calcDistance(Body c) {
		return Math.sqrt((this.xxPos-c.xxPos)*(this.xxPos-c.xxPos)+(this.yyPos-c.yyPos)*(this.yyPos-c.yyPos));
	}
	public double calcForceExertedBy(Body c) {
		if (this.equals(c)) {return 0;}
		return 6.67 * Math.pow(10, -11) * this.mass * c.mass / Math.pow(this.calcDistance(c), 2);
	}
	public double calcForceExertedByX(Body c) {
		if (this.equals(c)) {return 0;}
		return this.calcForceExertedBy(c) * (c.xxPos-this.xxPos) / this.calcDistance(c);
	}
	public double calcForceExertedByY(Body c) {
		if (this.equals(c)) {return 0;}
		return this.calcForceExertedBy(c) * (c.yyPos-this.yyPos) / this.calcDistance(c);
	}
	public double calcNetForceExertedByX(Body[] k) {
		double ans = 0;
		for (Body i : k) {
			ans = ans + this.calcForceExertedByX(i);
		}
		return ans;
	}
	public double calcNetForceExertedByY(Body[] k) {
		double ans = 0;
		for (Body i : k) {
			ans = ans + this.calcForceExertedByY(i);
		}
		return ans;
	}
	public void update(double dt, double fx, double fy) {
		double ax = fx / this.mass;
		double ay = fy / this.mass;
		this.xxVel = this.xxVel + dt * ax;
		this.yyVel = this.yyVel + dt * ay;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}
	public void draw() {
		String k =  "images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, k);
	}
}
