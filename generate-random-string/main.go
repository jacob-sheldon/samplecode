package main

import (
	"fmt"
	"math/rand"
	"strings"
	"time"
	"unsafe"
)

var letterRunes = []rune("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")

func GenerteRandomStringV1(n int) string {
	b := make([]rune, n)
	for i := range b {
		b[i] = letterRunes[rand.Intn(len(letterRunes))]
	}
	return string(b)
}

var letterBytes = []byte("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")

func GenerteRandomStringV2(n int) string {
	b := make([]byte, n)
	for i := range b {
		b[i] = letterBytes[rand.Intn(len(letterBytes))]
	}
	return string(b)
}

func GenerteRandomStringV3(n int) string {
	b := make([]byte, n)
	for i := range b {
		b[i] = letterBytes[rand.Int63()%(int64(len(letterBytes)))]
	}
	return string(b)
}

// mask 掩码

const (
	letterIdxBits = 6 // 因为整个lettersBytes 的长度是52，可以用6位的二进制表示 111111b
	letterIdxMask = 1 << letterIdxBits
	letterIdxMax  = 63 / letterIdxBits
)

func GenerteRandomStringV4(n int) string {
	b := make([]byte, n)
	for i := 0; i < n; {
		// 只取最右边的6位
		// 随机的次数可能会大于n
		if idx := int(rand.Int63() & letterIdxMask); idx < len(letterBytes) {
			b[i] = letterBytes[idx]
			i++
		}
	}
	return string(b)
}

func GenerteRandomStringV5(n int) string {
	b := make([]byte, n)
	for i, cache, remain := n-1, rand.Int63(), letterIdxMax; i >= 0; {
		// 只取最右边的6位
		// 随机的次数可能会大于n
		// 尽量减少 rand.Int63() 的调用
		// 尽量能够把一次随机的结果都利用起来
		if remain == 0 {
			cache, remain = rand.Int63(), letterIdxMax
		}

		if idx := int(cache & letterIdxMask); idx < len(letterBytes) {
			b[i] = letterBytes[idx]
			i--
		}
		cache >>= letterIdxBits
		remain--
	}
	return string(b)
}

var src = rand.NewSource(time.Now().UnixNano())

func GenerteRandomStringV6(n int) string {
	b := make([]byte, n)
	for i, cache, remain := n-1, src.Int63(), letterIdxMax; i >= 0; {
		// 只取最右边的6位
		// 随机的次数可能会大于n
		// 尽量减少 rand.Int63() 的调用
		// 尽量能够把一次随机的结果都利用起来
		if remain == 0 {
			cache, remain = src.Int63(), letterIdxMax
		}

		if idx := int(cache & letterIdxMask); idx < len(letterBytes) {
			b[i] = letterBytes[idx]
			i--
		}
		cache >>= letterIdxBits
		remain--
	}
	return string(b)
}

func GenerteRandomStringV7(n int) string {
	// strings.Builder
	sb := strings.Builder{}
	sb.Grow(n)
	for i, cache, remain := n-1, src.Int63(), letterIdxMax; i >= 0; {
		// 只取最右边的6位
		// 随机的次数可能会大于n
		// 尽量减少 rand.Int63() 的调用
		// 尽量能够把一次随机的结果都利用起来
		if remain == 0 {
			cache, remain = src.Int63(), letterIdxMax
		}

		if idx := int(cache & letterIdxMask); idx < len(letterBytes) {
			sb.WriteByte(letterBytes[idx])
			i--
		}
		cache >>= letterIdxBits
		remain--
	}
	return sb.String()
}

func GenerteRandomStringV8(n int) string {
	// strings.Builder
	b := make([]byte, n)
	for i, cache, remain := n-1, src.Int63(), letterIdxMax; i >= 0; {
		// 只取最右边的6位
		// 随机的次数可能会大于n
		// 尽量减少 rand.Int63() 的调用
		// 尽量能够把一次随机的结果都利用起来
		if remain == 0 {
			cache, remain = src.Int63(), letterIdxMax
		}

		if idx := int(cache & letterIdxMask); idx < len(letterBytes) {
			b[i] = letterBytes[idx]
			i--
		}
		cache >>= letterIdxBits
		remain--
	}
	return (*(*string)(unsafe.Pointer(&b)))
}

func main() {
	fmt.Println("高效生成随机字符串")
}
