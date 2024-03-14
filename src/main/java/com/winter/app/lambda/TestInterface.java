package com.winter.app.lambda;

@FunctionalInterface	// 단 한개의 추상메서드가 있는 것을 보장해주는 어노테이션
public interface TestInterface {
	
	public abstract int t1(int n1, int n2);
}
