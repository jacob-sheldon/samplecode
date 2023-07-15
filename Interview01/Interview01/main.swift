//
//  main.swift
//  Interview01
//
//  Created by 施治昂 on 7/13/23.
//

import Foundation

struct Person {
    var age: Int
    var name: String
}

var p1 = Person(age: 10, name: "123")

func changePersonAge(p: inout Person) {
    p.age = 20
}

changePersonAge(p: &p1)
print(p1.age)


class Car {
    var wheelNum: Int
    
    init(wheelNum: Int) {
        self.wheelNum = wheelNum
    }
}

let trunck1 = Car(wheelNum: 8)
trunck1.wheelNum = 10
print(trunck1.wheelNum) // 10

let trunck2 = trunck1
trunck2.wheelNum = 12
print(trunck1.wheelNum) // 12
print(trunck2.wheelNum) // 12
