package UVa-Online-Judge-Solution;

import java.util.Scanner;
import java.lang.Math;
import java.awt.geom.Point2D;

public class BeijingGuards1335 {

    public static void main(String[] args) {
		
        Scanner input = new Scanner(System.in);

        int num;
        double Px, Py, Pr,
                Ox, Oy, Or;

        while (input.hasNextInt()) {

            num = input.nextInt();
			
            for (int i = 0; i < num; i++) {

                Px = input.nextDouble();
                Py = input.nextDouble();
                Pr = input.nextDouble();
                Ox = input.nextDouble();
                Oy = input.nextDouble();
                Or = input.nextDouble();

                TwoCircleRelativePosition crp = new TwoCircleRelativePosition(Px, Py, Pr, Ox, Oy, Or);

                int relativePosition = crp.getRelativePosition();
                System.out.println("Case " + relativePosition + ":");

                switch (relativePosition) {
                    case 4:
                        System.out.printf("(%.3f,%.3f) ", crp.getInternalHomotheticCenter().getX(),
                                crp.getInternalHomotheticCenter().getY());
                        System.out.printf("%.3f ", crp.getLengthOfInteriorCommonTangent());

                        double[] ICTslope = crp.getSlopeOfInteriorCommonTangent();
                        if (!Double.isNaN(ICTslope[0])) {
                            System.out.printf("%.3f ", ICTslope[0]);
                        } else {
                            System.out.print("Infinity ");
                        }

                        if (!Double.isNaN(ICTslope[1])) {
                            System.out.printf("%.3f", ICTslope[1]);
                        } else {
                            System.out.print("Infinity");
                        }

                        System.out.println();

                        if (!Double.isNaN(crp.getExternalHomotheticCenter().getX())) {
                            System.out.printf("(%.3f,%.3f) ", crp.getExternalHomotheticCenter().getX(),
                                    crp.getExternalHomotheticCenter().getY());
                        } else {
                            System.out.print("NoPoint ");
                        }
                        System.out.printf("%.3f ", crp.getLengthOfExteriorCommonTangent());

                        double[] ECTslope = crp.getSlopeOfExteriorCommonTangent();
                        if (!Double.isNaN(ECTslope[0])) {
                            System.out.printf("%.3f ", ECTslope[0]);
                        } else {
                            System.out.print("Infinity ");
                        }

                        if (!Double.isNaN(ECTslope[1])) {
                            System.out.printf("%.3f", ECTslope[1]);
                        } else {
                            System.out.print("Infinity");
                        }

                        System.out.println();
                        break;

                    case 5:

                        if (!Double.isNaN(crp.getExternalHomotheticCenter().getX())) {
                            System.out.printf("(%.3f,%.3f) ", crp.getExternalHomotheticCenter().getX(),
                                    crp.getExternalHomotheticCenter().getY());
                        } else {
                            System.out.print("NoPoint ");
                        }
                        System.out.printf("%.3f ", crp.getLengthOfExteriorCommonTangent());

                        ECTslope = crp.getSlopeOfExteriorCommonTangent();

                        if (!Double.isNaN(ECTslope[0])) {
                            System.out.printf("%.3f ", ECTslope[0]);
                        } else {
                            System.out.print("Infinity ");
                        }

                        if (!Double.isNaN(ECTslope[1])) {
                            System.out.printf("%.3f", ECTslope[1]);
                        } else {
                            System.out.print("Infinity");
                        }

                        System.out.println();

                        break;

                    case 6:
                        System.out.printf("(%.3f,%.3f) ", crp.getExternalHomotheticCenter().getX(),
                                crp.getExternalHomotheticCenter().getY());

                        ECTslope = crp.getSlopeOfExteriorCommonTangent();
                        System.out.printf("%.3f", ECTslope[0]);

                        System.out.println();
                        break;

                    case 7:
                        System.out.printf("(%.3f,%.3f) ", crp.getInternalHomotheticCenter().getX(),
                                crp.getInternalHomotheticCenter().getY());

                        ICTslope = crp.getSlopeOfInteriorCommonTangent();
                        System.out.printf("%.3f", ICTslope[0]);

                        System.out.println();

                        if (!Double.isNaN(crp.getExternalHomotheticCenter().getX())) {
                            System.out.printf("(%.3f,%.3f) ", crp.getExternalHomotheticCenter().getX(),
                                    crp.getExternalHomotheticCenter().getY());
                        } else {
                            System.out.print("NoPoint ");
                        }
                        System.out.printf("%.3f ", crp.getLengthOfExteriorCommonTangent());

                        ECTslope = crp.getSlopeOfExteriorCommonTangent();

                        if (!Double.isNaN(ECTslope[0])) {
                            System.out.printf("%.3f ", ECTslope[0]);
                        } else {
                            System.out.print("Infinity ");
                        }

                        if (!Double.isNaN(ECTslope[1])) {
                            System.out.printf("%.3f", ECTslope[1]);
                        } else {
                            System.out.print("Infinity");
                        }

                        System.out.println();
                        break;
                }
            }
        }
    }
}

class TwoCircleRelativePosition {

