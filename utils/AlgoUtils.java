package com.nwd.algo.common.util;

public class AlgoUtils {
	
	public static double featureMult(double[] a,double[] b) {
		double result = 0;
		for(int i=0;i< a.length;i++) {
			result += a[i] * b[i];
		}
		return result;
	}
	public static double featureMult(String[] a,String[] b) {
		double result = 0;
		for(int i=0;i< a.length;i++) {
			result += Double.valueOf(a[i]) * Double.valueOf(b[i]);
		}
		return result;
	}
	
	public static void main(String[] args) {
		double a = (double) -0.06276165693998337;
		System.out.println(a);
	}
}
