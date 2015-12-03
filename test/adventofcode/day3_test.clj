(ns adventofcode.day3-test
  (:require [adventofcode.day3 :refer :all]
            [clojure.test :refer :all]))

(deftest part1
  (is (= (move-and-count-houses ">") 2))
  (is (= (move-and-count-houses "^>v<") 4))
  (is (= (move-and-count-houses "^v^v^v^v^v") 2)))

