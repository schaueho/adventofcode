(ns adventofcode.day5-test
  (:require [adventofcode.day5 :refer :all]
            [clojure.test :refer :all]))

(deftest part1
  (is (= (nice-word? "ugknbfddgicrmopn") true))
  (is (= (nice-word? "aaa") true))
  (is (= (nice-word? "jchzalrnumimnmhp") false))
  (is (= (nice-word? "haegwjzuvuyypxyu") false))
  (is (= (nice-word? "dvszwmarrgswjxmb") false)))

(deftest part2
  (is (= (part2-nice "qjhvhtzxzqqjkmpb") true))
  (is (= (part2-nice "xxyxx") true))
  (is (= (part2-nice "uurcxstgmygtbstg") false))
  (is (= (part2-nice "ieodomkazucvgmuy") false)))
