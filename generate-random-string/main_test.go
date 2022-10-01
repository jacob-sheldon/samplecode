package main

import (
	"fmt"
	"testing"
)

func TestGenerateRandomString(t *testing.T) {
	res := GenerteRandomStringV1(6)
	if len(res) != 6 {
		t.Errorf("获取的随机字符串长度不是6位，而是 %d 位", len(res))
	}
}

func BenchmarkGenerateRandomStringV1(b *testing.B) {
	// b.N 会根据每一次的测试结果，决定是否还要进行下一次测试
	for i := 0; i < b.N; i++ {
		GenerteRandomStringV1(6)
	}
}

func BenchmarkGenerateRandomStringV2(b *testing.B) {
	// b.N 会根据每一次的测试结果，决定是否还要进行下一次测试
	for i := 0; i < b.N; i++ {
		GenerteRandomStringV2(6)
	}
}

func BenchmarkGenerateRandomStringV3(b *testing.B) {
	// b.N 会根据每一次的测试结果，决定是否还要进行下一次测试
	for i := 0; i < b.N; i++ {
		GenerteRandomStringV3(6)
	}
}

func BenchmarkGenerateRandomStringV4(b *testing.B) {
	// b.N 会根据每一次的测试结果，决定是否还要进行下一次测试
	for i := 0; i < b.N; i++ {
		GenerteRandomStringV4(6)
	}
}

func BenchmarkGenerateRandomStringV5(b *testing.B) {
	// b.N 会根据每一次的测试结果，决定是否还要进行下一次测试
	for i := 0; i < b.N; i++ {
		GenerteRandomStringV5(6)
	}
}

func BenchmarkGenerateRandomStringV6(b *testing.B) {
	// b.N 会根据每一次的测试结果，决定是否还要进行下一次测试
	for i := 0; i < b.N; i++ {
		GenerteRandomStringV6(6)
	}
}

func BenchmarkGenerateRandomStringV7(b *testing.B) {
	// b.N 会根据每一次的测试结果，决定是否还要进行下一次测试
	for i := 0; i < b.N; i++ {
		GenerteRandomStringV7(6)
	}
}

func BenchmarkGenerateRandomStringV8(b *testing.B) {
	// b.N 会根据每一次的测试结果，决定是否还要进行下一次测试
	for i := 0; i < b.N; i++ {
		GenerteRandomStringV8(6)
	}
}

func TestV8(t *testing.T) {
	fmt.Println(GenerteRandomStringV4(6))
}