    private double radiusA, radiusB;
    private Point2D.Double pointA, pointB,
            InHCenter, ExHCenter;

    private double d, sumR, diffR;

    double IHCx, IHCy;
    double EHCx, EHCy;

    public TwoCircleRelativePosition(double Px, double Py, double Pr,
            double Ox, double Oy, double Or) {

        if (Pr < Or) {
            double tmp;

            tmp = Pr;
            Pr = Or;
            Or = tmp;

            tmp = Px;
            Px = Ox;
            Ox = tmp;

            tmp = Py;
            Py = Oy;
            Oy = tmp;
        }

        this.radiusA = Pr;
        this.radiusB = Or;

        pointA = new Point2D.Double(Px, Py);
        pointB = new Point2D.Double(Ox, Oy);
    }

    public int getRelativePosition() {

        int relativePosition;

        d = Math.sqrt(Math.pow(pointA.getX() - pointB.getX(), 2) + Math.pow(pointA.getY() - pointB.getY(), 2));
        sumR = radiusA + radiusB;
        diffR = radiusA - radiusB;

        if (d == 0) {

            if ( radiusA == radiusB ) {
                relativePosition = 1;
            } else {
                relativePosition = 2;
            }

        } else if (d < sumR) {

            if (d < diffR) {
                relativePosition = 3;
            } else if (d == diffR) {
                relativePosition = 6;
            } else {
                relativePosition = 5;
            }

        } else if (d > sumR) {
            relativePosition = 4;
        } else {
            relativePosition = 7;
        }

        return relativePosition;
    }

    public Point2D.Double getInternalHomotheticCenter() {

        double x, y;

        x = (radiusA / sumR) * pointB.getX() + (radiusB / sumR) * pointA.getX();
        y = (radiusA / sumR) * pointB.getY() + (radiusB / sumR) * pointA.getY();

        InHCenter = new Point2D.Double(x, y);

        return InHCenter;
    }

    public Point2D.Double getExternalHomotheticCenter() {

        double x, y;

        x = (radiusA / diffR) * pointB.getX() + (-radiusB / diffR) * pointA.getX();
        y = (radiusA / diffR) * pointB.getY() + (-radiusB / diffR) * pointA.getY();

        ExHCenter = new Point2D.Double(x, y);

        return ExHCenter;
    }

    public double getLengthOfInteriorCommonTangent() {
        return Math.sqrt(d * d - sumR * sumR);
    }

    public double getLengthOfExteriorCommonTangent() {
        return Math.sqrt(d * d - diffR * diffR);
    }

    public double[] getSlopeOfInteriorCommonTangent() {

        double[] interiorSlope = new double[2];
        double a, b, c;

        a = (pointA.getX() - InHCenter.getX()) * (pointA.getX() - InHCenter.getX()) - (radiusA * radiusA);
        b = 2 * (pointA.getX() - InHCenter.getX()) * (InHCenter.getY() - pointA.getY());
        c = (InHCenter.getY() - pointA.getY()) * (InHCenter.getY() - pointA.getY()) - (radiusA * radiusA);

        interiorSlope[0] = ((-b) + Math.sqrt((b * b) - 4 * a * c)) / (2 * a);
        interiorSlope[1] = ((-b) - Math.sqrt((b * b) - 4 * a * c)) / (2 * a);

        if (interiorSlope[0] == 0) {
            interiorSlope[0] = Math.abs(interiorSlope[0]);
        }
        if (interiorSlope[1] == 0) {
            interiorSlope[1] = Math.abs(interiorSlope[1]);
        }

        if (interiorSlope[0] > interiorSlope[1]) {
            double tmp;
            tmp = interiorSlope[0];
            interiorSlope[0] = interiorSlope[1];
            interiorSlope[1] = tmp;
        }

        return interiorSlope;
    }

    public double[] getSlopeOfExteriorCommonTangent() {

        double[] exteriorSlope = new double[2];
        double a, b, c;

        if (!Double.isNaN(ExHCenter.getX())) {
            a = (pointA.getX() - ExHCenter.getX()) * (pointA.getX() - ExHCenter.getX()) - (radiusA * radiusA);
            b = 2 * (pointA.getX() - ExHCenter.getX()) * (ExHCenter.getY() - pointA.getY());
            c = (ExHCenter.getY() - pointA.getY()) * (ExHCenter.getY() - pointA.getY()) - (radiusA * radiusA);

            if (a == 0) {
                exteriorSlope[0] = -c / b;
            } else {
                exteriorSlope[0] = ((-b) + Math.sqrt((b * b) - 4 * a * c)) / (2 * a);
            }

            exteriorSlope[1] = ((-b) - Math.sqrt((b * b) - 4 * a * c)) / (2 * a);
        } else {
            exteriorSlope[0] = (pointA.getY() - pointB.getY()) / (pointA.getX() - pointB.getX());
            exteriorSlope[1] = exteriorSlope[0];
        }

        if (exteriorSlope[0] > exteriorSlope[1]) {
            double tmp;
            tmp = exteriorSlope[0];
            exteriorSlope[0] = exteriorSlope[1];
            exteriorSlope[1] = tmp;
        }

        return exteriorSlope;
    }
}
