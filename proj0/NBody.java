
public class NBody {
	public static double readRadius(String a) {
		In in = new In(a);
		int firstitem = in.readInt();
		double seconditem = in.readDouble();
		return seconditem;
	}
	public static Body[] readBodies(String a) {
		In in = new In(a);
		int n = in.readInt();
		double seconditem = in.readDouble();
		Body[] res = new Body[n];
		for (int i = 0; i < n; i++) {
			double first = in.readDouble();
			double second = in.readDouble();
			double third = in.readDouble();
			double fourth = in.readDouble();
			double fifth = in.readDouble();
			String six = in.readString();
			res[i] = new Body(first, second, third, fourth, fifth, six);
		}
		return res;
	}
	public static String imageToDraw = "images/starfield.jpg";
	
	public static void main(String[] args) {
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] bodies = readBodies(filename);
		
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();



		
		for (double i = 0; i < T; i = i + dt) {
			double[] xForces = new double[5];
			double[] yForces = new double[5];
			for (int j =0; j < 5; j++) {
				xForces[j] = bodies[j].calcNetForceExertedByX(bodies);
				yForces[j] = bodies[j].calcNetForceExertedByY(bodies);
				bodies[j].update(dt, xForces[j], yForces[j]);
			}
			StdDraw.picture(0, 0, imageToDraw);
			for (Body k : bodies) {
				k.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
		                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}
	}
}
