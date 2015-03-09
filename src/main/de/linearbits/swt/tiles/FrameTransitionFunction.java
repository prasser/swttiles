/*
 * MIT License
 * 
 * Copyright (c) 2013 Arian Stolwijk
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and 
 * associated documentation files (the "Software"), to deal in the Software without restriction, 
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, 
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial 
 * portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.linearbits.swt.tiles;


/**
 * A class that models a Cubic-Bezier curve.
 * 
 * CSS: Ease-in (0.42,0,1,1) (slow start) 
 * CSS: Ease-out (0,0,0.58,1) (slow end)
 * CSS: Ease-in-out (0.42,0,0.58,1) (slow start & end)
 * 
 * Ported from: https://github.com/arian/cubic-bezier/blob/master/index.js
 * 
 * @author Arian Stolwijk - Initial API and implementation
 * @author Fabian Prasser - Port to Java
 */
class FrameTransitionFunction {

    /** Parameter of the bezier curve*/
    private final double x1;
    /** Parameter of the bezier curve*/
    private final double y1;
    /** Parameter of the bezier curve*/
    private final double x2;
    /** Parameter of the bezier curve*/
    private final double y2;

    /**
     * Creates a new instance
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    FrameTransitionFunction(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    /**
     * Computes the position on the bezier curve
     * @param t
     * @param duration
     * @return
     */
    public double bezier(double t, double duration) {

        double x = t, t0 = 0, t1 = 0, t2 = 0, x2 = 0, d2 = 0, i = 0;

        // Compute epsilon as proposed at https://github.com/arian/cubic-bezier
        final double epsilon = (1000d / 60d / duration) / 4d;

        // First try a few iterations of Newton's method -- normally very fast.
        for (t2 = x, i = 0; i < 8; i++) {
            x2 = curveX(t2) - x;
            if (Math.abs(x2) < epsilon) return curveY(t2);
            d2 = derivativeCurveX(t2);
            if (Math.abs(d2) < 1e-6) break;
            t2 = t2 - x2 / d2;
        }

        t0 = 0;
        t1 = 1;
        t2 = x;

        if (t2 < t0) return curveY(t0);
        if (t2 > t1) return curveY(t1);

        // Fallback to the bisection method for reliability.
        while (t0 < t1) {
            x2 = curveX(t2);
            if (Math.abs(x2 - x) < epsilon) return curveY(t2);
            if (x > x2) t0 = t2;
            else t1 = t2;
            t2 = (t1 - t0) * .5 + t0;
        }

        // Failure
        return curveY(t2);
    }

    /**
     * Helper
     * @param t
     * @return
     */
    private double curveX(double t) {
        double v = 1 - t;
        return 3 * v * v * t * x1 + 3 * v * t * t * x2 + t * t * t;
    }

    /**
     * Helper
     * @param t
     * @return
     */
    private double curveY(double t) {
        double v = 1 - t;
        return 3 * v * v * t * y1 + 3 * v * t * t * y2 + t * t * t;
    }

    /**
     * Helper
     * @param t
     * @return
     */
    private double derivativeCurveX(double t) {
        double v = 1 - t;
        return 3 * (2 * (t - 1) * t + v * v) * x1 + 3 *
               (-t * t * t + 2 * v * t) * x2;
    };
}
