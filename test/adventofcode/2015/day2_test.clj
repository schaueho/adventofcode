(ns adventofcode.2015.day2-test
  (:require [adventofcode.2015.day2 :refer :all]
            [clojure.test :refer :all]))

(deftest paper-sizes
  (is (= (calculate-paper-size [[2 3 4]]) 58))
  (is (= (calculate-paper-size [[1 1 10]]) 43)))

(deftest ribbon-length
  (is (= (calc-ribbon-length [[2 3 4]]) 34))
  (is (= (calc-ribbon-length [[1 1 10]]) 14)))
    
