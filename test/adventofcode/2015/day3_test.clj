(ns adventofcode.2015.day3-test
  (:require [adventofcode.2015.day3 :refer :all]
            [clojure.test :refer :all]))

(deftest part1
  (is (= (move-and-count-houses ">") 2))
  (is (= (move-and-count-houses "^>v<") 4))
  (is (= (move-and-count-houses "^v^v^v^v^v") 2)))

(deftest part2
  (is (= (move-robot-and-count-houses "^v") 3))
  (is (= (move-robot-and-count-houses "^>v<") 3))
  (is (= (move-robot-and-count-houses "^v^v^v^v^v") 11)))
